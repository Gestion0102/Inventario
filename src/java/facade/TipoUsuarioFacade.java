/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controlador.util.JsfUtil;
import entidad.TipoUsuario;
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
public class TipoUsuarioFacade extends AbstractFacade<TipoUsuario> {

    @PersistenceContext(unitName = "InventarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoUsuarioFacade() {
        super(TipoUsuario.class);
    }

    public TipoUsuario guardarYobtenerIdTipoUsuario(TipoUsuario tipoUsuario) {
        try {
            super.create(tipoUsuario);
            Query query = em.createQuery("select tu FROM TipoUsuario tu order by tu.idTipousuario desc");
            tipoUsuario = (TipoUsuario) query.getResultList().get(0);
            System.out.println("facade: Listado tiene " + query.getResultList().size());
        } catch (Exception e) {
            JsfUtil.addErrorMessage("ErrorSaving TipoUsuario");
            System.out.println("ErrorSaving TipoUsuario" + e.getLocalizedMessage());

        }
        return tipoUsuario;
    }

}
