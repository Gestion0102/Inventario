package controlador;

import entidad.MovimientofisicoEquipo;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import controlador_temp.ServicioSesion;
import entidad.Usuario;
import facade.MovimientofisicoEquipoFacade;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import org.primefaces.event.FlowEvent;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@Named("movimientofisicoEquipoController")
@SessionScoped

public class MovimientofisicoEquipoController implements Serializable {

    @EJB
    protected facade.MovimientofisicoEquipoFacade facade;
    protected List<MovimientofisicoEquipo> items = null;
    protected MovimientofisicoEquipo selected;
    protected boolean needLoadTable = true;

    private final List<String> tiposMovimientoEquipo
            = Arrays.asList(
                    "Seleccionar",
                    "Movimiento entre centros de datos",
                    "Movimiento a otras áreas EEQ",
                    "Ingreso de nuevo equipo",
                    "Baja de equipo",
                    "Otro, escriba su texto");

    private final List<String> ubicaciones
            = Arrays.asList(
                    "Seleccionar",
                    "Centro de Datos Nacional Iñaquito",
                    "Data Center Las Casas",
                    "Otro, escriba su texto");

    //Constructor
    public MovimientofisicoEquipoController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de MovimientofisicoEquipos de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public MovimientofisicoEquipo prepareCreate() {
        file = null;
        filePDF = null;
        fileViewPDF = null;
        originalImageFile = null;
        croppedImage = null;

        selected = new MovimientofisicoEquipo();

        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad MovimientofisicoEquipo en la DB
    public void crear() {
        try {

            selected.setIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());
            selected.setUsuIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());

            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MovimientofisicoEquipoCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  MovimientofisicoEquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad MovimientofisicoEquipo en la DB
    public void actualizar() {
        try {
            selected.setUsuIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MovimientofisicoEquipoUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  MovimientofisicoEquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad MovimientofisicoEquipo de la DB
    public void eliminar(MovimientofisicoEquipo selectedTemp) {
        try {
            selected.setUsuIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MovimientofisicoEquipoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  MovimientofisicoEquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad MovimientofisicoEquipo mediante su id desde la DB hacia este Controlador
    public MovimientofisicoEquipo getMovimientofisicoEquipo(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los MovimientofisicoEquipo de la DB
    public List<MovimientofisicoEquipo> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<MovimientofisicoEquipo> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<MovimientofisicoEquipo> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = MovimientofisicoEquipo.class)
    public static class MovimientofisicoEquipoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovimientofisicoEquipoController controller = (MovimientofisicoEquipoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "movimientofisicoEquipoController");
            return controller.getMovimientofisicoEquipo(getKey(value));
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
            if (object instanceof MovimientofisicoEquipo) {
                MovimientofisicoEquipo o = (MovimientofisicoEquipo) object;
                return getStringKey(o.getIdMovimientoequipo());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovimientofisicoEquipo.class.getName()});
                return null;
            }
        }

    }

    /////////Documento adjunto//////////////
    private UploadedFile filePDF;
    private StreamedContent fileViewPDF = null;

    public void handleAdjuntoUpload(FileUploadEvent event) {
        filePDF = event.getFile();
        if (filePDF != null && filePDF.getContent() != null && filePDF.getContent().length > 0 && filePDF.getFileName() != null) {
//            this.originalImageFile = filePDF;
            selected.setDocumentorespaldoMovimientoequipo(filePDF.getContent());
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
            if (selected != null && selected.getDocumentorespaldoMovimientoequipo() != null) {
                fileViewPDF = DefaultStreamedContent.builder().
                        stream(()
                                -> new ByteArrayInputStream(selected.getDocumentorespaldoMovimientoequipo())
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
            selected.setDocumentorespaldoMovimientoequipo(null);
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

    private boolean skip;

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    /////////Imagenes//////////////
    private UploadedFile file;
    private CroppedImage croppedImage;
    private UploadedFile originalImageFile;

    public void prepareCargarImagen() {
        this.originalImageFile = null;
        this.croppedImage = null;
        file = null;

    }

    public void handleFileUpload(FileUploadEvent event) {
        this.originalImageFile = null;
        this.croppedImage = null;
        file = event.getFile();
        if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
            this.originalImageFile = file;
            selected.setImagenMovimientoequipo(event.getFile().getContent());
            JsfUtil.addSuccessMessage(this.originalImageFile.getFileName() + " se cargó correctamente");
        }
    }

    public void crop() {
        if (this.croppedImage == null || this.croppedImage.getBytes() == null || this.croppedImage.getBytes().length == 0) {
            selected.setImagenMovimientoequipo(file.getContent());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Cropping failed."));
        } else {
            selected.setImagenMovimientoequipo(this.croppedImage.getBytes());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                    "Cropped successfully."));
        }
    }

    public void quitarImagen() {
        try {
            file = null;
            selected.setImagenMovimientoequipo(null);
            this.originalImageFile = null;
            this.croppedImage = null;
            JsfUtil.addSuccessMessage("Se eliminó la imagen temporal, puede subir otra");
        } catch (Exception e) {
            JsfUtil.addFatalMessage("Error al intentar quitar archivo " + e.getLocalizedMessage());
        }
    }

    public StreamedContent getImage() {
        return DefaultStreamedContent.builder()
                .contentType(originalImageFile == null ? null : originalImageFile.getContentType())
                .stream(() -> {
                    if (originalImageFile == null
                            || originalImageFile.getContent() == null
                            || originalImageFile.getContent().length == 0) {
                        return null;
                    }

                    try {
                        return new ByteArrayInputStream(originalImageFile.getContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .build();
    }

    public StreamedContent getCropped() {
        return DefaultStreamedContent.builder()
                .contentType(originalImageFile == null ? null : originalImageFile.getContentType())
                .stream(() -> {
                    if (croppedImage == null
                            || croppedImage.getBytes() == null
                            || croppedImage.getBytes().length == 0) {
                        return null;
                    }

                    try {
                        return new ByteArrayInputStream(this.croppedImage.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .build();
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    public UploadedFile getOriginalImageFile() {
        return originalImageFile;
    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public MovimientofisicoEquipo getSelected() {
        return selected;
    }

    public void setSelected(MovimientofisicoEquipo selected) {
        this.selected = selected;
    }

    protected MovimientofisicoEquipoFacade getFacade() {
        return facade;
    }

    public List<String> getTiposMovimientoEquipo() {
        return tiposMovimientoEquipo;
    }

    public List<String> getUbicaciones() {
        return ubicaciones;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

}
