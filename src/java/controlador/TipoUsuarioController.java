package controlador;

import entidad.TipoUsuario;
import controlador.util.JsfUtil;
import entidad.OpcionSistema;
import entidad.PermisoAcceso;
import entidad.Usuario;
import facade.OpcionSistemaFacade;
import facade.PermisoAccesoFacade;
import facade.TipoUsuarioFacade;
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
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

@Named("tipoUsuarioController")
@SessionScoped

public class TipoUsuarioController implements Serializable {

    @EJB
    protected facade.TipoUsuarioFacade facade;

    protected List<TipoUsuario> items = null;
    protected TipoUsuario selected;
    protected boolean needLoadTable = true;

    List<OpcionSistema> listTodasOpcionesSistema = new ArrayList<>();
    List<OpcionSistema> listOpcionesSistemaTarget = new ArrayList<>();
    private DualListModel<OpcionSistema> dualListOpcionesSistema = new DualListModel<>();
    private DualListModel<PermisoAcceso> dualListPermisoAcceso = new DualListModel<>();

    //Constructor
    public TipoUsuarioController() {
        opcionSistemaFacade = new OpcionSistemaFacade();
        permisoAccesoFacade = new PermisoAccesoFacade();
        opcionSistemaController = new OpcionSistemaController();
    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {

    }

    //Permite refrescar el contenido de la tabla de TipoUsuarios de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Registra una nueva entidad TipoUsuario en la DB
    public void crear() {
        try {
//            facade.create(selected);
            selected = facade.guardarYobtenerIdTipoUsuario(selected);

            if (listPermisosAccesoTemp.size() > 0) {

                for (PermisoAcceso pa : listPermisosAccesoTemp) {
                    pa.setIdTipousuario(selected);
                    pa.setNombrePermisoacceso(selected.getNombreTipousuario().substring(0, 1) + "_" + pa.getIdOpcionsistema().getNombreOpcionsistema().substring(0, 1));
                    pa.setCodigoPermisoacceso("PA" + selected.getIdTipousuario() + "-" + pa.getIdOpcionsistema().getIdOpcionsistema());

                    permisoAccesoFacade.create(pa);
                }

            } else {
                System.out.println("El list de permisos está vacio");
            }

            listPermisosAccesoTemp = new ArrayList<>();
            listOpcionesSistemaTarget = new ArrayList<>();
            listTodasOpcionesSistema = new ArrayList<>();

            listPermisoAccesoTarget = new ArrayList<>();
            listPermisoAccesoSource = new ArrayList<>();
            dualListPermisoAcceso = new DualListModel<>();

            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoUsuarioCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  TipoUsuarioController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Prepara la entidad previo a la creación
    public TipoUsuario prepareCreate() {
        listTodasOpcionesSistema = opcionSistemaFacade.findAll();
        listPermisosAccesoTemp = new ArrayList<>();
        listOpcionesSistemaTarget = new ArrayList<>();

        //Enlista todas las opciones del sistema en un pickList
        dualListOpcionesSistema = new DualListModel<>(listTodasOpcionesSistema, listOpcionesSistemaTarget);
        selected = new TipoUsuario();
        inicializarVariables();
        return selected;
    }

    private int idTempPermisoAcceso;
    List<PermisoAcceso> listPermisoAccesoTarget;
    List<PermisoAcceso> listPermisoAccesoSource;

    //Prepara la entidad previo a la creación
    public TipoUsuario prepareEdit(TipoUsuario tipUs) {
        try {
            idTempPermisoAcceso = 0;
            selected = tipUs;
            listTodasOpcionesSistema = opcionSistemaFacade.findAll();

            listPermisoAccesoSource = new ArrayList<>();
            listPermisoAccesoTarget = permisoAccesoFacade.listarTodosPermisosPorTipoUsuario(selected);
            listPermisosAccesoTemp = new ArrayList<>();

            for (PermisoAcceso perm : listPermisoAccesoTarget) {
                if (listTodasOpcionesSistema.contains(perm.getIdOpcionsistema())) {
                    listTodasOpcionesSistema.remove(perm.getIdOpcionsistema());
                }
            }
            for (OpcionSistema ops : listTodasOpcionesSistema) {
                idTempPermisoAcceso--;
                permisoTemp = new PermisoAcceso(idTempPermisoAcceso);
                permisoTemp.setIdOpcionsistema(ops);
                permisoTemp.setIdTipousuario(selected);
                permisoTemp.setNombrePermisoacceso(selected.getNombreTipousuario().substring(0, 1) + "_" + ops.getNombreOpcionsistema().substring(0, 1));
                permisoTemp.setEstadoPermisoacceso(true);
                permisoTemp.setCodigoPermisoacceso("PA" + selected.getIdTipousuario() + "-" + ops.getIdOpcionsistema());
                permisoTemp.setSoloverPermisoacceso(false);

                listPermisoAccesoSource.add(permisoTemp);
            }
            //Enlista todas las opciones del sistema en un pickList
            dualListPermisoAcceso = new DualListModel<>(listPermisoAccesoSource, listPermisoAccesoTarget);
            System.out.println("Se cargaron los accesos del usuario " + listPermisoAccesoTarget.size());
        } catch (Exception e) {
            System.out.println("Ocurrio un error al prepareEdit de TipoUsuarioController " + e.getLocalizedMessage());
        }
//        inicializarVariables();
        return selected;
    }

    //Actualiza la entidad TipoUsuario en la DB
    public void actualizar() {
        try {
            facade.edit(selected);

            List<PermisoAcceso> listParaEliminar = new ArrayList<>();

            for (PermisoAcceso pas : listPermisoAccesoTarget) {
                if (listPermisosAccesoTemp.contains(pas)) {
//                        System.out.println("El listado si contenia "+pas.toString()+"no se agrega para eliminar");
                } else {
//                        System.out.println("El listado NO contenia "+pas.toString()+", se agrega para eliminar");
                    listParaEliminar.add(pas);
                }
            }

            System.out.println("listPermisosAccesoTemp " + listPermisosAccesoTemp.size());
            System.out.println("listPermisoAccesoTarget " + listPermisoAccesoTarget.size());
            System.out.println("listParaEliminar " + listParaEliminar.size());

            for (PermisoAcceso pas : listParaEliminar) {
                permisoAccesoFacade.remove(pas);
            }

            if (listPermisosAccesoTemp.size() > 0) {

                for (PermisoAcceso pa : listPermisosAccesoTemp) {
                    pa.setIdTipousuario(selected);
                    pa.setNombrePermisoacceso(selected.getNombreTipousuario().substring(0, 1) + "_" + pa.getIdOpcionsistema().getNombreOpcionsistema().substring(0, 1));
                    pa.setCodigoPermisoacceso("PA" + selected.getIdTipousuario() + "-" + pa.getIdOpcionsistema().getIdOpcionsistema());

//                    if (pa.getIdPermisoacceso() == null || pa.getIdPermisoacceso() <= 0) {
//                        permisoAccesoFacade.create(pa);
//                    } else {
                    permisoAccesoFacade.edit(pa);
//                    }
                }

            } else {
                System.out.println("El list de permisos está vacio");
            }

            listPermisosAccesoTemp = new ArrayList<>();
            listPermisoAccesoTarget = new ArrayList<>();
            listPermisoAccesoSource = new ArrayList<>();
            listParaEliminar = new ArrayList<>();
            dualListPermisoAcceso = new DualListModel<>();

            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoUsuarioUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  TipoUsuarioController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad TipoUsuario de la DB
    public void eliminar(TipoUsuario selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoUsuarioDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR: TipoUsuarioController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad TipoUsuario mediante su id desde la DB hacia este Controlador
    public TipoUsuario getTipoUsuario(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los TipoUsuario de la DB
    public List<TipoUsuario> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    //Metodo Transferir del picklist OpcionSistema
    public void onTransferOpcionSistema(TransferEvent event) {

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Item Transferred");
        for (Object item : event.getItems()) {
            msg.setSummary("Se registran " + dualListOpcionesSistema.getTarget().size() + " permisos");
        }
//        msg.setSummary("Items Transferred");
//        msg.setDetail(builder.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo Transferir del picklist permisoAcceso
    public void onTransferPermisoAcceso(TransferEvent event) {

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Item Transferred");
        for (Object item : event.getItems()) {
            msg.setSummary("Se registran " + dualListPermisoAcceso.getTarget().size() + " permisos");
        }
//        msg.setSummary("Items Transferred");
//        msg.setDetail(builder.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @FacesConverter(forClass = TipoUsuario.class)
    public static class TipoUsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoUsuarioController controller = (TipoUsuarioController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tipoUsuarioController");
            return controller.getTipoUsuario(getKey(value));
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
            if (object instanceof TipoUsuario) {
                TipoUsuario o = (TipoUsuario) object;
                return getStringKey(o.getIdTipousuario());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoUsuario.class.getName()});
                return null;
            }
        }

    }

    //////////////////Métodos para Editar Tipo Usuario///////////////////
    @EJB
    private PermisoAccesoFacade permisoAccesoFacade;

    @EJB
    private OpcionSistemaFacade opcionSistemaFacade;

    //Permite acceder a la información de un bean desde este bean
    @ManagedProperty("#{opcionSistemaController}")
    private OpcionSistemaController opcionSistemaController;

    private List<PermisoAcceso> listPermisosAccesoTemp;
    private PermisoAcceso permisoTemp;

    private boolean desabilitadoPorEdicion = false;

    public void onCellEdit(CellEditEvent event) {
        PermisoAcceso oldValue = (PermisoAcceso) event.getOldValue();
        PermisoAcceso newValue = (PermisoAcceso) event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onActualizarFila(RowEditEvent<PermisoAcceso> event) {
        try {
//            PermisoAcceso pa = (PermisoAcceso) event.getObject();
//            permisoAccesoFacade.edit(pa);
            JsfUtil.addSuccessMessage("Para completar los cambios presione el botón 'Guardar'");
        } catch (Exception e) {
            JsfUtil.addFatalMessage("Se presentó un error al onActualizarFila de la clase TipoUsuarioController" + e.getLocalizedMessage());
        } finally {
            desabilitadoPorEdicion = false;
        }
    }

    public void onCancelEditarFila(RowEditEvent<PermisoAcceso> event) {
        desabilitadoPorEdicion = false;
        JsfUtil.addWarningMessage("Se han cancelado los cambios realizados");
    }

    public void onIniciarEdicionFila(RowEditEvent<PermisoAcceso> event) {
        desabilitadoPorEdicion = true;
        JsfUtil.addSuccessMessage("Luego de realizar los cambios presione el botón derecho 'Guardar fila'");
    }

    public String onFlujoProcesoPermisoAccesoWizard(FlowEvent event) {
        listPermisosAccesoTemp = new ArrayList<>();
        try {
            if (dualListPermisoAcceso.getTarget().size() > 0) {
                for (PermisoAcceso perm : dualListPermisoAcceso.getTarget()) {
                    listPermisosAccesoTemp.add(perm);
                }
            } else {
                JsfUtil.addErrorMessage("Es necesario seleccionar permisos del listado");
            }
        } catch (Exception e) {
            JsfUtil.addFatalMessage("Error en onFlowProcess del controlador tipoUsuario");
        }
        return event.getNewStep();
    }

    public String onFlujoProcesoOpcionSistemaWizard(FlowEvent event) {
        listPermisosAccesoTemp = new ArrayList<>();
        try {
            if (dualListOpcionesSistema.getTarget().size() > 0) {
                for (OpcionSistema opcion : dualListOpcionesSistema.getTarget()) {
                    permisoTemp = new PermisoAcceso();
                    permisoTemp.setEstadoPermisoacceso(true);
                    permisoTemp.setSoloverPermisoacceso(false);
                    permisoTemp.setIdOpcionsistema(opcion);
                    listPermisosAccesoTemp.add(permisoTemp);
                }
            } else {
                JsfUtil.addErrorMessage("Es necesario seleccionar permisos del listado");
            }
        } catch (Exception e) {
            JsfUtil.addFatalMessage("Error en onFlowProcess del controlador tipoUsuario");
        }

        return event.getNewStep();
    }

    private Usuario usuarioLogeado = null;

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public List<TipoUsuario> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<TipoUsuario> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    public TipoUsuario getSelected() {
        return selected;
    }

    public void setSelected(TipoUsuario selected) {
        this.selected = selected;
    }

    protected TipoUsuarioFacade getFacade() {
        return facade;
    }

    public DualListModel<OpcionSistema> getDualListOpcionesSistema() {
        return dualListOpcionesSistema;
    }

    public void setDualListOpcionesSistema(DualListModel<OpcionSistema> dualListOpcionesSistema) {
        this.dualListOpcionesSistema = dualListOpcionesSistema;
    }

    public DualListModel<PermisoAcceso> getDualListPermisoAcceso() {
        return dualListPermisoAcceso;
    }

    public void setDualListPermisoAcceso(DualListModel<PermisoAcceso> dualListPermisoAcceso) {
        this.dualListPermisoAcceso = dualListPermisoAcceso;
    }

    public PermisoAccesoFacade getPermisoAccesoFacade() {
        return permisoAccesoFacade;
    }

    public OpcionSistemaFacade getOpcionSistemaFacade() {
        return opcionSistemaFacade;
    }

    public OpcionSistemaController getOpcionSistemaController() {
        return opcionSistemaController;
    }

    public void setPermisoAccesoFacade(PermisoAccesoFacade permisoAccesoFacade) {
        this.permisoAccesoFacade = permisoAccesoFacade;
    }

    public void setOpcionSistemaFacade(OpcionSistemaFacade opcionSistemaFacade) {
        this.opcionSistemaFacade = opcionSistemaFacade;
    }

    public void setOpcionSistemaController(OpcionSistemaController opcionSistemaController) {
        this.opcionSistemaController = opcionSistemaController;
    }

    public List<OpcionSistema> getListTodasOpcionesSistema() {
        listTodasOpcionesSistema = opcionSistemaFacade.findAll();
        return listTodasOpcionesSistema;
    }

    public List<PermisoAcceso> getListPermisosAccesoTemp() {
        return listPermisosAccesoTemp;
    }

    public boolean isDesabilitadoPorEdicion() {
        return desabilitadoPorEdicion;
    }

}
