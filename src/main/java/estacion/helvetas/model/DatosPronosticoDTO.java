package estacion.helvetas.model;

public class DatosPronosticoDTO {

    private int idUsuario;
    private String municipio;
    private String zona;
    private String nombreCompleto;
    private java.sql.Timestamp fecha = new java.sql.Timestamp(System.currentTimeMillis());
    private Float tempMin;
    private Float tempMax;
    private Float pcpn;
    // private int idFenologia;

    public DatosPronosticoDTO() {
        this.idUsuario = idUsuario;
        this.municipio = municipio;
        this.zona = zona;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pcpn = pcpn;
        // this.idFenologia = idFenologia;
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

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public java.sql.Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Timestamp fecha) {
        this.fecha = fecha;
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

    public Float getPcpn() {
        return pcpn;
    }

    public void setPcpn(Float pcpn) {
        this.pcpn = pcpn;
    }

    // public int getIdFenologia() {
    // return idFenologia;
    // }

    // public void setIdFenologia(int idFenologia) {
    // this.idFenologia = idFenologia;
    // }

    // Getters y setters
}