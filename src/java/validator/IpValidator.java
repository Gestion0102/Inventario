/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import controlador.util.JsfUtil;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.print.attribute.standard.Severity;

@FacesValidator("ipValidator")
public class IpValidator implements Validator {

    private static final String IP_ADDRESS_PATTERN
            = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
        if ((String) value == null || (String) value == "" || ((String) value).equals("")) {

        } else {
            if (!((String) value).matches(IP_ADDRESS_PATTERN)) {
                JsfUtil.addErrorMessage("Dirección Ip incorrecta!");
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dirección Ip incorrecta!", ""));
            }
        }
    }
}
