package com.turksat.networkcheck.model;

import javax.persistence.*;


@Entity
@Table(name="log")
public class Log {
    private String hata;
    private String hatazaman;
    private int sunucununId;

    @Id
    @Basic(optional = false)
    @Column(name="Hata", unique = true, nullable = false)
    public String getHata() {
        return hata;
    }
    public void setHata(String hata) {
        this.hata = hata;
    }

    @Column(name="HataZamani", unique = true, nullable = false)
    public String getHatazaman() {
        return hatazaman;
    }
    public void setHatazaman(String hatazaman) {
        this.hatazaman = hatazaman;
    }

    @Column(name="sunucununId", unique = true, nullable = false)
    public int getSunucununId() {
        return sunucununId;
    }
    public void setSunucununId(int sunucununId) {
        this.sunucununId = sunucununId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Loglar", nullable = false)

    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("Hata : ").append(getHata());
        strBuff.append(", HataZamani : ").append(getHatazaman());
        strBuff.append(", SunucununId : ").append(getSunucununId());

        return strBuff.toString();
    }
}
