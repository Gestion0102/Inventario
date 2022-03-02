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
 * @author Elena Paola
 */
@Entity
@Table(catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
    , @NamedQuery(name = "Proveedor.findByIdProveedor", query = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor")
    , @NamedQuery(name = "Proveedor.findByCodigoProveedor", query = "SELECT p FROM Proveedor p WHERE p.codigoProveedor = :codigoProveedor")
    , @NamedQuery(name = "Proveedor.findByNombreProveedor", query = "SELECT p FROM Proveedor p WHERE p.nombreProveedor = :nombreProveedor")
    , @NamedQuery(name = "Proveedor.findByDescripcionProveedor", query = "SELECT p FROM Proveedor p WHERE p.descripcionProveedor = :descripcionProveedor")
    , @NamedQuery(name = "Proveedor.findByDireccionProveedor", query = "SELECT p FROM Proveedor p WHERE p.direccionProveedor = :direccionProveedor")
    , @NamedQuery(name = "Proveedor.findByTelefonoProveedor", query = "SELECT p FROM Proveedor p WHERE p.telefonoProveedor = :telefonoProveedor")
    , @NamedQuery(name = "Proveedor.findByTipoProveedor", query = "SELECT p FROM Proveedor p WHERE p.tipoProveedor = :tipoProveedor")
    , @NamedQuery(name = "Proveedor.findByEstadoProveedor", query = "SELECT p FROM Proveedor p WHERE p.estadoProveedor = :estadoProveedor")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;
    @Size(max = 16)
    @Column(name = "CODIGO_PROVEEDOR")
    private String codigoProveedor;
    @Size(max = 32)
    @Column(name = "NOMBRE_PROVEEDOR")
    private String nombreProveedor;
    @Size(max = 256)
    @Column(name = "DESCRIPCION_PROVEEDOR")
    private String descripcionProveedor;
    @Size(max = 256)
    @Column(name = "DIRECCION_PROVEEDOR")
    private String direccionProveedor;
    @Size(max = 16)
    @Column(name = "TELEFONO_PROVEEDOR")
    private String telefonoProveedor;
    @Size(max = 16)
    @Column(name = "TIPO_PROVEEDOR")
    private String tipoProveedor;
    @Column(name = "ESTADO_PROVEEDOR")
    private Boolean estadoProveedor;
    @OneToMany(mappedBy = "idProveedor")
    private List<ContactoProveedor> contactoProveedorList;
    @OneToMany(mappedBy = "idProveedor")
    private List<AlarmaFisica> alarmaFisicaList;
    @OneToMany(mappedBy = "idProveedor")
    private List<Mantenimiento> mantenimientoList;
    @OneToMany(mappedBy = "idProveedor")
    private List<Equipo> equipoList;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(String codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getDescripcionProveedor() {
        return descripcionProveedor;
    }

    public void setDescripcionProveedor(String descripcionProveedor) {
        this.descripcionProveedor = descripcionProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public Boolean getEstadoProveedor() {
        return estadoProveedor;
    }

    public void setEstadoProveedor(Boolean estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }

    @XmlTransient
    public List<ContactoProveedor> getContactoProveedorList() {
        return contactoProveedorList;
    }

    public void setContactoProveedorList(List<ContactoProveedor> contactoProveedorList) {
        this.contactoProveedorList = contactoProveedorList;
    }

    @XmlTransient
    public List<AlarmaFisica> getAlarmaFisicaList() {
        return alarmaFisicaList;
    }

    public void setAlarmaFisicaList(List<AlarmaFisica> alarmaFisicaList) {
        this.alarmaFisicaList = alarmaFisicaList;
    }

    @XmlTransient
    public List<Mantenimiento> getMantenimientoList() {
        return mantenimientoList;
    }

    public void setMantenimientoList(List<Mantenimiento> mantenimientoList) {
        this.mantenimientoList = mantenimientoList;
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
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Proveedor[ idProveedor=" + idProveedor + " ]";
    }

}
