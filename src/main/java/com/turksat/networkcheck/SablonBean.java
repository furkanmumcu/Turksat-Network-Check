package com.turksat.networkcheck;

import javax.faces.bean.ManagedBean;

/**
 * Created by furkanmumcu on 07/08/2017.
 */

@ManagedBean
public class SablonBean {
    private String KullaniciEposta;
    private String KullaniciTelNo;
    private String KullaniciSifre;
    private String KullaniciYeniSifre;
    private String YeniSifreTekrar;

    public String getKullaniciEposta() {
        return KullaniciEposta;
    }

    public void setKullaniciEposta(String kullaniciEposta) {
        KullaniciEposta = kullaniciEposta;
    }

    public String getKullaniciTelNo() {
        return KullaniciTelNo;
    }

    public void setKullaniciTelNo(String kullaniciTelNo) {
        KullaniciTelNo = kullaniciTelNo;
    }

    public String getKullaniciSifre() {
        return KullaniciSifre;
    }

    public void setKullaniciSifre(String kullaniciSifre) {
        KullaniciSifre = kullaniciSifre;
    }

    public String getKullaniciYeniSifre() {
        return KullaniciYeniSifre;
    }

    public void setKullaniciYeniSifre(String kullaniciYeniSifre) {
        KullaniciYeniSifre = kullaniciYeniSifre;
    }

    public String getYeniSifreTekrar() {
        return YeniSifreTekrar;
    }

    public void setYeniSifreTekrar(String yeniSifreTekrar) {
        YeniSifreTekrar = yeniSifreTekrar;
    }

    public void SifreGuncelleButonu() {
    }

    public void BilgiGuncelleButonu() {
    }

    public String anasayfaButonu() {
        return "anasayfa?faces-redirect=true";
    }

    public String kullaniciButonu() {
        return "kullaniciislem?faces-redirect=true";
    }

    public String sunucuButonu() {
        return "sunucuislem?faces-redirect=true";
    }


}
