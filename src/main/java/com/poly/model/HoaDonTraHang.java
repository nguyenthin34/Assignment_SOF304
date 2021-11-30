/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;

import java.util.Date;


public class HoaDonTraHang {
    private int ID_HDTH, ID_HDB;
    private float TienCanTra;
    private Date NgayTao;
    private String NguoiTao, MoTa;
    public HoaDonTraHang() {
    }

    public HoaDonTraHang(int ID_HDTH, int ID_HDB, float TienCanTra, Date NgayTao, String NguoiTao, String MoTa) {
        this.ID_HDTH = ID_HDTH;
        this.ID_HDB = ID_HDB;
        this.TienCanTra = TienCanTra;
        this.NgayTao = NgayTao;
        this.NguoiTao = NguoiTao;
        this.MoTa = MoTa;
    }
    
    public HoaDonTraHang( int ID_HDB, float TienCanTra, Date NgayTao, String NguoiTao, String MoTa) {
        this.ID_HDB = ID_HDB;
        this.TienCanTra = TienCanTra;
        this.NgayTao = NgayTao;
        this.NguoiTao = NguoiTao;
        this.MoTa = MoTa;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    

    public int getID_HDTH() {
        return ID_HDTH;
    }

    public void setID_HDTH(int ID_HDTH) {
        this.ID_HDTH = ID_HDTH;
    }

    public int getID_HDB() {
        return ID_HDB;
    }

    public void setID_HDB(int ID_HDB) {
        this.ID_HDB = ID_HDB;
    }

    public float getTienCanTra() {
        return TienCanTra;
    }

    public void setTienCanTra(float TienCanTra) {
        this.TienCanTra = TienCanTra;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public void setNguoiTao(String NguoiTao) {
        this.NguoiTao = NguoiTao;
    }
    
    
}
