package controlador;

import entidad.Host;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import controlador_temp.ServicioSesion;
import facade.HostFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.FlowEvent;

@Named("hostController")
@SessionScoped

public class HostController implements Serializable {

    @EJB
    protected facade.HostFacade facade;
    protected List<Host> items = null;
    protected Host selected;
    protected boolean needLoadTable = true;

    //Constructor
    public HostController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de Hosts de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public Host prepareCreate() {
        selected = new Host();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad Host en la DB
    public void crear() {
        try {
            selected.setIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HostCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  HostController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad Host en la DB
    public void actualizar() {
        try {
            selected.setIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HostUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  HostController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad Host de la DB
    public void eliminar(Host selectedTemp) {
        try {
            selectedTemp.setIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HostDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  HostController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad Host mediante su id desde la DB hacia este Controlador
    public Host getHost(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los Host de la DB
    public List<Host> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<Host> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<Host> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = Host.class)
    public static class HostControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HostController controller = (HostController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "hostController");
            return controller.getHost(getKey(value));
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
            if (object instanceof Host) {
                Host o = (Host) object;
                return getStringKey(o.getIdHost());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Host.class.getName()});
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

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    
    

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public Host getSelected() {
        return selected;
    }

    public void setSelected(Host selected) {
        this.selected = selected;
    }

    protected HostFacade getFacade() {
        return facade;
    }

}
