package com.turksat.networkcheck.DAO;

import com.turksat.networkcheck.model.Sunucu;
import com.turksat.networkcheck.model.Kullanici;
import org.hibernate.SessionFactory;

public class DAO implements IDAO {


    private SessionFactory sessionFactory;
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addKullanici(Kullanici kullanici) {
        getSessionFactory().getCurrentSession().save(kullanici);
    }
    public void addSunucu(Sunucu sunucu) {
        getSessionFactory().getCurrentSession().save(sunucu);
    }

    @Override
    public void updateKullanici(Kullanici kullanici) {
        getSessionFactory().getCurrentSession().update(kullanici);
    }
    public void updateSunucu(Sunucu sunucu) {
        getSessionFactory().getCurrentSession().update(sunucu);
    }



}