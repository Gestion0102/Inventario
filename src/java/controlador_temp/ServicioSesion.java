/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador_temp;

import controlador.util.JsfUtil;
import entidad.Usuario;
import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.faces.context.ExternalContext;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import net.bootsfaces.expressions.ThisExpressionResolver;

/**
 * Clase encargada de gestionar la sesión de un usuario a nivel de JSF.
 *
 * @author Elena
 */
public class ServicioSesion {

    private static ExternalContext externalContext;

    private static Boolean sesionActiva;

    public static void iniciarSesion(FacesContext fc) {
        externalContext = fc.getExternalContext();
    }

    /**
     * Método encargado de iniciar una sesión y establecer los datos a gestionar
     * durante dicha sesión.
     *
     * @author Elena
     * @return 
     * @since 2017-06-01
     * @param Object
     */
//    public static void agregarObjetoASesion(String nomObjeto, Object objeto) {
//        try {
//            if (externalContext != null) {
//                sesionActiva = true;
//                externalContext.getSessionMap().put(nomObjeto, objeto);
//            } else {
//                sesionActiva = false;
//                throw new Exception("Error en el inicio de sesión");
//            }
//            externalContext.getSessionMap().put("sesionActiva", sesionActiva);
//        } catch (Exception e) {
//            externalContext.invalidateSession();
//            e.printStackTrace();
//        }
//    }

    
    public  static synchronized Usuario obtenerUsuarioLogeadoSesion() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            return (Usuario) fc.getExternalContext().getSessionMap().get("usuarioLogeadoSistema");
        } catch (Exception e) {
            System.out.println("Ocurrió un problema al obtenerUsuarioLogeadoSesion de la clase ControladorSesionUsuario " + e.getLocalizedMessage());
            return null;
        }
    }
    /**
     * Método encargado de cerrar una sesión actual.
     *
     * @author Elena
     * @since 
     */
    public static synchronized boolean cerrarSesionUsuario() {
        boolean cierreCorrecto;
        try {
            //Elimina toda la sesion del contexto externo 
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.invalidateSession();
//            Redirige a la pagina de inicio del sistema
            externalContext.redirect(ResourceBundle.getBundle("/Bundle").getString("UrlRedirigirPagLogin"));
            cierreCorrecto = true;
        } catch (IOException e) {
            cierreCorrecto = false;
            JsfUtil.addFatalMessage("ERROR: Se generó un error al cerrarSesionUsuario del ControladorLogin" + e.getLocalizedMessage());
        }
        return cierreCorrecto;
    }

    /**
     * Método encargado de iniciar una sesión para el usuario que se está
     * autenticando en el sistema.
     *
     * @author Elena
     * @since 2017-06-01
     * @return Object
     */
//    public static Object obtenerObjetoDeSesion(String nombreObjeto) {
//        if (externalContext != null) {
//            externalContext.getSessionMap().put("sesionActiva", sesionActiva);
//
//            return externalContext.getSessionMap().get(nombreObjeto);
//        } else {
//            System.out.println("No se encuentra el objeto en la sesión");
//            return null;
//        }
//    }

    /**
     * Método encargado de verificar el estado de una sesión de usuario.
     *
     * @author Elena
     * @since 2017-09-06
     * @return boolean
     */
//    public static Boolean verificarEstadoSesion() {
//        if (externalContext != null && externalContext.getSessionMap().get("sesionActiva") != null) {
//            return Boolean.parseBoolean(externalContext.getSessionMap().get("sesionActiva").toString());
//        } else {
//            System.out.println("Estado de sesión false");
//            return false;
//        }
//    }
}
