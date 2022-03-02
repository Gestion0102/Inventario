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
import javax.persistence.Lob;
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
@Table(name = "log_equipo", catalog = "inventario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogEquipo.findAll", query = "SELECT l FROM LogEquipo l")
    , @NamedQuery(name = "LogEquipo.findByIdLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.idLogequipo = :idLogequipo")
    , @NamedQuery(name = "LogEquipo.findByTipocrudLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.tipocrudLogequipo = :tipocrudLogequipo")
    , @NamedQuery(name = "LogEquipo.findByFechaLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.fechaLogequipo = :fechaLogequipo")
    , @NamedQuery(name = "LogEquipo.findByIdEquipo", query = "SELECT l FROM LogEquipo l WHERE l.idEquipo = :idEquipo")
    , @NamedQuery(name = "LogEquipo.findByIdTipoequipo", query = "SELECT l FROM LogEquipo l WHERE l.idTipoequipo = :idTipoequipo")
    , @NamedQuery(name = "LogEquipo.findByIdRack", query = "SELECT l FROM LogEquipo l WHERE l.idRack = :idRack")
    , @NamedQuery(name = "LogEquipo.findByIdProveedor", query = "SELECT l FROM LogEquipo l WHERE l.idProveedor = :idProveedor")
    , @NamedQuery(name = "LogEquipo.findByIdUsuario", query = "SELECT l FROM LogEquipo l WHERE l.idUsuario = :idUsuario")
    , @NamedQuery(name = "LogEquipo.findByCodigoLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.codigoLogequipo = :codigoLogequipo")
    , @NamedQuery(name = "LogEquipo.findByNombreLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.nombreLogequipo = :nombreLogequipo")
    , @NamedQuery(name = "LogEquipo.findByPosicionrackLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.posicionrackLogequipo = :posicionrackLogequipo")
    , @NamedQuery(name = "LogEquipo.findByUnidadrackLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.unidadrackLogequipo = :unidadrackLogequipo")
    , @NamedQuery(name = "LogEquipo.findByMarcaLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.marcaLogequipo = :marcaLogequipo")
    , @NamedQuery(name = "LogEquipo.findByMtmLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.mtmLogequipo = :mtmLogequipo")
    , @NamedQuery(name = "LogEquipo.findBySerieLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.serieLogequipo = :serieLogequipo")
    , @NamedQuery(name = "LogEquipo.findByModeloLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.modeloLogequipo = :modeloLogequipo")
    , @NamedQuery(name = "LogEquipo.findByPiduidLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.piduidLogequipo = :piduidLogequipo")
    , @NamedQuery(name = "LogEquipo.findByHostnameLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.hostnameLogequipo = :hostnameLogequipo")
    , @NamedQuery(name = "LogEquipo.findByIpadministracionLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.ipadministracionLogequipo = :ipadministracionLogequipo")
    , @NamedQuery(name = "LogEquipo.findByIpproduccionLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.ipproduccionLogequipo = :ipproduccionLogequipo")
    , @NamedQuery(name = "LogEquipo.findByIpvmotionLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.ipvmotionLogequipo = :ipvmotionLogequipo")
    , @NamedQuery(name = "LogEquipo.findByEstadoLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.estadoLogequipo = :estadoLogequipo")
    , @NamedQuery(name = "LogEquipo.findByCantidadprocesadoresLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.cantidadprocesadoresLogequipo = :cantidadprocesadoresLogequipo")
    , @NamedQuery(name = "LogEquipo.findByVelocidadprocesadorLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.velocidadprocesadorLogequipo = :velocidadprocesadorLogequipo")
    , @NamedQuery(name = "LogEquipo.findByVersionprocesadorLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.versionprocesadorLogequipo = :versionprocesadorLogequipo")
    , @NamedQuery(name = "LogEquipo.findByCantidadcoresLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.cantidadcoresLogequipo = :cantidadcoresLogequipo")
    , @NamedQuery(name = "LogEquipo.findByCantidadthreadscoresLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.cantidadthreadscoresLogequipo = :cantidadthreadscoresLogequipo")
    , @NamedQuery(name = "LogEquipo.findByCapacidadinstaladaramLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.capacidadinstaladaramLogequipo = :capacidadinstaladaramLogequipo")
    , @NamedQuery(name = "LogEquipo.findByVelocidadramLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.velocidadramLogequipo = :velocidadramLogequipo")
    , @NamedQuery(name = "LogEquipo.findByCantidadslotsramLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.cantidadslotsramLogequipo = :cantidadslotsramLogequipo")
    , @NamedQuery(name = "LogEquipo.findByCantidadslotsutilizadasramLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.cantidadslotsutilizadasramLogequipo = :cantidadslotsutilizadasramLogequipo")
    , @NamedQuery(name = "LogEquipo.findByPosicionesutilizadasslotsramLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.posicionesutilizadasslotsramLogequipo = :posicionesutilizadasslotsramLogequipo")
    , @NamedQuery(name = "LogEquipo.findByCantidadtotaldiscosLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.cantidadtotaldiscosLogequipo = :cantidadtotaldiscosLogequipo")
    , @NamedQuery(name = "LogEquipo.findByCantidaddiscosutilizadosLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.cantidaddiscosutilizadosLogequipo = :cantidaddiscosutilizadosLogequipo")
    , @NamedQuery(name = "LogEquipo.findByCantidaddiscosvaciosLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.cantidaddiscosvaciosLogequipo = :cantidaddiscosvaciosLogequipo")
    , @NamedQuery(name = "LogEquipo.findByCapacidadtotaldiscoLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.capacidadtotaldiscoLogequipo = :capacidadtotaldiscoLogequipo")
    , @NamedQuery(name = "LogEquipo.findByFrudiscoLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.frudiscoLogequipo = :frudiscoLogequipo")
    , @NamedQuery(name = "LogEquipo.findByInformaciondiscoLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.informaciondiscoLogequipo = :informaciondiscoLogequipo")
    , @NamedQuery(name = "LogEquipo.findByRaiddiscoLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.raiddiscoLogequipo = :raiddiscoLogequipo")
    , @NamedQuery(name = "LogEquipo.findBySistemaoperativoLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.sistemaoperativoLogequipo = :sistemaoperativoLogequipo")
    , @NamedQuery(name = "LogEquipo.findBySistemaoperativodistribucionLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.sistemaoperativodistribucionLogequipo = :sistemaoperativodistribucionLogequipo")
    , @NamedQuery(name = "LogEquipo.findBySistemaoperativoversionLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.sistemaoperativoversionLogequipo = :sistemaoperativoversionLogequipo")
    , @NamedQuery(name = "LogEquipo.findBySistemaoperativosubversionLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.sistemaoperativosubversionLogequipo = :sistemaoperativosubversionLogequipo")
    , @NamedQuery(name = "LogEquipo.findByCantidadtotalinterfacesredLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.cantidadtotalinterfacesredLogequipo = :cantidadtotalinterfacesredLogequipo")
    , @NamedQuery(name = "LogEquipo.findByTienemovimientovmotionLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.tienemovimientovmotionLogequipo = :tienemovimientovmotionLogequipo")
    , @NamedQuery(name = "LogEquipo.findByFuncionalidadLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.funcionalidadLogequipo = :funcionalidadLogequipo")
    , @NamedQuery(name = "LogEquipo.findByProyectosistemaareaLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.proyectosistemaareaLogequipo = :proyectosistemaareaLogequipo")
    , @NamedQuery(name = "LogEquipo.findByResponsableapagadoLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.responsableapagadoLogequipo = :responsableapagadoLogequipo")
    , @NamedQuery(name = "LogEquipo.findByEtiquetaLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.etiquetaLogequipo = :etiquetaLogequipo")
    , @NamedQuery(name = "LogEquipo.findByControlbienesLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.controlbienesLogequipo = :controlbienesLogequipo")
    , @NamedQuery(name = "LogEquipo.findByFechaadquisicionLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.fechaadquisicionLogequipo = :fechaadquisicionLogequipo")
    , @NamedQuery(name = "LogEquipo.findByFaseadquisicionLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.faseadquisicionLogequipo = :faseadquisicionLogequipo")
    , @NamedQuery(name = "LogEquipo.findByHitoadquisicionLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.hitoadquisicionLogequipo = :hitoadquisicionLogequipo")
    , @NamedQuery(name = "LogEquipo.findByObservacionLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.observacionLogequipo = :observacionLogequipo")
    , @NamedQuery(name = "LogEquipo.findByServicioLogequipo", query = "SELECT l FROM LogEquipo l WHERE l.servicioLogequipo = :servicioLogequipo")})
public class LogEquipo implements Serializable {

    @Lob
    @Column(name = "IMAGEN_LOGEQUIPO")
    private byte[] imagenLogequipo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LOGEQUIPO")
    private Integer idLogequipo;
    @Size(max = 16)
    @Column(name = "TIPOCRUD_LOGEQUIPO")
    private String tipocrudLogequipo;
    @Column(name = "FECHA_LOGEQUIPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLogequipo;

    @JoinColumn(name = "ID_EQUIPO", referencedColumnName = "ID_EQUIPO")
    @ManyToOne
    private Equipo idEquipo;
    @JoinColumn(name = "ID_TIPOEQUIPO", referencedColumnName = "ID_TIPOEQUIPO")
    @ManyToOne
    private TipoEquipo idTipoequipo;
    @JoinColumn(name = "ID_RACK", referencedColumnName = "ID_RACK")
    @ManyToOne
    private Rack idRack;
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne
    private Proveedor idProveedor;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    @Size(max = 16)
    @Column(name = "CODIGO_LOGEQUIPO")
    private String codigoLogequipo;
    @Size(max = 32)
    @Column(name = "NOMBRE_LOGEQUIPO")
    private String nombreLogequipo;
    @Size(max = 16)
    @Column(name = "POSICIONRACK_LOGEQUIPO")
    private String posicionrackLogequipo;
    @Size(max = 8)
    @Column(name = "UNIDADRACK_LOGEQUIPO")
    private String unidadrackLogequipo;
    @Size(max = 16)
    @Column(name = "MARCA_LOGEQUIPO")
    private String marcaLogequipo;
    @Size(max = 64)
    @Column(name = "MTM_LOGEQUIPO")
    private String mtmLogequipo;
    @Size(max = 32)
    @Column(name = "SERIE_LOGEQUIPO")
    private String serieLogequipo;
    @Size(max = 32)
    @Column(name = "MODELO_LOGEQUIPO")
    private String modeloLogequipo;
    @Size(max = 32)
    @Column(name = "PIDUID_LOGEQUIPO")
    private String piduidLogequipo;
    @Size(max = 64)
    @Column(name = "HOSTNAME_LOGEQUIPO")
    private String hostnameLogequipo;
    @Size(max = 16)
    @Column(name = "IPADMINISTRACION_LOGEQUIPO")
    private String ipadministracionLogequipo;
    @Size(max = 16)
    @Column(name = "IPPRODUCCION_LOGEQUIPO")
    private String ipproduccionLogequipo;
    @Size(max = 16)
    @Column(name = "IPVMOTION_LOGEQUIPO")
    private String ipvmotionLogequipo;
    @Size(max = 8)
    @Column(name = "ESTADO_LOGEQUIPO")
    private String estadoLogequipo;
    @Size(max = 8)
    @Column(name = "CANTIDADPROCESADORES_LOGEQUIPO")
    private String cantidadprocesadoresLogequipo;
    @Size(max = 16)
    @Column(name = "VELOCIDADPROCESADOR_LOGEQUIPO")
    private String velocidadprocesadorLogequipo;
    @Size(max = 32)
    @Column(name = "VERSIONPROCESADOR_LOGEQUIPO")
    private String versionprocesadorLogequipo;
    @Column(name = "CANTIDADCORES_LOGEQUIPO")
    private Integer cantidadcoresLogequipo;
    @Column(name = "CANTIDADTHREADSCORES_LOGEQUIPO")
    private Integer cantidadthreadscoresLogequipo;
    @Size(max = 16)
    @Column(name = "CAPACIDADINSTALADARAM_LOGEQUIPO")
    private String capacidadinstaladaramLogequipo;
    @Size(max = 16)
    @Column(name = "VELOCIDADRAM_LOGEQUIPO")
    private String velocidadramLogequipo;
    @Column(name = "CANTIDADSLOTSRAM_LOGEQUIPO")
    private Integer cantidadslotsramLogequipo;
    @Size(max = 8)
    @Column(name = "CANTIDADSLOTSUTILIZADASRAM_LOGEQUIPO")
    private String cantidadslotsutilizadasramLogequipo;
    @Size(max = 256)
    @Column(name = "POSICIONESUTILIZADASSLOTSRAM_LOGEQUIPO")
    private String posicionesutilizadasslotsramLogequipo;
    @Column(name = "CANTIDADTOTALDISCOS_LOGEQUIPO")
    private Integer cantidadtotaldiscosLogequipo;
    @Column(name = "CANTIDADDISCOSUTILIZADOS_LOGEQUIPO")
    private Integer cantidaddiscosutilizadosLogequipo;
    @Column(name = "CANTIDADDISCOSVACIOS_LOGEQUIPO")
    private Integer cantidaddiscosvaciosLogequipo;
    @Size(max = 256)
    @Column(name = "CAPACIDADTOTALDISCO_LOGEQUIPO")
    private String capacidadtotaldiscoLogequipo;
    @Size(max = 8)
    @Column(name = "FRUDISCO_LOGEQUIPO")
    private String frudiscoLogequipo;
    @Size(max = 256)
    @Column(name = "INFORMACIONDISCO_LOGEQUIPO")
    private String informaciondiscoLogequipo;
    @Column(name = "RAIDDISCO_LOGEQUIPO")
    private Integer raiddiscoLogequipo;
    @Size(max = 16)
    @Column(name = "SISTEMAOPERATIVO_LOGEQUIPO")
    private String sistemaoperativoLogequipo;
    @Size(max = 16)
    @Column(name = "SISTEMAOPERATIVODISTRIBUCION_LOGEQUIPO")
    private String sistemaoperativodistribucionLogequipo;
    @Size(max = 8)
    @Column(name = "SISTEMAOPERATIVOVERSION_LOGEQUIPO")
    private String sistemaoperativoversionLogequipo;
    @Size(max = 8)
    @Column(name = "SISTEMAOPERATIVOSUBVERSION_LOGEQUIPO")
    private String sistemaoperativosubversionLogequipo;
    @Column(name = "CANTIDADTOTALINTERFACESRED_LOGEQUIPO")
    private Integer cantidadtotalinterfacesredLogequipo;
    @Column(name = "TIENEMOVIMIENTOVMOTION_LOGEQUIPO")
    private Boolean tienemovimientovmotionLogequipo;
    @Size(max = 512)
    @Column(name = "FUNCIONALIDAD_LOGEQUIPO")
    private String funcionalidadLogequipo;
    @Size(max = 64)
    @Column(name = "PROYECTOSISTEMAAREA_LOGEQUIPO")
    private String proyectosistemaareaLogequipo;
    @Size(max = 64)
    @Column(name = "RESPONSABLEAPAGADO_LOGEQUIPO")
    private String responsableapagadoLogequipo;
    @Size(max = 32)
    @Column(name = "ETIQUETA_LOGEQUIPO")
    private String etiquetaLogequipo;
    @Size(max = 8)
    @Column(name = "CONTROLBIENES_LOGEQUIPO")
    private String controlbienesLogequipo;
    @Column(name = "FECHAADQUISICION_LOGEQUIPO")
    @Temporal(TemporalType.DATE)
    private Date fechaadquisicionLogequipo;
    @Size(max = 8)
    @Column(name = "FASEADQUISICION_LOGEQUIPO")
    private String faseadquisicionLogequipo;
    @Size(max = 32)
    @Column(name = "HITOADQUISICION_LOGEQUIPO")
    private String hitoadquisicionLogequipo;
    @Size(max = 512)
    @Column(name = "OBSERVACION_LOGEQUIPO")
    private String observacionLogequipo;
    @Size(max = 64)
    @Column(name = "SERVICIO_LOGEQUIPO")
    private String servicioLogequipo;

    public LogEquipo() {
    }

    public LogEquipo(Integer idLogequipo) {
        this.idLogequipo = idLogequipo;
    }

    public Integer getIdLogequipo() {
        return idLogequipo;
    }

    public void setIdLogequipo(Integer idLogequipo) {
        this.idLogequipo = idLogequipo;
    }

    public String getTipocrudLogequipo() {
        return tipocrudLogequipo;
    }

    public void setTipocrudLogequipo(String tipocrudLogequipo) {
        this.tipocrudLogequipo = tipocrudLogequipo;
    }

    public Date getFechaLogequipo() {
        return fechaLogequipo;
    }

    public void setFechaLogequipo(Date fechaLogequipo) {
        this.fechaLogequipo = fechaLogequipo;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public TipoEquipo getIdTipoequipo() {
        return idTipoequipo;
    }

    public void setIdTipoequipo(TipoEquipo idTipoequipo) {
        this.idTipoequipo = idTipoequipo;
    }

    public Rack getIdRack() {
        return idRack;
    }

    public void setIdRack(Rack idRack) {
        this.idRack = idRack;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigoLogequipo() {
        return codigoLogequipo;
    }

    public void setCodigoLogequipo(String codigoLogequipo) {
        this.codigoLogequipo = codigoLogequipo;
    }

    public String getNombreLogequipo() {
        return nombreLogequipo;
    }

    public void setNombreLogequipo(String nombreLogequipo) {
        this.nombreLogequipo = nombreLogequipo;
    }

    public String getPosicionrackLogequipo() {
        return posicionrackLogequipo;
    }

    public void setPosicionrackLogequipo(String posicionrackLogequipo) {
        this.posicionrackLogequipo = posicionrackLogequipo;
    }

    public String getUnidadrackLogequipo() {
        return unidadrackLogequipo;
    }

    public void setUnidadrackLogequipo(String unidadrackLogequipo) {
        this.unidadrackLogequipo = unidadrackLogequipo;
    }

    public String getMarcaLogequipo() {
        return marcaLogequipo;
    }

    public void setMarcaLogequipo(String marcaLogequipo) {
        this.marcaLogequipo = marcaLogequipo;
    }

    public String getMtmLogequipo() {
        return mtmLogequipo;
    }

    public void setMtmLogequipo(String mtmLogequipo) {
        this.mtmLogequipo = mtmLogequipo;
    }

    public String getSerieLogequipo() {
        return serieLogequipo;
    }

    public void setSerieLogequipo(String serieLogequipo) {
        this.serieLogequipo = serieLogequipo;
    }

    public String getModeloLogequipo() {
        return modeloLogequipo;
    }

    public void setModeloLogequipo(String modeloLogequipo) {
        this.modeloLogequipo = modeloLogequipo;
    }

    public String getPiduidLogequipo() {
        return piduidLogequipo;
    }

    public void setPiduidLogequipo(String piduidLogequipo) {
        this.piduidLogequipo = piduidLogequipo;
    }

    public String getHostnameLogequipo() {
        return hostnameLogequipo;
    }

    public void setHostnameLogequipo(String hostnameLogequipo) {
        this.hostnameLogequipo = hostnameLogequipo;
    }

    public String getIpadministracionLogequipo() {
        return ipadministracionLogequipo;
    }

    public void setIpadministracionLogequipo(String ipadministracionLogequipo) {
        this.ipadministracionLogequipo = ipadministracionLogequipo;
    }

    public String getIpproduccionLogequipo() {
        return ipproduccionLogequipo;
    }

    public void setIpproduccionLogequipo(String ipproduccionLogequipo) {
        this.ipproduccionLogequipo = ipproduccionLogequipo;
    }

    public String getIpvmotionLogequipo() {
        return ipvmotionLogequipo;
    }

    public void setIpvmotionLogequipo(String ipvmotionLogequipo) {
        this.ipvmotionLogequipo = ipvmotionLogequipo;
    }

    public String getEstadoLogequipo() {
        return estadoLogequipo;
    }

    public void setEstadoLogequipo(String estadoLogequipo) {
        this.estadoLogequipo = estadoLogequipo;
    }

    public String getCantidadprocesadoresLogequipo() {
        return cantidadprocesadoresLogequipo;
    }

    public void setCantidadprocesadoresLogequipo(String cantidadprocesadoresLogequipo) {
        this.cantidadprocesadoresLogequipo = cantidadprocesadoresLogequipo;
    }

    public String getVelocidadprocesadorLogequipo() {
        return velocidadprocesadorLogequipo;
    }

    public void setVelocidadprocesadorLogequipo(String velocidadprocesadorLogequipo) {
        this.velocidadprocesadorLogequipo = velocidadprocesadorLogequipo;
    }

    public String getVersionprocesadorLogequipo() {
        return versionprocesadorLogequipo;
    }

    public void setVersionprocesadorLogequipo(String versionprocesadorLogequipo) {
        this.versionprocesadorLogequipo = versionprocesadorLogequipo;
    }

    public Integer getCantidadcoresLogequipo() {
        return cantidadcoresLogequipo;
    }

    public void setCantidadcoresLogequipo(Integer cantidadcoresLogequipo) {
        this.cantidadcoresLogequipo = cantidadcoresLogequipo;
    }

    public Integer getCantidadthreadscoresLogequipo() {
        return cantidadthreadscoresLogequipo;
    }

    public void setCantidadthreadscoresLogequipo(Integer cantidadthreadscoresLogequipo) {
        this.cantidadthreadscoresLogequipo = cantidadthreadscoresLogequipo;
    }

    public String getCapacidadinstaladaramLogequipo() {
        return capacidadinstaladaramLogequipo;
    }

    public void setCapacidadinstaladaramLogequipo(String capacidadinstaladaramLogequipo) {
        this.capacidadinstaladaramLogequipo = capacidadinstaladaramLogequipo;
    }

    public String getVelocidadramLogequipo() {
        return velocidadramLogequipo;
    }

    public void setVelocidadramLogequipo(String velocidadramLogequipo) {
        this.velocidadramLogequipo = velocidadramLogequipo;
    }

    public Integer getCantidadslotsramLogequipo() {
        return cantidadslotsramLogequipo;
    }

    public void setCantidadslotsramLogequipo(Integer cantidadslotsramLogequipo) {
        this.cantidadslotsramLogequipo = cantidadslotsramLogequipo;
    }

    public String getCantidadslotsutilizadasramLogequipo() {
        return cantidadslotsutilizadasramLogequipo;
    }

    public void setCantidadslotsutilizadasramLogequipo(String cantidadslotsutilizadasramLogequipo) {
        this.cantidadslotsutilizadasramLogequipo = cantidadslotsutilizadasramLogequipo;
    }

    public String getPosicionesutilizadasslotsramLogequipo() {
        return posicionesutilizadasslotsramLogequipo;
    }

    public void setPosicionesutilizadasslotsramLogequipo(String posicionesutilizadasslotsramLogequipo) {
        this.posicionesutilizadasslotsramLogequipo = posicionesutilizadasslotsramLogequipo;
    }

    public Integer getCantidadtotaldiscosLogequipo() {
        return cantidadtotaldiscosLogequipo;
    }

    public void setCantidadtotaldiscosLogequipo(Integer cantidadtotaldiscosLogequipo) {
        this.cantidadtotaldiscosLogequipo = cantidadtotaldiscosLogequipo;
    }

    public Integer getCantidaddiscosutilizadosLogequipo() {
        return cantidaddiscosutilizadosLogequipo;
    }

    public void setCantidaddiscosutilizadosLogequipo(Integer cantidaddiscosutilizadosLogequipo) {
        this.cantidaddiscosutilizadosLogequipo = cantidaddiscosutilizadosLogequipo;
    }

    public Integer getCantidaddiscosvaciosLogequipo() {
        return cantidaddiscosvaciosLogequipo;
    }

    public void setCantidaddiscosvaciosLogequipo(Integer cantidaddiscosvaciosLogequipo) {
        this.cantidaddiscosvaciosLogequipo = cantidaddiscosvaciosLogequipo;
    }

    public String getCapacidadtotaldiscoLogequipo() {
        return capacidadtotaldiscoLogequipo;
    }

    public void setCapacidadtotaldiscoLogequipo(String capacidadtotaldiscoLogequipo) {
        this.capacidadtotaldiscoLogequipo = capacidadtotaldiscoLogequipo;
    }

    public String getFrudiscoLogequipo() {
        return frudiscoLogequipo;
    }

    public void setFrudiscoLogequipo(String frudiscoLogequipo) {
        this.frudiscoLogequipo = frudiscoLogequipo;
    }

    public String getInformaciondiscoLogequipo() {
        return informaciondiscoLogequipo;
    }

    public void setInformaciondiscoLogequipo(String informaciondiscoLogequipo) {
        this.informaciondiscoLogequipo = informaciondiscoLogequipo;
    }

    public Integer getRaiddiscoLogequipo() {
        return raiddiscoLogequipo;
    }

    public void setRaiddiscoLogequipo(Integer raiddiscoLogequipo) {
        this.raiddiscoLogequipo = raiddiscoLogequipo;
    }

    public String getSistemaoperativoLogequipo() {
        return sistemaoperativoLogequipo;
    }

    public void setSistemaoperativoLogequipo(String sistemaoperativoLogequipo) {
        this.sistemaoperativoLogequipo = sistemaoperativoLogequipo;
    }

    public String getSistemaoperativodistribucionLogequipo() {
        return sistemaoperativodistribucionLogequipo;
    }

    public void setSistemaoperativodistribucionLogequipo(String sistemaoperativodistribucionLogequipo) {
        this.sistemaoperativodistribucionLogequipo = sistemaoperativodistribucionLogequipo;
    }

    public String getSistemaoperativoversionLogequipo() {
        return sistemaoperativoversionLogequipo;
    }

    public void setSistemaoperativoversionLogequipo(String sistemaoperativoversionLogequipo) {
        this.sistemaoperativoversionLogequipo = sistemaoperativoversionLogequipo;
    }

    public String getSistemaoperativosubversionLogequipo() {
        return sistemaoperativosubversionLogequipo;
    }

    public void setSistemaoperativosubversionLogequipo(String sistemaoperativosubversionLogequipo) {
        this.sistemaoperativosubversionLogequipo = sistemaoperativosubversionLogequipo;
    }

    public Integer getCantidadtotalinterfacesredLogequipo() {
        return cantidadtotalinterfacesredLogequipo;
    }

    public void setCantidadtotalinterfacesredLogequipo(Integer cantidadtotalinterfacesredLogequipo) {
        this.cantidadtotalinterfacesredLogequipo = cantidadtotalinterfacesredLogequipo;
    }

    public Boolean getTienemovimientovmotionLogequipo() {
        return tienemovimientovmotionLogequipo;
    }

    public void setTienemovimientovmotionLogequipo(Boolean tienemovimientovmotionLogequipo) {
        this.tienemovimientovmotionLogequipo = tienemovimientovmotionLogequipo;
    }

    public String getFuncionalidadLogequipo() {
        return funcionalidadLogequipo;
    }

    public void setFuncionalidadLogequipo(String funcionalidadLogequipo) {
        this.funcionalidadLogequipo = funcionalidadLogequipo;
    }

    public String getProyectosistemaareaLogequipo() {
        return proyectosistemaareaLogequipo;
    }

    public void setProyectosistemaareaLogequipo(String proyectosistemaareaLogequipo) {
        this.proyectosistemaareaLogequipo = proyectosistemaareaLogequipo;
    }

    public String getResponsableapagadoLogequipo() {
        return responsableapagadoLogequipo;
    }

    public void setResponsableapagadoLogequipo(String responsableapagadoLogequipo) {
        this.responsableapagadoLogequipo = responsableapagadoLogequipo;
    }

    public String getEtiquetaLogequipo() {
        return etiquetaLogequipo;
    }

    public void setEtiquetaLogequipo(String etiquetaLogequipo) {
        this.etiquetaLogequipo = etiquetaLogequipo;
    }

    public String getControlbienesLogequipo() {
        return controlbienesLogequipo;
    }

    public void setControlbienesLogequipo(String controlbienesLogequipo) {
        this.controlbienesLogequipo = controlbienesLogequipo;
    }

    public Date getFechaadquisicionLogequipo() {
        return fechaadquisicionLogequipo;
    }

    public void setFechaadquisicionLogequipo(Date fechaadquisicionLogequipo) {
        this.fechaadquisicionLogequipo = fechaadquisicionLogequipo;
    }

    public String getFaseadquisicionLogequipo() {
        return faseadquisicionLogequipo;
    }

    public void setFaseadquisicionLogequipo(String faseadquisicionLogequipo) {
        this.faseadquisicionLogequipo = faseadquisicionLogequipo;
    }

    public String getHitoadquisicionLogequipo() {
        return hitoadquisicionLogequipo;
    }

    public void setHitoadquisicionLogequipo(String hitoadquisicionLogequipo) {
        this.hitoadquisicionLogequipo = hitoadquisicionLogequipo;
    }

    public String getObservacionLogequipo() {
        return observacionLogequipo;
    }

    public void setObservacionLogequipo(String observacionLogequipo) {
        this.observacionLogequipo = observacionLogequipo;
    }

    public byte[] getImagenLogequipo() {
        return imagenLogequipo;
    }

    public void setImagenLogequipo(byte[] imagenLogequipo) {
        this.imagenLogequipo = imagenLogequipo;
    }

    public String getServicioLogequipo() {
        return servicioLogequipo;
    }

    public void setServicioLogequipo(String servicioLogequipo) {
        this.servicioLogequipo = servicioLogequipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogequipo != null ? idLogequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogEquipo)) {
            return false;
        }
        LogEquipo other = (LogEquipo) object;
        if ((this.idLogequipo == null && other.idLogequipo != null) || (this.idLogequipo != null && !this.idLogequipo.equals(other.idLogequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.LogEquipo[ idLogequipo=" + idLogequipo + " ]";
    }

}
