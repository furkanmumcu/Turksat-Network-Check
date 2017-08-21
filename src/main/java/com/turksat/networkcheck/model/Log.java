package com.turksat.networkcheck.model;

import com.sun.tools.javac.api.ClientCodeWrapper;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;


@Entity
@Table(name="log")
public class Log implements Serializable{
    private String durum;
    private String sunucuId;
    private Sunucu sunucu;
    private Date date;
    private String time;
    private boolean isError;
    private int logid;

    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name = "logid", unique = true, nullable = false)
    public int getLogid() {
        return logid;
    }

    public void setLogid(int logid) {
        this.logid = logid;
    }

    @Column(name="durum", unique = true, nullable = false)
    public String getDurum() {
        return durum;
    }
    public void setDurum(String durum) {
        this.durum = durum;
    }

    @Column(name= "sunucuid", unique = true, nullable = false, insertable = false, updatable = false)
    public String getSunucuId() {
        return sunucuId;
    }
    public void setSunucuId(String sunucuId) {
        this.sunucuId = sunucuId;
    }

    @ManyToOne()
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

    @Column(name = "iserror", unique = false, nullable = true)
    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }


    @Override
    public String toString() {
        return "Log{" +
                "durum='" + durum + '\'' +
                ", sunucuId='" + sunucuId + '\'' +
                ", sunucu=" + sunucu +
                ", date=" + date +
                ", time='" + time + '\'' +
                '}';
    }
}
