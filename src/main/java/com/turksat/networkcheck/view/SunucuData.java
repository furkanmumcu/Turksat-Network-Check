package com.turksat.networkcheck.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by furkanmumcu on 07/08/2017.
 */

@ManagedBean(name = "sunucuData") // dene
@SessionScoped
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
    private String hataMesaj; //new

    private String id;

    private String sunucuAdi;

    private boolean aktifPasif;

    public boolean isAktifPasif() {
        return aktifPasif;
    }

    public void setAktifPasif(boolean aktifPasif) {
        this.aktifPasif = aktifPasif;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getKontrolPeriyodu() {
        return kontrolPeriyodu;
    }

    public void setKontrolPeriyodu(int kontrolPeriyodu) {
        this.kontrolPeriyodu = kontrolPeriyodu;
    }

    public String getSunucuKullaniciAdi() {
        return sunucuKullaniciAdi;
    }

    public void setSunucuKullaniciAdi(String sunucuKullaniciAdi) {
        this.sunucuKullaniciAdi = sunucuKullaniciAdi;
    }

    public String getSunucuSifre() {
        return sunucuSifre;
    }

    public void setSunucuSifre(String sunucuSifre) {
        this.sunucuSifre = sunucuSifre;
    }

    public String getHataMesaj() {
        return hataMesaj;
    }

    public void setHataMesaj(String hataMesaj) {
        this.hataMesaj = hataMesaj;
    }

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
        System.out.println(sunucuSanalAdi);
        System.out.println(protokol);


    }
}
