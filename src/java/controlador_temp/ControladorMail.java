/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador_temp;

import entidad.Usuario;
import java.io.Serializable;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Diego
 */
@Stateless
@LocalBean
public class ControladorMail implements Serializable {

    private Session session;
    private String correoAdmin;
    private String passAdmin;
//    private final UsuarioFacade usuarioFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public ControladorMail() {
//        usuarioFacade = new UsuarioFacade();

    }

    private synchronized void autenticarEnvioCorreo() {
        try {

            correoAdmin = "app.prueba.desarrollo@gmail.com";
            passAdmin = "123456Si$";
//            passAdmin = usuarioFacade.desencriptarInformacion(passAdmin);
//            System.out.println("CREDENCIALES: " + correoAdmin + ", contraseña: " + passAdmin);
        } catch (Exception e) {
            System.out.println("Error Autenticar envío de correo" + e.getLocalizedMessage());
        }

    }

    /**
     * returns: paramethers: descripción: inicializa las propiedades del
     * servidor smtp de google para el envio de correo desde java
     */
    private void init() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoAdmin, passAdmin);
            }
        });
    }

    /**
     * Envía el correo de acuerdo a los parámetros definidos en la clase Mail
     *
     * @author
     * @param mail
     * @fecha 2017-02-15
     * @return MensajeUsuario para la impresión de errores
     */
    public synchronized boolean enviarCorreoGeneral(Mail mail) {

        boolean seEnvioCorreo;

        try {
            this.init();
            this.autenticarEnvioCorreo();
            //Creamos un nuevo mensaje, y le pasamos nuestra sesión iniciada en el paso anterior.
            Message message = new MimeMessage(session);
            //Seteamos la dirección desde la cual enviaremos el mensaje
            message.setFrom(new InternetAddress(correoAdmin));
//            System.out.println("OJO: " + correoAdmin + " contraseña: " + passAdmin);
            //Seteamos el destino de nuestro mensaje
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getDirigidoA()));
            //Seteamos el asunto
            message.setSubject(mail.getAsunto());
            //Y por ultimo el texto.
            message.setText(mail.getEstructuraYContenidoMaill());
            //Esta orden envía el mensaje
            mail.toString();
            //AQUI SE PRESENTA EL ERROR POR VERSION DE JAVA
            Transport.send(message);

            System.out.println(mail.getEstructuraYContenidoMaill());

            seEnvioCorreo = true;
        } catch (MessagingException e) {

//            throw new RuntimeException(e);
            System.out.println("Ocurrió un error al enviar correo " + e.getLocalizedMessage());
            seEnvioCorreo = false;
        }

        return seEnvioCorreo;
    }

    public boolean enviarCorreoRestablecerContrasenia(Usuario user, String contraseniaTemp) {
        try {
            Mail mail = new Mail();
            mail.setDirigidoA(user.getCorreoUsuario());
            mail.setAsunto("Se ha solicitado el cambio de contraseña de tu cuenta Inventario CDN");
            mail.setSaludo("Hola " + user.getNombreUsuario());
            mail.setIntroduccion("Se ha generado una contraseña temporal.");
            mail.setDatos("Contraseña: " + contraseniaTemp);
            mail.setCuerpo("Ingresa con esta clave luego cambiala por tu seguridad");
            mail.setDespedida("Gracias");
            mail.setPie("En caso de que no hayas solicitado esta contraseña temporal notifica al administrador del sistema.");

            return this.enviarCorreoGeneral(mail);
        } catch (Exception e) {
            System.out.println("Se presentó un error al enviarCorreoRestablecerContrasenia del ControladorMail " + e.getLocalizedMessage());
            return false;
        }
    }
}
