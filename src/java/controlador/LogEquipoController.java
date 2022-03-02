package controlador;

import entidad.LogEquipo;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import facade.LogEquipoFacade;

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

@Named("logEquipoController")
@SessionScoped

public class LogEquipoController implements Serializable {

    @EJB
    protected facade.LogEquipoFacade facade;
    protected List<LogEquipo> items = null;
    protected LogEquipo selected;
    protected boolean needLoadTable = true;

    //Constructor
    public LogEquipoController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de LogEquipos de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public LogEquipo prepareCreate() {
        selected = new LogEquipo();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad LogEquipo en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogEquipoCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogEquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad LogEquipo en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogEquipoUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogEquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad LogEquipo de la DB
    public void eliminar(LogEquipo selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogEquipoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogEquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad LogEquipo mediante su id desde la DB hacia este Controlador
    public LogEquipo getLogEquipo(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los LogEquipo de la DB
    public List<LogEquipo> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
//            needLoadTable = false;
            needLoadTable = true;
        }
        return items;
    }

    public List<LogEquipo> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<LogEquipo> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = LogEquipo.class)
    public static class LogEquipoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LogEquipoController controller = (LogEquipoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "logEquipoController");
            return controller.getLogEquipo(getKey(value));
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
            if (object instanceof LogEquipo) {
                LogEquipo o = (LogEquipo) object;
                return getStringKey(o.getIdLogequipo());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LogEquipo.class.getName()});
                return null;
            }
        }

    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public LogEquipo getSelected() {
        return selected;
    }

    public void setSelected(LogEquipo selected) {
        this.selected = selected;
    }

    protected LogEquipoFacade getFacade() {
        return facade;
    }

}
