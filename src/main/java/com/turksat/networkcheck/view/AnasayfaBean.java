package com.turksat.networkcheck.view;

import com.turksat.networkcheck.model.Log;
import com.turksat.networkcheck.model.Sunucu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        /**
        // Date kullanarak arama yapma
        //String hql = "From Log L where L.date = :date";
        //String hql = "From Log L where L.date between :date AND :date2";
        String hql = "From Log L where L.sunucuId=:id AND L.date between :date AND :date2";
        //String hql = "FROM Log L where L.sunucuId = :id";
        //String hql = "FROM Log L where L.sunucuId = :id order by date desc , time desc ";

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setParameter("date2",new Date());
        query.setParameter("date",new Date(System.currentTimeMillis()-24*60*60*1000));
        query.setParameter("id","3075cc7a-76dd-4784-95fc-a8f79f159a4f");
        List <Log> logs = query.list(); //

        System.out.println(logs.size());
        System.out.println(logs.get(0).getDurum());
        */



        tablo.removeAll(tablo);
        //filtrele iki ozellik
        String hql = "From Sunucu";

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery(hql);
        List<Sunucu> sunucuList = query.list();


        //tabloyu doldur
        for(int i=0; i<sunucuList.size(); i++){


            AnaData anaData = new AnaData();

            anaData.setSunucuSanalAdi(sunucuList.get(i).getSunucuSanalAdi());
            anaData.setSunucuIp(sunucuList.get(i).getSunucuIp());
            anaData.setSunucuPortBilgisi(sunucuList.get(i).getSunucuPortBilgisi());
            anaData.setKontrolPeriyodu(sunucuList.get(i).getKontrolPeriyodu());
            anaData.setSunucuKullaniciAdi(sunucuList.get(i).getSunucuKullaniciAdi());
            anaData.setSunucuSifre(sunucuList.get(i).getSunucuSifre());
            anaData.setSunucuTipi(sunucuList.get(i).getSunucuTipi());
            anaData.setSunucuUygulamaTipi(sunucuList.get(i).getSunucuUygulamaTipi());
            anaData.setSunucuTuru(sunucuList.get(i).getSunucuTuru());
            anaData.setProtokol(sunucuList.get(i).getProtokol());
            anaData.setHataMesaj(sunucuList.get(i).getHataMesaj());
            anaData.setId(sunucuList.get(i).getSunucuId());
            anaData.setAktifPasif(sunucuList.get(i).getAktifPasif());

            float result = ulasilabilirlik(sunucuList.get(i).getSunucuId(),selectMenu1);
            anaData.setUlasim(Float.toString(result));

            String sonUlasimZamani = sonUlasmaZamani(sunucuList.get(i).getSunucuId());
            anaData.setSonUlasim(sonUlasimZamani);
            anaData.setSonDurumZamani(sonDurumZamani(sunucuList.get(i).getSunucuId()));

            boolean flag = sonDurum(sunucuList.get(i).getSunucuId());
            if(!sonUlasimZamani.isEmpty()) {
                if (flag) {
                    anaData.setSonDurum("down");
                } else {
                    anaData.setSonDurum("up");
                }
            }
            tablo.add(anaData);
        }

        session.close();

        /////////////////////////////////
        /////////////////////////////////
        /////////////////////////////////
        /////////////////////////////////
        /////////////////////////////////


        /*
        //String id = "c5f871cd-0dd9-4f61-b7f9-0f5818bd2d6c";
        String id = "6dfe10fd-8bc2-41d2-8697-73e2fe73d13e";
        System.out.println(sonUlasmaZamani(id));
        System.out.println(sonDurumZamanu(id));
        System.out.println(sonDurum(id));
        System.out.println(ulasilabilirlik(id,"Yillik"));
        */


    }

    public void logButonu() {
    }

    public void anlikButonu() {
    }

    public String sonUlasmaZamani (String id){
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String bhql = "FROM Log L where L.sunucuId = :id AND L.error = :error order by date desc , time desc ";
        Query bquery = session.createQuery(bhql);
        bquery.setParameter("error",false);
        bquery.setParameter("id",id);
        List<Log> logList = bquery.list();

        session.close();

        if(!logList.isEmpty()) {
            return logList.get(0).getTime();
        }
        else {
            return "";
        }
    }

    public String sonDurumZamani (String id){
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String bhql = "FROM Log L where L.sunucuId = :id order by date desc , time desc ";
        Query bquery = session.createQuery(bhql);
        bquery.setParameter("id",id);
        List<Log> logList = bquery.list();

        session.close();

        if(!logList.isEmpty()) {
            return logList.get(0).getTime();
        }
        else {
            return "";
        }
    }

    public boolean sonDurum (String id){
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String bhql = "FROM Log L where L.sunucuId = :id order by date desc , time desc ";
        Query bquery = session.createQuery(bhql);
        bquery.setParameter("id",id);
        List<Log> logList = bquery.list();

        session.close();

        if(!logList.isEmpty()) {
            return logList.get(0).isError();
        }
        else {
            return false;
        }
    }

    public float ulasilabilirlik (String id, String param){
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String ahql = "From Log L where L.sunucuId = :id AND L.date between :past AND :today";
        Query aquery = session.createQuery(ahql);
        aquery.setParameter("id",id);
        aquery.setParameter("today",new Date());

        if(param.equals("Gunluk")){
            aquery.setParameter("past",new Date(System.currentTimeMillis()-24*60*60*1000));
        }
        if(param.equals("Aylik")){
            aquery.setParameter("past",new Date(System.currentTimeMillis()-7*24*60*60*1000));
        }
        if(param.equals("Yillik")){
            aquery.setParameter("past",new Date(System.currentTimeMillis()-365*24*60*60*1000));
        }
        List<Log> logList = aquery.list();
        session.close();

        int allLogs = logList.size();
        int goodLogs = 0;
        for (int j =0; j<logList.size(); j++){
            if (!logList.get(j).isError()){
                goodLogs++;
            }
        }
        float result = 0;
        if(allLogs !=0)
            result = (float)goodLogs/allLogs;

        if(allLogs !=0) {
            return result;
        }
        else {
            return 0;
        }

    }

}
