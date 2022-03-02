package controlador;

import entidad.Usuario;
import controlador.util.JsfUtil;
import facade.UsuarioFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("usuarioController")
@SessionScoped

public class UsuarioController implements Serializable {
    
    @EJB
    protected facade.UsuarioFacade facade;
    protected List<Usuario> items = null;
    protected Usuario selected;
    protected boolean needLoadTable = true;

    //Constructor
    public UsuarioController() {
    }

    //setEmbeddableKeys
    protected void setVariablesIntegrables() {
    }

    //initializeEmbeddableKey
    protected void inicializarVariables() {
    }

    //Permite refrescar el contenido de la tabla de Usuarios de la vista de List 
    public void refreshRows() {
        needLoadTable = true;
    }

    //Prepara la entidad previo a la creación
    public Usuario prepareCreate() {
        selected = new Usuario();
        inicializarVariables();
        return selected;
    }

    //Registra una nueva entidad Usuario en la DB
    public void crear() {
        try {
            selected.setEstadoUsuario(true);
            selected.setContraseniaUsuario(facade.encriptarContrasenia(selected.getContraseniaUsuario()));
            selected.setContraseniaTemporalUsuario(selected.getContraseniaUsuario());
            selected.setCantidadIntentosLogeoUsuario(0);
            facade.create(selected);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  UsuarioController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }
    
    private static int respaldoIntentos = 0;
    
    public void prepareUpdate(Usuario us) {
        try {
            selected = us;
            
            if (selected.getCantidadIntentosLogeoUsuario() == null) {
                selected.setCantidadIntentosLogeoUsuario(0);
            }
            
            respaldoIntentos = selected.getCantidadIntentosLogeoUsuario();
        } catch (Exception e) {
            System.out.println("Se presentó un error al prepareUpdate de usuarioController " + e.getLocalizedMessage() + "ojo " + e.getMessage());
        }
    }

    //Actualiza la entidad Usuario en la DB
    public void actualizar() {
        try {
//            selected.setContraseniaUsuario(encriptarContrasenia(selected.getContraseniaUsuario()));
            selected.setCantidadIntentosLogeoUsuario(0);
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
//            JsfUtil.addSuccessMessage("Se actualizo correctamente");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  UsuarioController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }
    
    public void activarUsuario() {
        try {
            if (selected.getEstadoUsuario()) {
                selected.setCantidadIntentosLogeoUsuario(0);
                JsfUtil.addSuccessMessage("Cantidad de intentos de logeo se restablecieron a cero al cambiar el estado del usuario. No olvide guardar los cambios");
            } else {
                selected.setCantidadIntentosLogeoUsuario(respaldoIntentos);
                JsfUtil.addWarningMessage("Retorno al valor anterior " + respaldoIntentos);
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error: se requiere seleccionar el estado");
            System.out.println("Error: se requiere seleccionar el estado " + e.getLocalizedMessage());
        }
    }

    //Actualiza la entidad Usuario en la DB
    public void cambiarContrasenia() {
        try {
            selected.setContraseniaTemporalUsuario(null);
            selected.setContraseniaUsuario(facade.encriptarContrasenia(selected.getContraseniaUsuario()));
            facade.edit(selected);
            needLoadTable = true;
            selected = null;
//            JsfUtil.addSuccessMessage("Se actualizo correctamente");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  UsuarioController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Elimina la entidad Usuario de la DB
    public void eliminar(Usuario selectedTemp) {
        try {
            facade.remove(selectedTemp);
            needLoadTable = true;
            selected = null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERROR:  UsuarioController " + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured") + e.getMessage());
        }
    }

    //Permite obtener la entidad Usuario mediante su id desde la DB hacia este Controlador
    public Usuario getUsuario(java.lang.Integer id) {
        return facade.find(id);
    }

    //Lista todos los Usuario de la DB
    public List<Usuario> getItems() {
        if (needLoadTable) {
            items = getFacade().findAll();
            needLoadTable = false;
        }
        return items;
    }
    
    public List<Usuario> getItemsAvailableSelectMany() {
        return facade.findAll();
    }
    
    public List<Usuario> getItemsAvailableSelectOne() {
        return facade.findAll();
    }
    
    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUsuario());
                
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }
        
    }

    /////////////////////GETTERS Y SETTERS//////////////////////////
    public Usuario getSelected() {
        return selected;
    }
    
    public void setSelected(Usuario selected) {
        this.selected = selected;
    }
    
    protected UsuarioFacade getFacade() {
        return facade;
    }
    
}
