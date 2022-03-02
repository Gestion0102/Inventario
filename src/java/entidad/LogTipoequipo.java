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
@Table(name = "log_tipoequipo", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogTipoequipo.findAll", query = "SELECT l FROM LogTipoequipo l")
    , @NamedQuery(name = "LogTipoequipo.findByIdLogtipoequipo", query = "SELECT l FROM LogTipoequipo l WHERE l.idLogtipoequipo = :idLogtipoequipo")
    , @NamedQuery(name = "LogTipoequipo.findByTipocrudLogtipoequipo", query = "SELECT l FROM LogTipoequipo l WHERE l.tipocrudLogtipoequipo = :tipocrudLogtipoequipo")
    , @NamedQuery(name = "LogTipoequipo.findByFechaLogtipoequipo", query = "SELECT l FROM LogTipoequipo l WHERE l.fechaLogtipoequipo = :fechaLogtipoequipo")
    , @NamedQuery(name = "LogTipoequipo.findByIdTipoequipo", query = "SELECT l FROM LogTipoequipo l WHERE l.idTipoequipo = :idTipoequipo")
    , @NamedQuery(name = "LogTipoequipo.findByIdUsuario", query = "SELECT l FROM LogTipoequipo l WHERE l.idUsuario = :idUsuario")
    , @NamedQuery(name = "LogTipoequipo.findByCodigoLogtipoequipo", query = "SELECT l FROM LogTipoequipo l WHERE l.codigoLogtipoequipo = :codigoLogtipoequipo")
    , @NamedQuery(name = "LogTipoequipo.findByNombreLogtipoequipo", query = "SELECT l FROM LogTipoequipo l WHERE l.nombreLogtipoequipo = :nombreLogtipoequipo")
    , @NamedQuery(name = "LogTipoequipo.findByDescripcionLogtipoequipo", query = "SELECT l FROM LogTipoequipo l WHERE l.descripcionLogtipoequipo = :descripcionLogtipoequipo")})
public class LogTipoequipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LOGTIPOEQUIPO")
    private Integer idLogtipoequipo;
    @Size(max = 16)
    @Column(name = "TIPOCRUD_LOGTIPOEQUIPO")
    private String tipocrudLogtipoequipo;
    @Column(name = "FECHA_LOGTIPOEQUIPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLogtipoequipo;

    @JoinColumn(name = "ID_TIPOEQUIPO", referencedColumnName = "ID_TIPOEQUIPO")
    @ManyToOne
    private TipoEquipo idTipoequipo;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    @Size(max = 16)
    @Column(name = "CODIGO_LOGTIPOEQUIPO")
    private String codigoLogtipoequipo;
    @Size(max = 32)
    @Column(name = "NOMBRE_LOGTIPOEQUIPO")
    private String nombreLogtipoequipo;
    @Size(max = 64)
    @Column(name = "DESCRIPCION_LOGTIPOEQUIPO")
    private String descripcionLogtipoequipo;

    public LogTipoequipo() {
    }

    public LogTipoequipo(Integer idLogtipoequipo) {
        this.idLogtipoequipo = idLogtipoequipo;
    }

    public Integer getIdLogtipoequipo() {
        return idLogtipoequipo;
    }

    public void setIdLogtipoequipo(Integer idLogtipoequipo) {
        this.idLogtipoequipo = idLogtipoequipo;
    }

    public String getTipocrudLogtipoequipo() {
        return tipocrudLogtipoequipo;
    }

    public void setTipocrudLogtipoequipo(String tipocrudLogtipoequipo) {
        this.tipocrudLogtipoequipo = tipocrudLogtipoequipo;
    }

    public Date getFechaLogtipoequipo() {
        return fechaLogtipoequipo;
    }

    public void setFechaLogtipoequipo(Date fechaLogtipoequipo) {
        this.fechaLogtipoequipo = fechaLogtipoequipo;
    }

    public TipoEquipo getIdTipoequipo() {
        return idTipoequipo;
    }

    public void setIdTipoequipo(TipoEquipo idTipoequipo) {
        this.idTipoequipo = idTipoequipo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigoLogtipoequipo() {
        return codigoLogtipoequipo;
    }

    public void setCodigoLogtipoequipo(String codigoLogtipoequipo) {
        this.codigoLogtipoequipo = codigoLogtipoequipo;
    }

    public String getNombreLogtipoequipo() {
        return nombreLogtipoequipo;
    }

    public void setNombreLogtipoequipo(String nombreLogtipoequipo) {
        this.nombreLogtipoequipo = nombreLogtipoequipo;
    }

    public String getDescripcionLogtipoequipo() {
        return descripcionLogtipoequipo;
    }

    public void setDescripcionLogtipoequipo(String descripcionLogtipoequipo) {
        this.descripcionLogtipoequipo = descripcionLogtipoequipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogtipoequipo != null ? idLogtipoequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogTipoequipo)) {
            return false;
        }
        LogTipoequipo other = (LogTipoequipo) object;
        if ((this.idLogtipoequipo == null && other.idLogtipoequipo != null) || (this.idLogtipoequipo != null && !this.idLogtipoequipo.equals(other.idLogtipoequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.LogTipoequipo[ idLogtipoequipo=" + idLogtipoequipo + " ]";
    }

}
