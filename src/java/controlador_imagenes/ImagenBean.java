/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador_imagenes;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author super
 */
@Named(value = "imagenBean")
@SessionScoped
public class ImagenBean implements Serializable {

    /**
     * Creates a new instance of ImagenBean
     */
    public ImagenBean() {
    }

    public void visualizar(byte[] arrayImagen) {
        try {
            if (arrayImagen.length > 0) {
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.getOutputStream().write(arrayImagen);
                response.getOutputStream().close();

                FacesContext.getCurrentInstance().responseComplete();
            } else {
                FacesMessage message = new FacesMessage("No existe archivo");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Error al intentar ver archivo " + e.getLocalizedMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

}
