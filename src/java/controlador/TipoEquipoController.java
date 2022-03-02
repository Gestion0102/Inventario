package controlador;

import entidad.TipoEquipo;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import controlador_temp.ServicioSesion;
import facade.TipoEquipoFacade;

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

@Named("tipoEquipoController")
@SessionScoped

public class TipoEquipoController implements Serializable {

    @EJB
    protected facade.TipoEquipoFacade facade;
    protected List<TipoEquipo> items = null;
    protected TipoEquipo selected;
    protected boolean needLoadTable = true;

    //Constructor
    public TipoEquipoController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de TipoEquipos de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public TipoEquipo prepareCreate() {
        selected = new TipoEquipo();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad TipoEquipo en la DB
    public void crear() {
        try {
            selected.setIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoEquipoCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  TipoEquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad TipoEquipo en la DB
    public void actualizar() {
        try {
            selected.setIdUsuario(ServicioSesion.obtenerUsuarioLogeadoSesion());
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoEquipoUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  TipoEquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad TipoEquipo de la DB
    public void eliminar(TipoEquipo selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoEquipoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  TipoEquipoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad TipoEquipo mediante su id desde la DB hacia este Controlador
    public TipoEquipo getTipoEquipo(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los TipoEquipo de la DB
    public List<TipoEquipo> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<TipoEquipo> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<TipoEquipo> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = TipoEquipo.class)
    public static class TipoEquipoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoEquipoController controller = (TipoEquipoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tipoEquipoController");
            return controller.getTipoEquipo(getKey(value));
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
            if (object instanceof TipoEquipo) {
                TipoEquipo o = (TipoEquipo) object;
                return getStringKey(o.getIdTipoequipo());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoEquipo.class.getName()});
                return null;
            }
        }

    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public TipoEquipo getSelected() {
        return selected;
    }

    public void setSelected(TipoEquipo selected) {
        this.selected = selected;
    }

    protected TipoEquipoFacade getFacade() {
        return facade;
    }

}
