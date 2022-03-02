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
@Table(name = "alarma_fisica", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlarmaFisica.findAll", query = "SELECT a FROM AlarmaFisica a")
    , @NamedQuery(name = "AlarmaFisica.findByIdAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.idAlarma = :idAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByCodigoAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.codigoAlarma = :codigoAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByDescripcionAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.descripcionAlarma = :descripcionAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByFechainiciaAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.fechainiciaAlarma = :fechainiciaAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByFechafinalizaAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.fechafinalizaAlarma = :fechafinalizaAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByFecharegistroAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.fecharegistroAlarma = :fecharegistroAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByNumerocasoAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.numerocasoAlarma = :numerocasoAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByEstaequipoalarmadoAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.estaequipoalarmadoAlarma = :estaequipoalarmadoAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByTienecomponentealarmadoAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.tienecomponentealarmadoAlarma = :tienecomponentealarmadoAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByDescripcioncomponentealarmadoAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.descripcioncomponentealarmadoAlarma = :descripcioncomponentealarmadoAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByEstadoactualAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.estadoactualAlarma = :estadoactualAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByMostrarmonitoreodiarioAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.mostrarmonitoreodiarioAlarma = :mostrarmonitoreodiarioAlarma")
    , @NamedQuery(name = "AlarmaFisica.findByObservacionAlarma", query = "SELECT a FROM AlarmaFisica a WHERE a.observacionAlarma = :observacionAlarma")})
public class AlarmaFisica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ALARMA")
    private Integer idAlarma;
    @Size(max = 64)
    @Column(name = "CODIGO_ALARMA")
    private String codigoAlarma;
    @Size(max = 2048)
    @Column(name = "DESCRIPCION_ALARMA")
    private String descripcionAlarma;
    @Column(name = "FECHAINICIA_ALARMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainiciaAlarma;
    @Column(name = "FECHAFINALIZA_ALARMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafinalizaAlarma;
    @Column(name = "FECHAREGISTRO_ALARMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistroAlarma;
    @Size(max = 16)
    @Column(name = "NUMEROCASO_ALARMA")
    private String numerocasoAlarma;
    @Column(name = "ESTAEQUIPOALARMADO_ALARMA")
    private Boolean estaequipoalarmadoAlarma;
    @Column(name = "TIENECOMPONENTEALARMADO_ALARMA")
    private Boolean tienecomponentealarmadoAlarma;
    @Size(max = 512)
    @Column(name = "DESCRIPCIONCOMPONENTEALARMADO_ALARMA")
    private String descripcioncomponentealarmadoAlarma;
    @Column(name = "ESTADOACTUAL_ALARMA")
    private Short estadoactualAlarma;
    @Lob
    @Column(name = "IMAGENUNO_ALARMA")
    private byte[] imagenunoAlarma;
    @Lob
    @Column(name = "IMAGENDOS_ALARMA")
    private byte[] imagendosAlarma;
    @Column(name = "MOSTRARMONITOREODIARIO_ALARMA")
    private Boolean mostrarmonitoreodiarioAlarma;
    @Size(max = 64)
    @Column(name = "OBSERVACION_ALARMA")
    private String observacionAlarma;
    @JoinColumn(name = "ID_EQUIPO", referencedColumnName = "ID_EQUIPO")
    @ManyToOne
    private Equipo idEquipo;
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne
    private Proveedor idProveedor;
    @JoinColumn(name = "USU_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario usuIdUsuario;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idAlarma")
    private List<DetalleAlarma> detalleAlarmaList;

    public AlarmaFisica() {
    }

    public AlarmaFisica(Integer idAlarma) {
        this.idAlarma = idAlarma;
    }

    public Integer getIdAlarma() {
        return idAlarma;
    }

    public void setIdAlarma(Integer idAlarma) {
        this.idAlarma = idAlarma;
    }

    public String getCodigoAlarma() {
        return codigoAlarma;
    }

    public void setCodigoAlarma(String codigoAlarma) {
        this.codigoAlarma = codigoAlarma;
    }

    public String getDescripcionAlarma() {
        return descripcionAlarma;
    }

    public void setDescripcionAlarma(String descripcionAlarma) {
        this.descripcionAlarma = descripcionAlarma;
    }

    public Date getFechainiciaAlarma() {
        return fechainiciaAlarma;
    }

    public void setFechainiciaAlarma(Date fechainiciaAlarma) {
        this.fechainiciaAlarma = fechainiciaAlarma;
    }

    public Date getFechafinalizaAlarma() {
        return fechafinalizaAlarma;
    }

    public void setFechafinalizaAlarma(Date fechafinalizaAlarma) {
        this.fechafinalizaAlarma = fechafinalizaAlarma;
    }

    public Date getFecharegistroAlarma() {
        return fecharegistroAlarma;
    }

    public void setFecharegistroAlarma(Date fecharegistroAlarma) {
        this.fecharegistroAlarma = fecharegistroAlarma;
    }

    public String getNumerocasoAlarma() {
        return numerocasoAlarma;
    }

    public void setNumerocasoAlarma(String numerocasoAlarma) {
        this.numerocasoAlarma = numerocasoAlarma;
    }

    public Boolean getEstaequipoalarmadoAlarma() {
        return estaequipoalarmadoAlarma;
    }

    public void setEstaequipoalarmadoAlarma(Boolean estaequipoalarmadoAlarma) {
        this.estaequipoalarmadoAlarma = estaequipoalarmadoAlarma;
    }

    public Boolean getTienecomponentealarmadoAlarma() {
        return tienecomponentealarmadoAlarma;
    }

    public void setTienecomponentealarmadoAlarma(Boolean tienecomponentealarmadoAlarma) {
        this.tienecomponentealarmadoAlarma = tienecomponentealarmadoAlarma;
    }

    public String getDescripcioncomponentealarmadoAlarma() {
        return descripcioncomponentealarmadoAlarma;
    }

    public void setDescripcioncomponentealarmadoAlarma(String descripcioncomponentealarmadoAlarma) {
        this.descripcioncomponentealarmadoAlarma = descripcioncomponentealarmadoAlarma;
    }

    public Short getEstadoactualAlarma() {
        return estadoactualAlarma;
    }

    public void setEstadoactualAlarma(Short estadoactualAlarma) {
        this.estadoactualAlarma = estadoactualAlarma;
    }

    public byte[] getImagenunoAlarma() {
        return imagenunoAlarma;
    }

    public void setImagenunoAlarma(byte[] imagenunoAlarma) {
        this.imagenunoAlarma = imagenunoAlarma;
    }

    public byte[] getImagendosAlarma() {
        return imagendosAlarma;
    }

    public void setImagendosAlarma(byte[] imagendosAlarma) {
        this.imagendosAlarma = imagendosAlarma;
    }

    public Boolean getMostrarmonitoreodiarioAlarma() {
        return mostrarmonitoreodiarioAlarma;
    }

    public void setMostrarmonitoreodiarioAlarma(Boolean mostrarmonitoreodiarioAlarma) {
        this.mostrarmonitoreodiarioAlarma = mostrarmonitoreodiarioAlarma;
    }

    public String getObservacionAlarma() {
        return observacionAlarma;
    }

    public void setObservacionAlarma(String observacionAlarma) {
        this.observacionAlarma = observacionAlarma;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Usuario getUsuIdUsuario() {
        return usuIdUsuario;
    }

    public void setUsuIdUsuario(Usuario usuIdUsuario) {
        this.usuIdUsuario = usuIdUsuario;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<DetalleAlarma> getDetalleAlarmaList() {
        return detalleAlarmaList;
    }

    public void setDetalleAlarmaList(List<DetalleAlarma> detalleAlarmaList) {
        this.detalleAlarmaList = detalleAlarmaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlarma != null ? idAlarma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlarmaFisica)) {
            return false;
        }
        AlarmaFisica other = (AlarmaFisica) object;
        if ((this.idAlarma == null && other.idAlarma != null) || (this.idAlarma != null && !this.idAlarma.equals(other.idAlarma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.AlarmaFisica[ idAlarma=" + idAlarma + " ]";
    }

}
