package com.turksat.networkcheck;

import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by furkanmumcu on 07/08/2017.
 */
@ManagedBean
public class SunucuBean {
    private String SunucuBilgisi;
    private String SunucuTipi;
    private String SunucuTuru;
    private String SunucuUygulamaTipi;
    private String SunucuSifre;

    private String[] Protokol;
    private String[] HataMesaj;

    private List<SunucuData> SunucuTablo=new ArrayList<SunucuData>(  );
    private int KontrolPeriyodu;
    private String SunucuKullaniciAdi;

    public String getSunucuSifre() {
        return SunucuSifre;
    }

    public void setSunucuSifre(String sunucuSifre) {
        SunucuSifre = sunucuSifre;
    }


    public String getSunucuKullaniciAdi() {
        return SunucuKullaniciAdi;
    }

    public void setSunucuKullaniciAdi(String sunucuKullaniciAdi) {
        SunucuKullaniciAdi = sunucuKullaniciAdi;
    }

    public int getKontrolPeriyodu() {
        return KontrolPeriyodu;
    }

    public void setKontrolPeriyodu(int kontrolPeriyodu) {
        KontrolPeriyodu = kontrolPeriyodu;
    }

    public List<SunucuData> getSunucuTablo() {
        return SunucuTablo;
    }

    public void setSunucuTablo(List<SunucuData> sunucuTablo) {
        SunucuTablo = sunucuTablo;
    }

    public String getSunucuTipi() {
        return SunucuTipi;
    }

    public void setSunucuTipi(String sunucuTipi) {
        SunucuTipi = sunucuTipi;
    }

    public String getSunucuTuru() {
        return SunucuTuru;
    }

    public void setSunucuTuru(String sunucuTuru) {
        SunucuTuru = sunucuTuru;
    }

    public String getSunucuUygulamaTipi() {
        return SunucuUygulamaTipi;
    }

    public void setSunucuUygulamaTipi(String sunucuUygulamaTipi) {
        SunucuUygulamaTipi = sunucuUygulamaTipi;
    }


    public String[] getHataMesaj() {
        return HataMesaj;
    }

    public void setHataMesaj(String[] hataMesaj) {
        HataMesaj = hataMesaj;
    }

    public String getSunucuBilgisi() {
        return SunucuBilgisi;
    }

    public void setSunucuBilgisi(String sunucuBilgisi) {
        SunucuBilgisi = sunucuBilgisi;
    }

    public String[] getProtokol() {
        return Protokol;
    }

    public void setProtokol(String[] protokol) {
        Protokol = protokol;
    }


    public void sunucuaraButonu()  {    }

    public void tanimlaButonu() {
        // TODO: 4.08.2017
        //Sunucu sunucu = new Sunucu();

        //sets

        //Service service = new Service();
        //service.addSunucu(sunucu);
    }

    public void sunucuDuzenleButonu(){}

    public void sunucuAktifButonu(){}

    public void sunucuPasifButonu(){}

}
