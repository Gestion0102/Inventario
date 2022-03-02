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
import javax.persistence.Lob;
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
@Table(catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rack.findAll", query = "SELECT r FROM Rack r")
    , @NamedQuery(name = "Rack.findByIdRack", query = "SELECT r FROM Rack r WHERE r.idRack = :idRack")
    , @NamedQuery(name = "Rack.findByCodigoRack", query = "SELECT r FROM Rack r WHERE r.codigoRack = :codigoRack")
    , @NamedQuery(name = "Rack.findByNombreRack", query = "SELECT r FROM Rack r WHERE r.nombreRack = :nombreRack")
    , @NamedQuery(name = "Rack.findByDescripcionRack", query = "SELECT r FROM Rack r WHERE r.descripcionRack = :descripcionRack")
    , @NamedQuery(name = "Rack.findByMarcaRack", query = "SELECT r FROM Rack r WHERE r.marcaRack = :marcaRack")
    , @NamedQuery(name = "Rack.findByModeloRack", query = "SELECT r FROM Rack r WHERE r.modeloRack = :modeloRack")
    , @NamedQuery(name = "Rack.findBySerieRack", query = "SELECT r FROM Rack r WHERE r.serieRack = :serieRack")
    , @NamedQuery(name = "Rack.findByTipoRack", query = "SELECT r FROM Rack r WHERE r.tipoRack = :tipoRack")
    , @NamedQuery(name = "Rack.findByUbicaciongeograficaRack", query = "SELECT r FROM Rack r WHERE r.ubicaciongeograficaRack = :ubicaciongeograficaRack")})
public class Rack implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RACK")
    private Integer idRack;
    @Size(max = 8)
    @Column(name = "CODIGO_RACK")
    private String codigoRack;
    @Size(max = 16)
    @Column(name = "NOMBRE_RACK")
    private String nombreRack;
    @Size(max = 64)
    @Column(name = "DESCRIPCION_RACK")
    private String descripcionRack;
    @Size(max = 16)
    @Column(name = "MARCA_RACK")
    private String marcaRack;
    @Size(max = 16)
    @Column(name = "MODELO_RACK")
    private String modeloRack;
    @Size(max = 32)
    @Column(name = "SERIE_RACK")
    private String serieRack;
    @Size(max = 16)
    @Column(name = "TIPO_RACK")
    private String tipoRack;
    @Size(max = 4)
    @Column(name = "UBICACIONGEOGRAFICA_RACK")
    private String ubicaciongeograficaRack;
    @Lob
    @Column(name = "IMAGEN_RACK")
    private byte[] imagenRack;
    @JoinColumn(name = "ID_CENTRODATOS", referencedColumnName = "ID_CENTRODATOS")
    @ManyToOne
    private CentroDatos idCentrodatos;
    @OneToMany(mappedBy = "idRack")
    private List<Equipo> equipoList;

    public Rack() {
    }

    public Rack(Integer idRack) {
        this.idRack = idRack;
    }

    public Integer getIdRack() {
        return idRack;
    }

    public void setIdRack(Integer idRack) {
        this.idRack = idRack;
    }

    public String getCodigoRack() {
        return codigoRack;
    }

    public void setCodigoRack(String codigoRack) {
        this.codigoRack = codigoRack;
    }

    public String getNombreRack() {
        return nombreRack;
    }

    public void setNombreRack(String nombreRack) {
        this.nombreRack = nombreRack;
    }

    public String getDescripcionRack() {
        return descripcionRack;
    }

    public void setDescripcionRack(String descripcionRack) {
        this.descripcionRack = descripcionRack;
    }

    public String getMarcaRack() {
        return marcaRack;
    }

    public void setMarcaRack(String marcaRack) {
        this.marcaRack = marcaRack;
    }

    public String getModeloRack() {
        return modeloRack;
    }

    public void setModeloRack(String modeloRack) {
        this.modeloRack = modeloRack;
    }

    public String getSerieRack() {
        return serieRack;
    }

    public void setSerieRack(String serieRack) {
        this.serieRack = serieRack;
    }

    public String getTipoRack() {
        return tipoRack;
    }

    public void setTipoRack(String tipoRack) {
        this.tipoRack = tipoRack;
    }

    public String getUbicaciongeograficaRack() {
        return ubicaciongeograficaRack;
    }

    public void setUbicaciongeograficaRack(String ubicaciongeograficaRack) {
        this.ubicaciongeograficaRack = ubicaciongeograficaRack;
    }

    public byte[] getImagenRack() {
        return imagenRack;
    }

    public void setImagenRack(byte[] imagenRack) {
        this.imagenRack = imagenRack;
    }

    public CentroDatos getIdCentrodatos() {
        return idCentrodatos;
    }

    public void setIdCentrodatos(CentroDatos idCentrodatos) {
        this.idCentrodatos = idCentrodatos;
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
        hash += (idRack != null ? idRack.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rack)) {
            return false;
        }
        Rack other = (Rack) object;
        if ((this.idRack == null && other.idRack != null) || (this.idRack != null && !this.idRack.equals(other.idRack))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Rack[ idRack=" + idRack + " ]";
    }

}
