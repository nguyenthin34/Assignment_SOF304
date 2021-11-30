/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;

import java.util.Date;


public class PhieuChi {
    private int Ma_PC;
    private Date NgayLapPC;
    private String NguoiThu;
    
    private float TienNhapthuoc, TienTraHang, TongTienChi;

    public PhieuChi() {
    }

    public PhieuChi(int Ma_PC, Date NgayLapPC, String NguoiThu, float TienNhapthuoc, float TienTraHang, float TongTienChi) {
        this.Ma_PC = Ma_PC;
        this.NgayLapPC = NgayLapPC;
        this.NguoiThu = NguoiThu;
        this.TienNhapthuoc = TienNhapthuoc;
        this.TienTraHang = TienTraHang;
        this.TongTienChi = TongTienChi;
    }
    
    public PhieuChi( Date NgayLapPC, String NguoiThu, float TienNhapthuoc, float TienTraHang, float TongTienChi) {
        this.NgayLapPC = NgayLapPC;
        this.NguoiThu = NguoiThu;
        this.TienNhapthuoc = TienNhapthuoc;
        this.TienTraHang = TienTraHang;
        this.TongTienChi = TongTienChi;
    }

    public float getTienNhapthuoc() {
        return TienNhapthuoc;
    }

    public void setTienNhapthuoc(float TienNhapthuoc) {
        this.TienNhapthuoc = TienNhapthuoc;
    }

    public float getTienTraHang() {
        return TienTraHang;
    }

    public void setTienTraHang(float TienTraHang) {
        this.TienTraHang = TienTraHang;
    }

   

    public int getMa_PC() {
        return Ma_PC;
    }

    public void setMa_PC(int Ma_PC) {
        this.Ma_PC = Ma_PC;
    }

    public Date getNgayLapPC() {
        return NgayLapPC;
    }

    public void setNgayLapPC(Date NgayLapPC) {
        this.NgayLapPC = NgayLapPC;
    }

    public String getNguoiThu() {
        return NguoiThu;
    }

    public void setNguoiThu(String NguoiThu) {
        this.NguoiThu = NguoiThu;
    }

    public float getTongTienChi() {
        return TongTienChi;
    }

    public void setTongTienChi(float TongTienChi) {
        this.TongTienChi = TongTienChi;
    }
    
    
}
