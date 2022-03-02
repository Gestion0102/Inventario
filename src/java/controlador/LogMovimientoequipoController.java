package controlador;

import entidad.LogMovimientoequipo;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import facade.LogMovimientoequipoFacade;

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

@Named("logMovimientoequipoController")
@SessionScoped

public class LogMovimientoequipoController implements Serializable {

    @EJB
    protected facade.LogMovimientoequipoFacade facade;
    protected List<LogMovimientoequipo> items = null;
    protected LogMovimientoequipo selected;
    protected boolean needLoadTable = true;

    //Constructor
    public LogMovimientoequipoController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de LogMovimientoequipos de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public LogMovimientoequipo prepareCreate() {
        selected = new LogMovimientoequipo();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad LogMovimientoequipo en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogMovimientoequipoCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogMovimientoequipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad LogMovimientoequipo en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogMovimientoequipoUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogMovimientoequipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad LogMovimientoequipo de la DB
    public void eliminar(LogMovimientoequipo selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogMovimientoequipoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogMovimientoequipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad LogMovimientoequipo mediante su id desde la DB hacia este Controlador
    public LogMovimientoequipo getLogMovimientoequipo(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los LogMovimientoequipo de la DB
    public List<LogMovimientoequipo> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
//            needLoadTable = false;
            needLoadTable = true;
        }
        return items;
    }

    public List<LogMovimientoequipo> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<LogMovimientoequipo> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = LogMovimientoequipo.class)
    public static class LogMovimientoequipoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LogMovimientoequipoController controller = (LogMovimientoequipoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "logMovimientoequipoController");
            return controller.getLogMovimientoequipo(getKey(value));
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
            if (object instanceof LogMovimientoequipo) {
                LogMovimientoequipo o = (LogMovimientoequipo) object;
                return getStringKey(o.getIdLogmovimientoequipo());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LogMovimientoequipo.class.getName()});
                return null;
            }
        }

    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public LogMovimientoequipo getSelected() {
        return selected;
    }

    public void setSelected(LogMovimientoequipo selected) {
        this.selected = selected;
    }

    protected LogMovimientoequipoFacade getFacade() {
        return facade;
    }

}
