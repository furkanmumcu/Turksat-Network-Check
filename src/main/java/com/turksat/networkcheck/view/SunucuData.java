package com.turksat.networkcheck.view;

import javax.faces.bean.ManagedBean;

/**
 * Created by furkanmumcu on 07/08/2017.
 */

@ManagedBean(name = "sunucuData") // dene

public class SunucuData {
    private String sunucuSanalAdi;
    private String sunucuIp;
    private String sunucuPortBilgisi;
    private int kontrolPeriyodu; // new
    private String sunucuKullaniciAdi; // new
    private String sunucuSifre; // new
    private String sunucuTipi;
    private String sunucuUygulamaTipi;
    private String sunucuTuru;
    private String protokol; //new
    private String[] hataMesaj; //new


    private String sunucuAdi;

    public String getProtokol() {
        return protokol;
    }

    public void setProtokol(String protokol) {
        this.protokol = protokol;
    }

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


    //new
    public void tanimlaButonu(){

    }
}
