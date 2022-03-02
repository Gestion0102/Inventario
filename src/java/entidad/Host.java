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
@Table(catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Host.findAll", query = "SELECT h FROM Host h")
    , @NamedQuery(name = "Host.findByIdHost", query = "SELECT h FROM Host h WHERE h.idHost = :idHost")
    , @NamedQuery(name = "Host.findByCodigoHost", query = "SELECT h FROM Host h WHERE h.codigoHost = :codigoHost")
    , @NamedQuery(name = "Host.findByHostnameHost", query = "SELECT h FROM Host h WHERE h.hostnameHost = :hostnameHost")
    , @NamedQuery(name = "Host.findByDescripcionHost", query = "SELECT h FROM Host h WHERE h.descripcionHost = :descripcionHost")
    , @NamedQuery(name = "Host.findByIpadministracionHost", query = "SELECT h FROM Host h WHERE h.ipadministracionHost = :ipadministracionHost")
    , @NamedQuery(name = "Host.findByIpproduccionHost", query = "SELECT h FROM Host h WHERE h.ipproduccionHost = :ipproduccionHost")
    , @NamedQuery(name = "Host.findByIpvmotionHost", query = "SELECT h FROM Host h WHERE h.ipvmotionHost = :ipvmotionHost")
    , @NamedQuery(name = "Host.findByEstadoHost", query = "SELECT h FROM Host h WHERE h.estadoHost = :estadoHost")
    , @NamedQuery(name = "Host.findByCantidadprocesadoresHost", query = "SELECT h FROM Host h WHERE h.cantidadprocesadoresHost = :cantidadprocesadoresHost")
    , @NamedQuery(name = "Host.findByVersionprocesadorHost", query = "SELECT h FROM Host h WHERE h.versionprocesadorHost = :versionprocesadorHost")
    , @NamedQuery(name = "Host.findByCantidadcoresHost", query = "SELECT h FROM Host h WHERE h.cantidadcoresHost = :cantidadcoresHost")
    , @NamedQuery(name = "Host.findByCantidadthreadscoresHost", query = "SELECT h FROM Host h WHERE h.cantidadthreadscoresHost = :cantidadthreadscoresHost")
    , @NamedQuery(name = "Host.findByCapacidadinstaladaramHost", query = "SELECT h FROM Host h WHERE h.capacidadinstaladaramHost = :capacidadinstaladaramHost")
    , @NamedQuery(name = "Host.findByCantidadtotaldiscosHost", query = "SELECT h FROM Host h WHERE h.cantidadtotaldiscosHost = :cantidadtotaldiscosHost")
    , @NamedQuery(name = "Host.findByCapacidadtotaldiscoHost", query = "SELECT h FROM Host h WHERE h.capacidadtotaldiscoHost = :capacidadtotaldiscoHost")
    , @NamedQuery(name = "Host.findBySistemaoperativoHost", query = "SELECT h FROM Host h WHERE h.sistemaoperativoHost = :sistemaoperativoHost")
    , @NamedQuery(name = "Host.findBySistemaoperativodistribucionHost", query = "SELECT h FROM Host h WHERE h.sistemaoperativodistribucionHost = :sistemaoperativodistribucionHost")
    , @NamedQuery(name = "Host.findBySistemaoperativoversionHost", query = "SELECT h FROM Host h WHERE h.sistemaoperativoversionHost = :sistemaoperativoversionHost")
    , @NamedQuery(name = "Host.findBySistemaoperativosubversionHost", query = "SELECT h FROM Host h WHERE h.sistemaoperativosubversionHost = :sistemaoperativosubversionHost")
    , @NamedQuery(name = "Host.findByCantidadtotalinterfacesredHost", query = "SELECT h FROM Host h WHERE h.cantidadtotalinterfacesredHost = :cantidadtotalinterfacesredHost")
    , @NamedQuery(name = "Host.findByTienemovimientovmotionHost", query = "SELECT h FROM Host h WHERE h.tienemovimientovmotionHost = :tienemovimientovmotionHost")
    , @NamedQuery(name = "Host.findByFuncionalidadHost", query = "SELECT h FROM Host h WHERE h.funcionalidadHost = :funcionalidadHost")
    , @NamedQuery(name = "Host.findByProyectosistemaareaHost", query = "SELECT h FROM Host h WHERE h.proyectosistemaareaHost = :proyectosistemaareaHost")
    , @NamedQuery(name = "Host.findByResponsableapagadoHost", query = "SELECT h FROM Host h WHERE h.responsableapagadoHost = :responsableapagadoHost")
    , @NamedQuery(name = "Host.findByObservacionHost", query = "SELECT h FROM Host h WHERE h.observacionHost = :observacionHost")
    , @NamedQuery(name = "Host.findByServicioHost", query = "SELECT h FROM Host h WHERE h.servicioHost = :servicioHost")})
public class Host implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HOST")
    private Integer idHost;
    @Size(max = 16)
    @Column(name = "CODIGO_HOST")
    private String codigoHost;
    @Size(max = 32)
    @Column(name = "HOSTNAME_HOST")
    private String hostnameHost;
    @Size(max = 32)
    @Column(name = "DESCRIPCION_HOST")
    private String descripcionHost;
    @Size(max = 16)
    @Column(name = "IPADMINISTRACION_HOST")
    private String ipadministracionHost;
    @Size(max = 16)
    @Column(name = "IPPRODUCCION_HOST")
    private String ipproduccionHost;
    @Size(max = 16)
    @Column(name = "IPVMOTION_HOST")
    private String ipvmotionHost;
    @Column(name = "ESTADO_HOST")
    private Boolean estadoHost;
    @Size(max = 8)
    @Column(name = "CANTIDADPROCESADORES_HOST")
    private String cantidadprocesadoresHost;
    @Size(max = 32)
    @Column(name = "VERSIONPROCESADOR_HOST")
    private String versionprocesadorHost;
    @Size(max = 4)
    @Column(name = "CANTIDADCORES_HOST")
    private String cantidadcoresHost;
    @Column(name = "CANTIDADTHREADSCORES_HOST")
    private Integer cantidadthreadscoresHost;
    @Size(max = 16)
    @Column(name = "CAPACIDADINSTALADARAM_HOST")
    private String capacidadinstaladaramHost;
    @Size(max = 8)
    @Column(name = "CANTIDADTOTALDISCOS_HOST")
    private String cantidadtotaldiscosHost;
    @Size(max = 256)
    @Column(name = "CAPACIDADTOTALDISCO_HOST")
    private String capacidadtotaldiscoHost;
    @Size(max = 16)
    @Column(name = "SISTEMAOPERATIVO_HOST")
    private String sistemaoperativoHost;
    @Size(max = 16)
    @Column(name = "SISTEMAOPERATIVODISTRIBUCION_HOST")
    private String sistemaoperativodistribucionHost;
    @Size(max = 8)
    @Column(name = "SISTEMAOPERATIVOVERSION_HOST")
    private String sistemaoperativoversionHost;
    @Size(max = 8)
    @Column(name = "SISTEMAOPERATIVOSUBVERSION_HOST")
    private String sistemaoperativosubversionHost;
    @Column(name = "CANTIDADTOTALINTERFACESRED_HOST")
    private Integer cantidadtotalinterfacesredHost;
    @Column(name = "TIENEMOVIMIENTOVMOTION_HOST")
    private Boolean tienemovimientovmotionHost;
    @Size(max = 512)
    @Column(name = "FUNCIONALIDAD_HOST")
    private String funcionalidadHost;
    @Size(max = 64)
    @Column(name = "PROYECTOSISTEMAAREA_HOST")
    private String proyectosistemaareaHost;
    @Size(max = 64)
    @Column(name = "RESPONSABLEAPAGADO_HOST")
    private String responsableapagadoHost;
    @Size(max = 512)
    @Column(name = "OBSERVACION_HOST")
    private String observacionHost;
    @Size(max = 64)
    @Column(name = "SERVICIO_HOST")
    private String servicioHost;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "ID_EQUIPO", referencedColumnName = "ID_EQUIPO")
    @ManyToOne
    private Equipo idEquipo;

    public Host() {
    }

    public Host(Integer idHost) {
        this.idHost = idHost;
    }

    public Integer getIdHost() {
        return idHost;
    }

    public void setIdHost(Integer idHost) {
        this.idHost = idHost;
    }

    public String getCodigoHost() {
        return codigoHost;
    }

    public void setCodigoHost(String codigoHost) {
        this.codigoHost = codigoHost;
    }

    public String getHostnameHost() {
        return hostnameHost;
    }

    public void setHostnameHost(String hostnameHost) {
        this.hostnameHost = hostnameHost;
    }

    public String getDescripcionHost() {
        return descripcionHost;
    }

    public void setDescripcionHost(String descripcionHost) {
        this.descripcionHost = descripcionHost;
    }

    public String getIpadministracionHost() {
        return ipadministracionHost;
    }

    public void setIpadministracionHost(String ipadministracionHost) {
        this.ipadministracionHost = ipadministracionHost;
    }

    public String getIpproduccionHost() {
        return ipproduccionHost;
    }

    public void setIpproduccionHost(String ipproduccionHost) {
        this.ipproduccionHost = ipproduccionHost;
    }

    public String getIpvmotionHost() {
        return ipvmotionHost;
    }

    public void setIpvmotionHost(String ipvmotionHost) {
        this.ipvmotionHost = ipvmotionHost;
    }

    public Boolean getEstadoHost() {
        return estadoHost;
    }

    public void setEstadoHost(Boolean estadoHost) {
        this.estadoHost = estadoHost;
    }

    public String getCantidadprocesadoresHost() {
        return cantidadprocesadoresHost;
    }

    public void setCantidadprocesadoresHost(String cantidadprocesadoresHost) {
        this.cantidadprocesadoresHost = cantidadprocesadoresHost;
    }

    public String getVersionprocesadorHost() {
        return versionprocesadorHost;
    }

    public void setVersionprocesadorHost(String versionprocesadorHost) {
        this.versionprocesadorHost = versionprocesadorHost;
    }

    public String getCantidadcoresHost() {
        return cantidadcoresHost;
    }

    public void setCantidadcoresHost(String cantidadcoresHost) {
        this.cantidadcoresHost = cantidadcoresHost;
    }

    public Integer getCantidadthreadscoresHost() {
        return cantidadthreadscoresHost;
    }

    public void setCantidadthreadscoresHost(Integer cantidadthreadscoresHost) {
        this.cantidadthreadscoresHost = cantidadthreadscoresHost;
    }

    public String getCapacidadinstaladaramHost() {
        return capacidadinstaladaramHost;
    }

    public void setCapacidadinstaladaramHost(String capacidadinstaladaramHost) {
        this.capacidadinstaladaramHost = capacidadinstaladaramHost;
    }

    public String getCantidadtotaldiscosHost() {
        return cantidadtotaldiscosHost;
    }

    public void setCantidadtotaldiscosHost(String cantidadtotaldiscosHost) {
        this.cantidadtotaldiscosHost = cantidadtotaldiscosHost;
    }

    public String getCapacidadtotaldiscoHost() {
        return capacidadtotaldiscoHost;
    }

    public void setCapacidadtotaldiscoHost(String capacidadtotaldiscoHost) {
        this.capacidadtotaldiscoHost = capacidadtotaldiscoHost;
    }

    public String getSistemaoperativoHost() {
        return sistemaoperativoHost;
    }

    public void setSistemaoperativoHost(String sistemaoperativoHost) {
        this.sistemaoperativoHost = sistemaoperativoHost;
    }

    public String getSistemaoperativodistribucionHost() {
        return sistemaoperativodistribucionHost;
    }

    public void setSistemaoperativodistribucionHost(String sistemaoperativodistribucionHost) {
        this.sistemaoperativodistribucionHost = sistemaoperativodistribucionHost;
    }

    public String getSistemaoperativoversionHost() {
        return sistemaoperativoversionHost;
    }

    public void setSistemaoperativoversionHost(String sistemaoperativoversionHost) {
        this.sistemaoperativoversionHost = sistemaoperativoversionHost;
    }

    public String getSistemaoperativosubversionHost() {
        return sistemaoperativosubversionHost;
    }

    public void setSistemaoperativosubversionHost(String sistemaoperativosubversionHost) {
        this.sistemaoperativosubversionHost = sistemaoperativosubversionHost;
    }

    public Integer getCantidadtotalinterfacesredHost() {
        return cantidadtotalinterfacesredHost;
    }

    public void setCantidadtotalinterfacesredHost(Integer cantidadtotalinterfacesredHost) {
        this.cantidadtotalinterfacesredHost = cantidadtotalinterfacesredHost;
    }

    public Boolean getTienemovimientovmotionHost() {
        return tienemovimientovmotionHost;
    }

    public void setTienemovimientovmotionHost(Boolean tienemovimientovmotionHost) {
        this.tienemovimientovmotionHost = tienemovimientovmotionHost;
    }

    public String getFuncionalidadHost() {
        return funcionalidadHost;
    }

    public void setFuncionalidadHost(String funcionalidadHost) {
        this.funcionalidadHost = funcionalidadHost;
    }

    public String getProyectosistemaareaHost() {
        return proyectosistemaareaHost;
    }

    public void setProyectosistemaareaHost(String proyectosistemaareaHost) {
        this.proyectosistemaareaHost = proyectosistemaareaHost;
    }

    public String getResponsableapagadoHost() {
        return responsableapagadoHost;
    }

    public void setResponsableapagadoHost(String responsableapagadoHost) {
        this.responsableapagadoHost = responsableapagadoHost;
    }

    public String getObservacionHost() {
        return observacionHost;
    }

    public void setObservacionHost(String observacionHost) {
        this.observacionHost = observacionHost;
    }

    public String getServicioHost() {
        return servicioHost;
    }

    public void setServicioHost(String servicioHost) {
        this.servicioHost = servicioHost;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHost != null ? idHost.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Host)) {
            return false;
        }
        Host other = (Host) object;
        if ((this.idHost == null && other.idHost != null) || (this.idHost != null && !this.idHost.equals(other.idHost))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Host[ idHost=" + idHost + " ]";
    }

}
