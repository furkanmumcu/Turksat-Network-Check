package com.turksat.networkcheck.CheckSystem;


import com.turksat.networkcheck.model.Sunucu;

import javax.net.ssl.HttpsURLConnection;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

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

        Thread t = new Thread()
        {
            public void run() {
                int cnt = 0;
                while(sunucu.getAktifPasif()) {
                    if(serverType == "TCP/IP"){

                        boolean connCheck=false;

                        try{
                            Socket clientSocket = new Socket(serverName, portNumber);
                            clientSocket .setSoTimeout(5000);
                            connCheck = clientSocket.isConnected();
                            System.out.println("Server is up... " + "connCheck: " + connCheck);

                        }
                        catch (Exception e)
                        {
                            errorMsg(e);
                        }

                    }

                    if(serverType == "SQL"){
                        Connection c = null;
                        try {
                            Class.forName("org.postgresql.Driver");
                            /*
                            c = DriverManager
                                    .getConnection("jdbc:postgresql://localhost:5432/fortesting",
                                            "postgres", "123");
                            */

                            c = DriverManager
                                    .getConnection("jdbc:postgresql://"+ serverName + ":" + portNumber
                                            + "/" + dbName, userName,password);
                            System.out.println("Server is up");
                            c.close();
                        }
                        catch (Exception e) {
                            //errorMsg(e);
                            System.out.println("Server is down");
                        }
                    }

                    if(serverType == "HTTP"){


                        try{
                            String url = "http://"+serverName;

                            URL obj = new URL(url);
                            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                            System.out.println("http server is up... response code: " + con.getResponseCode());

                        }
                        catch (Exception e)
                        {
                            System.out.println("http server is down");
                            errorMsg(e);
                        }

                    }

                    if(serverType == "HTTPS"){


                        try{
                            String url = "https://"+serverName;

                            URL obj = new URL(url);
                            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
                            System.out.println("https server is up... response code: " + con.getResponseCode());

                        }
                        catch (Exception e)
                        {
                            System.out.println("https server is down");
                            errorMsg(e);
                        }

                    }


                    try {
                        Thread.sleep(duration * 1000);
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                    }

                }
            }
        }; t.start();

    }

    public void errorMsg (Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        System.out.println(sw.toString());

        if(mailAddress!=null){
            SendMail sendMail = new SendMail();
            sendMail.send(mailAddress,"server is down","info");
        }

        if(pnumber!=null){
            //SendSMS sendSMS = new SendSMS();
            //sendSMS.send(pnumber,"server is down");
        }
    }



}

