/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;


public class ThuocTam {
    private int Id_Thuoc;
    private String ten_thuoc;
    private float gianhap;
    private int soluong;

    public ThuocTam() {
    }

    public ThuocTam(int Id_Thuoc, String ten_thuoc, float gianhap, int soluong) {
        this.Id_Thuoc = Id_Thuoc;
        this.ten_thuoc = ten_thuoc;
        this.gianhap = gianhap;
        this.soluong = soluong;
    }

    public int getId_Thuoc() {
        return Id_Thuoc;
    }

    public void setId_Thuoc(int Id_Thuoc) {
        this.Id_Thuoc = Id_Thuoc;
    }

    public String getTen_thuoc() {
        return ten_thuoc;
    }

    public void setTen_thuoc(String ten_thuoc) {
        this.ten_thuoc = ten_thuoc;
    }

    public float getGianhap() {
        return gianhap;
    }

    public void setGianhap(float gianhap) {
        this.gianhap = gianhap;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    
}
