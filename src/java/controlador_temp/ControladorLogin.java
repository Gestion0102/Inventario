/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador_temp;

import controlador.util.JsfUtil;
import entidad.PermisoAcceso;
import entidad.Usuario;
import facade.PermisoAccesoFacade;
//import entidad.Usuario_;
import facade.UsuarioFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author HP
 */
@Named(value = "controladorLogin")
@SessionScoped
public class ControladorLogin implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private PermisoAccesoFacade permisoAccesoFacade;

    private Usuario usuarioTemp;
    private Usuario usuarioLogeado = null;

    private BeanGenerico beanGenerico = new BeanGenerico();
    private List<PermisoAcceso> listPermisosUsuarioLogeado = null;

    /**
     * Creates a new instance of ControladorLogin
     */
    public ControladorLogin() {
        usuarioFacade = new UsuarioFacade();
        permisoAccesoFacade = new PermisoAccesoFacade();

        usuarioTemp = new Usuario();//el usuario tiene sus campos vacios
        listPermisosUsuarioLogeado = new ArrayList<>();

    }

    /**
     * metodo para autetificar y autorizar el acceso del usuario al sistema
     * (enlaza al boton de la vista con una método de este controlador)
     *
     * @return
     */
    public String iniciarSesionUsuario() {
        String redireccionarPagina = null;
        try {

            usuarioLogeado = usuarioFacade.obtenerUsuarioPorRolUsuario(usuarioTemp.getRolUsuario());
            usuarioTemp.setContraseniaUsuario(usuarioFacade.encriptarContrasenia(usuarioTemp.getContraseniaUsuario()));

            if (usuarioLogeado.getEstadoUsuario() == false || usuarioLogeado.getCantidadIntentosLogeoUsuario() > 5) {
                JsfUtil.addErrorMessage("Usuario Bloqueado!!. Contáctese con el administrador");
            } else {
                if (usuarioTemp.getContraseniaUsuario().equals(usuarioLogeado.getContraseniaUsuario())
                        || (usuarioTemp.getContraseniaUsuario().equals(usuarioLogeado.getContraseniaTemporalUsuario())
                        && usuarioLogeado.getContraseniaTemporalUsuario() != null)) {
                    //Mensaje al usuario de logeo correcto
                    JsfUtil.addSuccessMessage("Credenciales correctas, ingresando al sistema...");
                    //Almacenar el usuario logeado en la sesion de JSF
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogeadoSistema", usuarioLogeado);
                    listPermisosUsuarioLogeado = permisoAccesoFacade.listarPermisosActivosPorUsuario(usuarioLogeado);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sesionListPermisosUsuarioLogeado", listPermisosUsuarioLogeado);
                    //System.out.println("La cantidad de permisos de acceso para el usuario es " + listPermisosUsuarioLogeado.size());
                    //ServicioSesion.agregarObjetoASesion("usuarioLogeadoSistema", usuarioLogeado);

                    usuarioLogeado.setCantidadIntentosLogeoUsuario(0);
                    usuarioFacade.edit(usuarioLogeado);

                    //Aqui redirigir a la pagina inicial del sistema
                    redireccionarPagina = "bienvenida?faces-redirect=true";
                } else {
                    usuarioLogeado.setCantidadIntentosLogeoUsuario(usuarioLogeado.getCantidadIntentosLogeoUsuario() + 1);
                    if (usuarioLogeado.getCantidadIntentosLogeoUsuario() < 5) {
                        usuarioFacade.edit(usuarioLogeado);
                        JsfUtil.addErrorMessage("Credenciales erroneas, vuelva a intentar! Cantidad de intentos: " + usuarioLogeado.getCantidadIntentosLogeoUsuario());
                    } else if (usuarioLogeado.getCantidadIntentosLogeoUsuario() == 5) {
                        usuarioLogeado.setEstadoUsuario(false);
                        usuarioFacade.edit(usuarioLogeado);
                        JsfUtil.addErrorMessage("Usuario Bloqueado!! Realizó 5 intentos erroneos. Contáctese con el administrador");
                    } else {
                        JsfUtil.addErrorMessage("Usuario Bloqueado!! Ha excedido la cantidad de 5 intentos erroneos. Contáctese con el administrador");
                    }
                }
            }

        } catch (Exception e) {
            usuarioTemp = new Usuario();
            usuarioLogeado = null;
            redireccionarPagina = null;
            JsfUtil.addFatalMessage("Error al iniciarSesionUsuario del ControladorLogin " + e.getLocalizedMessage());
        } finally {
            usuarioTemp = new Usuario();
        }

        return redireccionarPagina;
    }

    private Usuario usuarioContraseniaOlvidada;
    private ControladorMail controladorMail;
    private String contraseniaTemp;
    private int intentosEnvioCorreo = 0;

    public void enviarCorreoRestablecerContrasenia() {

        try {
            controladorMail = new ControladorMail();
            usuarioContraseniaOlvidada = usuarioFacade.obtenerUsuarioPorRolUsuario(usuarioTemp.getRolUsuario());

            if (usuarioContraseniaOlvidada.getEstadoUsuario() == false) {
                JsfUtil.addErrorMessage("No se envía el correo porque el usuario está bloqueado. Contáctese con el administrador");
            } else {
                intentosEnvioCorreo++;
                
                if (intentosEnvioCorreo <= 5) {
                    if (usuarioContraseniaOlvidada.getCorreoUsuario().equals(usuarioTemp.getCorreoUsuario())) {
                        contraseniaTemp = this.getRandomString();

                        usuarioContraseniaOlvidada.setContraseniaTemporalUsuario(usuarioFacade.encriptarContrasenia(contraseniaTemp));
                        usuarioFacade.edit(usuarioContraseniaOlvidada);
                        //Aqui enviar correo de usuario
                        if (controladorMail.enviarCorreoRestablecerContrasenia(usuarioContraseniaOlvidada, contraseniaTemp)) {
                            //Mensaje al usuario de logeo correcto
                            JsfUtil.addSuccessMessage("Credenciales correctas, Se ha enviado un correo al usuario con las credenciales temporales...");
                        } else {
                            JsfUtil.addFatalMessage("Error al enviarCorreoRestablecerContrasenia del ControladorLogin ");
                        }

                    } else {
                        usuarioContraseniaOlvidada = null;
                        usuarioTemp = new Usuario();
                        JsfUtil.addErrorMessage("Credenciales erroneas, vuelva a intentar!");
                    }
                } else {
                    JsfUtil.addErrorMessage("Ha sobrepasado la cantidad de envíos de correo. Inténtelo más tarde");
                }
            }
        } catch (Exception e) {
            usuarioTemp = new Usuario();
            usuarioContraseniaOlvidada = null;
            JsfUtil.addFatalMessage("Error al enviarCorreoRestablecerContrasenia del ControladorLogin " + e.getLocalizedMessage() + " Contáctese con el administrador");
            System.out.println("Error al enviarCorreoRestablecerContrasenia del ControladorLogin " + e.getLocalizedMessage());
        } finally {
            usuarioContraseniaOlvidada = null;
            usuarioTemp = new Usuario();
        }
    }

    public String getRandomString() {
        return UUID.randomUUID().toString().substring(0, 16);
    }

    public void limpiarCamposLogin() {
        usuarioTemp = new Usuario();
    }

    public synchronized void cerrarSesionUsuario() {
        if (ServicioSesion.cerrarSesionUsuario()) {
            usuarioLogeado = null;
            usuarioTemp = null;
            usuarioContraseniaOlvidada = null;
        } else {
            JsfUtil.addWarningMessage("No se pudo cerrar sesión, contáctese con el administrador");
        }
    }

    //    Mediante los getters y setters se enlaza la vista con el objeto requerido
    public Usuario getUsuarioTemp() {
        return usuarioTemp;
    }

    public void setUsuarioTemp(Usuario usuarioTemp) {
        this.usuarioTemp = usuarioTemp;
    }

}
