package controlador;

import entidad.DetalleEquiposMantenimiento;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import facade.DetalleEquiposMantenimientoFacade;

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

@Named("detalleEquiposMantenimientoController")
@SessionScoped

public class DetalleEquiposMantenimientoController implements Serializable {

    @EJB
    protected facade.DetalleEquiposMantenimientoFacade facade;
    protected List<DetalleEquiposMantenimiento> items = null;
    protected DetalleEquiposMantenimiento selected;
    protected boolean needLoadTable = true;

    //Constructor
    public DetalleEquiposMantenimientoController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de DetalleEquiposMantenimientos de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public DetalleEquiposMantenimiento prepareCreate() {
        selected = new DetalleEquiposMantenimiento();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad DetalleEquiposMantenimiento en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleEquiposMantenimientoCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  DetalleEquiposMantenimientoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad DetalleEquiposMantenimiento en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleEquiposMantenimientoUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  DetalleEquiposMantenimientoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad DetalleEquiposMantenimiento de la DB
    public void eliminar(DetalleEquiposMantenimiento selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleEquiposMantenimientoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  DetalleEquiposMantenimientoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad DetalleEquiposMantenimiento mediante su id desde la DB hacia este Controlador
    public DetalleEquiposMantenimiento getDetalleEquiposMantenimiento(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los DetalleEquiposMantenimiento de la DB
    public List<DetalleEquiposMantenimiento> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<DetalleEquiposMantenimiento> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<DetalleEquiposMantenimiento> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = DetalleEquiposMantenimiento.class)
    public static class DetalleEquiposMantenimientoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleEquiposMantenimientoController controller = (DetalleEquiposMantenimientoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "detalleEquiposMantenimientoController");
            return controller.getDetalleEquiposMantenimiento(getKey(value));
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
            if (object instanceof DetalleEquiposMantenimiento) {
                DetalleEquiposMantenimiento o = (DetalleEquiposMantenimiento) object;
                return getStringKey(o.getIdDetalleequipomantenimiento());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetalleEquiposMantenimiento.class.getName()});
                return null;
            }
        }

    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public DetalleEquiposMantenimiento getSelected() {
        return selected;
    }

    public void setSelected(DetalleEquiposMantenimiento selected) {
        this.selected = selected;
    }

    protected DetalleEquiposMantenimientoFacade getFacade() {
        return facade;
    }

}
