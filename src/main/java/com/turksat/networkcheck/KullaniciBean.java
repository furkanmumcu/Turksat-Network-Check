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
    private String Ad;
    private String SoyAd;
    private String RolTanimla;
    public String getAd() {
        return Ad;
    }

    public void setAd(String ad) {
        Ad = ad;
    }

    public String getSoyAd() {
        return SoyAd;
    }

    public void setSoyAd(String soyAd) {
        SoyAd = soyAd;
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

    public String getRolTanimla() {
        return RolTanimla;
    }

    public void setRolTanimla(String rolTanimla) {
        RolTanimla = rolTanimla;
    }

    public void kisiDuzenleButonu() {

    }

    public void kisiAktifButonu() {

    }

    public void kisiPasifButonu() {

    }



}
