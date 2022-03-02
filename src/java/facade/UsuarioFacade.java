/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidad.Usuario;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author HP
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "InventarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario obtenerUsuarioPorIdUsuario(int idUsuario) {
        Usuario usuario;
        try {
            Query query = em.createNamedQuery("Usuario.findByIdUsuario");
            query.setParameter("idUsuario", idUsuario);

            if (query.getResultList().size() <= 0) {
                usuario = new Usuario();
            } else {
                usuario = (Usuario) query.getSingleResult();
            }

        } catch (Exception e) {
            System.out.println("ERROR--> UsuarioFacade, obtenerUsuarioPorIdUsuario: " + e.getLocalizedMessage());
            usuario = new Usuario();
        }
        return usuario;
    }

    /**
     * Ejemplo de query con mètodo definido en ENTIDAD solo llamada por facade.
     * IMPORTANTE
     *
     * @param nombreUsuarioVar
     * @return
     */
    public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuarioVar) {
        Usuario usuario;
        try {
            Query query = em.createNamedQuery("Usuario.findByNombreUsuario");
            query.setParameter("nombreUsuario", nombreUsuarioVar);

            if (query.getResultList().size() <= 0) {
                usuario = new Usuario();
            } else {
                usuario = (Usuario) query.getResultList().get(0);
            }
        } catch (Exception e) {
            System.out.println("ERROR--> UsuarioFacade, obtenerUsuarioPorNombreUsuario: " + e.getLocalizedMessage());
            usuario = new Usuario();
        }
        return usuario;
    }

    /**
     * Ejemplo de query con mètodo definido en ENTIDAD solo llamada por facade.
     * IMPORTANTE
     *
     * @param rolUsuarioVar
     * @return
     */
    public Usuario obtenerUsuarioPorRolUsuario(String rolUsuarioVar) {
        Usuario usuario;
        try {
            Query query = em.createNamedQuery("Usuario.findByRolUsuario");
            query.setParameter("rolUsuario", rolUsuarioVar);
            List<Usuario> listUsuarios = query.getResultList();

            if (!listUsuarios.isEmpty()) { // listUsuarios.size() > 0) {
                usuario = listUsuarios.get(0);

//                usuario.setContraseniaUsuario(this.gDesencriptarPasswordParametroSistema(usuario.getContraseniaUsuario()));
            } else {
                usuario = new Usuario();
            }
        } catch (Exception e) {
            System.out.println("ERROR--> UsuarioFacade, obtenerUsuarioPorRolUsuario: " + e.getLocalizedMessage());
            usuario = new Usuario();
        }
        return usuario;
    }

    public Usuario obtenerUsuarioPorCedulaUsuario(String cedulaUsuarioVar) {
        Usuario usuario = new Usuario();
        try {
            Query query = em.createNamedQuery("Usuario.findByCedulaUsuario");
            query.setParameter("cedulaUsuario", cedulaUsuarioVar);

            if (query.getResultList().size() <= 0) {
            } else {
                usuario = (Usuario) query.getResultList().get(0);
            }
        } catch (Exception e) {
            System.out.println("ERROR--> UsuarioFacade, obtenerUsuarioPorCedulaUsuario: " + e.getLocalizedMessage());
            usuario = new Usuario();
        }
        return usuario;
    }

    public synchronized String encriptarContrasenia(String contrasenia) {
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {
            if (contrasenia != null) {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
                byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

                SecretKey key = new SecretKeySpec(keyBytes, "DESede");
                Cipher cipher = Cipher.getInstance("DESede");
                cipher.init(Cipher.ENCRYPT_MODE, key);

                byte[] plainTextBytes = contrasenia.getBytes("utf-8");
                byte[] buf = cipher.doFinal(plainTextBytes);
                byte[] base64Bytes = Base64.encodeBase64(buf);
                base64EncryptedString = new String(base64Bytes);
            } else {
                System.out.println("INFORMACION  --> contrasenia es nula en gEncriptarContrasenia");
                base64EncryptedString = "";
            }
        } catch (Exception e) {
            System.out.println("ERROR  --> Ocurrió un error en gEncriptarContrasenia: " + e.getLocalizedMessage());
            base64EncryptedString = "";
        }

        return base64EncryptedString;
    }

    public synchronized String desencriptarInformacion(String textoEncriptado) throws Exception {

        String base64EncryptedString = "";

        try {
            if (textoEncriptado != null) {
                String secretKey = "qualityinfosolutions"; //llave para encriptar datos

                byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
                byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
                SecretKey key = new SecretKeySpec(keyBytes, "DESede");

                Cipher decipher = Cipher.getInstance("DESede");
                decipher.init(Cipher.DECRYPT_MODE, key);

                byte[] plainText = decipher.doFinal(message);

                base64EncryptedString = new String(plainText, "UTF-8");
            } else {
                System.out.println("INFORMACION  --> texto es nulo en gDesencriptarPasswordParametroSistema");
            }

        } catch (Exception e) {
            System.out.println("ERROR  --> Ocurrió un error en gDesencriptarPasswordParametroSistema: " + e.getLocalizedMessage());
        }
        return base64EncryptedString;
    }

}
