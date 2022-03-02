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
@Table(name = "movimientofisico_equipo", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimientofisicoEquipo.findAll", query = "SELECT m FROM MovimientofisicoEquipo m")
    , @NamedQuery(name = "MovimientofisicoEquipo.findByIdMovimientoequipo", query = "SELECT m FROM MovimientofisicoEquipo m WHERE m.idMovimientoequipo = :idMovimientoequipo")
    , @NamedQuery(name = "MovimientofisicoEquipo.findByCodigoMovimientoequipo", query = "SELECT m FROM MovimientofisicoEquipo m WHERE m.codigoMovimientoequipo = :codigoMovimientoequipo")
    , @NamedQuery(name = "MovimientofisicoEquipo.findByDescripcionMovimientoequipo", query = "SELECT m FROM MovimientofisicoEquipo m WHERE m.descripcionMovimientoequipo = :descripcionMovimientoequipo")
    , @NamedQuery(name = "MovimientofisicoEquipo.findByFechaMovimientoequipo", query = "SELECT m FROM MovimientofisicoEquipo m WHERE m.fechaMovimientoequipo = :fechaMovimientoequipo")
    , @NamedQuery(name = "MovimientofisicoEquipo.findByTipoMovimientoequipo", query = "SELECT m FROM MovimientofisicoEquipo m WHERE m.tipoMovimientoequipo = :tipoMovimientoequipo")
    , @NamedQuery(name = "MovimientofisicoEquipo.findBySitioinicialMovimientoequipo", query = "SELECT m FROM MovimientofisicoEquipo m WHERE m.sitioinicialMovimientoequipo = :sitioinicialMovimientoequipo")
    , @NamedQuery(name = "MovimientofisicoEquipo.findBySitiofinalMovimientoequipo", query = "SELECT m FROM MovimientofisicoEquipo m WHERE m.sitiofinalMovimientoequipo = :sitiofinalMovimientoequipo")
    , @NamedQuery(name = "MovimientofisicoEquipo.findByDescripcionresponsableMovimientoequipo", query = "SELECT m FROM MovimientofisicoEquipo m WHERE m.descripcionresponsableMovimientoequipo = :descripcionresponsableMovimientoequipo")})
public class MovimientofisicoEquipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MOVIMIENTOEQUIPO")
    private Integer idMovimientoequipo;
    @Size(max = 8)
    @Column(name = "CODIGO_MOVIMIENTOEQUIPO")
    private String codigoMovimientoequipo;
    @Size(max = 128)
    @Column(name = "DESCRIPCION_MOVIMIENTOEQUIPO")
    private String descripcionMovimientoequipo;
    @Column(name = "FECHA_MOVIMIENTOEQUIPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimientoequipo;
    @Size(max = 64)
    @Column(name = "TIPO_MOVIMIENTOEQUIPO")
    private String tipoMovimientoequipo;
    @Size(max = 64)
    @Column(name = "SITIOINICIAL_MOVIMIENTOEQUIPO")
    private String sitioinicialMovimientoequipo;
    @Size(max = 64)
    @Column(name = "SITIOFINAL_MOVIMIENTOEQUIPO")
    private String sitiofinalMovimientoequipo;
    @Size(max = 32)
    @Column(name = "DESCRIPCIONRESPONSABLE_MOVIMIENTOEQUIPO")
    private String descripcionresponsableMovimientoequipo;
    @Lob
    @Column(name = "IMAGEN_MOVIMIENTOEQUIPO")
    private byte[] imagenMovimientoequipo;
    @Lob
    @Column(name = "DOCUMENTORESPALDO_MOVIMIENTOEQUIPO")
    private byte[] documentorespaldoMovimientoequipo;
    @JoinColumn(name = "ID_EQUIPO", referencedColumnName = "ID_EQUIPO")
    @ManyToOne
    private Equipo idEquipo;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "USU_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario usuIdUsuario;

    public MovimientofisicoEquipo() {
    }

    public MovimientofisicoEquipo(Integer idMovimientoequipo) {
        this.idMovimientoequipo = idMovimientoequipo;
    }

    public Integer getIdMovimientoequipo() {
        return idMovimientoequipo;
    }

    public void setIdMovimientoequipo(Integer idMovimientoequipo) {
        this.idMovimientoequipo = idMovimientoequipo;
    }

    public String getCodigoMovimientoequipo() {
        return codigoMovimientoequipo;
    }

    public void setCodigoMovimientoequipo(String codigoMovimientoequipo) {
        this.codigoMovimientoequipo = codigoMovimientoequipo;
    }

    public String getDescripcionMovimientoequipo() {
        return descripcionMovimientoequipo;
    }

    public void setDescripcionMovimientoequipo(String descripcionMovimientoequipo) {
        this.descripcionMovimientoequipo = descripcionMovimientoequipo;
    }

    public Date getFechaMovimientoequipo() {
        return fechaMovimientoequipo;
    }

    public void setFechaMovimientoequipo(Date fechaMovimientoequipo) {
        this.fechaMovimientoequipo = fechaMovimientoequipo;
    }

    public String getTipoMovimientoequipo() {
        return tipoMovimientoequipo;
    }

    public void setTipoMovimientoequipo(String tipoMovimientoequipo) {
        this.tipoMovimientoequipo = tipoMovimientoequipo;
    }

    public String getSitioinicialMovimientoequipo() {
        return sitioinicialMovimientoequipo;
    }

    public void setSitioinicialMovimientoequipo(String sitioinicialMovimientoequipo) {
        this.sitioinicialMovimientoequipo = sitioinicialMovimientoequipo;
    }

    public String getSitiofinalMovimientoequipo() {
        return sitiofinalMovimientoequipo;
    }

    public void setSitiofinalMovimientoequipo(String sitiofinalMovimientoequipo) {
        this.sitiofinalMovimientoequipo = sitiofinalMovimientoequipo;
    }

    public String getDescripcionresponsableMovimientoequipo() {
        return descripcionresponsableMovimientoequipo;
    }

    public void setDescripcionresponsableMovimientoequipo(String descripcionresponsableMovimientoequipo) {
        this.descripcionresponsableMovimientoequipo = descripcionresponsableMovimientoequipo;
    }

    public byte[] getImagenMovimientoequipo() {
        return imagenMovimientoequipo;
    }

    public void setImagenMovimientoequipo(byte[] imagenMovimientoequipo) {
        this.imagenMovimientoequipo = imagenMovimientoequipo;
    }

    public byte[] getDocumentorespaldoMovimientoequipo() {
        return documentorespaldoMovimientoequipo;
    }

    public void setDocumentorespaldoMovimientoequipo(byte[] documentorespaldoMovimientoequipo) {
        this.documentorespaldoMovimientoequipo = documentorespaldoMovimientoequipo;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuIdUsuario() {
        return usuIdUsuario;
    }

    public void setUsuIdUsuario(Usuario usuIdUsuario) {
        this.usuIdUsuario = usuIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimientoequipo != null ? idMovimientoequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientofisicoEquipo)) {
            return false;
        }
        MovimientofisicoEquipo other = (MovimientofisicoEquipo) object;
        if ((this.idMovimientoequipo == null && other.idMovimientoequipo != null) || (this.idMovimientoequipo != null && !this.idMovimientoequipo.equals(other.idMovimientoequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.MovimientofisicoEquipo[ idMovimientoequipo=" + idMovimientoequipo + " ]";
    }

}
