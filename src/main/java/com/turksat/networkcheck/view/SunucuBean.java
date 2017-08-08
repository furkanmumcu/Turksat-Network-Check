package com.turksat.networkcheck.view;

import com.turksat.networkcheck.Service.Service;
import com.turksat.networkcheck.model.Sunucu;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by furkanmumcu on 07/08/2017.
 */
@ManagedBean
public class SunucuBean {
    private String sunucuBilgisi;
    private String sunucuTipi;
    private String sunucuTuru;
    private String sunucuUygulamaTipi;
    private String sunucuSifre;


    //@ManagedProperty(value = "#{sunucuDataBean}")
    private SunucuData sunucuData1;

    private String[] protokol;
    private String[] hataMesaj;

    private List<SunucuData> sunucuTablo=new ArrayList<SunucuData>(  );
    private int kontrolPeriyodu;
    private String sunucuKullaniciAdi;

    public String getSunucuBilgisi() {
        return sunucuBilgisi;
    }

    public void setSunucuBilgisi(String sunucuBilgisi) {
        this.sunucuBilgisi = sunucuBilgisi;
    }

    public String getSunucuTipi() {
        return sunucuTipi;
    }

    public void setSunucuTipi(String sunucuTipi) {
        this.sunucuTipi = sunucuTipi;
    }

    public String getSunucuTuru() {
        return sunucuTuru;
    }

    public void setSunucuTuru(String sunucuTuru) {
        this.sunucuTuru = sunucuTuru;
    }

    public String getSunucuUygulamaTipi() {
        return sunucuUygulamaTipi;
    }

    public void setSunucuUygulamaTipi(String sunucuUygulamaTipi) {
        this.sunucuUygulamaTipi = sunucuUygulamaTipi;
    }

    public String getSunucuSifre() {
        return sunucuSifre;
    }

    public void setSunucuSifre(String sunucuSifre) {
        this.sunucuSifre = sunucuSifre;
    }

    public String[] getProtokol() {
        return protokol;
    }

    public void setProtokol(String[] protokol) {
        this.protokol = protokol;
    }

    public String[] getHataMesaj() {
        return hataMesaj;
    }

    public void setHataMesaj(String[] hataMesaj) {
        this.hataMesaj = hataMesaj;
    }

    public List<SunucuData> getSunucuTablo() {
        return sunucuTablo;
    }

    public void setSunucuTablo(List<SunucuData> sunucuTablo) {
        this.sunucuTablo = sunucuTablo;
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

    public void sunucuaraButonu()  {
        System.out.println(sunucuBilgisi);
    }

    public void tanimlaButonu() {
        // TODO: 4.08.2017
        System.out.println("Hello, World");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        SunucuData sunucuData
                = (SunucuData) facesContext.getApplication()
                .createValueBinding("#{sunucuData}").getValue(facesContext);


        System.out.println(sunucuData.getSunucuSanalAdi());
        System.out.println(sunucuData.getSunucuTipi());
        System.out.println("sifre " + sunucuData.getSunucuSifre());
        System.out.println(sunucuData.getHataMesaj());


        System.out.println(sunucuData.getKontrolPeriyodu());

        Sunucu sunucu = new Sunucu();

        /*
        sunucu.setSunucuSanalAdi(sunucuData.getSunucuSanalAdi());
        sunucu.setSunucuIp(sunucuData.getSunucuIp());
        sunucu.setSunucuPortBilgisi(sunucuData.getSunucuPortBilgisi());
        sunucu.setKontrolPeriyodu(sunucuData.getKontrolPeriyodu());
        sunucu.setSunucuKullaniciAdi(sunucuData.getSunucuKullaniciAdi());
        sunucu.setSunucuSifre(sunucuData.getSunucuSifre());
        sunucu.setSunucuTipi(sunucuData.getSunucuTipi());
        sunucu.setSunucuUgulamaTipi(sunucuData.getSunucuUygulamaTipi());
        sunucu.setSunucuTuru(sunucuData.getSunucuTuru());
        sunucu.setProtokol(sunucuData.getProtokol());
        sunucu.setHataMesaj(sunucuData.getHataMesaj());
        */


        //Service service = new Service();
        //service.addSunucu(sunucu);


    }

    public void sunucuDuzenleButonu(){}

    public void sunucuAktifButonu(){}

    public void sunucuPasifButonu(){}

}
