package controlador;

import entidad.Mantenimiento;
import controlador.util.JsfUtil;
import entidad.DetalleEquiposMantenimiento;
import entidad.Equipo;
import facade.DetalleEquiposMantenimientoFacade;
import facade.MantenimientoFacade;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@Named("mantenimientoController")
@SessionScoped

public class MantenimientoController implements Serializable {

    @EJB
    private DetalleEquiposMantenimientoFacade detalleEquiposMantenimientoFacade;

    @EJB
    protected facade.MantenimientoFacade facade;

    private Equipo equipoBuscado = null;

    protected List<Mantenimiento> items = null;
    protected Mantenimiento selected;
    protected boolean needLoadTable = true;

    //Constructor
    public MantenimientoController() {
        selectedEquipos = new ArrayList<>();
    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de Mantenimientos de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public Mantenimiento prepareCreate() {
        selected = new Mantenimiento();
        equipoBuscado = null;
        idTemporal = 0;

        selectedEquipos = new ArrayList<>();
        listDetalleEquiposMantSelected = new ArrayList<>();
        listDetalleEquiposMantEliminar = new ArrayList<>();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad Mantenimiento en la DB
    public void crear() {
        DetalleEquiposMantenimiento dm;
        try {
//            if (selectedEquipos != null || selectedEquipos.size() > 0) {
            if (listDetalleEquiposMantSelected != null && listDetalleEquiposMantSelected.size() > 0) {

//                facade.create(selected);
                selected = facade.guardarYobtenerIdMantenimiento(selected);
                for (DetalleEquiposMantenimiento dmTemp : listDetalleEquiposMantSelected) {
                    dmTemp.setIdMantenimiento(selected);
                    detalleEquiposMantenimientoFacade.create(dmTemp);
//                    System.out.println("Se va a guardar: " + dmTemp.toString());
                }

                needLoadTable = true;
                selected = null;
                selectedEquipos = null;
                listDetalleEquiposMantSelected = null;
                listDetalleEquiposMantEliminar = null;

                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MantenimientoCreated"));
            } else {
                JsfUtil.addErrorMessage("Se necesita seleccionar al menos un equipo para el registro");
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  MantenimientoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Prepara la entidad previo a la edición
    public Mantenimiento prepareUpdate(Mantenimiento m) {
        selected = m;
        equipoBuscado = null;
        idTemporal = 0;

        selectedEquipos = new ArrayList<>();
        listDetalleEquiposMantSelected = listarTodosDetallesPorMantenimiento(selected);
        listDetalleEquiposMantEliminar = new ArrayList<>();

        for (DetalleEquiposMantenimiento dm : listDetalleEquiposMantSelected) {
            selectedEquipos.add(dm.getIdEquipo());
        }

        inicializarVariables();
        return selected;
    }

    //Actualiza la entidad Mantenimiento en la DB
    public void actualizar() {
        try {
            DetalleEquiposMantenimiento dm;
            if (listDetalleEquiposMantSelected != null && listDetalleEquiposMantSelected.size() > 0) {

                facade.edit(selected);

                for (DetalleEquiposMantenimiento dmTemp : listDetalleEquiposMantSelected) {
                    dmTemp.setIdMantenimiento(selected);

                    if (dmTemp.getIdDetalleequipomantenimiento() <= 0) {
//                        System.out.println("Se va a Crear el siguiente dm: " + dmTemp.toString());
                        detalleEquiposMantenimientoFacade.create(dmTemp);
                    } else {
//                        System.out.println("Se va a Actualizar el siguiente dm: " + dmTemp.toString());
                        detalleEquiposMantenimientoFacade.edit(dmTemp);
                    }
                }

                for (DetalleEquiposMantenimiento dmTemp : listDetalleEquiposMantEliminar) {
                    if (dmTemp.getIdDetalleequipomantenimiento() <= 0) {
                    } else {
//                        System.out.println("Se va a Eliminar el siguiente dm: " + dmTemp.toString());
                        detalleEquiposMantenimientoFacade.remove(dmTemp);
                    }
                }

                needLoadTable = true;
                selected = null;
                selectedEquipos = null;
                listDetalleEquiposMantSelected = null;
                listDetalleEquiposMantEliminar = null;
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MantenimientoUpdated"));
            } else {
                JsfUtil.addErrorMessage("Se necesita seleccionar al menos un equipo para el registro");
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  MantenimientoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad Mantenimiento de la DB
    public void eliminar(Mantenimiento selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MantenimientoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  MantenimientoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    public void validarFechas() {
        try {
            if (selected != null && selected.getFechainiciacoberturaMantenimiento() != null && selected.getFechafinalizacoberturaMantenimiento() != null) {
                if (selected.getFechainiciacoberturaMantenimiento().compareTo(selected.getFechafinalizacoberturaMantenimiento()) > 0) {
                    JsfUtil.addErrorMessage("Las fechas contradicen su inicio/finalización");
                }
                else{
                    JsfUtil.addSuccessMessage("Fechas inicio/finalización correctas");
                }
            }
//            JsfUtil.addSuccessMessage("Comparación de fechas: " + selected.getFechainiciacoberturaMantenimiento().compareTo(selected.getFechafinalizacoberturaMantenimiento()));
        } catch (Exception e) {
            JsfUtil.addFatalMessage("Ocurrió un error al verificar fechas " + e.getLocalizedMessage());
        }
    }

    //Permite obtener la entidad Mantenimiento mediante su id desde la DB hacia este Controlador
    public Mantenimiento getMantenimiento(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los Mantenimiento de la DB
    public List<Mantenimiento> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<Mantenimiento> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<Mantenimiento> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = Mantenimiento.class)
    public static class MantenimientoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MantenimientoController controller = (MantenimientoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "mantenimientoController");
            return controller.getMantenimiento(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Mantenimiento) {
                Mantenimiento o = (Mantenimiento) object;
                return getStringKey(o.getIdMantenimiento());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Mantenimiento.class.getName()});
                return null;
            }
        }

    }

    /////////Equipos y Detalle Equipos para Mantenimiento /////////
    private List<Equipo> selectedEquipos = null;
    private List<DetalleEquiposMantenimiento> listDetalleEquiposMantInitDb = null;
    private List<DetalleEquiposMantenimiento> listDetalleEquiposMantSelected = null;
    private List<DetalleEquiposMantenimiento> listDetalleEquiposMantEliminar = null;

    public List<DetalleEquiposMantenimiento> listarTodosDetallesPorMantenimiento(Mantenimiento mant) {
        return detalleEquiposMantenimientoFacade.listarTodosDetallesPorMantenimiento(mant);
    }

    private int idTemporal;

    public void onItemSelectAutocompleteEquipo(SelectEvent<Equipo> event) {
        try {
            DetalleEquiposMantenimiento dm;

            equipoBuscado = (Equipo) event.getObject();

            if (selectedEquipos.contains(this.equipoBuscado)) {
                JsfUtil.addWarningMessage("El objeto ya fue seleccionado, no se agrega al listado");
            } else {
                idTemporal--;
                dm = new DetalleEquiposMantenimiento();
                dm.setIdDetalleequipomantenimiento(idTemporal);
                dm.setCoberturaDetalleequipomantenimiento("24x7");
                dm.setIdMantenimiento(selected);
                dm.setIdEquipo(equipoBuscado);

                listDetalleEquiposMantSelected.add(dm);
                selectedEquipos.add(equipoBuscado);
                JsfUtil.addSuccessMessage("Se agregó el equipo" + equipoBuscado.getNombreEquipo() + " al listado correctamente");
            }
            equipoBuscado = null;
            dm = null;
        } catch (Exception e) {
            System.out.println("Ocurrió un error al seleccionar equipo en onItemSelectAutocompleteEquipo " + e.getLocalizedMessage());
        }

    }

    public void onRowSelectTableEquipos(SelectEvent<Equipo> event) {
        this.onItemSelectAutocompleteEquipo(event);
//        try {
//            equipoBuscado = (Equipo) event.getObject();
//
//            if (selectedEquipos.contains(this.equipoBuscado)) {
//                JsfUtil.addWarningMessage("El objeto ya fue seleccionado, no se agrega al listado");
//            } else {
//                selectedEquipos.add(equipoBuscado);
//                JsfUtil.addSuccessMessage("Se agregó el equipo" + equipoBuscado.getNombreEquipo() + " al listado correctamente");
//            }
//            equipoBuscado = null;
//
//        } catch (Exception e) {
//            System.out.println("Ocurrió un error al seleccionar equipo en onRowSelect " + e.getLocalizedMessage());
//        }
    }

    public void onRowUnselectTableEquipos(UnselectEvent<Equipo> event) {
        selectedEquipos.remove((Equipo) (event.getObject()));

        FacesMessage msg = new FacesMessage("Equipo Unselected", String.valueOf(event.getObject().getIdEquipo()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void quitarEquipoDeList(Equipo eq) {
        try {
            if (selectedEquipos.contains(eq)) {
                selectedEquipos.remove(eq);
            } else {

            }

            JsfUtil.addSuccessMessage("Se retiró el equipo" + eq.getNombreEquipo() + " del listado correctamente");

        } catch (Exception e) {
            System.out.println("Ocurrió un error al quitarEquipoDeList " + e.getLocalizedMessage());
        }
    }

    public void quitarDetalleMantDeList(DetalleEquiposMantenimiento dm) {
        try {
            if (listDetalleEquiposMantSelected.contains(dm)) {
                selectedEquipos.remove(dm.getIdEquipo());
                listDetalleEquiposMantSelected.remove(dm);
                listDetalleEquiposMantEliminar.add(dm);
                JsfUtil.addSuccessMessage("Se retiró el equipo" + dm.getIdEquipo().getNombreEquipo() + " del listado correctamente");
            } else {
                JsfUtil.addWarningMessage("El equipo seleccionado no se encuentra en el listado ");
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error al quitarEquipoDeList " + e.getLocalizedMessage());
        }
    }

    private boolean skip;

    public String onFlowProcessWizard(FlowEvent event) {
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<Equipo> getSelectedEquipos() {
        return selectedEquipos;
    }

    public void setSelectedEquipos(List<Equipo> selectedEquipos) {
        this.selectedEquipos = selectedEquipos;
    }

    public List<DetalleEquiposMantenimiento> getListDetalleEquiposMantSelected() {
        return listDetalleEquiposMantSelected;
    }

    public void setListDetalleEquiposMantSelected(List<DetalleEquiposMantenimiento> listDetalleEquiposMantSelected) {
        this.listDetalleEquiposMantSelected = listDetalleEquiposMantSelected;
    }

    /////////Documento adjunto//////////////
    private UploadedFile filePDF;
    private StreamedContent fileViewPDF = null;

    public void handleAdjuntoUpload(FileUploadEvent event) {
        filePDF = event.getFile();
        if (filePDF != null && filePDF.getContent() != null && filePDF.getContent().length > 0 && filePDF.getFileName() != null) {
//            this.originalImageFile = filePDF;
            selected.setDocumentoMantenimiento(filePDF.getContent());
            JsfUtil.addSuccessMessage(filePDF.getFileName() + " se cargó correctamente");
        }
    }

    public StreamedContent verArchivoAdjunto() {
        return DefaultStreamedContent.builder()
                .contentType(filePDF == null ? null : filePDF.getContentType())
                .stream(() -> {
                    if (filePDF == null
                            || filePDF.getContent() == null
                            || filePDF.getContent().length == 0) {
                        return null;
                    }

                    try {
                        return new ByteArrayInputStream(filePDF.getContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .build();
    }

    public StreamedContent generarPdfDesdeObjetoBytes() {
        fileViewPDF = null;
        filePDF = null;
        try {
            if (selected != null && selected.getDocumentoMantenimiento() != null) {
                fileViewPDF = DefaultStreamedContent.builder().
                        stream(()
                                -> new ByteArrayInputStream(selected.getDocumentoMantenimiento())
                        )
                        .contentType("application/pdf")
                        .name("prueba.pdf")
                        .build();
            } else {
                System.out.println("No seleccionó un elemento para ver");
                JsfUtil.addErrorMessage("No seleccionó un elemento para ver");
            }
        } catch (final Exception e) {
            System.out.println("Ocurrió un error al setearFilePDF del movimientoEquipoController" + e.getLocalizedMessage());
        }
        return fileViewPDF;
    }

    public void quitarDocumento() {
        try {
            filePDF = null;
            fileViewPDF = null;
            selected.setDocumentoMantenimiento(null);
            JsfUtil.addSuccessMessage("Se eliminó el archivo adjunto, puede subir otro");
        } catch (Exception e) {
            System.out.println("Error al intentar quitar el archivo adjunto " + e.getLocalizedMessage());
            JsfUtil.addFatalMessage("Error al intentar quitar el archivo adjunto " + e.getLocalizedMessage());
        }
    }

    public UploadedFile getFilePDF() {
        return filePDF;
    }

    public StreamedContent getFileViewPDF() {
        return fileViewPDF;
    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public Mantenimiento getSelected() {
        return selected;
    }

    public void setSelected(Mantenimiento selected) {
        this.selected = selected;
    }

    protected MantenimientoFacade getFacade() {
        return facade;
    }

    public Equipo getEquipoBuscado() {
        return equipoBuscado;
    }

    public void setEquipoBuscado(Equipo equipoBuscado) {
        this.equipoBuscado = equipoBuscado;
    }

}
