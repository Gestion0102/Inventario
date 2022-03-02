package controlador;

import entidad.LogHost;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import facade.LogHostFacade;

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

@Named("logHostController")
@SessionScoped

public class LogHostController implements Serializable {

    @EJB
    protected facade.LogHostFacade facade;
    protected List<LogHost> items = null;
    protected LogHost selected;
    protected boolean needLoadTable = true;

    //Constructor
    public LogHostController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de LogHosts de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public LogHost prepareCreate() {
        selected = new LogHost();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad LogHost en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogHostCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogHostController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad LogHost en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogHostUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogHostController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad LogHost de la DB
    public void eliminar(LogHost selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogHostDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogHostController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad LogHost mediante su id desde la DB hacia este Controlador
    public LogHost getLogHost(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los LogHost de la DB
    public List<LogHost> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
//            needLoadTable = false;
            needLoadTable = true;
        }
        return items;
    }

    public List<LogHost> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<LogHost> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = LogHost.class)
    public static class LogHostControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LogHostController controller = (LogHostController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "logHostController");
            return controller.getLogHost(getKey(value));
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
            if (object instanceof LogHost) {
                LogHost o = (LogHost) object;
                return getStringKey(o.getIdLoghost());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LogHost.class.getName()});
                return null;
            }
        }

    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public LogHost getSelected() {
        return selected;
    }

    public void setSelected(LogHost selected) {
        this.selected = selected;
    }

    protected LogHostFacade getFacade() {
        return facade;
    }

}
