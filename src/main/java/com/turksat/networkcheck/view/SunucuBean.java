package com.turksat.networkcheck.view;

import com.turksat.networkcheck.Service.Service;
import com.turksat.networkcheck.model.Sunucu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.primefaces.context.RequestContext;
//import org.hibernate.service.ServiceRegistryBuilder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by furkanmumcu on 07/08/2017.
 */
@ManagedBean
@SessionScoped
public class SunucuBean implements Serializable{
    private String sunucuBilgisi;
    private String sunucuTipi;
    private String sunucuTuru;
    private String sunucuUygulamaTipi;
    private String sunucuSifre; // kullanilmiyor

    private String[] protokol;
    private String[] hataMesaj;

    private List<SunucuData> sunucuTablo=new ArrayList<SunucuData>(  );
    private int kontrolPeriyodu;
    private String sunucuKullaniciAdi;

    private SunucuData selectedSunucuData;

    public SunucuData getSelectedSunucuData() {
        return selectedSunucuData;
    }

    public void setSelectedSunucuData(SunucuData selectedSunucu) {
        this.selectedSunucuData = selectedSunucu;
    }

    public String getSunucuBilgisi() {
        return sunucuBilgisi;
    }

    public void setSunucuBilgisi(String sunucuBilgisi) {
        this.sunucuBilgisi = sunucuBilgisi;
    }

    public String getSunucuTipi() {
        return sunucuTipi;
    }

    public void setSunucuTipi(String sunucuTipi) {
        this.sunucuTipi = sunucuTipi;
    }

    public String getSunucuTuru() {
        return sunucuTuru;
    }

    public void setSunucuTuru(String sunucuTuru) {
        this.sunucuTuru = sunucuTuru;
    }

    public String getSunucuUygulamaTipi() {
        return sunucuUygulamaTipi;
    }

    public void setSunucuUygulamaTipi(String sunucuUygulamaTipi) {
        this.sunucuUygulamaTipi = sunucuUygulamaTipi;
    }

    public String getSunucuSifre() {
        return sunucuSifre;
    }

    public void setSunucuSifre(String sunucuSifre) {
        this.sunucuSifre = sunucuSifre;
    }

    public String[] getProtokol() {
        return protokol;
    }

    public void setProtokol(String[] protokol) {
        this.protokol = protokol;
    }

    public String[] getHataMesaj() {
        return hataMesaj;
    }

    public void setHataMesaj(String[] hataMesaj) {
        this.hataMesaj = hataMesaj;
    }

    public List<SunucuData> getSunucuTablo() {
        return sunucuTablo;
    }

    public void setSunucuTablo(List<SunucuData> sunucuTablo) {
        this.sunucuTablo = sunucuTablo;
    }

    public int getKontrolPeriyodu() {
        return kontrolPeriyodu;
    }

    public void setKontrolPeriyodu(int kontrolPeriyodu) {
        this.kontrolPeriyodu = kontrolPeriyodu;
    }

    public String getSunucuKullaniciAdi() {
        return sunucuKullaniciAdi;
    }

    public void setSunucuKullaniciAdi(String sunucuKullaniciAdi) {
        this.sunucuKullaniciAdi = sunucuKullaniciAdi;
    }

    public void sunucuaraButonu()  {
        /*
        SunucuData sunucu1 = new SunucuData();
        sunucu1.setSunucuSanalAdi("ewerwer");
        sunucu1.setSunucuIp("erwfs");
        sunucu1.setSunucuPortBilgisi("fdsfsdfsf");
        sunucu1.setKontrolPeriyodu(10);
        sunucu1.setSunucuKullaniciAdi("dasd");
        sunucu1.setSunucuSifre("dfsdfefd");
        sunucu1.setSunucuTipi("fsdfsdf");
        sunucu1.setSunucuUygulamaTipi("dsfefdsfe");
        sunucu1.setSunucuTuru("fsfesfdf");
        sunucu1.setProtokol("fedfefds");
        sunucu1.setHataMesaj("fsdfesdfesfd");
        //sunucuTablo.add(sunucu1);
        */
        sunucuTablo.removeAll(sunucuTablo);

        // TODO: 09/08/2017 serverdan tabloyu doldur/ filtreye gore

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        System.out.println(sunucuBilgisi);
        System.out.println(sunucuTipi);
        System.out.println(sunucuTuru);
        System.out.println(sunucuUygulamaTipi);
        System.out.println(sunucuBilgisi.isEmpty());


        String hql;
        Query query;
        if(sunucuBilgisi.isEmpty()&&sunucuTipi.isEmpty()&&sunucuUygulamaTipi.isEmpty()&&sunucuTuru.isEmpty()){
            hql = "FROM Sunucu";
            query = session.createQuery(hql);

        }
        else {
            hql = "FROM Sunucu S where S.sunucuTipi = :tip AND S.sunucuTuru = :tur AND S.sunucuUygulamaTipi = :utip AND S.sunucuPortBilgisi = :port";
            query = session.createQuery(hql);
            query.setParameter("tip",sunucuTipi);
            query.setParameter("tur", sunucuTuru);
            query.setParameter("utip", sunucuUygulamaTipi);
            query.setParameter("port", sunucuBilgisi);
        }
        List<Sunucu> sunucuList = query.list();

        System.out.println("sunuculist uzunluk: " + sunucuList.size());

        for(int i = 0; i<sunucuList.size(); i++){
            SunucuData sunucu = new SunucuData();

            sunucu.setSunucuSanalAdi(sunucuList.get(i).getSunucuSanalAdi());
            sunucu.setSunucuIp(sunucuList.get(i).getSunucuIp());
            sunucu.setSunucuPortBilgisi(sunucuList.get(i).getSunucuPortBilgisi());
            sunucu.setKontrolPeriyodu(sunucuList.get(i).getKontrolPeriyodu());
            sunucu.setSunucuKullaniciAdi(sunucuList.get(i).getSunucuKullaniciAdi());
            sunucu.setSunucuSifre(sunucuList.get(i).getSunucuSifre());
            sunucu.setSunucuTipi(sunucuList.get(i).getSunucuTipi());
            sunucu.setSunucuUygulamaTipi(sunucuList.get(i).getSunucuUygulamaTipi());
            sunucu.setSunucuTuru(sunucuList.get(i).getSunucuTuru());
            sunucu.setProtokol(sunucuList.get(i).getProtokol());
            sunucu.setHataMesaj(sunucuList.get(i).getHataMesaj());

            //System.out.println(sunucuList.get(i).getSunucuSanalAdi());

            sunucuTablo.add(sunucu);
        }



        /////////////////////
        System.out.println("sunucu tablo uzunluk " + sunucuTablo.size());

        refresh();

    }

    public void tanimlaButonu() {
        // TODO: 4.08.2017
        System.out.println("Hello, World");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        SunucuData sunucuData
                = (SunucuData) facesContext.getApplication()
                .createValueBinding("#{sunucuData}").getValue(facesContext);

        System.out.println(sunucuData.getSunucuSanalAdi());
        System.out.println(sunucuData.getProtokol());

        /* debug outs
        System.out.println(sunucuData.getSunucuSanalAdi());
        System.out.println(sunucuData.getSunucuTipi());
        System.out.println("sifre " + sunucuData.getSunucuSifre());
        System.out.println(sunucuData.getHataMesaj());
        System.out.println(sunucuData.getKontrolPeriyodu());
        */

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Sunucu sunucu = new Sunucu();

        sunucu.setSunucuSanalAdi(sunucuData.getSunucuSanalAdi());
        sunucu.setSunucuIp(sunucuData.getSunucuIp());
        sunucu.setSunucuPortBilgisi(sunucuData.getSunucuPortBilgisi());
        sunucu.setKontrolPeriyodu(sunucuData.getKontrolPeriyodu());
        sunucu.setSunucuKullaniciAdi(sunucuData.getSunucuKullaniciAdi());
        sunucu.setSunucuSifre(sunucuData.getSunucuSifre());
        sunucu.setSunucuTipi(sunucuData.getSunucuTipi());
        sunucu.setSunucuUygulamaTipi(sunucuData.getSunucuUygulamaTipi());
        sunucu.setSunucuTuru(sunucuData.getSunucuTuru());
        sunucu.setProtokol(sunucuData.getProtokol());
        sunucu.setHataMesaj(sunucuData.getHataMesaj());

        session.save(sunucu);
        session.getTransaction().commit();
        session.close();



        //close the popup
        //RequestContext requestContext = RequestContext.getCurrentInstance();
        //requestContext.execute("PF('dlg1').hide()");
        //last step redirect to same page
        //refresh();

    }

    public void sunucuDuzenleButonu(){
        System.out.println("duzenlebutonu");
        System.out.println(getSelectedSunucuData().getSunucuSanalAdi());
        System.out.println(getSelectedSunucuData().getSunucuIp());
        System.out.println(getSelectedSunucuData().getSunucuPortBilgisi());
        System.out.println("protokol "+ getSelectedSunucuData().getProtokol());


        // TODO: 10/08/2017 sunucuyu db de bul, guncelle
        /*
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query;
        String hql = "FROM Sunucu S where S.sunucuSanalAdi = :ad AND S.sunucuPortBilgisi = :port AND S.sunucuIp = :ip";
        query = session.createQuery(hql);
        query.setParameter("ad",sunucuTipi);
        query.setParameter("port", sunucuTuru);
        query.setParameter("ip", sunucuUygulamaTipi);
        */

    }

    public void guncelleButonu(){

        FacesContext facesContext = FacesContext.getCurrentInstance();
        SunucuDataDuzenle sunucuDataDuzenle
                = (SunucuDataDuzenle) facesContext.getApplication()
                .createValueBinding("#{sunucuDataDuzenle}").getValue(facesContext);
        System.out.println("guncellebutonu row bilgisi");
        System.out.println(getSelectedSunucuData().getSunucuSanalAdi());
        System.out.println(getSelectedSunucuData().getSunucuIp());
        System.out.println(getSelectedSunucuData().getSunucuPortBilgisi());

        System.out.println("guncellebutonu dinamik bean bilgisi");
        System.out.println(sunucuDataDuzenle.getSunucuSanalAdi());
        System.out.println(sunucuDataDuzenle.getSunucuTuru());



        Configuration configuration = new Configuration();
        configuration.configure();

        //query ile id sini bul

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Sunucu sunucu = new Sunucu();

        if(!sunucuDataDuzenle.getSunucuSanalAdi().isEmpty())
            sunucu.setSunucuSanalAdi(sunucuDataDuzenle.getSunucuSanalAdi());
        else
            sunucu.setSunucuSanalAdi(selectedSunucuData.getSunucuSanalAdi());

        if(!sunucuDataDuzenle.getSunucuIp().isEmpty())
            sunucu.setSunucuIp(sunucuDataDuzenle.getSunucuIp());
        else
            sunucu.setSunucuIp(selectedSunucuData.getSunucuIp());

        if(!sunucuDataDuzenle.getSunucuPortBilgisi().isEmpty())
            sunucu.setSunucuPortBilgisi(sunucuDataDuzenle.getSunucuPortBilgisi());
        else
            sunucu.setSunucuPortBilgisi(selectedSunucuData.getSunucuPortBilgisi());

        if(sunucuDataDuzenle.getKontrolPeriyodu()!=0)
            sunucu.setKontrolPeriyodu(sunucuDataDuzenle.getKontrolPeriyodu());
        else
            sunucu.setKontrolPeriyodu(selectedSunucuData.getKontrolPeriyodu());

        if(!sunucuDataDuzenle.getSunucuKullaniciAdi().isEmpty())
            sunucu.setSunucuKullaniciAdi(sunucuDataDuzenle.getSunucuKullaniciAdi());
        else
            sunucu.setSunucuKullaniciAdi(selectedSunucuData.getSunucuKullaniciAdi());

        if(!sunucuDataDuzenle.getSunucuSifre().isEmpty())
            sunucu.setSunucuSifre(sunucuDataDuzenle.getSunucuSifre());
        else
            sunucu.setSunucuSifre(selectedSunucuData.getSunucuSifre());

        if(!sunucuDataDuzenle.getSunucuTipi().isEmpty())
            sunucu.setSunucuTipi(sunucuDataDuzenle.getSunucuTipi());
        else
            sunucu.setSunucuTipi(selectedSunucuData.getSunucuTipi());

        if(!sunucuDataDuzenle.getSunucuUygulamaTipi().isEmpty())
            sunucu.setSunucuUygulamaTipi(sunucuDataDuzenle.getSunucuUygulamaTipi());
        else
            sunucu.setSunucuUygulamaTipi(selectedSunucuData.getSunucuUygulamaTipi());

        if(!sunucuDataDuzenle.getSunucuTuru().isEmpty())
            sunucu.setSunucuTuru(sunucuDataDuzenle.getSunucuTuru());
        else
            sunucu.setSunucuTuru(selectedSunucuData.getSunucuTuru());

        if(!sunucuDataDuzenle.getProtokol().isEmpty())
            sunucu.setProtokol(sunucuDataDuzenle.getProtokol());
        else
            sunucu.setProtokol(selectedSunucuData.getProtokol());

        if(!sunucuDataDuzenle.getHataMesaj().isEmpty())
            sunucu.setHataMesaj(sunucuDataDuzenle.getHataMesaj());
        else
            sunucu.setHataMesaj(selectedSunucuData.getHataMesaj());

        session.update(sunucu);
        session.getTransaction().commit();
        session.close();

    }

    public void sunucuAktifButonu(){}

    public void sunucuPasifButonu(){}

    public void refresh(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("sunucuislem.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
