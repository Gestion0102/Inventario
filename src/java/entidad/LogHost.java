/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Elena
 */
@Entity
@Table(name = "log_host", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogHost.findAll", query = "SELECT l FROM LogHost l")
    , @NamedQuery(name = "LogHost.findByIdLoghost", query = "SELECT l FROM LogHost l WHERE l.idLoghost = :idLoghost")
    , @NamedQuery(name = "LogHost.findByTipocrudLoghost", query = "SELECT l FROM LogHost l WHERE l.tipocrudLoghost = :tipocrudLoghost")
    , @NamedQuery(name = "LogHost.findByFechaLoghost", query = "SELECT l FROM LogHost l WHERE l.fechaLoghost = :fechaLoghost")
    , @NamedQuery(name = "LogHost.findByIdHost", query = "SELECT l FROM LogHost l WHERE l.idHost = :idHost")
    , @NamedQuery(name = "LogHost.findByIdEquipo", query = "SELECT l FROM LogHost l WHERE l.idEquipo = :idEquipo")
    , @NamedQuery(name = "LogHost.findByIdUsuario", query = "SELECT l FROM LogHost l WHERE l.idUsuario = :idUsuario")
    , @NamedQuery(name = "LogHost.findByCodigoLoghost", query = "SELECT l FROM LogHost l WHERE l.codigoLoghost = :codigoLoghost")
    , @NamedQuery(name = "LogHost.findByHostnameLoghost", query = "SELECT l FROM LogHost l WHERE l.hostnameLoghost = :hostnameLoghost")
    , @NamedQuery(name = "LogHost.findByDescripcionLoghost", query = "SELECT l FROM LogHost l WHERE l.descripcionLoghost = :descripcionLoghost")
    , @NamedQuery(name = "LogHost.findByIpadministracionLoghost", query = "SELECT l FROM LogHost l WHERE l.ipadministracionLoghost = :ipadministracionLoghost")
    , @NamedQuery(name = "LogHost.findByIpproduccionLoghost", query = "SELECT l FROM LogHost l WHERE l.ipproduccionLoghost = :ipproduccionLoghost")
    , @NamedQuery(name = "LogHost.findByIpvmotionLoghost", query = "SELECT l FROM LogHost l WHERE l.ipvmotionLoghost = :ipvmotionLoghost")
    , @NamedQuery(name = "LogHost.findByEstadoLoghost", query = "SELECT l FROM LogHost l WHERE l.estadoLoghost = :estadoLoghost")
    , @NamedQuery(name = "LogHost.findByCantidadprocesadoresLoghost", query = "SELECT l FROM LogHost l WHERE l.cantidadprocesadoresLoghost = :cantidadprocesadoresLoghost")
    , @NamedQuery(name = "LogHost.findByVersionprocesadorLoghost", query = "SELECT l FROM LogHost l WHERE l.versionprocesadorLoghost = :versionprocesadorLoghost")
    , @NamedQuery(name = "LogHost.findByCantidadcoresLoghost", query = "SELECT l FROM LogHost l WHERE l.cantidadcoresLoghost = :cantidadcoresLoghost")
    , @NamedQuery(name = "LogHost.findByCantidadthreadscoresLoghost", query = "SELECT l FROM LogHost l WHERE l.cantidadthreadscoresLoghost = :cantidadthreadscoresLoghost")
    , @NamedQuery(name = "LogHost.findByCapacidadinstaladaramLoghost", query = "SELECT l FROM LogHost l WHERE l.capacidadinstaladaramLoghost = :capacidadinstaladaramLoghost")
    , @NamedQuery(name = "LogHost.findByCantidadtotaldiscosLoghost", query = "SELECT l FROM LogHost l WHERE l.cantidadtotaldiscosLoghost = :cantidadtotaldiscosLoghost")
    , @NamedQuery(name = "LogHost.findByCapacidadtotaldiscoLoghost", query = "SELECT l FROM LogHost l WHERE l.capacidadtotaldiscoLoghost = :capacidadtotaldiscoLoghost")
    , @NamedQuery(name = "LogHost.findBySistemaoperativoLoghost", query = "SELECT l FROM LogHost l WHERE l.sistemaoperativoLoghost = :sistemaoperativoLoghost")
    , @NamedQuery(name = "LogHost.findBySistemaoperativodistribucionLoghost", query = "SELECT l FROM LogHost l WHERE l.sistemaoperativodistribucionLoghost = :sistemaoperativodistribucionLoghost")
    , @NamedQuery(name = "LogHost.findBySistemaoperativoversionLoghost", query = "SELECT l FROM LogHost l WHERE l.sistemaoperativoversionLoghost = :sistemaoperativoversionLoghost")
    , @NamedQuery(name = "LogHost.findBySistemaoperativosubversionLoghost", query = "SELECT l FROM LogHost l WHERE l.sistemaoperativosubversionLoghost = :sistemaoperativosubversionLoghost")
    , @NamedQuery(name = "LogHost.findByCantidadtotalinterfacesredLoghost", query = "SELECT l FROM LogHost l WHERE l.cantidadtotalinterfacesredLoghost = :cantidadtotalinterfacesredLoghost")
    , @NamedQuery(name = "LogHost.findByTienemovimientovmotionLoghost", query = "SELECT l FROM LogHost l WHERE l.tienemovimientovmotionLoghost = :tienemovimientovmotionLoghost")
    , @NamedQuery(name = "LogHost.findByFuncionalidadLoghost", query = "SELECT l FROM LogHost l WHERE l.funcionalidadLoghost = :funcionalidadLoghost")
    , @NamedQuery(name = "LogHost.findByProyectosistemaareaLoghost", query = "SELECT l FROM LogHost l WHERE l.proyectosistemaareaLoghost = :proyectosistemaareaLoghost")
    , @NamedQuery(name = "LogHost.findByResponsableapagadoLoghost", query = "SELECT l FROM LogHost l WHERE l.responsableapagadoLoghost = :responsableapagadoLoghost")
    , @NamedQuery(name = "LogHost.findByObservacionLoghost", query = "SELECT l FROM LogHost l WHERE l.observacionLoghost = :observacionLoghost")
    , @NamedQuery(name = "LogHost.findByServicioLoghost", query = "SELECT l FROM LogHost l WHERE l.servicioLoghost = :servicioLoghost")})
public class LogHost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LOGHOST")
    private Integer idLoghost;
    @Size(max = 16)
    @Column(name = "TIPOCRUD_LOGHOST")
    private String tipocrudLoghost;
    @Column(name = "FECHA_LOGHOST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLoghost;

    @JoinColumn(name = "ID_HOST", referencedColumnName = "ID_HOST")
    @ManyToOne
    private Host idHost;
    @JoinColumn(name = "ID_EQUIPO", referencedColumnName = "ID_EQUIPO")
    @ManyToOne
    private Equipo idEquipo;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    @Size(max = 16)
    @Column(name = "CODIGO_LOGHOST")
    private String codigoLoghost;
    @Size(max = 32)
    @Column(name = "HOSTNAME_LOGHOST")
    private String hostnameLoghost;
    @Size(max = 32)
    @Column(name = "DESCRIPCION_LOGHOST")
    private String descripcionLoghost;
    @Size(max = 16)
    @Column(name = "IPADMINISTRACION_LOGHOST")
    private String ipadministracionLoghost;
    @Size(max = 16)
    @Column(name = "IPPRODUCCION_LOGHOST")
    private String ipproduccionLoghost;
    @Size(max = 16)
    @Column(name = "IPVMOTION_LOGHOST")
    private String ipvmotionLoghost;
    @Column(name = "ESTADO_LOGHOST")
    private Boolean estadoLoghost;
    @Size(max = 8)
    @Column(name = "CANTIDADPROCESADORES_LOGHOST")
    private String cantidadprocesadoresLoghost;
    @Size(max = 32)
    @Column(name = "VERSIONPROCESADOR_LOGHOST")
    private String versionprocesadorLoghost;
    @Size(max = 4)
    @Column(name = "CANTIDADCORES_LOGHOST")
    private String cantidadcoresLoghost;
    @Column(name = "CANTIDADTHREADSCORES_LOGHOST")
    private Integer cantidadthreadscoresLoghost;
    @Size(max = 16)
    @Column(name = "CAPACIDADINSTALADARAM_LOGHOST")
    private String capacidadinstaladaramLoghost;
    @Size(max = 8)
    @Column(name = "CANTIDADTOTALDISCOS_LOGHOST")
    private String cantidadtotaldiscosLoghost;
    @Size(max = 256)
    @Column(name = "CAPACIDADTOTALDISCO_LOGHOST")
    private String capacidadtotaldiscoLoghost;
    @Size(max = 16)
    @Column(name = "SISTEMAOPERATIVO_LOGHOST")
    private String sistemaoperativoLoghost;
    @Size(max = 16)
    @Column(name = "SISTEMAOPERATIVODISTRIBUCION_LOGHOST")
    private String sistemaoperativodistribucionLoghost;
    @Size(max = 8)
    @Column(name = "SISTEMAOPERATIVOVERSION_LOGHOST")
    private String sistemaoperativoversionLoghost;
    @Size(max = 8)
    @Column(name = "SISTEMAOPERATIVOSUBVERSION_LOGHOST")
    private String sistemaoperativosubversionLoghost;
    @Column(name = "CANTIDADTOTALINTERFACESRED_LOGHOST")
    private Integer cantidadtotalinterfacesredLoghost;
    @Column(name = "TIENEMOVIMIENTOVMOTION_LOGHOST")
    private Boolean tienemovimientovmotionLoghost;
    @Size(max = 512)
    @Column(name = "FUNCIONALIDAD_LOGHOST")
    private String funcionalidadLoghost;
    @Size(max = 64)
    @Column(name = "PROYECTOSISTEMAAREA_LOGHOST")
    private String proyectosistemaareaLoghost;
    @Size(max = 64)
    @Column(name = "RESPONSABLEAPAGADO_LOGHOST")
    private String responsableapagadoLoghost;
    @Size(max = 512)
    @Column(name = "OBSERVACION_LOGHOST")
    private String observacionLoghost;
    @Size(max = 64)
    @Column(name = "SERVICIO_LOGHOST")
    private String servicioLoghost;

    public LogHost() {
    }

    public LogHost(Integer idLoghost) {
        this.idLoghost = idLoghost;
    }

    public Integer getIdLoghost() {
        return idLoghost;
    }

    public void setIdLoghost(Integer idLoghost) {
        this.idLoghost = idLoghost;
    }

    public String getTipocrudLoghost() {
        return tipocrudLoghost;
    }

    public void setTipocrudLoghost(String tipocrudLoghost) {
        this.tipocrudLoghost = tipocrudLoghost;
    }

    public Date getFechaLoghost() {
        return fechaLoghost;
    }

    public void setFechaLoghost(Date fechaLoghost) {
        this.fechaLoghost = fechaLoghost;
    }

    public Host getIdHost() {
        return idHost;
    }

    public void setIdHost(Host idHost) {
        this.idHost = idHost;
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

    public String getCodigoLoghost() {
        return codigoLoghost;
    }

    public void setCodigoLoghost(String codigoLoghost) {
        this.codigoLoghost = codigoLoghost;
    }

    public String getHostnameLoghost() {
        return hostnameLoghost;
    }

    public void setHostnameLoghost(String hostnameLoghost) {
        this.hostnameLoghost = hostnameLoghost;
    }

    public String getDescripcionLoghost() {
        return descripcionLoghost;
    }

    public void setDescripcionLoghost(String descripcionLoghost) {
        this.descripcionLoghost = descripcionLoghost;
    }

    public String getIpadministracionLoghost() {
        return ipadministracionLoghost;
    }

    public void setIpadministracionLoghost(String ipadministracionLoghost) {
        this.ipadministracionLoghost = ipadministracionLoghost;
    }

    public String getIpproduccionLoghost() {
        return ipproduccionLoghost;
    }

    public void setIpproduccionLoghost(String ipproduccionLoghost) {
        this.ipproduccionLoghost = ipproduccionLoghost;
    }

    public String getIpvmotionLoghost() {
        return ipvmotionLoghost;
    }

    public void setIpvmotionLoghost(String ipvmotionLoghost) {
        this.ipvmotionLoghost = ipvmotionLoghost;
    }

    public Boolean getEstadoLoghost() {
        return estadoLoghost;
    }

    public void setEstadoLoghost(Boolean estadoLoghost) {
        this.estadoLoghost = estadoLoghost;
    }

    public String getCantidadprocesadoresLoghost() {
        return cantidadprocesadoresLoghost;
    }

    public void setCantidadprocesadoresLoghost(String cantidadprocesadoresLoghost) {
        this.cantidadprocesadoresLoghost = cantidadprocesadoresLoghost;
    }

    public String getVersionprocesadorLoghost() {
        return versionprocesadorLoghost;
    }

    public void setVersionprocesadorLoghost(String versionprocesadorLoghost) {
        this.versionprocesadorLoghost = versionprocesadorLoghost;
    }

    public String getCantidadcoresLoghost() {
        return cantidadcoresLoghost;
    }

    public void setCantidadcoresLoghost(String cantidadcoresLoghost) {
        this.cantidadcoresLoghost = cantidadcoresLoghost;
    }

    public Integer getCantidadthreadscoresLoghost() {
        return cantidadthreadscoresLoghost;
    }

    public void setCantidadthreadscoresLoghost(Integer cantidadthreadscoresLoghost) {
        this.cantidadthreadscoresLoghost = cantidadthreadscoresLoghost;
    }

    public String getCapacidadinstaladaramLoghost() {
        return capacidadinstaladaramLoghost;
    }

    public void setCapacidadinstaladaramLoghost(String capacidadinstaladaramLoghost) {
        this.capacidadinstaladaramLoghost = capacidadinstaladaramLoghost;
    }

    public String getCantidadtotaldiscosLoghost() {
        return cantidadtotaldiscosLoghost;
    }

    public void setCantidadtotaldiscosLoghost(String cantidadtotaldiscosLoghost) {
        this.cantidadtotaldiscosLoghost = cantidadtotaldiscosLoghost;
    }

    public String getCapacidadtotaldiscoLoghost() {
        return capacidadtotaldiscoLoghost;
    }

    public void setCapacidadtotaldiscoLoghost(String capacidadtotaldiscoLoghost) {
        this.capacidadtotaldiscoLoghost = capacidadtotaldiscoLoghost;
    }

    public String getSistemaoperativoLoghost() {
        return sistemaoperativoLoghost;
    }

    public void setSistemaoperativoLoghost(String sistemaoperativoLoghost) {
        this.sistemaoperativoLoghost = sistemaoperativoLoghost;
    }

    public String getSistemaoperativodistribucionLoghost() {
        return sistemaoperativodistribucionLoghost;
    }

    public void setSistemaoperativodistribucionLoghost(String sistemaoperativodistribucionLoghost) {
        this.sistemaoperativodistribucionLoghost = sistemaoperativodistribucionLoghost;
    }

    public String getSistemaoperativoversionLoghost() {
        return sistemaoperativoversionLoghost;
    }

    public void setSistemaoperativoversionLoghost(String sistemaoperativoversionLoghost) {
        this.sistemaoperativoversionLoghost = sistemaoperativoversionLoghost;
    }

    public String getSistemaoperativosubversionLoghost() {
        return sistemaoperativosubversionLoghost;
    }

    public void setSistemaoperativosubversionLoghost(String sistemaoperativosubversionLoghost) {
        this.sistemaoperativosubversionLoghost = sistemaoperativosubversionLoghost;
    }

    public Integer getCantidadtotalinterfacesredLoghost() {
        return cantidadtotalinterfacesredLoghost;
    }

    public void setCantidadtotalinterfacesredLoghost(Integer cantidadtotalinterfacesredLoghost) {
        this.cantidadtotalinterfacesredLoghost = cantidadtotalinterfacesredLoghost;
    }

    public Boolean getTienemovimientovmotionLoghost() {
        return tienemovimientovmotionLoghost;
    }

    public void setTienemovimientovmotionLoghost(Boolean tienemovimientovmotionLoghost) {
        this.tienemovimientovmotionLoghost = tienemovimientovmotionLoghost;
    }

    public String getFuncionalidadLoghost() {
        return funcionalidadLoghost;
    }

    public void setFuncionalidadLoghost(String funcionalidadLoghost) {
        this.funcionalidadLoghost = funcionalidadLoghost;
    }

    public String getProyectosistemaareaLoghost() {
        return proyectosistemaareaLoghost;
    }

    public void setProyectosistemaareaLoghost(String proyectosistemaareaLoghost) {
        this.proyectosistemaareaLoghost = proyectosistemaareaLoghost;
    }

    public String getResponsableapagadoLoghost() {
        return responsableapagadoLoghost;
    }

    public void setResponsableapagadoLoghost(String responsableapagadoLoghost) {
        this.responsableapagadoLoghost = responsableapagadoLoghost;
    }

    public String getObservacionLoghost() {
        return observacionLoghost;
    }

    public void setObservacionLoghost(String observacionLoghost) {
        this.observacionLoghost = observacionLoghost;
    }

    public String getServicioLoghost() {
        return servicioLoghost;
    }

    public void setServicioLoghost(String servicioLoghost) {
        this.servicioLoghost = servicioLoghost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoghost != null ? idLoghost.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogHost)) {
            return false;
        }
        LogHost other = (LogHost) object;
        if ((this.idLoghost == null && other.idLoghost != null) || (this.idLoghost != null && !this.idLoghost.equals(other.idLoghost))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.LogHost[ idLoghost=" + idLoghost + " ]";
    }

}
