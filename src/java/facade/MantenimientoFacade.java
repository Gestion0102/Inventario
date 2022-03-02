/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controlador.util.JsfUtil;
import entidad.DetalleEquiposMantenimiento;
import entidad.Mantenimiento;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Elena Paola
 */
@Stateless
public class MantenimientoFacade extends AbstractFacade<Mantenimiento> {

    @PersistenceContext(unitName = "InventarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MantenimientoFacade() {
        super(Mantenimiento.class);
    }
    
    public Mantenimiento guardarYobtenerIdMantenimiento(Mantenimiento mantenimiento) {
        try {
            super.create(mantenimiento);
            Query query = em.createQuery("select tu FROM Mantenimiento tu order by tu.idMantenimiento desc");
            mantenimiento = (Mantenimiento) query.getResultList().get(0);
            System.out.println("facade: Listado mantenimiento tiene " + query.getResultList().size());
        } catch (Exception e) {
            JsfUtil.addErrorMessage("ErrorSaving TipoUsuario");
            System.out.println("ErrorSaving TipoUsuario" + e.getLocalizedMessage());

        }
        return mantenimiento;
    }
    

}
