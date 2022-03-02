/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import controlador.util.JsfUtil;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("correoValidator")
public class ValidadorTresLetras implements Validator, Serializable {

    @Override
    public synchronized void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {

        String correoElectronico = "" + value.toString();
        String expreRegCorreo = "^[_ñÑA-Za-z0-9-\\+]+(\\.[_ñÑA-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern mask;
        Matcher matcher;

        if (correoElectronico.length() > 0) {

            mask = Pattern.compile(expreRegCorreo);
            matcher = mask.matcher(correoElectronico);

            validarMatch(matcher);
        } else {
            //throw new ValidatorException(new FacesMessage("El componente tiene un formato incorrecto"));
        }
    }

    private synchronized void validarMatch(Matcher matcher) {
        if (!matcher.matches()) {
            JsfUtil.addErrorMessage("El formato del correo es incorrecto");
            throw new ValidatorException(new FacesMessage("El componente tiene un formato incorrecto"));
        }
    }

}

