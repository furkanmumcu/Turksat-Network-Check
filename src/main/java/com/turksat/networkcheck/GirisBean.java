package com.turksat.networkcheck;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by furkanmumcu on 07/08/2017.
 */

@ManagedBean
@SessionScoped
public class GirisBean {
    private String KullaniciAdi;
    private String Sifre;
    private String Eposta;
    private String kullaniciAdiUnuttum;

    public String getKullaniciAdiUnuttum() {
        return kullaniciAdiUnuttum;
    }

    public void setKullaniciAdiUnuttum(String kullaniciAdiUnuttum) {
        this.kullaniciAdiUnuttum = kullaniciAdiUnuttum;
    }

    public GirisBean(){}

    public String getKullaniciAdi() {
        return KullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        KullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return Sifre;
    }

    public void setSifre(String sifre) {
        Sifre = sifre;
    }

    public String getEposta() {
        return Eposta;
    }

    public void setEposta(String eposta) {
        Eposta = eposta;
    }

    public void girisButonu() {  }

    public void dogrulaButonu() {

    }
}

