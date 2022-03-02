package controlador;

import entidad.OpcionSistema;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import facade.OpcionSistemaFacade;

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

@Named("opcionSistemaController")
@SessionScoped

public class OpcionSistemaController implements Serializable {

    @EJB
    protected facade.OpcionSistemaFacade facade;
    protected List<OpcionSistema> items = null;
    protected OpcionSistema selected;
    protected boolean needLoadTable = true;

    //Constructor
    public OpcionSistemaController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de OpcionSistemas de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public OpcionSistema prepareCreate() {
        selected = new OpcionSistema();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad OpcionSistema en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OpcionSistemaCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  OpcionSistemaController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad OpcionSistema en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OpcionSistemaUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  OpcionSistemaController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad OpcionSistema de la DB
    public void eliminar(OpcionSistema selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OpcionSistemaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  OpcionSistemaController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad OpcionSistema mediante su id desde la DB hacia este Controlador
    public OpcionSistema getOpcionSistema(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los OpcionSistema de la DB
    public List<OpcionSistema> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<OpcionSistema> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<OpcionSistema> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = OpcionSistema.class)
    public static class OpcionSistemaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OpcionSistemaController controller = (OpcionSistemaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "opcionSistemaController");
            return controller.getOpcionSistema(getKey(value));
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
            if (object instanceof OpcionSistema) {
                OpcionSistema o = (OpcionSistema) object;
                return getStringKey(o.getIdOpcionsistema());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), OpcionSistema.class.getName()});
                return null;
            }
        }

    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public OpcionSistema getSelected() {
        return selected;
    }

    public void setSelected(OpcionSistema selected) {
        this.selected = selected;
    }

    protected OpcionSistemaFacade getFacade() {
        return facade;
    }

}
