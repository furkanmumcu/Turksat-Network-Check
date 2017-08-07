package com.turksat.networkcheck.Service;


import com.turksat.networkcheck.DAO.IDAO;
import com.turksat.networkcheck.model.Kullanici;
import com.turksat.networkcheck.model.Sunucu;

public class Service implements IService {

    IDAO kullaniciDAO;

    @Override
    public void addKullanici(Kullanici kullanici) {
        getkullaniciDAO().addKullanici(kullanici);
    }
    public void addSunucu(Sunucu sunucu) {
        getkullaniciDAO().addSunucu(sunucu);
    }


    @Override
    public void updateKullanici(Kullanici kullanici) {
        getkullaniciDAO().updateKullanici(kullanici);
    }
    public void updateSunucu(Sunucu sunucu) {
        getkullaniciDAO().updateSunucu(sunucu);
    }


    @Override
    public void updateSifre(Kullanici sifre) {
        getkullaniciDAO().updateKullanici(sifre);
    }
    public void updateEposta(Kullanici Eposta) {
        getkullaniciDAO().updateKullanici(Eposta);
    }



    public IDAO getkullaniciDAO() {
        return kullaniciDAO;
    }


}
