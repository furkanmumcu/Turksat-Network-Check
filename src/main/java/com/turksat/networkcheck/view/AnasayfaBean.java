package com.turksat.networkcheck.view;

import com.turksat.networkcheck.model.Log;
import com.turksat.networkcheck.model.Sunucu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("Duplicates")


@ManagedBean
@SessionScoped
public class AnasayfaBean {
    private String selectMenu1;
    private String selectMenu2;
    private String selectMenu3;

    private List<LogDetay> gunlukLoglar=new ArrayList<LogDetay>(  );
    private List<LogDetay> haftalikLoglar=new ArrayList<LogDetay>(  );
    private List<LogDetay> aylikLoglar=new ArrayList<LogDetay>(  );
    private List<LogDetay> yillikLoglar=new ArrayList<LogDetay>(  );

    private List<AnaData> tablo=new ArrayList<AnaData> (  );

    private AnaData selectedAnaData;

    public List<LogDetay> getGunlukLoglar() {
        return gunlukLoglar;
    }

    public void setGunlukLoglar(List<LogDetay> gunlukLoglar) {
        this.gunlukLoglar = gunlukLoglar;
    }

    public List<LogDetay> getHaftalikLoglar() {
        return haftalikLoglar;
    }

    public void setHaftalikLoglar(List<LogDetay> haftalikLoglar) {
        this.haftalikLoglar = haftalikLoglar;
    }

    public List<LogDetay> getAylikLoglar() {
        return aylikLoglar;
    }

    public void setAylikLoglar(List<LogDetay> aylikLoglar) {
        this.aylikLoglar = aylikLoglar;
    }

    public List<LogDetay> getYillikLoglar() {
        return yillikLoglar;
    }

    public void setYillikLoglar(List<LogDetay> yillikLoglar) {
        this.yillikLoglar = yillikLoglar;
    }

    public AnaData getSelectedAnaData() {
        return selectedAnaData;
    }

    public void setSelectedAnaData(AnaData selectedAnaData) {
        this.selectedAnaData = selectedAnaData;
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
            float result2 = result * 100;
            int result3 = Math.round(result2);

            anaData.setUlasim("%" + Integer.toString(result3));
            //anaData.setUlasim(Float.toString(result));

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
        sessionFactory.close();
    }

    public void logButonu() {
        System.out.println("LOG");
        System.out.println(selectedAnaData.getSunucuSanalAdi());
        System.out.println(selectedAnaData.getId());

        //
        yillikLoglar.removeAll(yillikLoglar);
        gunlukLoglar.removeAll(gunlukLoglar);
        aylikLoglar.removeAll(aylikLoglar);
        haftalikLoglar.removeAll(haftalikLoglar);

        String id = selectedAnaData.getId();
        Date gunluk = new Date(System.currentTimeMillis()-24*60*60*1000);
        Date haftalik = new Date(System.currentTimeMillis()-7*24*60*60*1000);
        Date aylik = new Date(System.currentTimeMillis()-(30L*24*60*60*1000));
        Date yillik = new Date(System.currentTimeMillis()-(365L*24*60*60*1000));

        List<Log> gunlukLoglarL = historicLogs(id,gunluk);
        List<Log> haftalikLoglarL = historicLogs(id,haftalik);
        List<Log> aylikLoglarL = historicLogs(id,aylik);
        List<Log> yillikLoglarL = historicLogs(id,yillik);

        if(!gunlukLoglarL.isEmpty()){
            for(int i =0; i<gunlukLoglarL.size(); i++){
                LogDetay logDetay = new LogDetay();
                logDetay.setHata(gunlukLoglarL.get(i).getDurum());

                String date = gunlukLoglarL.get(i).getDate().toString();
                String[] parts = date.split(" ");
                String time = gunlukLoglarL.get(i).getTime();
                System.out.println(time);

                logDetay.setHatazaman(parts[0] + " " + time);
                gunlukLoglar.add(logDetay);
            }
        }

        if(!aylikLoglarL.isEmpty()){
            for(int i =0; i<aylikLoglarL.size(); i++){
                LogDetay logDetay = new LogDetay();
                logDetay.setHata(aylikLoglarL.get(i).getDurum());

                String date = aylikLoglarL.get(i).getDate().toString();
                String[] parts = date.split(" ");
                String time = aylikLoglarL.get(i).getTime();

                logDetay.setHatazaman(parts[0] + " " + time);
                aylikLoglar.add(logDetay);
            }
        }

        if(!haftalikLoglarL.isEmpty()){
            for(int i =0; i<haftalikLoglarL.size(); i++){
                LogDetay logDetay = new LogDetay();
                logDetay.setHata(haftalikLoglarL.get(i).getDurum());

                String date = haftalikLoglarL.get(i).getDate().toString();
                String[] parts = date.split(" ");
                String time = haftalikLoglarL.get(i).getTime();

                logDetay.setHatazaman(parts[0] + " " + time);
                haftalikLoglar.add(logDetay);
            }
        }

        if(!yillikLoglarL.isEmpty()){
            for(int i =0; i<yillikLoglarL.size(); i++){
                LogDetay logDetay = new LogDetay();
                logDetay.setHata(yillikLoglarL.get(i).getDurum());

                String date = yillikLoglarL.get(i).getDate().toString();
                String[] parts = date.split(" ");
                String time = yillikLoglarL.get(i).getTime();

                logDetay.setHatazaman(parts[0] + " " + time);
                yillikLoglar.add(logDetay);
            }
        }


    }

    public void anlikButonu() {
        System.out.println("ANLIK");
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
        sessionFactory.close();

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
        sessionFactory.close();

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
        sessionFactory.close();

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
            aquery.setParameter("past",new Date(System.currentTimeMillis()-(30L*24*60*60*1000)));
        }
        if(param.equals("Yillik")){
            aquery.setParameter("past",new Date(System.currentTimeMillis()-(365L*24*60*60*1000)));
        }
        if(param.equals("Haftalik")){
            aquery.setParameter("past",new Date(System.currentTimeMillis()-7*24*60*60*1000));
        }
        List<Log> logList = aquery.list();
        session.close();
        sessionFactory.close();

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

    public List<Log> historicLogs(String id, Date past){
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String ahql = "From Log L where L.sunucuId = :id AND L.error = :error AND L.date between :past AND :today";
        Query aquery = session.createQuery(ahql);
        aquery.setParameter("error",true);
        aquery.setParameter("id",id);
        aquery.setParameter("today",new Date());
        aquery.setParameter("past",past);

        List<Log> logList = aquery.list();
        session.close();
        sessionFactory.close();

        return logList;
    }

}
