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
@Table(name = "opcion_sistema", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcionSistema.findAll", query = "SELECT o FROM OpcionSistema o")
    , @NamedQuery(name = "OpcionSistema.findByIdOpcionsistema", query = "SELECT o FROM OpcionSistema o WHERE o.idOpcionsistema = :idOpcionsistema")
    , @NamedQuery(name = "OpcionSistema.findByCodigoOpcionsistema", query = "SELECT o FROM OpcionSistema o WHERE o.codigoOpcionsistema = :codigoOpcionsistema")
    , @NamedQuery(name = "OpcionSistema.findByNombreOpcionsistema", query = "SELECT o FROM OpcionSistema o WHERE o.nombreOpcionsistema = :nombreOpcionsistema")
    , @NamedQuery(name = "OpcionSistema.findByDescripcionOpcionsistema", query = "SELECT o FROM OpcionSistema o WHERE o.descripcionOpcionsistema = :descripcionOpcionsistema")
    , @NamedQuery(name = "OpcionSistema.findByUrlOpcionsistema", query = "SELECT o FROM OpcionSistema o WHERE o.urlOpcionsistema = :urlOpcionsistema")})
public class OpcionSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OPCIONSISTEMA")
    private Integer idOpcionsistema;
    @Size(max = 32)
    @Column(name = "CODIGO_OPCIONSISTEMA")
    private String codigoOpcionsistema;
    @Size(max = 32)
    @Column(name = "NOMBRE_OPCIONSISTEMA")
    private String nombreOpcionsistema;
    @Size(max = 64)
    @Column(name = "DESCRIPCION_OPCIONSISTEMA")
    private String descripcionOpcionsistema;
    @Size(max = 128)
    @Column(name = "URL_OPCIONSISTEMA")
    private String urlOpcionsistema;
    @Size(max = 32)
    @Column(name = "ICONO_OPCIONSISTEMA")
    private String iconoOpcionsistema;

    @OneToMany(mappedBy = "idOpcionsistema")
    private List<PermisoAcceso> permisoAccesoList;

    public OpcionSistema() {
    }

    public OpcionSistema(Integer idOpcionsistema) {
        this.idOpcionsistema = idOpcionsistema;
    }

    public Integer getIdOpcionsistema() {
        return idOpcionsistema;
    }

    public void setIdOpcionsistema(Integer idOpcionsistema) {
        this.idOpcionsistema = idOpcionsistema;
    }

    public String getCodigoOpcionsistema() {
        return codigoOpcionsistema;
    }

    public void setCodigoOpcionsistema(String codigoOpcionsistema) {
        this.codigoOpcionsistema = codigoOpcionsistema;
    }

    public String getNombreOpcionsistema() {
        return nombreOpcionsistema;
    }

    public void setNombreOpcionsistema(String nombreOpcionsistema) {
        this.nombreOpcionsistema = nombreOpcionsistema;
    }

    public String getDescripcionOpcionsistema() {
        return descripcionOpcionsistema;
    }

    public void setDescripcionOpcionsistema(String descripcionOpcionsistema) {
        this.descripcionOpcionsistema = descripcionOpcionsistema;
    }

    public String getUrlOpcionsistema() {
        return urlOpcionsistema;
    }

    public void setUrlOpcionsistema(String urlOpcionsistema) {
        this.urlOpcionsistema = urlOpcionsistema;
    }

    @XmlTransient
    public List<PermisoAcceso> getPermisoAccesoList() {
        return permisoAccesoList;
    }

    public void setPermisoAccesoList(List<PermisoAcceso> permisoAccesoList) {
        this.permisoAccesoList = permisoAccesoList;
    }

    public String getIconoOpcionsistema() {
        return iconoOpcionsistema;
    }

    public void setIconoOpcionsistema(String iconoOpcionsistema) {
        this.iconoOpcionsistema = iconoOpcionsistema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcionsistema != null ? idOpcionsistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionSistema)) {
            return false;
        }
        OpcionSistema other = (OpcionSistema) object;
        if ((this.idOpcionsistema == null && other.idOpcionsistema != null) || (this.idOpcionsistema != null && !this.idOpcionsistema.equals(other.idOpcionsistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.OpcionSistema[ idOpcionsistema=" + idOpcionsistema + " ]";
    }

}
