package com.turksat.networkcheck.view;

import javax.faces.bean.ManagedBean;

/**
 * Created by furkanmumcu on 07/08/2017.
 */

@ManagedBean
public class AnaData {
    private String sunucuAdi;
    private String sunucuIp;
    private String sunucuUygulamaTipi;
    private String sunucuTipi;
    private String sunucuPort;
    private String sunucuTuru;
    private String ulasim;
    private String sonUlasim;
    private String sonDurumZamani;
    private String sonDurum;

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

    public String getSunucuUygulamaTipi() {
        return sunucuUygulamaTipi;
    }

    public void setSunucuUygulamaTipi(String sunucuUygulamaTipi) {
        this.sunucuUygulamaTipi = sunucuUygulamaTipi;
    }

    public String getSunucuTipi() {
        return sunucuTipi;
    }

    public void setSunucuTipi(String sunucuTipi) {
        this.sunucuTipi = sunucuTipi;
    }

    public String getSunucuPort() {
        return sunucuPort;
    }

    public void setSunucuPort(String sunucuPort) {
        this.sunucuPort = sunucuPort;
    }

    public String getSunucuTuru() {
        return sunucuTuru;
    }

    public void setSunucuTuru(String sunucuTuru) {
        this.sunucuTuru = sunucuTuru;
    }

    public String getUlasim() {
        return ulasim;
    }

    public void setUlasim(String ulasim) {
        this.ulasim = ulasim;
    }

    public String getSonUlasim() {
        return sonUlasim;
    }

    public void setSonUlasim(String sonUlasim) {
        this.sonUlasim = sonUlasim;
    }

    public String getSonDurumZamani() {
        return sonDurumZamani;
    }

    public void setSonDurumZamani(String sonDurumZamani) {
        this.sonDurumZamani = sonDurumZamani;
    }

    public String getSonDurum() {
        return sonDurum;
    }

    public void setSonDurum(String sonDurum) {
        this.sonDurum = sonDurum;
    }
}

