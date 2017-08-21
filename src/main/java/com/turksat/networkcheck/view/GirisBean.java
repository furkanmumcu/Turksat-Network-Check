package com.turksat.networkcheck.view;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.swing.*;

import com.turksat.networkcheck.model.Kullanici;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;


@ManagedBean
@SessionScoped
public class GirisBean implements Serializable {
    private String kullaniciAdi;
    private String sifre;
    private String eposta;
    private String kullaniciAdiUnuttum;
    private boolean loggedIn = false;
    private Kullanici cuurentKullanici;


    public Kullanici getCuurentKullanici() {
        return cuurentKullanici;
    }

    public String getKullaniciAdiUnuttum() {
        return kullaniciAdiUnuttum;
    }

    public void setKullaniciAdiUnuttum(String kullaniciAdiUnuttum) {
        this.kullaniciAdiUnuttum = kullaniciAdiUnuttum;
    }

    public GirisBean() {
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }



    public void girisButonu() {

        // TODO: 21/08/2017  giris yapan kullanici bulunup currentKullanici ya esitlenecek

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql;
        Query query;
        hql = "FROM Kullanici";
        query = session.createQuery(hql);

        List<Kullanici> list = query.list();

        String encSifre="";
        try {
            MessageDigest messageDigestNesnesi = MessageDigest.getInstance("MD5");
            messageDigestNesnesi.update(sifre.getBytes());
            byte messageDigestDizisi[] = messageDigestNesnesi.digest();
            StringBuffer sb16 = new StringBuffer();
            for (int k = 0; k < messageDigestDizisi.length; k++) {
                sb16.append(Integer.toString((messageDigestDizisi[k] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println("Parolanın Şifrelenmiş Hali:(16) " + sb16.toString());
            encSifre = sb16.toString();
        }
        catch(NoSuchAlgorithmException ex){
            System.err.println(ex);
        }
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getKullaniciAdi().equals(kullaniciAdi) && list.get(i).getSifre().equals(encSifre)) {
                loggedIn = true;
                //System.out.println(list);
                //return navigationBean.redirectToWelcome();
                //return navigationBean.redirectToWelcome();
                //
                break;
            }
        }
        if (loggedIn){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("secured/anasayfa.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.print("yanlis");
            FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("login error", msg);
            JOptionPane.showMessageDialog(null, "Yanlıs kullanıcı adı veya şifre, Bilgilerinizi kontrol ederek tekrar deneyiniz");
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("giris.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
