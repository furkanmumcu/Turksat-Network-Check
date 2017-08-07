package com.turksat.networkcheck;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by furkanmumcu on 07/08/2017.
 */

@SessionScoped
@ManagedBean
public class KullaniciBean {
    private String ad;
    private String soyAd;
    private String rolTanimla;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyAd() {
        return soyAd;
    }

    public void setSoyAd(String soyAd) {
        this.soyAd = soyAd;
    }

    public String getRolTanimla() {
        return rolTanimla;
    }

    public void setRolTanimla(String rolTanimla) {
        this.rolTanimla = rolTanimla;
    }

    private List<KullaniciData> kullaniciTablo=new ArrayList<KullaniciData>(  );

    public List<KullaniciData> getKullaniciTablo() {
        return kullaniciTablo;
    }

    public void setKullaniciTablo(List<KullaniciData> kullaniciTablo) {
        this.kullaniciTablo = kullaniciTablo;
    }

    public void kisiKaydetButonu() {

    }

    public void kisiAraButonu() {


    }

    public void kisiDuzenleButonu() {

    }

    public void kisiAktifButonu() {

    }

    public void kisiPasifButonu() {

    }



}
