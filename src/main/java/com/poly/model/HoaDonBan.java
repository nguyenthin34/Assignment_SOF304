
package com.poly.model;

import java.util.Date;


public class HoaDonBan {
    private int ID_HDB;
    private String SDT_KH;
    private float TongTien;
    private Date NgayLapHD;
    private String NguoiTao;

    public HoaDonBan() {
    }

    public HoaDonBan(Date NgayLapHD) {
        this.NgayLapHD = NgayLapHD;
    }
    
    
    public HoaDonBan(int ID_HDB, String SDT_KH, float TongTien, Date NgayLapHD, String NguoiTao) {
        this.ID_HDB = ID_HDB;
        this.SDT_KH = SDT_KH;
        this.TongTien = TongTien;
        this.NgayLapHD = NgayLapHD;
        this.NguoiTao = NguoiTao;
    }
    
    public HoaDonBan( String SDT_KH, float TongTien, Date NgayLapHD, String NguoiTao) {
        this.SDT_KH = SDT_KH;
        this.TongTien = TongTien;
        this.NgayLapHD = NgayLapHD;
        this.NguoiTao = NguoiTao;
    }

    public int getID_HDB() {
        return ID_HDB;
    }

    public void setID_HDB(int ID_HDB) {
        this.ID_HDB = ID_HDB;
    }

    public String getSDT_KH() {
        return SDT_KH;
    }

    public void setSDT_KH(String SDT_KH) {
        this.SDT_KH = SDT_KH;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
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
