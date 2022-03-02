package controlador;

import entidad.AlarmaFisica;
import controlador.util.JsfUtil;
import facade.AlarmaFisicaFacade;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import java.io.Serializable;
import java.util.Arrays;
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
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@Named("alarmaFisicaController")
@SessionScoped

public class AlarmaFisicaController implements Serializable {

    @EJB
    protected facade.AlarmaFisicaFacade facade;
    protected List<AlarmaFisica> items = null;
    protected AlarmaFisica selected;
    protected boolean needLoadTable = true;

    private final List<String> estadosAlarma
            = Arrays.asList(
                    "Seleccionar",
                    "No reportada",
                    "Reportada",
                    "En verificación del administrador",
                    "En atención del proveedor",
                    "Fin parcial. No tiene corrección",
                    "Fin parcial. No tiene soporte",
                    "Finalizada y corregida");

    //Constructor
    public AlarmaFisicaController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de AlarmaFisicas de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public AlarmaFisica prepareCreate() {
        selected = new AlarmaFisica();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad AlarmaFisica en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AlarmaFisicaCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  AlarmaFisicaController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad AlarmaFisica en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AlarmaFisicaUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  AlarmaFisicaController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad AlarmaFisica de la DB
    public void eliminar(AlarmaFisica selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AlarmaFisicaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  AlarmaFisicaController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad AlarmaFisica mediante su id desde la DB hacia este Controlador
    public AlarmaFisica getAlarmaFisica(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los AlarmaFisica de la DB
    public List<AlarmaFisica> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<AlarmaFisica> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<AlarmaFisica> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = AlarmaFisica.class)
    public static class AlarmaFisicaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AlarmaFisicaController controller = (AlarmaFisicaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "alarmaFisicaController");
            return controller.getAlarmaFisica(getKey(value));
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
            if (object instanceof AlarmaFisica) {
                AlarmaFisica o = (AlarmaFisica) object;
                return getStringKey(o.getIdAlarma());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AlarmaFisica.class.getName()});
                return null;
            }
        }

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

    public void validarFechas() {
        try {
            if (selected != null && selected.getFechainiciaAlarma() != null && selected.getFechafinalizaAlarma() != null) {
                if (selected.getFechainiciaAlarma().compareTo(selected.getFechafinalizaAlarma()) > 0) {
                    JsfUtil.addErrorMessage("Las fechas contradicen su inicio/finalización");
                } else {
                    JsfUtil.addSuccessMessage("Fechas inicio/finalización correctas");
                }
            }
//            JsfUtil.addSuccessMessage("Comparación de fechas: " + selected.getFechainiciacoberturaMantenimiento().compareTo(selected.getFechafinalizacoberturaMantenimiento()));
        } catch (Exception e) {
            JsfUtil.addFatalMessage("Ocurrió un error al verificar fechas " + e.getLocalizedMessage());
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
            selected.setImagenunoAlarma(event.getFile().getContent());
            JsfUtil.addSuccessMessage(this.originalImageFile.getFileName() + " se cargó correctamente");
        }
    }

    public void quitarImagenUno() {
        try {
            file = null;
            selected.setImagenunoAlarma(null);
            this.originalImageFile = null;
            this.croppedImage = null;
            JsfUtil.addSuccessMessage("Se eliminó la imagen temporal, puede subir otra");
        } catch (Exception e) {
            JsfUtil.addFatalMessage("Error al intentar quitar archivo " + e.getLocalizedMessage());
        }
    }

    public void crop() {
        if (this.croppedImage == null || this.croppedImage.getBytes() == null || this.croppedImage.getBytes().length == 0) {
            selected.setImagenunoAlarma(file.getContent());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Recorte fallido."));
        } else {
            selected.setImagenunoAlarma(this.croppedImage.getBytes());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                    " Recorte exitoso."));
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
    public AlarmaFisica getSelected() {
        return selected;
    }

    public void setSelected(AlarmaFisica selected) {
        this.selected = selected;
    }

    protected AlarmaFisicaFacade getFacade() {
        return facade;
    }

    public boolean isSkip() {
        return skip;
    }

    public List<String> getEstadosAlarma() {
        return estadosAlarma;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

}
