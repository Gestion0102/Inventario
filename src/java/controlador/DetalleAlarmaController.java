package controlador;

import entidad.DetalleAlarma;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import facade.DetalleAlarmaFacade;

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

@Named("detalleAlarmaController")
@SessionScoped

public class DetalleAlarmaController implements Serializable {

    @EJB
    protected facade.DetalleAlarmaFacade facade;
    protected List<DetalleAlarma> items = null;
    protected DetalleAlarma selected;
    protected boolean needLoadTable = true;

    //Constructor
    public DetalleAlarmaController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de DetalleAlarmas de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public DetalleAlarma prepareCreate() {
        selected = new DetalleAlarma();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad DetalleAlarma en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleAlarmaCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  DetalleAlarmaController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad DetalleAlarma en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleAlarmaUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  DetalleAlarmaController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad DetalleAlarma de la DB
    public void eliminar(DetalleAlarma selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleAlarmaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  DetalleAlarmaController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad DetalleAlarma mediante su id desde la DB hacia este Controlador
    public DetalleAlarma getDetalleAlarma(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los DetalleAlarma de la DB
    public List<DetalleAlarma> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<DetalleAlarma> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<DetalleAlarma> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = DetalleAlarma.class)
    public static class DetalleAlarmaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleAlarmaController controller = (DetalleAlarmaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "detalleAlarmaController");
            return controller.getDetalleAlarma(getKey(value));
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
            if (object instanceof DetalleAlarma) {
                DetalleAlarma o = (DetalleAlarma) object;
                return getStringKey(o.getIdDetallealarma());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetalleAlarma.class.getName()});
                return null;
            }
        }

    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public DetalleAlarma getSelected() {
        return selected;
    }

    public void setSelected(DetalleAlarma selected) {
        this.selected = selected;
    }

    protected DetalleAlarmaFacade getFacade() {
        return facade;
    }

}
