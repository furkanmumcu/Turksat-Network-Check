package com.turksat.networkcheck.view;

import javax.faces.bean.ManagedBean;

/**
 * Created by furkanmumcu on 07/08/2017.
 */
@ManagedBean
public class LogDetay {
    private String hata;
    private String hatazaman;

    public String getHata() {
        return hata;
    }

    public void setHata(String hata) {
        this.hata = hata;
    }

    public String getHatazaman() {
        return hatazaman;
    }

    public void setHatazaman(String hatazaman) {
        this.hatazaman = hatazaman;
    }
}
