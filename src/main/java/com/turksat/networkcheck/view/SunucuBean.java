package com.turksat.networkcheck.view;

import com.turksat.networkcheck.model.Sunucu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")

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

    private String currentHQL;

    private int[] emptyCheckPasser;

    public String getCurrentHQL() {
        return currentHQL;
    }

    public void setCurrentHQL(String currentHQL) {
        this.currentHQL = currentHQL;
    }

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
        sunucuTablo.removeAll(sunucuTablo);

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

        int [] emptyCheck = {0,0,0,0};

        if(!sunucuTipi.isEmpty())
            emptyCheck[0]=1;

        if(!sunucuTuru.isEmpty())
            emptyCheck[1]=1;

        if(!sunucuUygulamaTipi.isEmpty())
            emptyCheck[2]=1;

        if(!sunucuBilgisi.isEmpty())
            emptyCheck[3]=1;

        String[] emptyCheckNames = {"S.sunucuTipi = :tip","S.sunucuTuru = :tur","S.sunucuUygulamaTipi = :utip","S.sunucuPortBilgisi = :port"};
        emptyCheckPasser = emptyCheck;

        String hql = "FROM Sunucu";
        Query query;
        boolean isfirst = true;
        if(sunucuBilgisi.isEmpty()&&sunucuTipi.isEmpty()&&sunucuUygulamaTipi.isEmpty()&&sunucuTuru.isEmpty()){
            hql = "FROM Sunucu";
            query = session.createQuery(hql);

        }
        else {
            System.out.println("girdim");
            hql = hql + " S where ";
            for(int i =0; i<4; i++){
                if(emptyCheck[i] == 1 && !isfirst){
                    hql = hql + " AND " + emptyCheckNames[i];
                }
                if(emptyCheck[i] == 1 && isfirst){
                    hql = hql + emptyCheckNames[i];
                    isfirst = false;
                }
            }
            //hql = "FROM Sunucu S where S.sunucuTipi = :tip AND S.sunucuTuru = :tur AND S.sunucuUygulamaTipi = :utip AND S.sunucuPortBilgisi = :port";
            System.out.println(hql);
            query = session.createQuery(hql);
            if(emptyCheck[0] == 1)
                query.setParameter("tip",sunucuTipi);

            if(emptyCheck[1] == 1)
                query.setParameter("tur", sunucuTuru);

            if(emptyCheck[2] == 1)
                query.setParameter("utip", sunucuUygulamaTipi);

            if(emptyCheck[3] == 1)
                query.setParameter("port", sunucuBilgisi);
        }
        List<Sunucu> sunucuList = query.list();

        System.out.println("sunuculist uzunluk: " + sunucuList.size());
        setCurrentHQL(hql);


        for(int i = 0; i<sunucuList.size(); i++){
            SunucuData sunucuData = new SunucuData();

            sunucuData.setSunucuSanalAdi(sunucuList.get(i).getSunucuSanalAdi());
            sunucuData.setSunucuIp(sunucuList.get(i).getSunucuIp());
            sunucuData.setSunucuPortBilgisi(sunucuList.get(i).getSunucuPortBilgisi());
            sunucuData.setKontrolPeriyodu(sunucuList.get(i).getKontrolPeriyodu());
            sunucuData.setSunucuKullaniciAdi(sunucuList.get(i).getSunucuKullaniciAdi());
            sunucuData.setSunucuSifre(sunucuList.get(i).getSunucuSifre());
            sunucuData.setSunucuTipi(sunucuList.get(i).getSunucuTipi());
            sunucuData.setSunucuUygulamaTipi(sunucuList.get(i).getSunucuUygulamaTipi());
            sunucuData.setSunucuTuru(sunucuList.get(i).getSunucuTuru());
            sunucuData.setProtokol(sunucuList.get(i).getProtokol());
            sunucuData.setHataMesaj(sunucuList.get(i).getHataMesaj());

            //System.out.println(sunucuList.get(i).getSunucuSanalAdi());

            sunucuTablo.add(sunucuData);

            session.close(); //???
        }

        /////////////////////
        System.out.println("sunucu tablo uzunluk " + sunucuTablo.size());

        refresh();

    }

    public void tanimlaButonu() {
        System.out.println("Hello, World");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        SunucuData sunucuData
                = (SunucuData) facesContext.getApplication()
                .createValueBinding("#{sunucuData}").getValue(facesContext);

        System.out.println(sunucuData.getSunucuSanalAdi());
        System.out.println(sunucuData.getProtokol());

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
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlg1').hide()");
        //last step redirect to same page
        fillTable();
        refresh();

    }

    public void sunucuDuzenleButonu(){
        System.out.println("duzenlebutonu");
        System.out.println(getSelectedSunucuData().getSunucuSanalAdi());
        System.out.println(getSelectedSunucuData().getSunucuIp());
        System.out.println(getSelectedSunucuData().getSunucuPortBilgisi());
        System.out.println("protokol "+ getSelectedSunucuData().getProtokol());

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

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlg6').hide()");
        fillTable();
        refresh();
    }

    public void sunucuAktifButonu(){

    }

    public void sunucuPasifButonu(){

    }

    public void refresh(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("sunucuislem.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillTable(){
        sunucuTablo.removeAll(sunucuTablo);

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = getCurrentHQL();
        Query query = session.createQuery(hql);

        if(emptyCheckPasser[0] == 1)
            query.setParameter("tip",sunucuTipi);

        if(emptyCheckPasser[1] == 1)
            query.setParameter("tur", sunucuTuru);

        if(emptyCheckPasser[2] == 1)
            query.setParameter("utip", sunucuUygulamaTipi);

        if(emptyCheckPasser[3] == 1)
            query.setParameter("port", sunucuBilgisi);

        List<Sunucu> sunucuList = query.list();

        for(int i = 0; i<sunucuList.size(); i++){
            SunucuData sunucuData = new SunucuData();
            sunucuData.setSunucuSanalAdi(sunucuList.get(i).getSunucuSanalAdi());
            sunucuData.setSunucuIp(sunucuList.get(i).getSunucuIp());
            sunucuData.setSunucuPortBilgisi(sunucuList.get(i).getSunucuPortBilgisi());
            sunucuData.setKontrolPeriyodu(sunucuList.get(i).getKontrolPeriyodu());
            sunucuData.setSunucuKullaniciAdi(sunucuList.get(i).getSunucuKullaniciAdi());
            sunucuData.setSunucuSifre(sunucuList.get(i).getSunucuSifre());
            sunucuData.setSunucuTipi(sunucuList.get(i).getSunucuTipi());
            sunucuData.setSunucuUygulamaTipi(sunucuList.get(i).getSunucuUygulamaTipi());
            sunucuData.setSunucuTuru(sunucuList.get(i).getSunucuTuru());
            sunucuData.setProtokol(sunucuList.get(i).getProtokol());
            sunucuData.setHataMesaj(sunucuList.get(i).getHataMesaj());
            sunucuTablo.add(sunucuData);
            session.close();
        }

    }

}
