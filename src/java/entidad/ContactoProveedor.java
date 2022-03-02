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
 * @author Elena Paola
 */
@Entity
@Table(name = "contacto_proveedor", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactoProveedor.findAll", query = "SELECT c FROM ContactoProveedor c")
    , @NamedQuery(name = "ContactoProveedor.findByIdContactoproveedor", query = "SELECT c FROM ContactoProveedor c WHERE c.idContactoproveedor = :idContactoproveedor")
    , @NamedQuery(name = "ContactoProveedor.findByCodigoContactoproveedor", query = "SELECT c FROM ContactoProveedor c WHERE c.codigoContactoproveedor = :codigoContactoproveedor")
    , @NamedQuery(name = "ContactoProveedor.findByNombreContactoproveedor", query = "SELECT c FROM ContactoProveedor c WHERE c.nombreContactoproveedor = :nombreContactoproveedor")
    , @NamedQuery(name = "ContactoProveedor.findByCargoContactoproveedor", query = "SELECT c FROM ContactoProveedor c WHERE c.cargoContactoproveedor = :cargoContactoproveedor")
    , @NamedQuery(name = "ContactoProveedor.findByCelularContactoproveedor", query = "SELECT c FROM ContactoProveedor c WHERE c.celularContactoproveedor = :celularContactoproveedor")
    , @NamedQuery(name = "ContactoProveedor.findByCorreoContactoproveedor", query = "SELECT c FROM ContactoProveedor c WHERE c.correoContactoproveedor = :correoContactoproveedor")
    , @NamedQuery(name = "ContactoProveedor.findByEstadoContactoproveedor", query = "SELECT c FROM ContactoProveedor c WHERE c.estadoContactoproveedor = :estadoContactoproveedor")})
public class ContactoProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CONTACTOPROVEEDOR")
    private Integer idContactoproveedor;
    @Size(max = 16)
    @Column(name = "CODIGO_CONTACTOPROVEEDOR")
    private String codigoContactoproveedor;
    @Size(max = 64)
    @Column(name = "NOMBRE_CONTACTOPROVEEDOR")
    private String nombreContactoproveedor;
    @Size(max = 16)
    @Column(name = "CARGO_CONTACTOPROVEEDOR")
    private String cargoContactoproveedor;
    @Size(max = 12)
    @Column(name = "CELULAR_CONTACTOPROVEEDOR")
    private String celularContactoproveedor;
    @Size(max = 64)
    @Column(name = "CORREO_CONTACTOPROVEEDOR")
    private String correoContactoproveedor;
    @Column(name = "ESTADO_CONTACTOPROVEEDOR")
    private Boolean estadoContactoproveedor;
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne
    private Proveedor idProveedor;

    public ContactoProveedor() {
    }

    public ContactoProveedor(Integer idContactoproveedor) {
        this.idContactoproveedor = idContactoproveedor;
    }

    public Integer getIdContactoproveedor() {
        return idContactoproveedor;
    }

    public void setIdContactoproveedor(Integer idContactoproveedor) {
        this.idContactoproveedor = idContactoproveedor;
    }

    public String getCodigoContactoproveedor() {
        return codigoContactoproveedor;
    }

    public void setCodigoContactoproveedor(String codigoContactoproveedor) {
        this.codigoContactoproveedor = codigoContactoproveedor;
    }

    public String getNombreContactoproveedor() {
        return nombreContactoproveedor;
    }

    public void setNombreContactoproveedor(String nombreContactoproveedor) {
        this.nombreContactoproveedor = nombreContactoproveedor;
    }

    public String getCargoContactoproveedor() {
        return cargoContactoproveedor;
    }

    public void setCargoContactoproveedor(String cargoContactoproveedor) {
        this.cargoContactoproveedor = cargoContactoproveedor;
    }

    public String getCelularContactoproveedor() {
        return celularContactoproveedor;
    }

    public void setCelularContactoproveedor(String celularContactoproveedor) {
        this.celularContactoproveedor = celularContactoproveedor;
    }

    public String getCorreoContactoproveedor() {
        return correoContactoproveedor;
    }

    public void setCorreoContactoproveedor(String correoContactoproveedor) {
        this.correoContactoproveedor = correoContactoproveedor;
    }

    public Boolean getEstadoContactoproveedor() {
        return estadoContactoproveedor;
    }

    public void setEstadoContactoproveedor(Boolean estadoContactoproveedor) {
        this.estadoContactoproveedor = estadoContactoproveedor;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContactoproveedor != null ? idContactoproveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactoProveedor)) {
            return false;
        }
        ContactoProveedor other = (ContactoProveedor) object;
        if ((this.idContactoproveedor == null && other.idContactoproveedor != null) || (this.idContactoproveedor != null && !this.idContactoproveedor.equals(other.idContactoproveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.ContactoProveedor[ idContactoproveedor=" + idContactoproveedor + " ]";
    }

}
