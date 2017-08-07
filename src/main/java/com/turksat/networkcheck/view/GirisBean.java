package com.turksat.networkcheck.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by furkanmumcu on 07/08/2017.
 */

@ManagedBean
@SessionScoped
public class GirisBean {
    private String kullaniciAdi;
    private String sifre;
    private String eposta;
    private String kullaniciAdiUnuttum;

    public String getKullaniciAdiUnuttum() {
        return kullaniciAdiUnuttum;
    }

    public void setKullaniciAdiUnuttum(String kullaniciAdiUnuttum) {
        this.kullaniciAdiUnuttum = kullaniciAdiUnuttum;
    }

    public GirisBean(){}

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public void girisButonu() {  }

    public void dogrulaButonu() {

    }
}

