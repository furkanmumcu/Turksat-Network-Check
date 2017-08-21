package com.turksat.networkcheck.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="sunucu")
public class Sunucu implements Serializable{
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
    private int kontrolPeriyodu;
    private String sunucuId;
    private Set<Log> loglar; //= new ArrayList<Log>();
    private boolean aktifPasif;

    @Column(name="sunucuSanalAdi", unique = true, nullable = false)
    public String getSunucuSanalAdi() {
        return sunucuSanalAdi;
    }
    public void setSunucuSanalAdi(String sunucuSanalAdi) {
        this.sunucuSanalAdi = sunucuSanalAdi;
    }

    @Column(name="sunucuKullaniciAdi", unique = false, nullable = false)
    public String getSunucuKullaniciAdi() {
        return sunucuKullaniciAdi;
    }
    public void setSunucuKullaniciAdi(String sunucuKullaniciAdi) {
        this.sunucuKullaniciAdi = sunucuKullaniciAdi;
    }

    @Column(name="sunucuIp", unique = false, nullable = false)
    public String getSunucuIp() {
        return sunucuIp;
    }
    public void setSunucuIp(String sunucuIp) {
        this.sunucuIp = sunucuIp;
    }

    @Column(name="sunucuUygulamaTipi", unique = false, nullable = false)
    public String getSunucuUygulamaTipi() {
        return sunucuUygulamaTipi;
    }
    public void setSunucuUygulamaTipi(String sunucuUgulamaTipit) {
        sunucuUygulamaTipi = sunucuUgulamaTipit;
    }

    @Column(name="sunucuTipi", unique = false, nullable = false)
    public String getSunucuTipi() {
        return sunucuTipi;
    }
    public void setSunucuTipi(String sunucuTipi) {
        this.sunucuTipi = sunucuTipi;
    }

    @Column(name="sunucuPortBilgisi", unique = false, nullable = false)
    public String getSunucuPortBilgisi() {
        return sunucuPortBilgisi;
    }
    public void setSunucuPortBilgisi(String sunucuPortBilgisi) {
        this.sunucuPortBilgisi = sunucuPortBilgisi;
    }

    @Column(name="sunucuTuru", unique = false, nullable = false)
    public String getSunucuTuru() {
        return sunucuTuru;
    }
    public void setSunucuTuru(String sunucuTuru) {
        this.sunucuTuru = sunucuTuru;
    }


    @Column(name="sunucuSifre", unique = false, nullable = false)
    public String getSunucuSifre() {
        return sunucuSifre;
    }
    public void setSunucuSifre(String sunucuSifre) {
        this.sunucuSifre = sunucuSifre;
    }

    @Column(name="kontrolPeriyodu", unique = false, nullable = false)
    public int getKontrolPeriyodu() {
        return kontrolPeriyodu;
    }
    public void setKontrolPeriyodu(int kontrolPeriyodu) {
        this.kontrolPeriyodu = kontrolPeriyodu;
    }

    @Column(name="hataMesaji", unique = false, nullable = false)
    public String getHataMesaj() {
        return hataMesaj;
    }
    public void setHataMesaj(String hataMesaj) {
        this.hataMesaj = hataMesaj;
    }

    @Column(name="sunucuProtokolu", unique = false, nullable = false)
    public String getProtokol() {
        return protokol;
    }
    public void setProtokol(String protokol) {
        this.protokol = protokol;
    }

    @Id
    @Column(name="sunucuid", unique = true, nullable = false)
    public String getSunucuId() {
        return sunucuId;
    }
    public void setSunucuId(String sunucuId) {
        this.sunucuId = sunucuId;
    }


    @Column(name = "aktifpasif", unique = false, nullable = false)
    public boolean getAktifPasif() {return aktifPasif;}
    public void setAktifPasif(boolean aktifPasif) {
        this.aktifPasif = aktifPasif;
    }



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sunucu", cascade = CascadeType.PERSIST)
    public Set<Log> getLoglar() {
        return loglar;
    }

    public void setLoglar(Set<Log> durumlar) {
        this.loglar = loglar;
    }

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