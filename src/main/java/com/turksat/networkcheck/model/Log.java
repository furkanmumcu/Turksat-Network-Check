package com.turksat.networkcheck.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;


@Entity
@Table(name="log")
public class Log {
    private String durum;
    private String zaman;
    private String sunucuId;
    private Sunucu sunucu;
    private Date date;
    private String time;


    @Id
    @Column(name="durum", unique = true, nullable = false)
    public String getDurum() {
        return durum;
    }
    public void setDurum(String durum) {
        this.durum = durum;
    }

    @Column(name="zaman", unique = true, nullable = false)
    public String getZaman() {
        return zaman;
    }
    public void setZaman(String zaman) {
        this.zaman = zaman;
    }

    @Column(name= "sunucuid", unique = true, nullable = false, insertable = false, updatable = false)
    public String getSunucuId() {
        return sunucuId;
    }
    public void setSunucuId(String sunucuId) {
        this.sunucuId = sunucuId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sunucuid", nullable = false)
    public Sunucu getSunucu(){return sunucu;}
    public void setSunucu(Sunucu sunucu){this.sunucu=sunucu;}

    @Column(name = "time", unique = false, nullable = false)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //@Temporal(TemporalType.TIMESTAMP)
    @Type(type = "timestamp")
    @Column(name = "date", unique = true, nullable = false, length = 10)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("Durum : ").append(getDurum());
        strBuff.append(", Zaman : ").append(getZaman());
        strBuff.append(", SunucununId : ").append(getSunucuId());

        return strBuff.toString();
    }
}
