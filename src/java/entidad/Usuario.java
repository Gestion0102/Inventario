/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByCodigoUsuario", query = "SELECT u FROM Usuario u WHERE u.codigoUsuario = :codigoUsuario")
    , @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Usuario.findByDescripcionUsuario", query = "SELECT u FROM Usuario u WHERE u.descripcionUsuario = :descripcionUsuario")
    , @NamedQuery(name = "Usuario.findByCedulaUsuario", query = "SELECT u FROM Usuario u WHERE u.cedulaUsuario = :cedulaUsuario")
    , @NamedQuery(name = "Usuario.findByRolUsuario", query = "SELECT u FROM Usuario u WHERE u.rolUsuario = :rolUsuario")
    , @NamedQuery(name = "Usuario.findByCorreoUsuario", query = "SELECT u FROM Usuario u WHERE u.correoUsuario = :correoUsuario")
    , @NamedQuery(name = "Usuario.findByContraseniaUsuario", query = "SELECT u FROM Usuario u WHERE u.contraseniaUsuario = :contraseniaUsuario")
    , @NamedQuery(name = "Usuario.findByContraseniaTemporalUsuario", query = "SELECT u FROM Usuario u WHERE u.contraseniaTemporalUsuario = :contraseniaTemporalUsuario")
    , @NamedQuery(name = "Usuario.findByEstadoUsuario", query = "SELECT u FROM Usuario u WHERE u.estadoUsuario = :estadoUsuario")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Size(max = 8)
    @Column(name = "CODIGO_USUARIO")
    private String codigoUsuario;
    @Size(max = 64)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Size(max = 64)
    @Column(name = "DESCRIPCION_USUARIO")
    private String descripcionUsuario;
    @Size(max = 16)
    @Column(name = "CEDULA_USUARIO")
    private String cedulaUsuario;
    @Size(max = 16)
    @Column(name = "ROL_USUARIO")
    private String rolUsuario;
    @Size(max = 128)
    @Column(name = "CORREO_USUARIO")
    private String correoUsuario;
    @Size(max = 1024)
    @Column(name = "CONTRASENIA_USUARIO")
    private String contraseniaUsuario;
    @Size(max = 1024)
    @Column(name = "CONTRASENIATEMPORAL_USUARIO")
    private String contraseniaTemporalUsuario;
    @Column(name = "ESTADO_USUARIO")
    private Boolean estadoUsuario;
    @Column(name = "CANTIDADINTENTOSLOGEO_USUARIO")
    private Integer cantidadIntentosLogeoUsuario;

    @JoinColumn(name = "ID_TIPOUSUARIO", referencedColumnName = "ID_TIPOUSUARIO")
    @ManyToOne
    private TipoUsuario idTipousuario;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDescripcionUsuario() {
        return descripcionUsuario;
    }

    public void setDescripcionUsuario(String descripcionUsuario) {
        this.descripcionUsuario = descripcionUsuario;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContraseniaUsuario() {
        return contraseniaUsuario;
    }

    public void setContraseniaUsuario(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }

    public String getContraseniaTemporalUsuario() {
        return contraseniaTemporalUsuario;
    }

    public void setContraseniaTemporalUsuario(String contraseniaTemporalUsuario) {
        this.contraseniaTemporalUsuario = contraseniaTemporalUsuario;
    }

    public Boolean getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Boolean estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public Integer getCantidadIntentosLogeoUsuario() {
        return cantidadIntentosLogeoUsuario;
    }

    public void setCantidadIntentosLogeoUsuario(Integer cantidadIntentosLogeoUsuario) {
        this.cantidadIntentosLogeoUsuario = cantidadIntentosLogeoUsuario;
    }

    public TipoUsuario getIdTipousuario() {
        return idTipousuario;
    }

    public void setIdTipousuario(TipoUsuario idTipousuario) {
        this.idTipousuario = idTipousuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", codigoUsuario=" + codigoUsuario + ", nombreUsuario=" + nombreUsuario + ", descripcionUsuario=" + descripcionUsuario + ", cedulaUsuario=" + cedulaUsuario + ", rolUsuario=" + rolUsuario + ", correoUsuario=" + correoUsuario + ", contraseniaUsuario=" + contraseniaUsuario + ", contraseniaTemporalUsuario=" + contraseniaTemporalUsuario + ", estadoUsuario=" + estadoUsuario + ", idTipousuario=" + idTipousuario + '}';
    }

}
