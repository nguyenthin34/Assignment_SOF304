package com.poly.Jdbc;
import java.io.Serializable;

public class hostt implements Serializable{
    public String host, user, pass, db, port;

    public hostt() {
    }

    public hostt(String host, String user, String pass, String db, String port) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.db = db;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    
    
}
