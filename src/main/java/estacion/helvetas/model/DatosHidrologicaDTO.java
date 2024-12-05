package estacion.helvetas.model;

import java.sql.Date;
import java.sql.Timestamp;

public class DatosHidrologicaDTO {

    private int idUsuario;
    private String municipio;
    private String estacion;
    private String tipoEstacion;
    private String nombreCompleto;
    private java.sql.Timestamp fechaReg = new java.sql.Timestamp(System.currentTimeMillis());
    private Float tempMin;
    private Float tempMax;
    private Float tempAmb;
    private Float pcpn;
    private Float taevap;
    private String dirViento;
    private Float velViento;
    private int idEstacion;
    private boolean codTipoEstacion;

    public DatosHidrologicaDTO() {
        this.idUsuario = idUsuario;
        this.municipio = municipio;
        this.estacion = estacion;
        this.tipoEstacion = tipoEstacion;
        this.nombreCompleto = nombreCompleto;
        this.fechaReg = fechaReg;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.tempAmb = tempAmb;
        this.pcpn = pcpn;
        this.taevap = taevap;
        this.dirViento = dirViento;
        this.velViento = velViento;
        this.idEstacion = idEstacion;
        this.codTipoEstacion = codTipoEstacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public String getTipoEstacion() {
        return tipoEstacion;
    }

    public void setTipoEstacion(String tipoEstacion) {
        this.tipoEstacion = tipoEstacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Timestamp getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Timestamp fechaReg) {
        this.fechaReg = fechaReg;
    }

    public Date getFechaRegAsDate() {
        return new Date(fechaReg.getTime());
    }

    public void setFechaRegAsDate(Date fechaRegDate) {
        this.fechaReg = new Timestamp(fechaRegDate.getTime());
    }

    public Float getTempMin() {
        return tempMin;
    }

    public void setTempMin(Float tempMin) {
        this.tempMin = tempMin;
    }

    public Float getTempMax() {
        return tempMax;
    }

    public void setTempMax(Float tempMax) {
        this.tempMax = tempMax;
    }

    public Float getTempAmb() {
        return tempAmb;
    }

    public void setTempAmb(Float tempAmb) {
        this.tempAmb = tempAmb;
    }

    public Float getPcpn() {
        return pcpn;
    }

    public void setPcpn(Float pcpn) {
        this.pcpn = pcpn;
    }

    public Float getTaevap() {
        return taevap;
    }

    public void setTaevap(Float taevap) {
        this.taevap = taevap;
    }

    public String getDirViento() {
        return dirViento;
    }

    public void setDirViento(String dirViento) {
        this.dirViento = dirViento;
    }

    public Float getVelViento() {
        return velViento;
    }

    public void setVelViento(Float velViento) {
        this.velViento = velViento;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public boolean isCodTipoEstacion() {
        return codTipoEstacion;
    }

    public void setCodTipoEstacion(boolean codTipoEstacion) {
        this.codTipoEstacion = codTipoEstacion;
    }
}