/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Elena Paola
 */
@Entity
@Table(name = "detalle_alarma", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleAlarma.findAll", query = "SELECT d FROM DetalleAlarma d")
    , @NamedQuery(name = "DetalleAlarma.findByIdDetallealarma", query = "SELECT d FROM DetalleAlarma d WHERE d.idDetallealarma = :idDetallealarma")
    , @NamedQuery(name = "DetalleAlarma.findByCodigoDetallealarma", query = "SELECT d FROM DetalleAlarma d WHERE d.codigoDetallealarma = :codigoDetallealarma")
    , @NamedQuery(name = "DetalleAlarma.findByNombreDetallealarma", query = "SELECT d FROM DetalleAlarma d WHERE d.nombreDetallealarma = :nombreDetallealarma")
    , @NamedQuery(name = "DetalleAlarma.findByDescripcionDetallealarma", query = "SELECT d FROM DetalleAlarma d WHERE d.descripcionDetallealarma = :descripcionDetallealarma")
    , @NamedQuery(name = "DetalleAlarma.findByFechaDetallealarma", query = "SELECT d FROM DetalleAlarma d WHERE d.fechaDetallealarma = :fechaDetallealarma")
    , @NamedQuery(name = "DetalleAlarma.findByTipoDetallealarma", query = "SELECT d FROM DetalleAlarma d WHERE d.tipoDetallealarma = :tipoDetallealarma")})
public class DetalleAlarma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DETALLEALARMA")
    private Integer idDetallealarma;
    @Size(max = 64)
    @Column(name = "CODIGO_DETALLEALARMA")
    private String codigoDetallealarma;
    @Size(max = 128)
    @Column(name = "NOMBRE_DETALLEALARMA")
    private String nombreDetallealarma;
    @Size(max = 2048)
    @Column(name = "DESCRIPCION_DETALLEALARMA")
    private String descripcionDetallealarma;
    @Column(name = "FECHA_DETALLEALARMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDetallealarma;
    @Size(max = 16)
    @Column(name = "TIPO_DETALLEALARMA")
    private String tipoDetallealarma;
    @Lob
    @Column(name = "IMAGEN_DETALLEALARMA")
    private byte[] imagenDetallealarma;
    @JoinColumn(name = "ID_ALARMA", referencedColumnName = "ID_ALARMA")
    @ManyToOne
    private AlarmaFisica idAlarma;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public DetalleAlarma() {
    }

    public DetalleAlarma(Integer idDetallealarma) {
        this.idDetallealarma = idDetallealarma;
    }

    public Integer getIdDetallealarma() {
        return idDetallealarma;
    }

    public void setIdDetallealarma(Integer idDetallealarma) {
        this.idDetallealarma = idDetallealarma;
    }

    public String getCodigoDetallealarma() {
        return codigoDetallealarma;
    }

    public void setCodigoDetallealarma(String codigoDetallealarma) {
        this.codigoDetallealarma = codigoDetallealarma;
    }

    public String getNombreDetallealarma() {
        return nombreDetallealarma;
    }

    public void setNombreDetallealarma(String nombreDetallealarma) {
        this.nombreDetallealarma = nombreDetallealarma;
    }

    public String getDescripcionDetallealarma() {
        return descripcionDetallealarma;
    }

    public void setDescripcionDetallealarma(String descripcionDetallealarma) {
        this.descripcionDetallealarma = descripcionDetallealarma;
    }

    public Date getFechaDetallealarma() {
        return fechaDetallealarma;
    }

    public void setFechaDetallealarma(Date fechaDetallealarma) {
        this.fechaDetallealarma = fechaDetallealarma;
    }

    public String getTipoDetallealarma() {
        return tipoDetallealarma;
    }

    public void setTipoDetallealarma(String tipoDetallealarma) {
        this.tipoDetallealarma = tipoDetallealarma;
    }

    public byte[] getImagenDetallealarma() {
        return imagenDetallealarma;
    }

    public void setImagenDetallealarma(byte[] imagenDetallealarma) {
        this.imagenDetallealarma = imagenDetallealarma;
    }

    public AlarmaFisica getIdAlarma() {
        return idAlarma;
    }

    public void setIdAlarma(AlarmaFisica idAlarma) {
        this.idAlarma = idAlarma;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetallealarma != null ? idDetallealarma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleAlarma)) {
            return false;
        }
        DetalleAlarma other = (DetalleAlarma) object;
        if ((this.idDetallealarma == null && other.idDetallealarma != null) || (this.idDetallealarma != null && !this.idDetallealarma.equals(other.idDetallealarma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.DetalleAlarma[ idDetallealarma=" + idDetallealarma + " ]";
    }

}
