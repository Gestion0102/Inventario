/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidad.AlarmaFisica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Elena Paola
 */
@Stateless
public class AlarmaFisicaFacade extends AbstractFacade<AlarmaFisica> {

    @PersistenceContext(unitName = "InventarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlarmaFisicaFacade() {
        super(AlarmaFisica.class);
    }

}
