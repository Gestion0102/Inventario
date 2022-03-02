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
import javax.persistence.Lob;
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
@Table(name = "centro_datos", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CentroDatos.findAll", query = "SELECT c FROM CentroDatos c")
    , @NamedQuery(name = "CentroDatos.findByIdCentrodatos", query = "SELECT c FROM CentroDatos c WHERE c.idCentrodatos = :idCentrodatos")
    , @NamedQuery(name = "CentroDatos.findByCodigoCentrodatos", query = "SELECT c FROM CentroDatos c WHERE c.codigoCentrodatos = :codigoCentrodatos")
    , @NamedQuery(name = "CentroDatos.findByNombreCentrodatos", query = "SELECT c FROM CentroDatos c WHERE c.nombreCentrodatos = :nombreCentrodatos")
    , @NamedQuery(name = "CentroDatos.findByDescripcionCentrodatos", query = "SELECT c FROM CentroDatos c WHERE c.descripcionCentrodatos = :descripcionCentrodatos")})
public class CentroDatos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CENTRODATOS")
    private Integer idCentrodatos;
    @Size(max = 8)
    @Column(name = "CODIGO_CENTRODATOS")
    private String codigoCentrodatos;
    @Size(max = 32)
    @Column(name = "NOMBRE_CENTRODATOS")
    private String nombreCentrodatos;
    @Size(max = 64)
    @Column(name = "DESCRIPCION_CENTRODATOS")
    private String descripcionCentrodatos;
    @Lob
    @Column(name = "IMAGEN_CENTRODATOS")
    private byte[] imagenCentrodatos;
    @OneToMany(mappedBy = "idCentrodatos")
    private List<Rack> rackList;

    public CentroDatos() {
    }

    public CentroDatos(Integer idCentrodatos) {
        this.idCentrodatos = idCentrodatos;
    }

    public Integer getIdCentrodatos() {
        return idCentrodatos;
    }

    public void setIdCentrodatos(Integer idCentrodatos) {
        this.idCentrodatos = idCentrodatos;
    }

    public String getCodigoCentrodatos() {
        return codigoCentrodatos;
    }

    public void setCodigoCentrodatos(String codigoCentrodatos) {
        this.codigoCentrodatos = codigoCentrodatos;
    }

    public String getNombreCentrodatos() {
        return nombreCentrodatos;
    }

    public void setNombreCentrodatos(String nombreCentrodatos) {
        this.nombreCentrodatos = nombreCentrodatos;
    }

    public String getDescripcionCentrodatos() {
        return descripcionCentrodatos;
    }

    public void setDescripcionCentrodatos(String descripcionCentrodatos) {
        this.descripcionCentrodatos = descripcionCentrodatos;
    }

    public byte[] getImagenCentrodatos() {
        return imagenCentrodatos;
    }

    public void setImagenCentrodatos(byte[] imagenCentrodatos) {
        this.imagenCentrodatos = imagenCentrodatos;
    }

    @XmlTransient
    public List<Rack> getRackList() {
        return rackList;
    }

    public void setRackList(List<Rack> rackList) {
        this.rackList = rackList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCentrodatos != null ? idCentrodatos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentroDatos)) {
            return false;
        }
        CentroDatos other = (CentroDatos) object;
        if ((this.idCentrodatos == null && other.idCentrodatos != null) || (this.idCentrodatos != null && !this.idCentrodatos.equals(other.idCentrodatos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.CentroDatos[ idCentrodatos=" + idCentrodatos + " ]";
    }

}
