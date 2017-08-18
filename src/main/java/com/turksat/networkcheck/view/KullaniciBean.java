package com.turksat.networkcheck.view;

import com.turksat.networkcheck.model.Kullanici;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.primefaces.context.RequestContext;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("Duplicates")

@ManagedBean
@SessionScoped
public class KullaniciBean implements Serializable {
    private String ad;
    private String soyAd;
    private String rol;
    private String currentHQL="";
    private KullaniciData selectedKullaniciData;
    private int[] emptyCheckPasser;


    public KullaniciData getSelectedKullaniciData() {
        return selectedKullaniciData;
    }

    public void setSelectedKullaniciData(KullaniciData selectedKullaniciData) {
        this.selectedKullaniciData = selectedKullaniciData;
    }

    public String getCurrentHQL() {
        return currentHQL;
    }

    public void setCurrentHQL(String currentHQL) {
        this.currentHQL = currentHQL;
    }

    public int[] getEmptyCheckPasser() {
        return emptyCheckPasser;
    }

    public void setEmptyCheckPasser(int[] emptyCheckPasser) {
        this.emptyCheckPasser = emptyCheckPasser;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyAd() {
        return soyAd;
    }

    public void setSoyAd(String soyAd) {
        this.soyAd = soyAd;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    private List<KullaniciData> kullaniciTablo=new ArrayList<KullaniciData>(  );

    public List<KullaniciData> getKullaniciTablo() {
        return kullaniciTablo;
    }

    public void setKullaniciTablo(List<KullaniciData> kullaniciTablo) {
        this.kullaniciTablo = kullaniciTablo;
    }

    public void kisiKaydetButonu() throws NoSuchAlgorithmException {

        System.out.println("Hello, World");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        KullaniciData kullaniciData
                = (KullaniciData) facesContext.getApplication().createValueBinding("#{kullaniciData}").getValue(facesContext);


        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Kullanici kullanici = new Kullanici();
        session.beginTransaction();


        kullanici.setAd(kullaniciData.getAd());
        kullanici.setSoyAd(kullaniciData.getSoyAd());
        kullanici.setKullaniciAdi(kullaniciData.getKullaniciAdi());
        //kullanici.setSifre(kullaniciData.getSifre());

        String encSifre="";
        try {
            MessageDigest messageDigestNesnesi = MessageDigest.getInstance("MD5");
            messageDigestNesnesi.update(kullaniciData.getSifre().getBytes());
            byte messageDigestDizisi[] = messageDigestNesnesi.digest();
            StringBuffer sb16 = new StringBuffer();
            StringBuffer sb32 = new StringBuffer();
            for (int i = 0; i < messageDigestDizisi.length; i++) {
                sb16.append(Integer.toString((messageDigestDizisi[i] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println("Parolanın Şifrelenmiş Hali:(16) " + sb16.toString());
            encSifre = sb16.toString();
        }
        catch(NoSuchAlgorithmException ex){
            System.err.println(ex);
        }
        kullanici.setSifre(encSifre);
        kullanici.setSifreTekrar(encSifre);
        kullanici.setTelNo(kullaniciData.getTelNo());
        kullanici.setEposta(kullaniciData.getEposta());
        kullanici.setRol(kullaniciData.getRol());

        session.save(kullanici);
        session.getTransaction().commit();
        session.close();

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlg1').hide()");
        fillTable();
        refresh();


    }

    public void kisiAraButonu() {

        kullaniciTablo.removeAll(kullaniciTablo);

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        System.out.println(ad);
        System.out.println(soyAd);


        int [] emptyCheck = {0,0};

        if(!ad.isEmpty())
            emptyCheck[0]=1;

        if(!soyAd.isEmpty())
            emptyCheck[1]=1;


        String[] emptyCheckNames = {"S.ad = :ad","S.soyAd = :soyad"};
        emptyCheckPasser = emptyCheck;

        String hql = "FROM Kullanici ";
        Query query;
        boolean isfirst = true;

        if(ad.isEmpty()&&soyAd.isEmpty()){
            hql = "FROM Kullanici ";
            query = session.createQuery(hql);
        }
        else {
            System.out.println("girdim");
            hql = hql + " S where ";
            for (int i = 0; i < 2; i++) {
                if (emptyCheck[i] == 1 && !isfirst) {
                    hql = hql + " AND " + emptyCheckNames[i];
                }
                if (emptyCheck[i] == 1 && isfirst) {
                    hql = hql + emptyCheckNames[i];
                    isfirst = false;
                }
            }

            System.out.println(hql);
            query = session.createQuery(hql);
            if (emptyCheck[0] == 1)
                query.setParameter("ad", ad);

            if (emptyCheck[1] == 1)
                query.setParameter("soyad", soyAd);

        }

        List<Kullanici> kullaniciList = query.list();

        System.out.println("kullaniciList uzunluk: " + kullaniciList.size());
        setCurrentHQL(hql);

        for(int i = 0; i<kullaniciList.size(); i++){
            KullaniciData kullaniciData = new KullaniciData();

            kullaniciData.setAd(kullaniciList.get(i).getAd());
            kullaniciData.setSoyAd(kullaniciList.get(i).getSoyAd());
            kullaniciData.setTelNo(kullaniciList.get(i).getTelNo());
            kullaniciData.setEposta(kullaniciList.get(i).getEposta());
            kullaniciData.setRol(kullaniciList.get(i).getRol());

            kullaniciTablo.add(kullaniciData);

            session.close();
        }
        refresh();
    }

    private void refresh() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("kullaniciislem.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillTable(){
        if(!currentHQL.isEmpty()) {
            kullaniciTablo.removeAll(kullaniciTablo);

            Configuration configuration = new Configuration();
            configuration.configure();

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            String hql = getCurrentHQL();
            Query query = session.createQuery(hql);

            if (emptyCheckPasser[0] == 1)
                query.setParameter("ad", ad);

            if (emptyCheckPasser[1] == 1)
                query.setParameter("soyad", soyAd);


            List<Kullanici> kullaniciList = query.list();

            for (int i = 0; i < kullaniciList.size(); i++) {
                KullaniciData kullaniciData = new KullaniciData();

                kullaniciData.setAd(kullaniciList.get(i).getAd());
                kullaniciData.setSoyAd(kullaniciList.get(i).getSoyAd());
                kullaniciData.setTelNo(kullaniciList.get(i).getTelNo());
                kullaniciData.setEposta(kullaniciList.get(i).getEposta());
                kullaniciData.setRol(kullaniciList.get(i).getRol());

                kullaniciTablo.add(kullaniciData);
                session.close();
            }
        }

    }

    public void kullaniciDuzenleButonu(){
        System.out.println("duzenlebutonu");


        FacesContext facesContext = FacesContext.getCurrentInstance();
        KullaniciDataDuzenleme kullaniciDataDuzenleme
                = (KullaniciDataDuzenleme) facesContext.getApplication()
                .createValueBinding("#{kullaniciDataDuzenleme}").getValue(facesContext);

        kullaniciDataDuzenleme.setAd(selectedKullaniciData.getAd());
        kullaniciDataDuzenleme.setSoyAd(selectedKullaniciData.getSoyAd());
        kullaniciDataDuzenleme.setKullaniciAdi(selectedKullaniciData.getKullaniciAdi());
        kullaniciDataDuzenleme.setEposta(selectedKullaniciData.getEposta());
        kullaniciDataDuzenleme.setTelNo(selectedKullaniciData.getTelNo());
        kullaniciDataDuzenleme.setRol(selectedKullaniciData.getRol());

    }

    public void kisiGuncelleButonu() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        KullaniciDataDuzenleme kullaniciDataDuzenleme
                = (KullaniciDataDuzenleme) facesContext.getApplication()
                .createValueBinding("#{kullaniciDataDuzenleme}").getValue(facesContext);

        System.out.println("guncellebutonu row bilgisi");

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Kullanici kullanici = new Kullanici();

        if(!kullaniciDataDuzenleme.getAd().isEmpty())
            kullanici.setAd(kullaniciDataDuzenleme.getAd());
        else
            kullanici.setAd(selectedKullaniciData.getAd());

        if(!kullaniciDataDuzenleme.getSoyAd().isEmpty())
            kullanici.setSoyAd(kullaniciDataDuzenleme.getSoyAd());
        else
            kullanici.setSoyAd(selectedKullaniciData.getSoyAd());

        if(!kullaniciDataDuzenleme.getKullaniciAdi().isEmpty())
            kullanici.setKullaniciAdi(kullaniciDataDuzenleme.getKullaniciAdi());
        else
            kullanici.setKullaniciAdi(selectedKullaniciData.getKullaniciAdi());

        if(!kullaniciDataDuzenleme.getTelNo().isEmpty())
            kullanici.setTelNo(kullaniciDataDuzenleme.getTelNo());
        else
            kullanici.setTelNo(selectedKullaniciData.getTelNo());

        if(!kullaniciDataDuzenleme.getRol().isEmpty())
            kullanici.setRol(kullaniciDataDuzenleme.getRol());
        else
            kullanici.setRol(selectedKullaniciData.getRol());

        session.update(kullanici);
        session.getTransaction().commit();
        session.close();

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlg6').hide()");
        fillTable();
        refresh();

    }

    public void kisiAktifButonu() {

    }

    public void kisiPasifButonu() {

    }



}