package com.turksat.networkcheck;

import javax.faces.bean.ManagedBean;

/**
 * Created by furkanmumcu on 07/08/2017.
 */
@ManagedBean
public class KullaniciData {
    private String KullaniciAdi;
    private String SoyAd;
    private String Ad;
    private String Sifre;
    private String SifreTekrar;
    private String TelNo;
    private String Eposta;
    private String Rol;

    public String getKullaniciAdi() {
        return KullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        KullaniciAdi = kullaniciAdi;
    }

    public String getSoyAd() {
        return SoyAd;
    }

    public void setSoyAd(String soyAd) {
        SoyAd = soyAd;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String ad) {
        Ad = ad;
    }

    public String getSifre() {
        return Sifre;
    }

    public void setSifre(String sifre) {
        Sifre = sifre;
    }

    public String getSifreTekrar() {
        return SifreTekrar;
    }

    public void setSifreTekrar(String sifreTekrar) {
        SifreTekrar = sifreTekrar;
    }

    public String getTelNo() {
        return TelNo;
    }

    public void setTelNo(String telNo) {
        TelNo = telNo;
    }

    public String getEposta() {
        return Eposta;
    }

    public void setEposta(String eposta) {
        Eposta = eposta;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }
}

