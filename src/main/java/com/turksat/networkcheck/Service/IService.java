package com.turksat.networkcheck.Service;

import com.turksat.networkcheck.model.Kullanici;
import com.turksat.networkcheck.model.Sunucu;

/**
 * Created by hilmimert on 3.08.2017.
 */
public interface IService {



    public void addKullanici(Kullanici kullanici);
    public void addSunucu(Sunucu sunucu);

    public void updateKullanici(Kullanici kullanici);
    public void updateSunucu(Sunucu sunucu);
    public void updateSifre(Kullanici sifre);
    public void updateEposta(Kullanici Eposta);
}
