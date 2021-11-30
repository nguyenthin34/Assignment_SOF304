/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;

import java.util.Date;


public class PhieuNhapHang {
    private int ID_HDN;
    private String ID_NhaCC;
    private float TongTienHang;
    private Date NgayLapHD;
    private String NguoiTao;

    public PhieuNhapHang() {
    }

    public PhieuNhapHang(int ID_HDN, String ID_NhaCC, float TongTienHang, Date NgayLapHD, String NguoiTao) {
        this.ID_HDN = ID_HDN;
        this.ID_NhaCC = ID_NhaCC;
        this.TongTienHang = TongTienHang;
        this.NgayLapHD = NgayLapHD;
        this.NguoiTao = NguoiTao;
    }
    
    public PhieuNhapHang(String ID_NhaCC, float TongTienHang, Date NgayLapHD, String NguoiTao) {
        this.ID_NhaCC = ID_NhaCC;
        this.TongTienHang = TongTienHang;
        this.NgayLapHD = NgayLapHD;
        this.NguoiTao = NguoiTao;
    }

    public int getID_HDN() {
        return ID_HDN;
    }

    public void setID_HDN(int ID_HDN) {
        this.ID_HDN = ID_HDN;
    }

    public String getID_NhaCC() {
        return ID_NhaCC;
    }

    public void setID_NhaCC(String ID_NhaCC) {
        this.ID_NhaCC = ID_NhaCC;
    }

    public float getTongTienHang() {
        return TongTienHang;
    }

    public void setTongTienHang(float TongTienHang) {
        this.TongTienHang = TongTienHang;
    }

    public Date getNgayLapHD() {
        return NgayLapHD;
    }

    public void setNgayLapHD(Date NgayLapHD) {
        this.NgayLapHD = NgayLapHD;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public void setNguoiTao(String NguoiTao) {
        this.NguoiTao = NguoiTao;
    }
    
    
}
