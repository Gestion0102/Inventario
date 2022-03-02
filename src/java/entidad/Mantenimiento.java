/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m")
    , @NamedQuery(name = "Mantenimiento.findByIdMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.idMantenimiento = :idMantenimiento")
    , @NamedQuery(name = "Mantenimiento.findByCodigoMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.codigoMantenimiento = :codigoMantenimiento")
    , @NamedQuery(name = "Mantenimiento.findByNombreMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.nombreMantenimiento = :nombreMantenimiento")
    , @NamedQuery(name = "Mantenimiento.findByDescripcioncontratoMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.descripcioncontratoMantenimiento = :descripcioncontratoMantenimiento")
    , @NamedQuery(name = "Mantenimiento.findByFechainiciacoberturaMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.fechainiciacoberturaMantenimiento = :fechainiciacoberturaMantenimiento")
    , @NamedQuery(name = "Mantenimiento.findByFechafinalizacoberturaMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.fechafinalizacoberturaMantenimiento = :fechafinalizacoberturaMantenimiento")})
public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MANTENIMIENTO")
    private Integer idMantenimiento;
    @Size(max = 32)
    @Column(name = "CODIGO_MANTENIMIENTO")
    private String codigoMantenimiento;
    @Size(max = 128)
    @Column(name = "NOMBRE_MANTENIMIENTO")
    private String nombreMantenimiento;
    @Size(max = 1024)
    @Column(name = "DESCRIPCIONCONTRATO_MANTENIMIENTO")
    private String descripcioncontratoMantenimiento;
    @Column(name = "FECHAINICIACOBERTURA_MANTENIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechainiciacoberturaMantenimiento;
    @Column(name = "FECHAFINALIZACOBERTURA_MANTENIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechafinalizacoberturaMantenimiento;
    @Lob
    @Column(name = "DOCUMENTO_MANTENIMIENTO")
    private byte[] documentoMantenimiento;
    @OneToMany(mappedBy = "idMantenimiento")
    private List<DetalleEquiposMantenimiento> detalleEquiposMantenimientoList;
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne
    private Proveedor idProveedor;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public String getCodigoMantenimiento() {
        return codigoMantenimiento;
    }

    public void setCodigoMantenimiento(String codigoMantenimiento) {
        this.codigoMantenimiento = codigoMantenimiento;
    }

    public String getNombreMantenimiento() {
        return nombreMantenimiento;
    }

    public void setNombreMantenimiento(String nombreMantenimiento) {
        this.nombreMantenimiento = nombreMantenimiento;
    }

    public String getDescripcioncontratoMantenimiento() {
        return descripcioncontratoMantenimiento;
    }

    public void setDescripcioncontratoMantenimiento(String descripcioncontratoMantenimiento) {
        this.descripcioncontratoMantenimiento = descripcioncontratoMantenimiento;
    }

    public Date getFechainiciacoberturaMantenimiento() {
        return fechainiciacoberturaMantenimiento;
    }

    public void setFechainiciacoberturaMantenimiento(Date fechainiciacoberturaMantenimiento) {
        this.fechainiciacoberturaMantenimiento = fechainiciacoberturaMantenimiento;
    }

    public Date getFechafinalizacoberturaMantenimiento() {
        return fechafinalizacoberturaMantenimiento;
    }

    public void setFechafinalizacoberturaMantenimiento(Date fechafinalizacoberturaMantenimiento) {
        this.fechafinalizacoberturaMantenimiento = fechafinalizacoberturaMantenimiento;
    }

    public byte[] getDocumentoMantenimiento() {
        return documentoMantenimiento;
    }

    public void setDocumentoMantenimiento(byte[] documentoMantenimiento) {
        this.documentoMantenimiento = documentoMantenimiento;
    }

    @XmlTransient
    public List<DetalleEquiposMantenimiento> getDetalleEquiposMantenimientoList() {
        return detalleEquiposMantenimientoList;
    }

    public void setDetalleEquiposMantenimientoList(List<DetalleEquiposMantenimiento> detalleEquiposMantenimientoList) {
        this.detalleEquiposMantenimientoList = detalleEquiposMantenimientoList;
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
        hash += (idMantenimiento != null ? idMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.idMantenimiento == null && other.idMantenimiento != null) || (this.idMantenimiento != null && !this.idMantenimiento.equals(other.idMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Mantenimiento[ idMantenimiento=" + idMantenimiento + " ]";
    }

}
