package com.turksat.networkcheck.Service;


import com.turksat.networkcheck.DAO.IDAO;
import com.turksat.networkcheck.model.Kullanici;
import com.turksat.networkcheck.model.Sunucu;
import com.turksat.networkcheck.model.Log;

public class Service implements IService {

    IDAO kullaniciDAO;

    @Override
    public void addKullanici(Kullanici kullanici) {
        getkullaniciDAO().addKullanici(kullanici);
    }
    public void addSunucu(Sunucu sunucu) {
        getkullaniciDAO().addSunucu(sunucu);
    }
    public void addLog(Log log){getkullaniciDAO().addLog(log);}

    @Override
    public void updateKullanici(Kullanici kullanici) {
        getkullaniciDAO().updateKullanici(kullanici);
    }
    public void updateSunucu(Sunucu sunucu) {
        getkullaniciDAO().updateSunucu(sunucu);
    }
    public void updateLog(Log log){getkullaniciDAO().updateLog(log);}

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
