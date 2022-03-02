package controlador;

import entidad.PermisoAcceso;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import entidad.OpcionSistema;
import entidad.TipoUsuario;
import facade.OpcionSistemaFacade;
import facade.PermisoAccesoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.DualListModel;

@Named("permisoAccesoController")
@SessionScoped

public class PermisoAccesoController implements Serializable {

    @EJB
    protected facade.PermisoAccesoFacade facade;
    protected List<PermisoAcceso> items = null;
    protected PermisoAcceso selected;
    protected boolean needLoadTable = true;

    private final List<String> tiposGestionPermisoAcceso
            = Arrays.asList(
                    "Seleccionar varios",
                    "Ver",
                    "Crear",
                    "Editar",
                    "Eliminar");

    //Constructor
    public PermisoAccesoController() {

    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de PermisoAccesos de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public PermisoAcceso prepareCreate() {
        selected = new PermisoAcceso();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad PermisoAcceso en la DB
    public void crear() {
        try {
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PermisoAccesoCreated"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  PermisoAccesoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Actualiza la entidad PermisoAcceso en la DB
    public void actualizar() {
        try {
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PermisoAccesoUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  PermisoAccesoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad PermisoAcceso de la DB
    public void eliminar(PermisoAcceso selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PermisoAccesoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  PermisoAccesoController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad PermisoAcceso mediante su id desde la DB hacia este Controlador
    public PermisoAcceso getPermisoAcceso(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los PermisoAcceso de la DB
    public List<PermisoAcceso> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }

    public List<PermisoAcceso> getItemsAvailableSelectMany() {
        return facade.findAll();
    }

    public List<PermisoAcceso> getItemsAvailableSelectOne() {
        return facade.findAll();
    }

    @FacesConverter(forClass = PermisoAcceso.class)
    public static class PermisoAccesoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PermisoAccesoController controller = (PermisoAccesoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "permisoAccesoController");
            return controller.getPermisoAcceso(getKey(value));
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
            if (object instanceof PermisoAcceso) {
                PermisoAcceso o = (PermisoAcceso) object;
                return getStringKey(o.getIdPermisoacceso());

            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PermisoAcceso.class.getName()});
                return null;
            }
        }

    }

    public List<PermisoAcceso> listarTodosPermisosPorTipoUsuario(TipoUsuario tUsuario) {
        return facade.listarTodosPermisosPorTipoUsuario(tUsuario);
    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public PermisoAcceso getSelected() {
        return selected;
    }

    public void setSelected(PermisoAcceso selected) {
        this.selected = selected;
    }

    protected PermisoAccesoFacade getFacade() {
        return facade;
    }

    public List<String> getTiposGestionPermisoAcceso() {
        return tiposGestionPermisoAcceso;
    }

}
