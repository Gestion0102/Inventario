/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controlador.util.JsfUtil;
import entidad.Equipo;
import entidad.TipoUsuario;
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
public class EquipoFacade extends AbstractFacade<Equipo> {

    @PersistenceContext(unitName = "InventarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoFacade() {
        super(Equipo.class);
    }

    public List<Equipo> listEquiposTipoVirtualizadores() {
        List<Equipo> listEquiposVirtualizadores = null;
        try {
            /*SELECT * FROM inventario.equipo e 
                INNER JOIN tipo_equipo te
                WHERE e.id_tipoEquipo = te.id_tipoEquipo
                AND te.esvirtualizador_tipoEquipo = TRUE;*/
//            Query query = em.createQuery("SELECT e FROM Equipo e where e.idEquipo = 1");
//            Query query = em.createQuery("SELECT e FROM Equipo e where e.idTipoequipo.idTipoequipo = 1");
            Query query = em.createQuery("SELECT e FROM Equipo e "
                    + "INNER JOIN TipoEquipo te "
                    + "where e.idTipoequipo.idTipoequipo = te.idTipoequipo "
                    + "AND te.esVirtualizadorTipoequipo = true");
            listEquiposVirtualizadores = query.getResultList();

            if (listEquiposVirtualizadores.size() <= 0) {
                listEquiposVirtualizadores = new ArrayList<>();
            }
//            else {
//                listEquiposVirtualizadores = (List<Equipo>) query.getResultList();
//            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("ErrorFinding" + e.getLocalizedMessage());
            System.out.println("ErrorFinding listarTodosPermisosPorTipoUsuario de la clase PermisoAccesoFacade" + e.getLocalizedMessage());
        } finally {
            if (listEquiposVirtualizadores == null || listEquiposVirtualizadores.size() <= 0) {
                listEquiposVirtualizadores = new ArrayList<>();
            }
        }

        return listEquiposVirtualizadores;
    }

}
