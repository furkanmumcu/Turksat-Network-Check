package com.turksat.networkcheck.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@SessionScoped
public class AnasayfaBean {
    private String selectMenu1;
    private String selectMenu2;
    private String selectMenu3;
    private List<LogDetay> loglar=new ArrayList<LogDetay>(  );
    private List<AnaData> tablo=new ArrayList<AnaData> (  );
    public List<LogDetay> getLoglar() {
        return loglar;
    }

    public void setLoglar(List<LogDetay> loglar) {
        this.loglar = loglar;
    }


    public List<AnaData> getTablo() {
        return tablo;
    }

    public void setTablo(List<AnaData> tablo) {
        this.tablo = tablo;
    }

    public String getSelectMenu1() {
        return selectMenu1;
    }

    public void setSelectMenu1(String selectMenu1) {
        this.selectMenu1 = selectMenu1;
    }

    public String getSelectMenu2() {
        return selectMenu2;
    }

    public void setSelectMenu2(String selectMenu2) {
        this.selectMenu2 = selectMenu2;
    }

    public String getSelectMenu3() {
        return selectMenu3;
    }

    public void setSelectMenu3(String selectMenu3) {
        this.selectMenu3 = selectMenu3;
    }

    public void uygulaButonu() {
        // TODO: 4.08.2017 bura
    }

    public void logButonu() {
    }

    public void anlikButonu() {
    }

}
