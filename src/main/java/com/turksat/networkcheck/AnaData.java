package com.turksat.networkcheck;

import javax.faces.bean.ManagedBean;

/**
 * Created by furkanmumcu on 07/08/2017.
 */

@ManagedBean
public class AnaData {
    private String SunucuAdi;
    private String SunucuIp;
    private String SunucuUygulamaTipi;
    private String SunucuTipi;
    private String SunucuPort;
    private String SunucuTuru;
    private String Ulasim;
    private String SonUlasim;
    private String SonDurumZamani;
    private String SonDurum;

    public String getSonDurum() {
        return SonDurum;
    }

    public void setSonDurum(String sonDurum) {
        SonDurum = sonDurum;
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

    public String getSunucuUygulamaTipi() {
        return SunucuUygulamaTipi;
    }

    public void setSunucuUygulamaTipi(String sunucuUygulamaTipi) {
        SunucuUygulamaTipi = sunucuUygulamaTipi;
    }

    public String getSunucuTipi() {
        return SunucuTipi;
    }

    public void setSunucuTipi(String sunucuTipi) {
        SunucuTipi = sunucuTipi;
    }

    public String getSunucuPort() {
        return SunucuPort;
    }

    public void setSunucuPort(String sunucuPort) {
        SunucuPort = sunucuPort;
    }

    public String getSunucuTuru() {
        return SunucuTuru;
    }

    public void setSunucuTuru(String sunucuTuru) {
        SunucuTuru = sunucuTuru;
    }

    public String getUlasim() {
        return Ulasim;
    }

    public void setUlasim(String ulasim) {
        Ulasim = ulasim;
    }

    public String getSonUlasim() {
        return SonUlasim;
    }

    public void setSonUlasim(String sonUlasim) {
        SonUlasim = sonUlasim;
    }

    public String getSonDurumZamani() {
        return SonDurumZamani;
    }

    public void setSonDurumZamani(String sonDurumZamani) {
        SonDurumZamani = sonDurumZamani;
    }
}

