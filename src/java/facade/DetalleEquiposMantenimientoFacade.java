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
public class DetalleEquiposMantenimientoFacade extends AbstractFacade<DetalleEquiposMantenimiento> {

    @PersistenceContext(unitName = "InventarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleEquiposMantenimientoFacade() {
        super(DetalleEquiposMantenimiento.class);
    }

    public List<DetalleEquiposMantenimiento> listarTodosDetallesPorMantenimiento(Mantenimiento mantenimiento) {
        List<DetalleEquiposMantenimiento> listPermisosTemp = new ArrayList<>();

        try {
            Query query = em.createQuery("SELECT dm FROM DetalleEquiposMantenimiento dm WHERE dm.idMantenimiento = :idMantenimiento");

//            Query query = em.createNamedQuery("PermisoAcceso.findByIdMantenimiento");
            query.setParameter("idMantenimiento", mantenimiento);
            listPermisosTemp = (List<DetalleEquiposMantenimiento>) query.getResultList();
//            if (listPermisosTemp.size() <= 0) {
//                listPermisosTemp = new ArrayList<>();
//            } else {
//                listPermisosTemp = (List<DetalleEquiposMantenimiento>) query.getResultList();
//            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("ErrorFinding" + e.getLocalizedMessage());
            System.out.println("ErrorFinding listarTodosDetallesPorMantenimiento de la clase MantenimientoFacade" + e.getLocalizedMessage());
        } finally {
            if (listPermisosTemp == null || listPermisosTemp.size() <= 0) {
                listPermisosTemp = new ArrayList<>();
            }
        }

        return listPermisosTemp;
    }

}
