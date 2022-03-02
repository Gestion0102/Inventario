package controlador;

import entidad.Equipo;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import controlador_temp.ControladorSesionUsuario;
import controlador_temp.ServicioSesion;
import facade.EquipoFacade;
import java.io.ByteArrayInputStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import org.primefaces.event.FlowEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@Named("equipoController")
@SessionScoped

public class EquipoController implements Serializable {

    @EJB
    protected facade.EquipoFacade facade;
    protected List<Equipo> items = null;
    protected Equipo selected;
    protected boolean needLoadTable = true;

    private boolean skip;

    //Constructor
    public EquipoController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de Equipos de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public Equipo prepareCreate() {
        selected = new Equipo();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad Equipo en la DB
    public void crear() {
        try {
            selected.setIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EquipoCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  EquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad Equipo en la DB
    public void actualizar() {
        try {
            selected.setIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EquipoUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  EquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad Equipo de la DB
    public void eliminar(Equipo selectedTemp) {
        try {
            selectedTemp.setIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EquipoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  EquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad Equipo mediante su id desde la DB hacia este Controlador
    public Equipo getEquipo(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los Equipo de la DB
    public List<Equipo> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<Equipo> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<Equipo> getItemsAvailableSelectOne() {
        return facade.listEquiposTipoVirtualizadores();
    }
    
    

    @FacesConverter(forClass = Equipo.class)
    public static class EquipoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EquipoController controller = (EquipoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "equipoController");
            return controller.getEquipo(getKey(value));
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
            if (object instanceof Equipo) {
                Equipo o = (Equipo) object;
                return getStringKey(o.getIdEquipo());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Equipo.class.getName()});
                return null;
            }
        }

    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    ///////////////Búsqueda de equipos para complete//////////////
    public List<Equipo> completeText(String query) {
//        String queryLowerCase = query.toLowerCase();
//        List<String> countryList = new ArrayList<>();
//        List<Equipo> countries = facade.findAll();
//        for (Equipo country : countries) {
//            countryList.add(country.getSerieEquipo());
//        }
//
//        return countryList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());

        String queryLowerCase = query.toLowerCase();
        List<Equipo> countries = facade.findAll();
        return countries.stream().filter(t -> t.getSerieEquipo().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());

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
            selected.setImagenEquipo(event.getFile().getContent());
            JsfUtil.addSuccessMessage(this.originalImageFile.getFileName() + " se cargó correctamente");
        }
    }

    public void crop() {
        if (this.croppedImage == null || this.croppedImage.getBytes() == null || this.croppedImage.getBytes().length == 0) {
            selected.setImagenEquipo(file.getContent());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "Cropping failed."));
        } else {
            selected.setImagenEquipo(this.croppedImage.getBytes());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                    "Cropped successfully."));
        }
    }

    public void quitarImagen() {
        try {
            file = null;
            selected.setImagenEquipo(null);
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
    public Equipo getSelected() {
        return selected;
    }

    public void setSelected(Equipo selected) {
        this.selected = selected;
    }

    protected EquipoFacade getFacade() {
        return facade;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

}
