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
@Table(name = "detalle_equipos_mantenimiento", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleEquiposMantenimiento.findAll", query = "SELECT d FROM DetalleEquiposMantenimiento d")
    , @NamedQuery(name = "DetalleEquiposMantenimiento.findByIdDetalleequipomantenimiento", query = "SELECT d FROM DetalleEquiposMantenimiento d WHERE d.idDetalleequipomantenimiento = :idDetalleequipomantenimiento")
    , @NamedQuery(name = "DetalleEquiposMantenimiento.findByIdMantenimiento", query = "SELECT d FROM DetalleEquiposMantenimiento d WHERE d.idMantenimiento = :idMantenimiento")
    , @NamedQuery(name = "DetalleEquiposMantenimiento.findByCodigoDetalleequipomantenimiento", query = "SELECT d FROM DetalleEquiposMantenimiento d WHERE d.codigoDetalleequipomantenimiento = :codigoDetalleequipomantenimiento")
    , @NamedQuery(name = "DetalleEquiposMantenimiento.findByCoberturaDetalleequipomantenimiento", query = "SELECT d FROM DetalleEquiposMantenimiento d WHERE d.coberturaDetalleequipomantenimiento = :coberturaDetalleequipomantenimiento")})
public class DetalleEquiposMantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DETALLEEQUIPOMANTENIMIENTO")
    private Integer idDetalleequipomantenimiento;
    @Size(max = 16)
    @Column(name = "CODIGO_DETALLEEQUIPOMANTENIMIENTO")
    private String codigoDetalleequipomantenimiento;
    @Size(max = 8)
    @Column(name = "COBERTURA_DETALLEEQUIPOMANTENIMIENTO")
    private String coberturaDetalleequipomantenimiento;
    @JoinColumn(name = "ID_EQUIPO", referencedColumnName = "ID_EQUIPO")
    @ManyToOne
    private Equipo idEquipo;
    @JoinColumn(name = "ID_MANTENIMIENTO", referencedColumnName = "ID_MANTENIMIENTO")
    @ManyToOne
    private Mantenimiento idMantenimiento;

    public DetalleEquiposMantenimiento() {
    }

    public DetalleEquiposMantenimiento(Integer idDetalleequipomantenimiento) {
        this.idDetalleequipomantenimiento = idDetalleequipomantenimiento;
    }

    public Integer getIdDetalleequipomantenimiento() {
        return idDetalleequipomantenimiento;
    }

    public void setIdDetalleequipomantenimiento(Integer idDetalleequipomantenimiento) {
        this.idDetalleequipomantenimiento = idDetalleequipomantenimiento;
    }

    public String getCodigoDetalleequipomantenimiento() {
        return codigoDetalleequipomantenimiento;
    }

    public void setCodigoDetalleequipomantenimiento(String codigoDetalleequipomantenimiento) {
        this.codigoDetalleequipomantenimiento = codigoDetalleequipomantenimiento;
    }

    public String getCoberturaDetalleequipomantenimiento() {
        return coberturaDetalleequipomantenimiento;
    }

    public void setCoberturaDetalleequipomantenimiento(String coberturaDetalleequipomantenimiento) {
        this.coberturaDetalleequipomantenimiento = coberturaDetalleequipomantenimiento;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Mantenimiento getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Mantenimiento idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleequipomantenimiento != null ? idDetalleequipomantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleEquiposMantenimiento)) {
            return false;
        }
        DetalleEquiposMantenimiento other = (DetalleEquiposMantenimiento) object;
        if ((this.idDetalleequipomantenimiento == null && other.idDetalleequipomantenimiento != null) || (this.idDetalleequipomantenimiento != null && !this.idDetalleequipomantenimiento.equals(other.idDetalleequipomantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleEquiposMantenimiento{" + "idDetalleequipomantenimiento=" + idDetalleequipomantenimiento + ", codigoDetalleequipomantenimiento=" + codigoDetalleequipomantenimiento + ", coberturaDetalleequipomantenimiento=" + coberturaDetalleequipomantenimiento + ", idEquipo=" + idEquipo + ", idMantenimiento=" + idMantenimiento + '}';
    }

}
