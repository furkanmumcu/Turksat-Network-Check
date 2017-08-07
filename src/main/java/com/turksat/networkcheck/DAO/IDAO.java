package com.turksat.networkcheck.DAO;

import com.turksat.networkcheck.model.Sunucu;
import com.turksat.networkcheck.model.Kullanici;
public interface IDAO {


    public void addKullanici(Kullanici kullanici);
    public void addSunucu(Sunucu sunucu);


    public void updateKullanici(Kullanici kullanici);
    public void updateSunucu(Sunucu sunucu);
}