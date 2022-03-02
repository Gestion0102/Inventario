/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controlador.util.JsfUtil;
import entidad.PermisoAcceso;
import entidad.TipoUsuario;
import entidad.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class PermisoAccesoFacade extends AbstractFacade<PermisoAcceso> {

    @PersistenceContext(unitName = "InventarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisoAccesoFacade() {
        super(PermisoAcceso.class);
    }

    public List<PermisoAcceso> listarTodosPermisosPorTipoUsuario(TipoUsuario tipoUsuario) {
        List<PermisoAcceso> listPermisosTemp = new ArrayList<>();

        try {
            Query query = em.createQuery("SELECT pa FROM PermisoAcceso pa WHERE pa.idTipousuario = :idTipousuario");
//            Query query = em.createNamedQuery("PermisoAcceso.findByIdTipousuario");
            query.setParameter("idTipousuario", tipoUsuario);

            if (query.getResultList().size() <= 0) {
                listPermisosTemp = new ArrayList<>();
            } else {
                listPermisosTemp = (List<PermisoAcceso>) query.getResultList();
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("ErrorFinding" + e.getLocalizedMessage());
            System.out.println("ErrorFinding listarTodosPermisosPorTipoUsuario de la clase PermisoAccesoFacade" + e.getLocalizedMessage());
        } finally {
            if (listPermisosTemp == null || listPermisosTemp.size() <= 0) {
                listPermisosTemp = new ArrayList<>();
            }
        }

        return listPermisosTemp;
    }

    public List<PermisoAcceso> listarPermisosActivosPorUsuario(Usuario usuario) {
        List<PermisoAcceso> listPermisosTemp = new ArrayList<>();

        try {

            /*SELECT * FROM inventario.permiso_acceso pa
                INNER join usuario u 
                where pa.ID_TIPOUSUARIO = u.ID_TIPOUSUARIO
                and pa.ESTADO_PERMISOACCESO = true
                and u.ID_USUARIO = 5;*/
            Query query = em.createQuery("SELECT pa FROM PermisoAcceso pa "
                    + "inner join Usuario u "
                    + "where pa.idTipousuario = u.idTipousuario "
                    + "and pa.estadoPermisoacceso = 'true'"
                    + "and u.idUsuario = :idUsuario");

            query.setParameter("idUsuario", usuario.getIdUsuario());

            if (query.getResultList().size() <= 0) {
                listPermisosTemp = new ArrayList<>();
            } else {
                listPermisosTemp = (List<PermisoAcceso>) query.getResultList();
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("ErrorFinding" + e.getLocalizedMessage());
            System.out.println("Error al consultar listarPermisosActivosPorUsuario de la clase PermisoAccesoFacade" + e.getLocalizedMessage());
        } finally {
            if (listPermisosTemp == null || listPermisosTemp.size() <= 0) {
                listPermisosTemp = new ArrayList<>();
            }
        }

        return listPermisosTemp;
    }
}
