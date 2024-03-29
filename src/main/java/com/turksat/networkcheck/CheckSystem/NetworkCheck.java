package com.turksat.networkcheck.CheckSystem;


import com.turksat.networkcheck.model.Kullanici;
import com.turksat.networkcheck.model.Log;
import com.turksat.networkcheck.model.Sunucu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.net.ssl.HttpsURLConnection;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressWarnings("Duplicates")

/**
 * Created by furkanmumcu on 02/08/2017.
 */
public class NetworkCheck {


    private String serverType;
    private int duration;
    private String serverName;
    private int portNumber;
    private String userName;
    private String password;
    private String dbName;
    private String mailAddress = null;
    private String pnumber = null;
    private Sunucu sunucu;
    private String sunucuId;


    public NetworkCheck(String serverType, int waitSeconds, String servername, int portNumber) {
        this.serverType = serverType;
        this.duration = waitSeconds;
        this.serverName = servername;
        this.portNumber = portNumber;
    }

    public NetworkCheck(String serverType, int duration, String serverName, int portNumber, String userName, String password, String dbName) {
        this.serverType = serverType;
        this.duration = duration;
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.userName = userName;
        this.password = password;
        this.dbName = dbName;
    }

    public NetworkCheck(){}

    public NetworkCheck(Sunucu sunucu){
        this.sunucu = sunucu;
    }

    public NetworkCheck(String sunucuId) {
        this.sunucuId = sunucuId;
    }

    public Sunucu getSunucu() {
        return sunucu;
    }

    public void setSunucu(Sunucu sunucu) {
        this.sunucu = sunucu;
    }

    public String getSunucuId() {
        return sunucuId;
    }

    public void setSunucuId(String sunucuId) {
        this.sunucuId = sunucuId;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int waitSeconds) {
        this.duration = waitSeconds;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String servername) {
        this.serverName = servername;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public void check(){

        Thread t = new Thread(sunucuId)
        {
            public void run() {

                while(true) {
                    String hql = "From Sunucu S where S.sunucuId = :id";

                    Configuration configuration = new Configuration();
                    configuration.configure();

                    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Query query = session.createQuery(hql);
                    query.setParameter("id", sunucuId);
                    List<Sunucu> sunucuList = query.list();

                    sunucu = sunucuList.get(0);
                    session.close();
                    sessionFactory.close();
                    if(sunucu.getAktifPasif()) {
                        if ("TPC/IP".equals(sunucu.getProtokol())) {
                            System.out.println("GIRDIM");
                            boolean connCheck = false;

                            try {
                                Socket clientSocket = new Socket(sunucu.getSunucuIp(), Integer.parseInt(sunucu.getSunucuPortBilgisi()));
                                clientSocket.setSoTimeout(5000);
                                connCheck = clientSocket.isConnected();
                                System.out.println("Server is up... " + "connCheck: " + connCheck);

                                //log olustur
                                logOlustur();

                            } catch (Exception e) {
                                //errorMsg(e);
                                logOlustur(e);
                                //notifyUser(sunucu.getSunucuIp());
                            }

                        }

                        if ("JDBC".equals(sunucu.getProtokol())) {
                            Connection c = null;
                            try {
                                Class.forName("org.postgresql.Driver");

                                //c = DriverManager
                                //      .getConnection("jdbc:postgresql://localhost:5432/fortesting",
                                //            "postgres", "123");


                                c = DriverManager
                                        .getConnection("jdbc:postgresql://" + sunucu.getSunucuIp() + ":" + sunucu.getSunucuPortBilgisi()
                                                + "/", sunucu.getSunucuKullaniciAdi(), sunucu.getSunucuSifre());
                                System.out.println("Server is up");
                                c.close();

                                logOlustur();
                            } catch (Exception e) {
                                //errorMsg(e);
                                System.out.println("Server is down");
                                logOlustur(e);
                            }
                        }

                        if ("HTTP".equals(sunucu.getProtokol())) {


                            try {
                                String url = "http://" + sunucu.getSunucuIp();

                                URL obj = new URL(url);
                                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                                System.out.println("http server is up... response code: " + con.getResponseCode());
                                logOlustur();

                            } catch (Exception e) {
                                System.out.println("http server is down");
                                //errorMsg(e);
                                logOlustur(e);
                            }

                        }

                        if ("HTTPS".equals(sunucu.getProtokol())) {


                            try {
                                String url = "https://" + sunucu.getSunucuIp();

                                URL obj = new URL(url);
                                HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
                                System.out.println("https server is up... response code: " + con.getResponseCode());
                                logOlustur();
                            } catch (Exception e) {
                                System.out.println("https server is down");
                                errorMsg(e);
                                logOlustur(e);
                            }

                        }

                        try {
                            Thread.sleep(sunucu.getKontrolPeriyodu() * 60000);
                        } catch (InterruptedException e) {
                            //e.printStackTrace();
                        }
                    }
                    else{
                        System.out.println("NOT AKTIF");

                        try {
                            Thread.sleep(sunucu.getKontrolPeriyodu() * 60000);
                        } catch (InterruptedException e) {
                            //e.printStackTrace();
                        }
                    }
                }
            }
        }; t.start();

    }

    public void oneTimeCheck(){
        String hql = "From Sunucu S where S.sunucuId = :id";

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setParameter("id", sunucuId);
        List<Sunucu> sunucuList = query.list();

        sunucu = sunucuList.get(0);
        session.close();
        sessionFactory.close();

        if ("TPC/IP".equals(sunucu.getProtokol())) {
            System.out.println("GIRDIM");
            boolean connCheck = false;

            try {
                Socket clientSocket = new Socket(sunucu.getSunucuIp(), Integer.parseInt(sunucu.getSunucuPortBilgisi()));
                clientSocket.setSoTimeout(5000);
                connCheck = clientSocket.isConnected();
                System.out.println("Server is up... " + "connCheck: " + connCheck);

                //log olustur
                logOlustur();

            } catch (Exception e) {
                //errorMsg(e);
                logOlustur(e);
                //notifyUser(sunucu.getSunucuIp());
            }

        }

        if ("JDBC".equals(sunucu.getProtokol())) {
            Connection c = null;
            try {
                Class.forName("org.postgresql.Driver");

                //c = DriverManager
                //      .getConnection("jdbc:postgresql://localhost:5432/fortesting",
                //            "postgres", "123");


                c = DriverManager
                        .getConnection("jdbc:postgresql://" + sunucu.getSunucuIp() + ":" + sunucu.getSunucuPortBilgisi()
                                + "/", sunucu.getSunucuKullaniciAdi(), sunucu.getSunucuSifre());
                System.out.println("Server is up");
                c.close();

                logOlustur();
            } catch (Exception e) {
                //errorMsg(e);
                System.out.println("Server is down");
                logOlustur(e);
            }
        }

        if ("HTTP".equals(sunucu.getProtokol())) {


            try {
                String url = "http://" + sunucu.getSunucuIp();

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                System.out.println("http server is up... response code: " + con.getResponseCode());
                logOlustur();

            } catch (Exception e) {
                System.out.println("http server is down");
                //errorMsg(e);
                logOlustur(e);
            }

        }

        if ("HTTPS".equals(sunucu.getProtokol())) {


            try {
                String url = "https://" + sunucu.getSunucuIp();

                URL obj = new URL(url);
                HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
                System.out.println("https server is up... response code: " + con.getResponseCode());
                logOlustur();
            } catch (Exception e) {
                System.out.println("https server is down");
                errorMsg(e);
                logOlustur(e);
            }

        }

    }

    private String errorMsg (Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        //System.out.println(sw.toString());
        String lines[] = sw.toString().split("\\r?\\n");
        System.out.println(lines[0]);
        return sw.toString();
    }

    private void notifyUser(String info){
        //admin kullanicilarin mail adresini bul
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "From Kullanici K where K.rol =:rol";

        Query query = session.createQuery(hql);
        query.setParameter("rol", "Yonetici");
        List<Kullanici> kullaniciList = query.list();

        for(int i = 0; i<kullaniciList.size(); i++){
            if(kullaniciList.get(i).getEposta()!=null){
                SendMail sendMail = new SendMail();
                String msg = "The server " + info + " is down";
                sendMail.send(kullaniciList.get(i).getEposta(),"server is down",msg);
            }

            if(kullaniciList.get(i).getTelNo()!=null){
                //SendSMS sendSMS = new SendSMS();
                //sendSMS.send(pnumber,"server is down");
            }
        }

    }

    private void logOlustur(){
        Configuration logConfiguration = new Configuration();
        logConfiguration.configure();

        SessionFactory logSessionFactory = new Configuration().configure().buildSessionFactory();
        Session logSession = logSessionFactory.openSession();
        logSession.beginTransaction();

        Log log = new Log();
        log.setDurum("server is up");
        log.setDate(new Date());
        log.setTime(new SimpleDateFormat("HH:mm").format(new Date()));
        log.setSunucuId(sunucuId);
        log.setSunucu(sunucu);
        log.setError(false);

        logSession.save(log);
        logSession.getTransaction().commit();
        logSession.close();
        logSessionFactory.close();
    }

    private void logOlustur(Exception e){
        Configuration logConfiguration = new Configuration();
        logConfiguration.configure();

        SessionFactory logSessionFactory = new Configuration().configure().buildSessionFactory();
        Session logSession = logSessionFactory.openSession();
        logSession.beginTransaction();

        Log log = new Log();
        log.setDurum("ERROR: " + errorMsg(e));
        log.setDate(new Date());
        log.setTime(new SimpleDateFormat("HH:mm").format(new Date()));
        log.setSunucuId(sunucuId);
        log.setSunucu(sunucu);
        log.setError(true);

        logSession.save(log);
        logSession.getTransaction().commit();
        logSession.close();
        logSessionFactory.close();

    }

}

