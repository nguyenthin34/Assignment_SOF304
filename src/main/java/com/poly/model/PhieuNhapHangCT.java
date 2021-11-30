/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;

import java.util.Date;


public class PhieuNhapHangCT {
    private int ID_HDN, ID_Thuoc, Soluong;
    private float GiaNhap;
    private Date HanSuDung;

    public PhieuNhapHangCT() {
    }

    public PhieuNhapHangCT(int ID_HDN, int ID_Thuoc, int Soluong, float GiaNhap, Date HanSuDung) {
        this.ID_HDN = ID_HDN;
        this.ID_Thuoc = ID_Thuoc;
        this.Soluong = Soluong;
        this.GiaNhap = GiaNhap;
        this.HanSuDung = HanSuDung;
    }

    public Date getHanSuDung() {
        return HanSuDung;
    }

    public void setHanSuDung(Date HanSuDung) {
        this.HanSuDung = HanSuDung;
    }

  

    public int getID_HDN() {
        return ID_HDN;
    }

    public void setID_HDN(int ID_HDN) {
        this.ID_HDN = ID_HDN;
    }

    public int getID_Thuoc() {
        return ID_Thuoc;
    }

    public void setID_Thuoc(int ID_Thuoc) {
        this.ID_Thuoc = ID_Thuoc;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public float getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(float GiaNhap) {
        this.GiaNhap = GiaNhap;
    }
    
    
}
