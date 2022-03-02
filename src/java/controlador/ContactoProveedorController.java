package controlador;

import entidad.ContactoProveedor;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import facade.ContactoProveedorFacade;

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

@Named("contactoProveedorController")
@SessionScoped

public class ContactoProveedorController implements Serializable {

    @EJB
    protected facade.ContactoProveedorFacade facade;
    protected List<ContactoProveedor> items = null;
    protected ContactoProveedor selected;
    protected boolean needLoadTable = true;

    //Constructor
    public ContactoProveedorController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de ContactoProveedors de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public ContactoProveedor prepareCreate() {
        selected = new ContactoProveedor();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad ContactoProveedor en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ContactoProveedorCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  ContactoProveedorController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad ContactoProveedor en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ContactoProveedorUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  ContactoProveedorController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad ContactoProveedor de la DB
    public void eliminar(ContactoProveedor selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ContactoProveedorDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  ContactoProveedorController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad ContactoProveedor mediante su id desde la DB hacia este Controlador
    public ContactoProveedor getContactoProveedor(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los ContactoProveedor de la DB
    public List<ContactoProveedor> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<ContactoProveedor> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<ContactoProveedor> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = ContactoProveedor.class)
    public static class ContactoProveedorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContactoProveedorController controller = (ContactoProveedorController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "contactoProveedorController");
            return controller.getContactoProveedor(getKey(value));
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
            if (object instanceof ContactoProveedor) {
                ContactoProveedor o = (ContactoProveedor) object;
                return getStringKey(o.getIdContactoproveedor());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ContactoProveedor.class.getName()});
                return null;
            }
        }

    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public ContactoProveedor getSelected() {
        return selected;
    }

    public void setSelected(ContactoProveedor selected) {
        this.selected = selected;
    }

    protected ContactoProveedorFacade getFacade() {
        return facade;
    }

}
