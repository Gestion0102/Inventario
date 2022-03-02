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
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e")
    , @NamedQuery(name = "Equipo.findByIdEquipo", query = "SELECT e FROM Equipo e WHERE e.idEquipo = :idEquipo")
    , @NamedQuery(name = "Equipo.findByIdTipoEquipo", query = "SELECT e FROM Equipo e where e.idTipoequipo.idTipoequipo = :idTipoequipo")
    , @NamedQuery(name = "Equipo.findByCodigoEquipo", query = "SELECT e FROM Equipo e WHERE e.codigoEquipo = :codigoEquipo")
    , @NamedQuery(name = "Equipo.findByNombreEquipo", query = "SELECT e FROM Equipo e WHERE e.nombreEquipo = :nombreEquipo")
    , @NamedQuery(name = "Equipo.findByPosicionrackEquipo", query = "SELECT e FROM Equipo e WHERE e.posicionrackEquipo = :posicionrackEquipo")
    , @NamedQuery(name = "Equipo.findByUnidadrackEquipo", query = "SELECT e FROM Equipo e WHERE e.unidadrackEquipo = :unidadrackEquipo")
    , @NamedQuery(name = "Equipo.findByMarcaEquipo", query = "SELECT e FROM Equipo e WHERE e.marcaEquipo = :marcaEquipo")
    , @NamedQuery(name = "Equipo.findByMtmEquipo", query = "SELECT e FROM Equipo e WHERE e.mtmEquipo = :mtmEquipo")
    , @NamedQuery(name = "Equipo.findBySerieEquipo", query = "SELECT e FROM Equipo e WHERE e.serieEquipo = :serieEquipo")
    , @NamedQuery(name = "Equipo.findByModeloEquipo", query = "SELECT e FROM Equipo e WHERE e.modeloEquipo = :modeloEquipo")
    , @NamedQuery(name = "Equipo.findByPiduidEquipo", query = "SELECT e FROM Equipo e WHERE e.piduidEquipo = :piduidEquipo")
    , @NamedQuery(name = "Equipo.findByHostnameEquipo", query = "SELECT e FROM Equipo e WHERE e.hostnameEquipo = :hostnameEquipo")
    , @NamedQuery(name = "Equipo.findByIpadministracionEquipo", query = "SELECT e FROM Equipo e WHERE e.ipadministracionEquipo = :ipadministracionEquipo")
    , @NamedQuery(name = "Equipo.findByIpproduccionEquipo", query = "SELECT e FROM Equipo e WHERE e.ipproduccionEquipo = :ipproduccionEquipo")
    , @NamedQuery(name = "Equipo.findByIpvmotionEquipo", query = "SELECT e FROM Equipo e WHERE e.ipvmotionEquipo = :ipvmotionEquipo")
    , @NamedQuery(name = "Equipo.findByEstadoEquipo", query = "SELECT e FROM Equipo e WHERE e.estadoEquipo = :estadoEquipo")
    , @NamedQuery(name = "Equipo.findByCantidadprocesadoresEquipo", query = "SELECT e FROM Equipo e WHERE e.cantidadprocesadoresEquipo = :cantidadprocesadoresEquipo")
    , @NamedQuery(name = "Equipo.findByVelocidadprocesadorEquipo", query = "SELECT e FROM Equipo e WHERE e.velocidadprocesadorEquipo = :velocidadprocesadorEquipo")
    , @NamedQuery(name = "Equipo.findByVersionprocesadorEquipo", query = "SELECT e FROM Equipo e WHERE e.versionprocesadorEquipo = :versionprocesadorEquipo")
    , @NamedQuery(name = "Equipo.findByCantidadcoresEquipo", query = "SELECT e FROM Equipo e WHERE e.cantidadcoresEquipo = :cantidadcoresEquipo")
    , @NamedQuery(name = "Equipo.findByCantidadthreadscoresEquipo", query = "SELECT e FROM Equipo e WHERE e.cantidadthreadscoresEquipo = :cantidadthreadscoresEquipo")
    , @NamedQuery(name = "Equipo.findByCapacidadinstaladaramEquipo", query = "SELECT e FROM Equipo e WHERE e.capacidadinstaladaramEquipo = :capacidadinstaladaramEquipo")
    , @NamedQuery(name = "Equipo.findByVelocidadramEquipo", query = "SELECT e FROM Equipo e WHERE e.velocidadramEquipo = :velocidadramEquipo")
    , @NamedQuery(name = "Equipo.findByCantidadslotsramEquipo", query = "SELECT e FROM Equipo e WHERE e.cantidadslotsramEquipo = :cantidadslotsramEquipo")
    , @NamedQuery(name = "Equipo.findByCantidadslotsutilizadasramEquipo", query = "SELECT e FROM Equipo e WHERE e.cantidadslotsutilizadasramEquipo = :cantidadslotsutilizadasramEquipo")
    , @NamedQuery(name = "Equipo.findByPosicionesutilizadasslotsramEquipo", query = "SELECT e FROM Equipo e WHERE e.posicionesutilizadasslotsramEquipo = :posicionesutilizadasslotsramEquipo")
    , @NamedQuery(name = "Equipo.findByCantidadtotaldiscosEquipo", query = "SELECT e FROM Equipo e WHERE e.cantidadtotaldiscosEquipo = :cantidadtotaldiscosEquipo")
    , @NamedQuery(name = "Equipo.findByCantidaddiscosutilizadosEquipo", query = "SELECT e FROM Equipo e WHERE e.cantidaddiscosutilizadosEquipo = :cantidaddiscosutilizadosEquipo")
    , @NamedQuery(name = "Equipo.findByCantidaddiscosvaciosEquipo", query = "SELECT e FROM Equipo e WHERE e.cantidaddiscosvaciosEquipo = :cantidaddiscosvaciosEquipo")
    , @NamedQuery(name = "Equipo.findByCapacidadtotaldiscoEquipo", query = "SELECT e FROM Equipo e WHERE e.capacidadtotaldiscoEquipo = :capacidadtotaldiscoEquipo")
    , @NamedQuery(name = "Equipo.findByFrudiscoEquipo", query = "SELECT e FROM Equipo e WHERE e.frudiscoEquipo = :frudiscoEquipo")
    , @NamedQuery(name = "Equipo.findByInformaciondiscoEquipo", query = "SELECT e FROM Equipo e WHERE e.informaciondiscoEquipo = :informaciondiscoEquipo")
    , @NamedQuery(name = "Equipo.findByRaiddiscoEquipo", query = "SELECT e FROM Equipo e WHERE e.raiddiscoEquipo = :raiddiscoEquipo")
    , @NamedQuery(name = "Equipo.findBySistemaoperativoEquipo", query = "SELECT e FROM Equipo e WHERE e.sistemaoperativoEquipo = :sistemaoperativoEquipo")
    , @NamedQuery(name = "Equipo.findBySistemaoperativodistribucionEquipo", query = "SELECT e FROM Equipo e WHERE e.sistemaoperativodistribucionEquipo = :sistemaoperativodistribucionEquipo")
    , @NamedQuery(name = "Equipo.findBySistemaoperativoversionEquipo", query = "SELECT e FROM Equipo e WHERE e.sistemaoperativoversionEquipo = :sistemaoperativoversionEquipo")
    , @NamedQuery(name = "Equipo.findBySistemaoperativosubversionEquipo", query = "SELECT e FROM Equipo e WHERE e.sistemaoperativosubversionEquipo = :sistemaoperativosubversionEquipo")
    , @NamedQuery(name = "Equipo.findByCantidadtotalinterfacesredEquipo", query = "SELECT e FROM Equipo e WHERE e.cantidadtotalinterfacesredEquipo = :cantidadtotalinterfacesredEquipo")
    , @NamedQuery(name = "Equipo.findByTienemovimientovmotionEquipo", query = "SELECT e FROM Equipo e WHERE e.tienemovimientovmotionEquipo = :tienemovimientovmotionEquipo")
    , @NamedQuery(name = "Equipo.findByFuncionalidadEquipo", query = "SELECT e FROM Equipo e WHERE e.funcionalidadEquipo = :funcionalidadEquipo")
    , @NamedQuery(name = "Equipo.findByProyectosistemaareaEquipo", query = "SELECT e FROM Equipo e WHERE e.proyectosistemaareaEquipo = :proyectosistemaareaEquipo")
    , @NamedQuery(name = "Equipo.findByResponsableapagadoEquipo", query = "SELECT e FROM Equipo e WHERE e.responsableapagadoEquipo = :responsableapagadoEquipo")
    , @NamedQuery(name = "Equipo.findByEtiquetaEquipo", query = "SELECT e FROM Equipo e WHERE e.etiquetaEquipo = :etiquetaEquipo")
    , @NamedQuery(name = "Equipo.findByControlbienesEquipo", query = "SELECT e FROM Equipo e WHERE e.controlbienesEquipo = :controlbienesEquipo")
    , @NamedQuery(name = "Equipo.findByFechaadquisicionEquipo", query = "SELECT e FROM Equipo e WHERE e.fechaadquisicionEquipo = :fechaadquisicionEquipo")
    , @NamedQuery(name = "Equipo.findByFaseadquisicionEquipo", query = "SELECT e FROM Equipo e WHERE e.faseadquisicionEquipo = :faseadquisicionEquipo")
    , @NamedQuery(name = "Equipo.findByHitoadquisicionEquipo", query = "SELECT e FROM Equipo e WHERE e.hitoadquisicionEquipo = :hitoadquisicionEquipo")
    , @NamedQuery(name = "Equipo.findByObservacionEquipo", query = "SELECT e FROM Equipo e WHERE e.observacionEquipo = :observacionEquipo")
    , @NamedQuery(name = "Equipo.findByServicioEquipo", query = "SELECT e FROM Equipo e WHERE e.servicioEquipo = :servicioEquipo")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EQUIPO")
    private Integer idEquipo;
    @Size(max = 16)
    @Column(name = "CODIGO_EQUIPO")
    private String codigoEquipo;
    @Size(max = 32)
    @Column(name = "NOMBRE_EQUIPO")
    private String nombreEquipo;
    @Size(max = 16)
    @Column(name = "POSICIONRACK_EQUIPO")
    private String posicionrackEquipo;
    @Size(max = 8)
    @Column(name = "UNIDADRACK_EQUIPO")
    private String unidadrackEquipo;
    @Size(max = 16)
    @Column(name = "MARCA_EQUIPO")
    private String marcaEquipo;
    @Size(max = 64)
    @Column(name = "MTM_EQUIPO")
    private String mtmEquipo;
    @Size(max = 32)
    @Column(name = "SERIE_EQUIPO")
    private String serieEquipo;
    @Size(max = 32)
    @Column(name = "MODELO_EQUIPO")
    private String modeloEquipo;
    @Size(max = 32)
    @Column(name = "PIDUID_EQUIPO")
    private String piduidEquipo;
    @Size(max = 64)
    @Column(name = "HOSTNAME_EQUIPO")
    private String hostnameEquipo;
    @Size(max = 16)
    @Column(name = "IPADMINISTRACION_EQUIPO")
    private String ipadministracionEquipo;
    @Size(max = 16)
    @Column(name = "IPPRODUCCION_EQUIPO")
    private String ipproduccionEquipo;
    @Size(max = 16)
    @Column(name = "IPVMOTION_EQUIPO")
    private String ipvmotionEquipo;
    @Column(name = "ESTADO_EQUIPO")
    private Boolean estadoEquipo;
    @Size(max = 8)
    @Column(name = "CANTIDADPROCESADORES_EQUIPO")
    private String cantidadprocesadoresEquipo;
    @Size(max = 16)
    @Column(name = "VELOCIDADPROCESADOR_EQUIPO")
    private String velocidadprocesadorEquipo;
    @Size(max = 32)
    @Column(name = "VERSIONPROCESADOR_EQUIPO")
    private String versionprocesadorEquipo;
    @Column(name = "CANTIDADCORES_EQUIPO")
    private Integer cantidadcoresEquipo;
    @Column(name = "CANTIDADTHREADSCORES_EQUIPO")
    private Integer cantidadthreadscoresEquipo;
    @Size(max = 16)
    @Column(name = "CAPACIDADINSTALADARAM_EQUIPO")
    private String capacidadinstaladaramEquipo;
    @Size(max = 16)
    @Column(name = "VELOCIDADRAM_EQUIPO")
    private String velocidadramEquipo;
    @Column(name = "CANTIDADSLOTSRAM_EQUIPO")
    private Integer cantidadslotsramEquipo;
    @Size(max = 8)
    @Column(name = "CANTIDADSLOTSUTILIZADASRAM_EQUIPO")
    private String cantidadslotsutilizadasramEquipo;
    @Size(max = 256)
    @Column(name = "POSICIONESUTILIZADASSLOTSRAM_EQUIPO")
    private String posicionesutilizadasslotsramEquipo;
    @Column(name = "CANTIDADTOTALDISCOS_EQUIPO")
    private Integer cantidadtotaldiscosEquipo;
    @Column(name = "CANTIDADDISCOSUTILIZADOS_EQUIPO")
    private Integer cantidaddiscosutilizadosEquipo;
    @Column(name = "CANTIDADDISCOSVACIOS_EQUIPO")
    private Integer cantidaddiscosvaciosEquipo;
    @Size(max = 256)
    @Column(name = "CAPACIDADTOTALDISCO_EQUIPO")
    private String capacidadtotaldiscoEquipo;
    @Size(max = 8)
    @Column(name = "FRUDISCO_EQUIPO")
    private String frudiscoEquipo;
    @Size(max = 256)
    @Column(name = "INFORMACIONDISCO_EQUIPO")
    private String informaciondiscoEquipo;
    @Column(name = "RAIDDISCO_EQUIPO")
    private Integer raiddiscoEquipo;
    @Size(max = 16)
    @Column(name = "SISTEMAOPERATIVO_EQUIPO")
    private String sistemaoperativoEquipo;
    @Size(max = 16)
    @Column(name = "SISTEMAOPERATIVODISTRIBUCION_EQUIPO")
    private String sistemaoperativodistribucionEquipo;
    @Size(max = 8)
    @Column(name = "SISTEMAOPERATIVOVERSION_EQUIPO")
    private String sistemaoperativoversionEquipo;
    @Size(max = 8)
    @Column(name = "SISTEMAOPERATIVOSUBVERSION_EQUIPO")
    private String sistemaoperativosubversionEquipo;
    @Column(name = "CANTIDADTOTALINTERFACESRED_EQUIPO")
    private Integer cantidadtotalinterfacesredEquipo;
    @Column(name = "TIENEMOVIMIENTOVMOTION_EQUIPO")
    private Boolean tienemovimientovmotionEquipo;
    @Size(max = 512)
    @Column(name = "FUNCIONALIDAD_EQUIPO")
    private String funcionalidadEquipo;
    @Size(max = 64)
    @Column(name = "PROYECTOSISTEMAAREA_EQUIPO")
    private String proyectosistemaareaEquipo;
    @Size(max = 64)
    @Column(name = "RESPONSABLEAPAGADO_EQUIPO")
    private String responsableapagadoEquipo;
    @Size(max = 32)
    @Column(name = "ETIQUETA_EQUIPO")
    private String etiquetaEquipo;
    @Size(max = 8)
    @Column(name = "CONTROLBIENES_EQUIPO")
    private String controlbienesEquipo;
    @Column(name = "FECHAADQUISICION_EQUIPO")
    @Temporal(TemporalType.DATE)
    private Date fechaadquisicionEquipo;
    @Size(max = 8)
    @Column(name = "FASEADQUISICION_EQUIPO")
    private String faseadquisicionEquipo;
    @Size(max = 32)
    @Column(name = "HITOADQUISICION_EQUIPO")
    private String hitoadquisicionEquipo;
    @Size(max = 512)
    @Column(name = "OBSERVACION_EQUIPO")
    private String observacionEquipo;
    @Lob
    @Column(name = "IMAGEN_EQUIPO")
    private byte[] imagenEquipo;
    @Size(max = 64)
    @Column(name = "SERVICIO_EQUIPO")
    private String servicioEquipo;
    @OneToMany(mappedBy = "idEquipo")
    private List<DetalleEquiposMantenimiento> detalleEquiposMantenimientoList;
    @OneToMany(mappedBy = "idEquipo")
    private List<MovimientofisicoEquipo> movimientofisicoEquipoList;
    @OneToMany(mappedBy = "idEquipo")
    private List<AlarmaFisica> alarmaFisicaList;

    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne
    private Proveedor idProveedor;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "ID_RACK", referencedColumnName = "ID_RACK")
    @ManyToOne
    private Rack idRack;
    @JoinColumn(name = "ID_TIPOEQUIPO", referencedColumnName = "ID_TIPOEQUIPO")
    @ManyToOne
    private TipoEquipo idTipoequipo;
    @OneToMany(mappedBy = "idEquipo")
    private List<Host> hostList;

    public Equipo() {
    }

    public Equipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getCodigoEquipo() {
        return codigoEquipo;
    }

    public void setCodigoEquipo(String codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getPosicionrackEquipo() {
        return posicionrackEquipo;
    }

    public void setPosicionrackEquipo(String posicionrackEquipo) {
        this.posicionrackEquipo = posicionrackEquipo;
    }

    public String getUnidadrackEquipo() {
        return unidadrackEquipo;
    }

    public void setUnidadrackEquipo(String unidadrackEquipo) {
        this.unidadrackEquipo = unidadrackEquipo;
    }

    public String getMarcaEquipo() {
        return marcaEquipo;
    }

    public void setMarcaEquipo(String marcaEquipo) {
        this.marcaEquipo = marcaEquipo;
    }

    public String getMtmEquipo() {
        return mtmEquipo;
    }

    public void setMtmEquipo(String mtmEquipo) {
        this.mtmEquipo = mtmEquipo;
    }

    public String getSerieEquipo() {
        return serieEquipo;
    }

    public void setSerieEquipo(String serieEquipo) {
        this.serieEquipo = serieEquipo;
    }

    public String getModeloEquipo() {
        return modeloEquipo;
    }

    public void setModeloEquipo(String modeloEquipo) {
        this.modeloEquipo = modeloEquipo;
    }

    public String getPiduidEquipo() {
        return piduidEquipo;
    }

    public void setPiduidEquipo(String piduidEquipo) {
        this.piduidEquipo = piduidEquipo;
    }

    public String getHostnameEquipo() {
        return hostnameEquipo;
    }

    public void setHostnameEquipo(String hostnameEquipo) {
        this.hostnameEquipo = hostnameEquipo;
    }

    public String getIpadministracionEquipo() {
        return ipadministracionEquipo;
    }

    public void setIpadministracionEquipo(String ipadministracionEquipo) {
        this.ipadministracionEquipo = ipadministracionEquipo;
    }

    public String getIpproduccionEquipo() {
        return ipproduccionEquipo;
    }

    public void setIpproduccionEquipo(String ipproduccionEquipo) {
        this.ipproduccionEquipo = ipproduccionEquipo;
    }

    public String getIpvmotionEquipo() {
        return ipvmotionEquipo;
    }

    public void setIpvmotionEquipo(String ipvmotionEquipo) {
        this.ipvmotionEquipo = ipvmotionEquipo;
    }

    public Boolean getEstadoEquipo() {
        return estadoEquipo;
    }

    public void setEstadoEquipo(Boolean estadoEquipo) {
        this.estadoEquipo = estadoEquipo;
    }

    public String getCantidadprocesadoresEquipo() {
        return cantidadprocesadoresEquipo;
    }

    public void setCantidadprocesadoresEquipo(String cantidadprocesadoresEquipo) {
        this.cantidadprocesadoresEquipo = cantidadprocesadoresEquipo;
    }

    public String getVelocidadprocesadorEquipo() {
        return velocidadprocesadorEquipo;
    }

    public void setVelocidadprocesadorEquipo(String velocidadprocesadorEquipo) {
        this.velocidadprocesadorEquipo = velocidadprocesadorEquipo;
    }

    public String getVersionprocesadorEquipo() {
        return versionprocesadorEquipo;
    }

    public void setVersionprocesadorEquipo(String versionprocesadorEquipo) {
        this.versionprocesadorEquipo = versionprocesadorEquipo;
    }

    public Integer getCantidadcoresEquipo() {
        return cantidadcoresEquipo;
    }

    public void setCantidadcoresEquipo(Integer cantidadcoresEquipo) {
        this.cantidadcoresEquipo = cantidadcoresEquipo;
    }

    public Integer getCantidadthreadscoresEquipo() {
        return cantidadthreadscoresEquipo;
    }

    public void setCantidadthreadscoresEquipo(Integer cantidadthreadscoresEquipo) {
        this.cantidadthreadscoresEquipo = cantidadthreadscoresEquipo;
    }

    public String getCapacidadinstaladaramEquipo() {
        return capacidadinstaladaramEquipo;
    }

    public void setCapacidadinstaladaramEquipo(String capacidadinstaladaramEquipo) {
        this.capacidadinstaladaramEquipo = capacidadinstaladaramEquipo;
    }

    public String getVelocidadramEquipo() {
        return velocidadramEquipo;
    }

    public void setVelocidadramEquipo(String velocidadramEquipo) {
        this.velocidadramEquipo = velocidadramEquipo;
    }

    public Integer getCantidadslotsramEquipo() {
        return cantidadslotsramEquipo;
    }

    public void setCantidadslotsramEquipo(Integer cantidadslotsramEquipo) {
        this.cantidadslotsramEquipo = cantidadslotsramEquipo;
    }

    public String getCantidadslotsutilizadasramEquipo() {
        return cantidadslotsutilizadasramEquipo;
    }

    public void setCantidadslotsutilizadasramEquipo(String cantidadslotsutilizadasramEquipo) {
        this.cantidadslotsutilizadasramEquipo = cantidadslotsutilizadasramEquipo;
    }

    public String getPosicionesutilizadasslotsramEquipo() {
        return posicionesutilizadasslotsramEquipo;
    }

    public void setPosicionesutilizadasslotsramEquipo(String posicionesutilizadasslotsramEquipo) {
        this.posicionesutilizadasslotsramEquipo = posicionesutilizadasslotsramEquipo;
    }

    public Integer getCantidadtotaldiscosEquipo() {
        return cantidadtotaldiscosEquipo;
    }

    public void setCantidadtotaldiscosEquipo(Integer cantidadtotaldiscosEquipo) {
        this.cantidadtotaldiscosEquipo = cantidadtotaldiscosEquipo;
    }

    public Integer getCantidaddiscosutilizadosEquipo() {
        return cantidaddiscosutilizadosEquipo;
    }

    public void setCantidaddiscosutilizadosEquipo(Integer cantidaddiscosutilizadosEquipo) {
        this.cantidaddiscosutilizadosEquipo = cantidaddiscosutilizadosEquipo;
    }

    public Integer getCantidaddiscosvaciosEquipo() {
        return cantidaddiscosvaciosEquipo;
    }

    public void setCantidaddiscosvaciosEquipo(Integer cantidaddiscosvaciosEquipo) {
        this.cantidaddiscosvaciosEquipo = cantidaddiscosvaciosEquipo;
    }

    public String getCapacidadtotaldiscoEquipo() {
        return capacidadtotaldiscoEquipo;
    }

    public void setCapacidadtotaldiscoEquipo(String capacidadtotaldiscoEquipo) {
        this.capacidadtotaldiscoEquipo = capacidadtotaldiscoEquipo;
    }

    public String getFrudiscoEquipo() {
        return frudiscoEquipo;
    }

    public void setFrudiscoEquipo(String frudiscoEquipo) {
        this.frudiscoEquipo = frudiscoEquipo;
    }

    public String getInformaciondiscoEquipo() {
        return informaciondiscoEquipo;
    }

    public void setInformaciondiscoEquipo(String informaciondiscoEquipo) {
        this.informaciondiscoEquipo = informaciondiscoEquipo;
    }

    public Integer getRaiddiscoEquipo() {
        return raiddiscoEquipo;
    }

    public void setRaiddiscoEquipo(Integer raiddiscoEquipo) {
        this.raiddiscoEquipo = raiddiscoEquipo;
    }

    public String getSistemaoperativoEquipo() {
        return sistemaoperativoEquipo;
    }

    public void setSistemaoperativoEquipo(String sistemaoperativoEquipo) {
        this.sistemaoperativoEquipo = sistemaoperativoEquipo;
    }

    public String getSistemaoperativodistribucionEquipo() {
        return sistemaoperativodistribucionEquipo;
    }

    public void setSistemaoperativodistribucionEquipo(String sistemaoperativodistribucionEquipo) {
        this.sistemaoperativodistribucionEquipo = sistemaoperativodistribucionEquipo;
    }

    public String getSistemaoperativoversionEquipo() {
        return sistemaoperativoversionEquipo;
    }

    public void setSistemaoperativoversionEquipo(String sistemaoperativoversionEquipo) {
        this.sistemaoperativoversionEquipo = sistemaoperativoversionEquipo;
    }

    public String getSistemaoperativosubversionEquipo() {
        return sistemaoperativosubversionEquipo;
    }

    public void setSistemaoperativosubversionEquipo(String sistemaoperativosubversionEquipo) {
        this.sistemaoperativosubversionEquipo = sistemaoperativosubversionEquipo;
    }

    public Integer getCantidadtotalinterfacesredEquipo() {
        return cantidadtotalinterfacesredEquipo;
    }

    public void setCantidadtotalinterfacesredEquipo(Integer cantidadtotalinterfacesredEquipo) {
        this.cantidadtotalinterfacesredEquipo = cantidadtotalinterfacesredEquipo;
    }

    public Boolean getTienemovimientovmotionEquipo() {
        return tienemovimientovmotionEquipo;
    }

    public void setTienemovimientovmotionEquipo(Boolean tienemovimientovmotionEquipo) {
        this.tienemovimientovmotionEquipo = tienemovimientovmotionEquipo;
    }

    public String getFuncionalidadEquipo() {
        return funcionalidadEquipo;
    }

    public void setFuncionalidadEquipo(String funcionalidadEquipo) {
        this.funcionalidadEquipo = funcionalidadEquipo;
    }

    public String getProyectosistemaareaEquipo() {
        return proyectosistemaareaEquipo;
    }

    public void setProyectosistemaareaEquipo(String proyectosistemaareaEquipo) {
        this.proyectosistemaareaEquipo = proyectosistemaareaEquipo;
    }

    public String getResponsableapagadoEquipo() {
        return responsableapagadoEquipo;
    }

    public void setResponsableapagadoEquipo(String responsableapagadoEquipo) {
        this.responsableapagadoEquipo = responsableapagadoEquipo;
    }

    public String getEtiquetaEquipo() {
        return etiquetaEquipo;
    }

    public void setEtiquetaEquipo(String etiquetaEquipo) {
        this.etiquetaEquipo = etiquetaEquipo;
    }

    public String getControlbienesEquipo() {
        return controlbienesEquipo;
    }

    public void setControlbienesEquipo(String controlbienesEquipo) {
        this.controlbienesEquipo = controlbienesEquipo;
    }

    public Date getFechaadquisicionEquipo() {
        return fechaadquisicionEquipo;
    }

    public void setFechaadquisicionEquipo(Date fechaadquisicionEquipo) {
        this.fechaadquisicionEquipo = fechaadquisicionEquipo;
    }

    public String getFaseadquisicionEquipo() {
        return faseadquisicionEquipo;
    }

    public void setFaseadquisicionEquipo(String faseadquisicionEquipo) {
        this.faseadquisicionEquipo = faseadquisicionEquipo;
    }

    public String getHitoadquisicionEquipo() {
        return hitoadquisicionEquipo;
    }

    public void setHitoadquisicionEquipo(String hitoadquisicionEquipo) {
        this.hitoadquisicionEquipo = hitoadquisicionEquipo;
    }

    public String getObservacionEquipo() {
        return observacionEquipo;
    }

    public void setObservacionEquipo(String observacionEquipo) {
        this.observacionEquipo = observacionEquipo;
    }

    public byte[] getImagenEquipo() {
        return imagenEquipo;
    }

    public void setImagenEquipo(byte[] imagenEquipo) {
        this.imagenEquipo = imagenEquipo;
    }

    public String getServicioEquipo() {
        return servicioEquipo;
    }

    public void setServicioEquipo(String servicioEquipo) {
        this.servicioEquipo = servicioEquipo;
    }

    @XmlTransient
    public List<DetalleEquiposMantenimiento> getDetalleEquiposMantenimientoList() {
        return detalleEquiposMantenimientoList;
    }

    public void setDetalleEquiposMantenimientoList(List<DetalleEquiposMantenimiento> detalleEquiposMantenimientoList) {
        this.detalleEquiposMantenimientoList = detalleEquiposMantenimientoList;
    }

    @XmlTransient
    public List<MovimientofisicoEquipo> getMovimientofisicoEquipoList() {
        return movimientofisicoEquipoList;
    }

    public void setMovimientofisicoEquipoList(List<MovimientofisicoEquipo> movimientofisicoEquipoList) {
        this.movimientofisicoEquipoList = movimientofisicoEquipoList;
    }

    @XmlTransient
    public List<AlarmaFisica> getAlarmaFisicaList() {
        return alarmaFisicaList;
    }

    public void setAlarmaFisicaList(List<AlarmaFisica> alarmaFisicaList) {
        this.alarmaFisicaList = alarmaFisicaList;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Rack getIdRack() {
        return idRack;
    }

    public void setIdRack(Rack idRack) {
        this.idRack = idRack;
    }

    public TipoEquipo getIdTipoequipo() {
        return idTipoequipo;
    }

    public void setIdTipoequipo(TipoEquipo idTipoequipo) {
        this.idTipoequipo = idTipoequipo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<Host> getHostList() {
        return hostList;
    }

    public void setHostList(List<Host> hostList) {
        this.hostList = hostList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipo != null ? idEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.idEquipo == null && other.idEquipo != null) || (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipo{" + ", nombreEquipo=" + nombreEquipo + ", marcaEquipo=" + marcaEquipo + ", mtmEquipo=" + mtmEquipo + ", serieEquipo=" + serieEquipo + ", modeloEquipo=" + modeloEquipo + ", ipadministracionEquipo=" + ipadministracionEquipo + ", servicioEquipo=" + servicioEquipo + ", idRack=" + idRack + ", unidadrackEquipo=" + unidadrackEquipo + '}';
    }

}
