package com.turksat.networkcheck.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="kullanici")
public class Kullanici implements Serializable {
    private String kullaniciAdi;
    private String soyAd;
    private String ad;
    private String sifre;
    private String sifreTekrar;
    private String telNo;
    private String eposta;
    private String rol;

    private static final long serialVersionUID = -1;

    @Id
    @Basic(optional = false)
    @Column(name="kullaniciAdi", unique = true, nullable = false)
    public String getKullaniciAdi() {
        return kullaniciAdi;
    }
    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    @Column(name="sifre", unique = true, nullable = false)
    public String getSifre() {
        return sifre;
    }
    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    @Column(name="ad", unique = true, nullable = false)
    public String getAd() {
        return ad;
    }
    public void setAd(String ad) {
        this.ad = ad;
    }

    @Column(name="soyad", unique = true, nullable = false)
    public String getSoyAd() {
        return soyAd;
    }
    public void setSoyAd(String soyAd) {
        this.soyAd = soyAd;
    }

    @Column(name="eposta", unique = true, nullable = false)
    public String getEposta() {
        return eposta;
    }
    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    @Column(name="telNo", unique = true, nullable = false)
    public String getTelNo() {
        return telNo;
    }
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    @Column(name="rol", unique = true, nullable = false)
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    @Column(name="sifretekrar", unique = true, nullable = false)
    public String getSifreTekrar() {
        return sifreTekrar;
    }
    public void setSifreTekrar(String sifreTekrar) {
        this.sifreTekrar = sifreTekrar;
    }

    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("kullanici : ").append(getKullaniciAdi());
        strBuff.append(", sifre : ").append(getSifre());
        strBuff.append("sifreTekrar : ").append(getSifreTekrar());
        strBuff.append(", ad : ").append(getAd());
        strBuff.append("soyad : ").append(getSoyAd());
        strBuff.append(", telNo : ").append(getTelNo());
        strBuff.append("eposta : ").append(getEposta());
        strBuff.append(", rol : ").append(getRol());

        return strBuff.toString();
    }


}