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
 * @author Elena
 */
@Entity
@Table(name = "log_movimientoequipo", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogMovimientoequipo.findAll", query = "SELECT l FROM LogMovimientoequipo l")
    , @NamedQuery(name = "LogMovimientoequipo.findByIdLogmovimientoequipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.idLogmovimientoequipo = :idLogmovimientoequipo")
    , @NamedQuery(name = "LogMovimientoequipo.findByTipocrudLogmovimientoequipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.tipocrudLogmovimientoequipo = :tipocrudLogmovimientoequipo")
    , @NamedQuery(name = "LogMovimientoequipo.findByFechaLogmovimientoequipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.fechaLogmovimientoequipo = :fechaLogmovimientoequipo")
    , @NamedQuery(name = "LogMovimientoequipo.findByIdMovimientoequipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.idMovimientoequipo = :idMovimientoequipo")
    , @NamedQuery(name = "LogMovimientoequipo.findByIdEquipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.idEquipo = :idEquipo")
    , @NamedQuery(name = "LogMovimientoequipo.findByIdUsuario", query = "SELECT l FROM LogMovimientoequipo l WHERE l.idUsuario = :idUsuario")
    , @NamedQuery(name = "LogMovimientoequipo.findByUsuIdUsuario", query = "SELECT l FROM LogMovimientoequipo l WHERE l.idUsuario = :UsuidUsuario")
    , @NamedQuery(name = "LogMovimientoequipo.findByCodigoLogmovimientoequipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.codigoLogmovimientoequipo = :codigoLogmovimientoequipo")
    , @NamedQuery(name = "LogMovimientoequipo.findByDescripcionLogmovimientoequipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.descripcionLogmovimientoequipo = :descripcionLogmovimientoequipo")
    , @NamedQuery(name = "LogMovimientoequipo.findByFechaMovimientoequipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.fechaMovimientoequipo = :fechaMovimientoequipo")
    , @NamedQuery(name = "LogMovimientoequipo.findByTipoLogmovimientoequipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.tipoLogmovimientoequipo = :tipoLogmovimientoequipo")
    , @NamedQuery(name = "LogMovimientoequipo.findBySitioinicialLogmovimientoequipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.sitioinicialLogmovimientoequipo = :sitioinicialLogmovimientoequipo")
    , @NamedQuery(name = "LogMovimientoequipo.findBySitiofinalLogmovimientoequipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.sitiofinalLogmovimientoequipo = :sitiofinalLogmovimientoequipo")
    , @NamedQuery(name = "LogMovimientoequipo.findByDescripcionresponsableLogmovimientoequipo", query = "SELECT l FROM LogMovimientoequipo l WHERE l.descripcionresponsableLogmovimientoequipo = :descripcionresponsableLogmovimientoequipo")})
public class LogMovimientoequipo implements Serializable {

    @Lob
    @Column(name = "IMAGEN_LOGMOVIMIENTOEQUIPO")
    private byte[] imagenLogmovimientoequipo;
    @Lob
    @Column(name = "DOCUMENTORESPALDO_LOGMOVIMIENTOEQUIPO")
    private byte[] documentorespaldoLogmovimientoequipo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LOGMOVIMIENTOEQUIPO")
    private Integer idLogmovimientoequipo;
    @Size(max = 16)
    @Column(name = "TIPOCRUD_LOGMOVIMIENTOEQUIPO")
    private String tipocrudLogmovimientoequipo;
    @Column(name = "FECHA_LOGMOVIMIENTOEQUIPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLogmovimientoequipo;

    @JoinColumn(name = "ID_MOVIMIENTOEQUIPO", referencedColumnName = "ID_MOVIMIENTOEQUIPO")
    @ManyToOne
    private MovimientofisicoEquipo idMovimientoequipo;
    @JoinColumn(name = "ID_EQUIPO", referencedColumnName = "ID_EQUIPO")
    @ManyToOne
    private Equipo idEquipo;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "USU_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario usuIdUsuario;

    @Size(max = 8)
    @Column(name = "CODIGO_LOGMOVIMIENTOEQUIPO")
    private String codigoLogmovimientoequipo;
    @Size(max = 128)
    @Column(name = "DESCRIPCION_LOGMOVIMIENTOEQUIPO")
    private String descripcionLogmovimientoequipo;
    @Column(name = "FECHA_MOVIMIENTOEQUIPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimientoequipo;
    @Size(max = 64)
    @Column(name = "TIPO_LOGMOVIMIENTOEQUIPO")
    private String tipoLogmovimientoequipo;
    @Size(max = 32)
    @Column(name = "SITIOINICIAL_LOGMOVIMIENTOEQUIPO")
    private String sitioinicialLogmovimientoequipo;
    @Size(max = 32)
    @Column(name = "SITIOFINAL_LOGMOVIMIENTOEQUIPO")
    private String sitiofinalLogmovimientoequipo;
    @Size(max = 32)
    @Column(name = "DESCRIPCIONRESPONSABLE_LOGMOVIMIENTOEQUIPO")
    private String descripcionresponsableLogmovimientoequipo;

    public LogMovimientoequipo() {
    }

    public LogMovimientoequipo(Integer idLogmovimientoequipo) {
        this.idLogmovimientoequipo = idLogmovimientoequipo;
    }

    public Integer getIdLogmovimientoequipo() {
        return idLogmovimientoequipo;
    }

    public void setIdLogmovimientoequipo(Integer idLogmovimientoequipo) {
        this.idLogmovimientoequipo = idLogmovimientoequipo;
    }

    public String getTipocrudLogmovimientoequipo() {
        return tipocrudLogmovimientoequipo;
    }

    public void setTipocrudLogmovimientoequipo(String tipocrudLogmovimientoequipo) {
        this.tipocrudLogmovimientoequipo = tipocrudLogmovimientoequipo;
    }

    public Date getFechaLogmovimientoequipo() {
        return fechaLogmovimientoequipo;
    }

    public void setFechaLogmovimientoequipo(Date fechaLogmovimientoequipo) {
        this.fechaLogmovimientoequipo = fechaLogmovimientoequipo;
    }

    public MovimientofisicoEquipo getIdMovimientoequipo() {
        return idMovimientoequipo;
    }

    public void setIdMovimientoequipo(MovimientofisicoEquipo idMovimientoequipo) {
        this.idMovimientoequipo = idMovimientoequipo;
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

    public String getCodigoLogmovimientoequipo() {
        return codigoLogmovimientoequipo;
    }

    public void setCodigoLogmovimientoequipo(String codigoLogmovimientoequipo) {
        this.codigoLogmovimientoequipo = codigoLogmovimientoequipo;
    }

    public String getDescripcionLogmovimientoequipo() {
        return descripcionLogmovimientoequipo;
    }

    public void setDescripcionLogmovimientoequipo(String descripcionLogmovimientoequipo) {
        this.descripcionLogmovimientoequipo = descripcionLogmovimientoequipo;
    }

    public Date getFechaMovimientoequipo() {
        return fechaMovimientoequipo;
    }

    public void setFechaMovimientoequipo(Date fechaMovimientoequipo) {
        this.fechaMovimientoequipo = fechaMovimientoequipo;
    }

    public String getTipoLogmovimientoequipo() {
        return tipoLogmovimientoequipo;
    }

    public void setTipoLogmovimientoequipo(String tipoLogmovimientoequipo) {
        this.tipoLogmovimientoequipo = tipoLogmovimientoequipo;
    }

    public String getSitioinicialLogmovimientoequipo() {
        return sitioinicialLogmovimientoequipo;
    }

    public void setSitioinicialLogmovimientoequipo(String sitioinicialLogmovimientoequipo) {
        this.sitioinicialLogmovimientoequipo = sitioinicialLogmovimientoequipo;
    }

    public String getSitiofinalLogmovimientoequipo() {
        return sitiofinalLogmovimientoequipo;
    }

    public void setSitiofinalLogmovimientoequipo(String sitiofinalLogmovimientoequipo) {
        this.sitiofinalLogmovimientoequipo = sitiofinalLogmovimientoequipo;
    }

    public String getDescripcionresponsableLogmovimientoequipo() {
        return descripcionresponsableLogmovimientoequipo;
    }

    public void setDescripcionresponsableLogmovimientoequipo(String descripcionresponsableLogmovimientoequipo) {
        this.descripcionresponsableLogmovimientoequipo = descripcionresponsableLogmovimientoequipo;
    }

    public byte[] getImagenLogmovimientoequipo() {
        return imagenLogmovimientoequipo;
    }

    public void setImagenLogmovimientoequipo(byte[] imagenLogmovimientoequipo) {
        this.imagenLogmovimientoequipo = imagenLogmovimientoequipo;
    }

    public byte[] getDocumentorespaldoLogmovimientoequipo() {
        return documentorespaldoLogmovimientoequipo;
    }

    public void setDocumentorespaldoLogmovimientoequipo(byte[] documentorespaldoLogmovimientoequipo) {
        this.documentorespaldoLogmovimientoequipo = documentorespaldoLogmovimientoequipo;
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
        hash += (idLogmovimientoequipo != null ? idLogmovimientoequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogMovimientoequipo)) {
            return false;
        }
        LogMovimientoequipo other = (LogMovimientoequipo) object;
        if ((this.idLogmovimientoequipo == null && other.idLogmovimientoequipo != null) || (this.idLogmovimientoequipo != null && !this.idLogmovimientoequipo.equals(other.idLogmovimientoequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.LogMovimientoequipo[ idLogmovimientoequipo=" + idLogmovimientoequipo + " ]";
    }

}
