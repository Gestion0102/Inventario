/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador_temp;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.UUID;
import javax.faces.context.FacesContext;

/**
 *
 * @author Elena
 */
@Named(value = "beanGenerico")
@SessionScoped
public class BeanGenerico implements Serializable {

    /**
     * Creates a new instance of BeanGenerico
     */
    public BeanGenerico() {
    }

    /**
     * Metodo que permite redirigir una pagina a otra interactivamente
     * utilizando la clave/valor del archivo de constantes
     *
     * @autor
     * @fecha 2017-05-27
     * @param paginaRedirigida
     */
    public synchronized void irAPagina(String paginaRedirigida) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
//            String raizInterfaces = "Login/";
            String raizInterfaces = "faces/";
            context.getExternalContext().redirect(raizInterfaces + paginaRedirigida);
        } catch (Exception e) {
            System.out.println("ERROR --> Se encontró un error al irAPagina en BeanGenerico: " + e.getLocalizedMessage());
        }
    }

    /**
     * Generar String Randómico
     *
     * @auto
     * @fecha 2020-11-08
     * @return String
     */
    public String generarRandomString(int cantidadCaracteres) {
        return UUID.randomUUID().toString().substring(0, cantidadCaracteres);
    }

    /**
     * Generar Int Randómico
     *
     * @autor
     * @fecha 2020-11-08
     * @return String
     */
    public int getRandomInt() {
        return (int) (Math.random() * 100000);
    }

}
