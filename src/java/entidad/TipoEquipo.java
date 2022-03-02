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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elena Paola
 */
@Entity
@Table(name = "tipo_equipo", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEquipo.findAll", query = "SELECT t FROM TipoEquipo t")
    , @NamedQuery(name = "TipoEquipo.findByIdTipoequipo", query = "SELECT t FROM TipoEquipo t WHERE t.idTipoequipo = :idTipoequipo")
    , @NamedQuery(name = "TipoEquipo.findByCodigoTipoequipo", query = "SELECT t FROM TipoEquipo t WHERE t.codigoTipoequipo = :codigoTipoequipo")
    , @NamedQuery(name = "TipoEquipo.findByNombreTipoequipo", query = "SELECT t FROM TipoEquipo t WHERE t.nombreTipoequipo = :nombreTipoequipo")
    , @NamedQuery(name = "TipoEquipo.findByDescripcionTipoequipo", query = "SELECT t FROM TipoEquipo t WHERE t.descripcionTipoequipo = :descripcionTipoequipo")})
public class TipoEquipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPOEQUIPO")
    private Integer idTipoequipo;
    @Size(max = 16)
    @Column(name = "CODIGO_TIPOEQUIPO")
    private String codigoTipoequipo;
    @Size(max = 32)
    @Column(name = "NOMBRE_TIPOEQUIPO")
    private String nombreTipoequipo;
    @Size(max = 64)
    @Column(name = "DESCRIPCION_TIPOEQUIPO")
    private String descripcionTipoequipo;
    @Column(name = "ESVIRTUALIZADOR_TIPOEQUIPO")
    private Boolean esVirtualizadorTipoequipo;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idTipoequipo")
    private List<Equipo> equipoList;

    public TipoEquipo() {
    }

    public TipoEquipo(Integer idTipoequipo) {
        this.idTipoequipo = idTipoequipo;
    }

    public Integer getIdTipoequipo() {
        return idTipoequipo;
    }

    public void setIdTipoequipo(Integer idTipoequipo) {
        this.idTipoequipo = idTipoequipo;
    }

    public String getCodigoTipoequipo() {
        return codigoTipoequipo;
    }

    public void setCodigoTipoequipo(String codigoTipoequipo) {
        this.codigoTipoequipo = codigoTipoequipo;
    }

    public String getNombreTipoequipo() {
        return nombreTipoequipo;
    }

    public void setNombreTipoequipo(String nombreTipoequipo) {
        this.nombreTipoequipo = nombreTipoequipo;
    }

    public String getDescripcionTipoequipo() {
        return descripcionTipoequipo;
    }

    public void setDescripcionTipoequipo(String descripcionTipoequipo) {
        this.descripcionTipoequipo = descripcionTipoequipo;
    }

    public Boolean getEsVirtualizadorTipoequipo() {
        return esVirtualizadorTipoequipo;
    }

    public void setEsVirtualizadorTipoequipo(Boolean esVirtualizadorTipoequipo) {
        this.esVirtualizadorTipoequipo = esVirtualizadorTipoequipo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoequipo != null ? idTipoequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEquipo)) {
            return false;
        }
        TipoEquipo other = (TipoEquipo) object;
        if ((this.idTipoequipo == null && other.idTipoequipo != null) || (this.idTipoequipo != null && !this.idTipoequipo.equals(other.idTipoequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.TipoEquipo[ idTipoequipo=" + idTipoequipo + ", nombreTipoEquipo" + nombreTipoequipo + ", esVirtualizador" + esVirtualizadorTipoequipo + " ]";
    }

}
