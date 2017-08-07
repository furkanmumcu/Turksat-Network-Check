package com.turksat.networkcheck;

import javax.faces.bean.ManagedBean;

/**
 * Created by furkanmumcu on 07/08/2017.
 */

@ManagedBean

public class SunucuData {
    private String SunucuSanalAdi;
    private String SunucuAdi;
    private String SunucuIp;
    private String SunucuUgulamaTipi;
    private int SunucuTipi;
    private String SunucuPortBilgisi;
    private String SunucuTuru;


    public String getSunucuSanalAdi() {
        return SunucuSanalAdi;
    }

    public void setSunucuSanalAdi(String sunucuSanalAdi) {
        SunucuSanalAdi = sunucuSanalAdi;
    }

    public String getSunucuAdi() {
        return SunucuAdi;
    }

    public void setSunucuAdi(String sunucuAdi) {
        SunucuAdi = sunucuAdi;
    }

    public String getSunucuIp() {
        return SunucuIp;
    }

    public void setSunucuIp(String sunucuIp) {
        SunucuIp = sunucuIp;
    }

    public String getSunucuUgulamaTipi() {
        return SunucuUgulamaTipi;
    }

    public void setSunucuUgulamaTipi(String sunucuUgulamaTipit) {
        SunucuUgulamaTipi = sunucuUgulamaTipit;
    }

    public int getSunucuTipi() {
        return SunucuTipi;
    }

    public void setSunucuTipi(int sunucuTipi) {
        SunucuTipi = sunucuTipi;
    }

    public String getSunucuPortBilgisi() {
        return SunucuPortBilgisi;
    }

    public void setSunucuPortBilgisi(String sunucuPortBilgisi) {
        SunucuPortBilgisi = sunucuPortBilgisi;
    }

    public String getSunucuTuru() {
        return SunucuTuru;
    }

    public void setSunucuTuru(String sunucuTuru) {
        SunucuTuru = sunucuTuru;
    }
}
