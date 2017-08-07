package com.turksat.networkcheck.view;

import javax.faces.bean.ManagedBean;

/**
 * Created by furkanmumcu on 07/08/2017.
 */

@ManagedBean

public class SunucuData {
    private String sunucuSanalAdi;
    private String sunucuAdi;
    private String sunucuIp;
    private String sunucuUgulamaTipi;
    private String sunucuTipi;
    private String sunucuPortBilgisi;
    private String sunucuTuru;

    public String getSunucuSanalAdi() {
        return sunucuSanalAdi;
    }

    public void setSunucuSanalAdi(String sunucuSanalAdi) {
        this.sunucuSanalAdi = sunucuSanalAdi;
    }

    public String getSunucuAdi() {
        return sunucuAdi;
    }

    public void setSunucuAdi(String sunucuAdi) {
        this.sunucuAdi = sunucuAdi;
    }

    public String getSunucuIp() {
        return sunucuIp;
    }

    public void setSunucuIp(String sunucuIp) {
        this.sunucuIp = sunucuIp;
    }

    public String getSunucuUgulamaTipi() {
        return sunucuUgulamaTipi;
    }

    public void setSunucuUgulamaTipi(String sunucuUgulamaTipi) {
        this.sunucuUgulamaTipi = sunucuUgulamaTipi;
    }

    public String getSunucuTipi() {
        return sunucuTipi;
    }

    public void setSunucuTipi(String sunucuTipi) {
        this.sunucuTipi = sunucuTipi;
    }

    public String getSunucuPortBilgisi() {
        return sunucuPortBilgisi;
    }

    public void setSunucuPortBilgisi(String sunucuPortBilgisi) {
        this.sunucuPortBilgisi = sunucuPortBilgisi;
    }

    public String getSunucuTuru() {
        return sunucuTuru;
    }

    public void setSunucuTuru(String sunucuTuru) {
        this.sunucuTuru = sunucuTuru;
    }
}
