package controlador_temp;

/**
 * Este objeto sirve para el envío de mails con esta estructura: saludo,
 * introduccion, datos, cuerpo, despedida, pie.
 *
 * @author
 * @since 2017-02-14
 */
public class Mail {

    private String dirigidoA;
    private String asunto;
    private String saludo;
    private String introduccion;
    private String datos;
    private String cuerpo;
    private String despedida;
    private String pie;

    // estructuraYContenidoMaill sirve para conformar la estructura del mail
    private String estructuraYContenidoMaill;

    public Mail(String dirigidoA, String asunto, String saludo, String introduccion, String datos, String cuerpo, String despedida, String pie) {
        this.dirigidoA = dirigidoA;
        this.asunto = asunto;
        this.saludo = saludo;
        this.introduccion = introduccion;
        this.datos = datos;
        this.cuerpo = cuerpo;
        this.despedida = despedida;
        this.pie = pie;
    }

    public Mail() {
    }

    public synchronized String getDirigidoA() {
        return dirigidoA;
    }

    public synchronized void setDirigidoA(String dirigidoA) {
        this.dirigidoA = dirigidoA;
    }

    public synchronized String getAsunto() {
        return asunto;
    }

    public synchronized void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public synchronized String getSaludo() {
        return saludo;
    }

    public synchronized void setSaludo(String saludo) {
        this.saludo = saludo;
    }

    public synchronized String getIntroduccion() {
        return introduccion;
    }

    public synchronized void setIntroduccion(String introduccion) {
        this.introduccion = introduccion;
    }

    public synchronized String getDatos() {
        return datos;
    }

    public synchronized void setDatos(String datos) {
        this.datos = datos;
    }

    public synchronized String getCuerpo() {
        return cuerpo;
    }

    public synchronized void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public synchronized String getDespedida() {
        return despedida;
    }

    public synchronized void setDespedida(String despedida) {
        this.despedida = despedida;
    }

    public synchronized String getPie() {
        return pie;
    }

    public synchronized void setPie(String pie) {
        this.pie = pie;
    }

    public synchronized String getEstructuraYContenidoMaill() {
        generarEstructuraYContenidoMail();
        return estructuraYContenidoMaill;
    }

    public synchronized String generarEstructuraYContenidoMail() {

        this.saludo = validarNulos(this.saludo);
        this.introduccion = validarNulos(this.introduccion);
        this.datos = validarNulos(this.datos);
        this.cuerpo = validarNulos(this.cuerpo);
        this.despedida = validarNulos(this.despedida);
        this.pie = validarNulos(this.pie);

        this.estructuraYContenidoMaill = this.saludo
                + this.introduccion
                + this.datos
                + this.cuerpo
                + this.despedida
                + this.pie + "\n\n" + "-------------------------------------------------------------";

        return this.estructuraYContenidoMaill;
    }

    private synchronized String validarNulos(String texto) {
        if (texto == null) {
            texto = "";
        } else {
            texto = "\n\n" + texto;
        }
        return texto;
    }

    @Override
    public synchronized String toString() {
        return "Mail{" + "dirigidoA=" + dirigidoA + ", asunto=" + asunto + ", saludo=" + saludo + ", introduccion=" + introduccion + ", datos=" + datos + ", cuerpo=" + cuerpo + ", despedida=" + despedida + ", pie=" + pie + ", mensaje=" + estructuraYContenidoMaill + '}';
    }

}
