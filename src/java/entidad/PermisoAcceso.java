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
@Table(name = "permiso_acceso", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermisoAcceso.findAll", query = "SELECT p FROM PermisoAcceso p")
    , @NamedQuery(name = "PermisoAcceso.findByIdPermisoacceso", query = "SELECT p FROM PermisoAcceso p WHERE p.idPermisoacceso = :idPermisoacceso")
    , @NamedQuery(name = "PermisoAcceso.findByNombrePermisoacceso", query = "SELECT p FROM PermisoAcceso p WHERE p.nombrePermisoacceso = :nombrePermisoacceso")
    , @NamedQuery(name = "PermisoAcceso.findByCodigoPermisoacceso", query = "SELECT p FROM PermisoAcceso p WHERE p.codigoPermisoacceso = :codigoPermisoacceso")
    , @NamedQuery(name = "PermisoAcceso.findByDescripcionPermisoacceso", query = "SELECT p FROM PermisoAcceso p WHERE p.descripcionPermisoacceso = :descripcionPermisoacceso")
    , @NamedQuery(name = "PermisoAcceso.findByEstadoPermisoacceso", query = "SELECT p FROM PermisoAcceso p WHERE p.estadoPermisoacceso = :estadoPermisoacceso")
    , @NamedQuery(name = "PermisoAcceso.findBySoloverPermisoacceso", query = "SELECT p FROM PermisoAcceso p WHERE p.soloverPermisoacceso = :soloverPermisoacceso")})
public class PermisoAcceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERMISOACCESO")
    private Integer idPermisoacceso;
    @Size(max = 16)
    @Column(name = "NOMBRE_PERMISOACCESO")
    private String nombrePermisoacceso;
    @Size(max = 8)
    @Column(name = "CODIGO_PERMISOACCESO")
    private String codigoPermisoacceso;
    @Size(max = 32)
    @Column(name = "DESCRIPCION_PERMISOACCESO")
    private String descripcionPermisoacceso;
    @Column(name = "ESTADO_PERMISOACCESO")
    private Boolean estadoPermisoacceso;
    @Column(name = "SOLOVER_PERMISOACCESO")
    private Boolean soloverPermisoacceso;
    @JoinColumn(name = "ID_OPCIONSISTEMA", referencedColumnName = "ID_OPCIONSISTEMA")
    @ManyToOne
    private OpcionSistema idOpcionsistema;
    @JoinColumn(name = "ID_TIPOUSUARIO", referencedColumnName = "ID_TIPOUSUARIO")
    @ManyToOne
    private TipoUsuario idTipousuario;

    public PermisoAcceso() {
    }

    public PermisoAcceso(Integer idPermisoacceso) {
        this.idPermisoacceso = idPermisoacceso;
    }

    public Integer getIdPermisoacceso() {
        return idPermisoacceso;
    }

    public void setIdPermisoacceso(Integer idPermisoacceso) {
        this.idPermisoacceso = idPermisoacceso;
    }

    public String getNombrePermisoacceso() {
        return nombrePermisoacceso;
    }

    public void setNombrePermisoacceso(String nombrePermisoacceso) {
        this.nombrePermisoacceso = nombrePermisoacceso;
    }

    public String getCodigoPermisoacceso() {
        return codigoPermisoacceso;
    }

    public void setCodigoPermisoacceso(String codigoPermisoacceso) {
        this.codigoPermisoacceso = codigoPermisoacceso;
    }

    public String getDescripcionPermisoacceso() {
        return descripcionPermisoacceso;
    }

    public void setDescripcionPermisoacceso(String descripcionPermisoacceso) {
        this.descripcionPermisoacceso = descripcionPermisoacceso;
    }

    public Boolean getEstadoPermisoacceso() {
        return estadoPermisoacceso;
    }

    public void setEstadoPermisoacceso(Boolean estadoPermisoacceso) {
        this.estadoPermisoacceso = estadoPermisoacceso;
    }

    public Boolean getSoloverPermisoacceso() {
        return soloverPermisoacceso;
    }

    public void setSoloverPermisoacceso(Boolean soloverPermisoacceso) {
        this.soloverPermisoacceso = soloverPermisoacceso;
    }

    public OpcionSistema getIdOpcionsistema() {
        return idOpcionsistema;
    }

    public void setIdOpcionsistema(OpcionSistema idOpcionsistema) {
        this.idOpcionsistema = idOpcionsistema;
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
        hash += (idPermisoacceso != null ? idPermisoacceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermisoAcceso)) {
            return false;
        }
        PermisoAcceso other = (PermisoAcceso) object;
        if ((this.idPermisoacceso == null && other.idPermisoacceso != null) || (this.idPermisoacceso != null && !this.idPermisoacceso.equals(other.idPermisoacceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PermisoAcceso{" + "idPermisoacceso=" + idPermisoacceso + ", nombrePermisoacceso=" + nombrePermisoacceso + ", codigoPermisoacceso=" + codigoPermisoacceso + ", descripcionPermisoacceso=" + descripcionPermisoacceso + ", estadoPermisoacceso=" + estadoPermisoacceso + ", soloverPermisoacceso=" + soloverPermisoacceso + ", idOpcionsistema=" + idOpcionsistema + ", idTipousuario=" + idTipousuario + '}';
    }

}
