/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador_temp;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

/**
 *
 * @author Elena
 */
@Named(value = "faseBean")
@RequestScoped
public class FaseBean {

    /**
     * Creates a new instance of FaseBean
     */
    public FaseBean() {
    }

    public void evaluarFase(PhaseEvent event) {
        try {
            if (PhaseId.APPLY_REQUEST_VALUES.equals(event.getPhaseId())) {
                System.out.println("Fase actual " + PhaseId.APPLY_REQUEST_VALUES);
            }
            if (PhaseId.INVOKE_APPLICATION.equals(event.getPhaseId())) {
                System.out.println("Fase actual " + PhaseId.INVOKE_APPLICATION);
            }
            if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())) {
                System.out.println("Fase actual " + PhaseId.RENDER_RESPONSE);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error de tipo " + e.getLocalizedMessage());
        }
    }

}
