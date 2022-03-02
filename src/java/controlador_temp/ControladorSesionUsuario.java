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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Elena
 */
@Named(value = "controladorSesionUsuario")
@ViewScoped
public class ControladorSesionUsuario implements Serializable {

    @EJB
    private PermisoAccesoFacade permisoAccesoFacade;

    private Usuario usuarioLogeado = null;
    private String mensaje;

    /**
     * Metodo para verificar que el usuario ha inicidado o no sesion desde
     * cualquier pagina del sistema
     */
    public void verificarSesionUsuarioIniciada() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            System.out.println("Pagina verificada es " + fc.getViewRoot().getViewId());

            usuarioLogeado = (Usuario) fc.getExternalContext().getSessionMap().get("usuarioLogeadoSistema");
            if (usuarioLogeado == null) {
                mensaje = "Inicie sesión con su usuario y contraseña";
                fc.getExternalContext().redirect(ResourceBundle.getBundle("/Bundle").getString("UrlRedirigirPagUsuarioNoLogeado"));
                usuarioLogeado = null;
            }
        } catch (Exception e) {
            usuarioLogeado = null;
            JsfUtil.addFatalMessage("Error al verificarSesionUsuario del ControladorSesionUsuario " + e.getLocalizedMessage());
        }
    }

    private List<PermisoAcceso> listPermisosUsuarioLogeado = null;
    private boolean tienePermisoDeAcceso;

    public void verificarPermisoAccesoUsuarioLogeadoAOpcionSistema() {
        tienePermisoDeAcceso = false;

        try {

            FacesContext fc = FacesContext.getCurrentInstance();
            String paginaVerificada = fc.getViewRoot().getViewId();

            usuarioLogeado = (Usuario) fc.getExternalContext().getSessionMap().get("usuarioLogeadoSistema");

            //Cuando el usuario no ha iniciado sesión le redirgie al login
            if (usuarioLogeado == null) {
                mensaje = "Inicie sesión con su usuario y contraseña";
                fc.getExternalContext().redirect(ResourceBundle.getBundle("/Bundle").getString("UrlRedirigirPagUsuarioNoLogeado"));
                usuarioLogeado = null;
                listPermisosUsuarioLogeado = null;
            } else {

                listPermisosUsuarioLogeado = new ArrayList<>();
                listPermisosUsuarioLogeado = permisoAccesoFacade.listarPermisosActivosPorUsuario(usuarioLogeado);
//                System.out.println("Los permisos del usuario logeado son: " + listPermisosUsuarioLogeado.size());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sesionListPermisosUsuarioLogeado", listPermisosUsuarioLogeado);

                if (paginaVerificada != null
                        && (paginaVerificada.toLowerCase().contains("bienvenida".toLowerCase())
                        || paginaVerificada.toLowerCase().contains("accesoNoPermitido".toLowerCase()))) {

//                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogeadoSistema", usuarioLogeado);
                    tienePermisoDeAcceso = true;
                } else if (paginaVerificada != null && paginaVerificada.contains("/pages/")) {
                    paginaVerificada = paginaVerificada.substring(0, paginaVerificada.lastIndexOf("/"));
                    paginaVerificada = (paginaVerificada.substring(paginaVerificada.lastIndexOf("/") + 1)).toLowerCase();

//                    listPermisosUsuarioLogeado = usuarioLogeado.getIdTipousuario().getPermisoAccesoList();
                    for (PermisoAcceso pa : listPermisosUsuarioLogeado) {
                        if (pa.getIdOpcionsistema().getCodigoOpcionsistema().toLowerCase().equals(paginaVerificada)) {
                            tienePermisoDeAcceso = true;
//                            System.out.println("La pagina se encoontro en " + pa.getIdOpcionsistema().getUrlOpcionsistema() + ". Acceso correcto");
                        }
                    }
                } else {
                    tienePermisoDeAcceso = false;
                    mensaje = ("No es una pagina interna");
                }

                if (!tienePermisoDeAcceso) {
                    mensaje = ("No tiene acceso a esta opción del sistema");
                    fc.getExternalContext().redirect(ResourceBundle.getBundle("/Bundle").getString("UrlRedirigirPagAccesoNoPermitido"));

                }
            }
            //System.out.println("Mensaje al verificar acceso a " + paginaVerificada + " es " + mensaje);

        } catch (Exception e) {
            usuarioLogeado = null;
            tienePermisoDeAcceso = false;
            System.out.println("Error al verificarPermisoAccesoUsuarioLogeadoAOpcionSistema del ControladorSesionUsuario " + e.getLocalizedMessage());
            mensaje = ("Error al verificarPermisoAccesoUsuarioLogeadoAOpcionSistema del ControladorSesionUsuario " + e.getLocalizedMessage());
        } finally {
            listPermisosUsuarioLogeado = null;
        }
    }

    

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public String getMensaje() {
        return mensaje;
    }

}
