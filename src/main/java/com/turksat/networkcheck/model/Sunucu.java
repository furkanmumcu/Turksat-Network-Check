package com.turksat.networkcheck.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sunucu")
public class Sunucu {
    private String sunucuSanalAdi;
    private String sunucuKullaniciAdi;
    private String sunucuIp;
    private String sunucuUygulamaTipi;
    private String sunucuTipi;
    private String sunucuPortBilgisi;
    private String sunucuTuru;
    private String sunucuSifre;

    private String protokol;
    private String hataMesaj;
    private List<Sunucu> sunucuTablo =new ArrayList<Sunucu> (  );
    private int kontrolPeriyodu;
    private int sunucuId;
    private List<Log> hatalar = new ArrayList<Log>();


    //@Id
    //@Basic(optional = false)
    @Id
    @Column(name="sunucuSanalAdi", unique = true, nullable = false)
    public String getSunucuSanalAdi() {
        return sunucuSanalAdi;
    }
    public void setSunucuSanalAdi(String sunucuSanalAdi) {
        this.sunucuSanalAdi = sunucuSanalAdi;
    }

    @Column(name="sunucuKullaniciAdi", unique = true, nullable = false)
    public String getSunucuKullaniciAdi() {
        return sunucuKullaniciAdi;
    }
    public void setSunucuKullaniciAdi(String sunucuKullaniciAdi) {
        this.sunucuKullaniciAdi = sunucuKullaniciAdi;
    }

    @Column(name="sunucuIp", unique = true, nullable = false)
    public String getSunucuIp() {
        return sunucuIp;
    }
    public void setSunucuIp(String sunucuIp) {
        this.sunucuIp = sunucuIp;
    }

    @Column(name="sunucuUygulamaTipi", unique = true, nullable = false)
    public String getSunucuUygulamaTipi() {
        return sunucuUygulamaTipi;
    }
    public void setSunucuUygulamaTipi(String sunucuUgulamaTipit) {
        sunucuUygulamaTipi = sunucuUgulamaTipit;
    }

    @Column(name="sunucuTipi", unique = true, nullable = false)
    public String getSunucuTipi() {
        return sunucuTipi;
    }
    public void setSunucuTipi(String sunucuTipi) {
        this.sunucuTipi = sunucuTipi;
    }

    @Column(name="sunucuPortBilgisi", unique = true, nullable = false)
    public String getSunucuPortBilgisi() {
        return sunucuPortBilgisi;
    }
    public void setSunucuPortBilgisi(String sunucuPortBilgisi) {
        this.sunucuPortBilgisi = sunucuPortBilgisi;
    }

    @Column(name="sunucuTuru", unique = true, nullable = false)
    public String getSunucuTuru() {
        return sunucuTuru;
    }
    public void setSunucuTuru(String sunucuTuru) {
        this.sunucuTuru = sunucuTuru;
    }


    @Column(name="sunucuSifre", unique = true, nullable = false)
    public String getSunucuSifre() {
        return sunucuSifre;
    }
    public void setSunucuSifre(String sunucuSifre) {
        this.sunucuSifre = sunucuSifre;
    }

    @Column(name="kontrolPeriyodu", unique = true, nullable = false)
    public int getKontrolPeriyodu() {
        return kontrolPeriyodu;
    }
    public void setKontrolPeriyodu(int kontrolPeriyodu) {
        this.kontrolPeriyodu = kontrolPeriyodu;
    }

    /*
    public List<Sunucu> getSunucuTablo() {
        return sunucuTablo;
    }
    public void setSunucuTablo(List<Sunucu> sunucuTablo) {
        this.sunucuTablo = sunucuTablo;
    }
    */
    @Column(name="hataMesaji", unique = true, nullable = false)
    public String getHataMesaj() {
        return hataMesaj;
    }
    public void setHataMesaj(String hataMesaj) {
        this.hataMesaj = hataMesaj;
    }

    @Column(name="sunucuProtokolu", unique = true, nullable = false)
    public String getProtokol() {
        return protokol;
    }
    public void setProtokol(String protokol) {
        this.protokol = protokol;
    }

    @Column(name="sunucuid", unique = true, nullable = false)
    public int getSunucuId() {
        return sunucuId;
    }
    public void setSunucuId(int sunucuId) {
        this.sunucuId = sunucuId;
    }

    /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sunucu")
    public List<Log> getHatalar() {
        return hatalar;
    }
    public void setHatalar(List<Log> hatalar) {
        this.hatalar = hatalar;
    }
    */
    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("sunucuSanalAdi : ").append(getSunucuSanalAdi());
        strBuff.append(", sunucuIp : ").append(getSunucuIp());
        strBuff.append("sunucuKullaniciAdi : ").append(getSunucuKullaniciAdi());
        strBuff.append(", sunucuTipi : ").append(getSunucuTipi());
        strBuff.append("SunucuUygulamaTipi : ").append(getSunucuUygulamaTipi());
        strBuff.append(", sunucuTuru : ").append(getSunucuTuru());
        strBuff.append("sunucuPortBilgisi : ").append(getSunucuPortBilgisi());
        strBuff.append("sunucuSifre : ").append(getSunucuSifre());
        strBuff.append("kontrolPeriyodu : ").append(getKontrolPeriyodu());
        strBuff.append("HataMesaji : ").append(getHataMesaj());
        strBuff.append("SunucuProtokolu : ").append(getProtokol());
        strBuff.append("sunucuSifre : ").append(getSunucuSifre());
        strBuff.append("SunucuId: ").append(getSunucuId());

        return strBuff.toString();
    }
}
