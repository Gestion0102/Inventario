package controlador;

import entidad.Rack;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import facade.RackFacade;
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

@Named("rackController")
@SessionScoped

public class RackController implements Serializable {

    @EJB
    protected facade.RackFacade facade;
    protected List<Rack> items = null;
    protected Rack selected;
    protected boolean needLoadTable = true;

    //Constructor
    public RackController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de Racks de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public Rack prepareCreate() {
        selected = new Rack();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad Rack en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RackCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  RackController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad Rack en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RackUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  RackController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad Rack de la DB
    public void eliminar(Rack selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RackDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  RackController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad Rack mediante su id desde la DB hacia este Controlador
    public Rack getRack(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los Rack de la DB
    public List<Rack> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<Rack> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<Rack> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = Rack.class)
    public static class RackControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RackController controller = (RackController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "rackController");
            return controller.getRack(getKey(value));
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
            if (object instanceof Rack) {
                Rack o = (Rack) object;
                return getStringKey(o.getIdRack());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Rack.class.getName()});
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
            selected.setImagenRack(event.getFile().getContent());
            JsfUtil.addSuccessMessage(this.originalImageFile.getFileName() + " se cargó correctamente");
        }
    }

    public void crop() {
        if (this.croppedImage == null || this.croppedImage.getBytes() == null || this.croppedImage.getBytes().length == 0) {
            selected.setImagenRack(file.getContent());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Cropping failed."));
        } else {
            selected.setImagenRack(this.croppedImage.getBytes());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                    "Cropped successfully."));
        }
    }

    public void quitarImagen() {
        try {
            file = null;
            selected.setImagenRack(null);
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
    public Rack getSelected() {
        return selected;
    }

    public void setSelected(Rack selected) {
        this.selected = selected;
    }

    protected RackFacade getFacade() {
        return facade;
    }

}
