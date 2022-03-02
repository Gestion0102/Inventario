package controlador;

import entidad.LogTipoequipo;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import facade.LogTipoequipoFacade;

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

@Named("logTipoequipoController")
@SessionScoped

public class LogTipoequipoController implements Serializable {

    @EJB
    protected facade.LogTipoequipoFacade facade;
    protected List<LogTipoequipo> items = null;
    protected LogTipoequipo selected;
    protected boolean needLoadTable = true;

    //Constructor
    public LogTipoequipoController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de LogTipoequipos de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public LogTipoequipo prepareCreate() {
        selected = new LogTipoequipo();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad LogTipoequipo en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogTipoequipoCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogTipoequipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad LogTipoequipo en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogTipoequipoUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogTipoequipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad LogTipoequipo de la DB
    public void eliminar(LogTipoequipo selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LogTipoequipoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  LogTipoequipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad LogTipoequipo mediante su id desde la DB hacia este Controlador
    public LogTipoequipo getLogTipoequipo(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los LogTipoequipo de la DB
    public List<LogTipoequipo> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
//            needLoadTable = false;
            needLoadTable = true;
        }
        return items;
    }

    public List<LogTipoequipo> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<LogTipoequipo> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = LogTipoequipo.class)
    public static class LogTipoequipoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LogTipoequipoController controller = (LogTipoequipoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "logTipoequipoController");
            return controller.getLogTipoequipo(getKey(value));
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
            if (object instanceof LogTipoequipo) {
                LogTipoequipo o = (LogTipoequipo) object;
                return getStringKey(o.getIdLogtipoequipo());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LogTipoequipo.class.getName()});
                return null;
            }
        }

    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public LogTipoequipo getSelected() {
        return selected;
    }

    public void setSelected(LogTipoequipo selected) {
        this.selected = selected;
    }

    protected LogTipoequipoFacade getFacade() {
        return facade;
    }

}
