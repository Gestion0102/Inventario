/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "tipo_usuario", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsuario.findAll", query = "SELECT t FROM TipoUsuario t")
    , @NamedQuery(name = "TipoUsuario.findByIdTipousuario", query = "SELECT t FROM TipoUsuario t WHERE t.idTipousuario = :idTipousuario")
    , @NamedQuery(name = "TipoUsuario.findByCodigoTipousuario", query = "SELECT t FROM TipoUsuario t WHERE t.codigoTipousuario = :codigoTipousuario")
    , @NamedQuery(name = "TipoUsuario.findByNombreTipousuario", query = "SELECT t FROM TipoUsuario t WHERE t.nombreTipousuario = :nombreTipousuario")
    , @NamedQuery(name = "TipoUsuario.findByDescripcionTipousuario", query = "SELECT t FROM TipoUsuario t WHERE t.descripcionTipousuario = :descripcionTipousuario")})
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPOUSUARIO")
    private Integer idTipousuario;
    @Size(max = 16)
    @Column(name = "CODIGO_TIPOUSUARIO")
    private String codigoTipousuario;
    @Size(max = 32)
    @Column(name = "NOMBRE_TIPOUSUARIO")
    private String nombreTipousuario;
    @Size(max = 64)
    @Column(name = "DESCRIPCION_TIPOUSUARIO")
    private String descripcionTipousuario;
    @OneToMany(mappedBy = "idTipousuario")
    private List<PermisoAcceso> permisoAccesoList;
    @OneToMany(mappedBy = "idTipousuario")
    private List<Usuario> usuarioList;

    public TipoUsuario() {
    }

    public TipoUsuario(Integer idTipousuario) {
        this.idTipousuario = idTipousuario;
    }

    public Integer getIdTipousuario() {
        return idTipousuario;
    }

    public void setIdTipousuario(Integer idTipousuario) {
        this.idTipousuario = idTipousuario;
    }

    public String getCodigoTipousuario() {
        return codigoTipousuario;
    }

    public void setCodigoTipousuario(String codigoTipousuario) {
        this.codigoTipousuario = codigoTipousuario;
    }

    public String getNombreTipousuario() {
        return nombreTipousuario;
    }

    public void setNombreTipousuario(String nombreTipousuario) {
        this.nombreTipousuario = nombreTipousuario;
    }

    public String getDescripcionTipousuario() {
        return descripcionTipousuario;
    }

    public void setDescripcionTipousuario(String descripcionTipousuario) {
        this.descripcionTipousuario = descripcionTipousuario;
    }

    @XmlTransient
    public List<PermisoAcceso> getPermisoAccesoList() {
        return permisoAccesoList;
    }

    public void setPermisoAccesoList(List<PermisoAcceso> permisoAccesoList) {
        this.permisoAccesoList = permisoAccesoList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipousuario != null ? idTipousuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.idTipousuario == null && other.idTipousuario != null) || (this.idTipousuario != null && !this.idTipousuario.equals(other.idTipousuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoUsuario{" + "idTipousuario=" + idTipousuario + ", codigoTipousuario=" + codigoTipousuario + ", nombreTipousuario=" + nombreTipousuario + ", descripcionTipousuario=" + descripcionTipousuario + ", permisoAccesoList=" + permisoAccesoList + ", usuarioList=" + usuarioList + '}';
    }

}
