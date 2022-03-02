package controlador;

import entidad.CentroDatos;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import facade.CentroDatosFacade;
import java.io.ByteArrayInputStream;

import java.io.Serializable;
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

@Named("centroDatosController")
@SessionScoped

public class CentroDatosController implements Serializable {

    @EJB
    protected facade.CentroDatosFacade facade;
    protected List<CentroDatos> items = null;
    protected CentroDatos selected;
    protected boolean needLoadTable = true;

    //Constructor
    public CentroDatosController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de CentroDatoss de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public CentroDatos prepareCreate() {
        selected = new CentroDatos();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad CentroDatos en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CentroDatosCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  CentroDatosController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad CentroDatos en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CentroDatosUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  CentroDatosController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad CentroDatos de la DB
    public void eliminar(CentroDatos selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CentroDatosDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  CentroDatosController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad CentroDatos mediante su id desde la DB hacia este Controlador
    public CentroDatos getCentroDatos(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los CentroDatos de la DB
    public List<CentroDatos> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<CentroDatos> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<CentroDatos> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = CentroDatos.class)
    public static class CentroDatosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CentroDatosController controller = (CentroDatosController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "centroDatosController");
            return controller.getCentroDatos(getKey(value));
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
            if (object instanceof CentroDatos) {
                CentroDatos o = (CentroDatos) object;
                return getStringKey(o.getIdCentrodatos());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CentroDatos.class.getName()});
                return null;
            }
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
            selected.setImagenCentrodatos(event.getFile().getContent());
            JsfUtil.addSuccessMessage(this.originalImageFile.getFileName() + " se cargó correctamente");
        }
    }

    public void crop() {
        if (this.croppedImage == null || this.croppedImage.getBytes() == null || this.croppedImage.getBytes().length == 0) {
            selected.setImagenCentrodatos(file.getContent());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Cropping failed."));
        } else {
            selected.setImagenCentrodatos(this.croppedImage.getBytes());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                    "Cropped successfully."));
        }
    }

    public void quitarImagen() {
        try {
            file = null;
            selected.setImagenCentrodatos(null);
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
    
//    public void guardarImagen(){
//        if(crop)
//    }

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
    public CentroDatos getSelected() {
        return selected;
    }

    public void setSelected(CentroDatos selected) {
        this.selected = selected;
    }

    protected CentroDatosFacade getFacade() {
        return facade;
    }

}
