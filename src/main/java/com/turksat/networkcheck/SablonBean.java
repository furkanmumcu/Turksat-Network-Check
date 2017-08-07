package com.turksat.networkcheck;

import javax.faces.bean.ManagedBean;

/**
 * Created by furkanmumcu on 07/08/2017.
 */

@ManagedBean
public class SablonBean {
    private String kullaniciEposta;
    private String kullaniciTelNo;
    private String kullaniciSifre;
    private String kullaniciYeniSifre;
    private String yeniSifreTekrar;

    public String getKullaniciEposta() {
        return kullaniciEposta;
    }

    public void setKullaniciEposta(String kullaniciEposta) {
        this.kullaniciEposta = kullaniciEposta;
    }

    public String getKullaniciTelNo() {
        return kullaniciTelNo;
    }

    public void setKullaniciTelNo(String kullaniciTelNo) {
        this.kullaniciTelNo = kullaniciTelNo;
    }

    public String getKullaniciSifre() {
        return kullaniciSifre;
    }

    public void setKullaniciSifre(String kullaniciSifre) {
        this.kullaniciSifre = kullaniciSifre;
    }

    public String getKullaniciYeniSifre() {
        return kullaniciYeniSifre;
    }

    public void setKullaniciYeniSifre(String kullaniciYeniSifre) {
        this.kullaniciYeniSifre = kullaniciYeniSifre;
    }

    public String getYeniSifreTekrar() {
        return yeniSifreTekrar;
    }

    public void setYeniSifreTekrar(String yeniSifreTekrar) {
        this.yeniSifreTekrar = yeniSifreTekrar;
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
