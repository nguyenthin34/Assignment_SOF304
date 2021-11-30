/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;

import java.util.Date;


public class PhieuThu {
    private int Ma_PT;
    private Date NgayThu;
    private String NguoiThu;
    private float TienThu;

    public PhieuThu() {
    }

    public PhieuThu(int Ma_PT, Date NgayThu, String NguoiThu, float TienThu) {
        this.Ma_PT = Ma_PT;
        this.NgayThu = NgayThu;
        this.NguoiThu = NguoiThu;
        this.TienThu = TienThu;
    }
    
    public PhieuThu(Date NgayThu, String NguoiThu, float TienThu) {
        this.NgayThu = NgayThu;
        this.NguoiThu = NguoiThu;
        this.TienThu = TienThu;
    }

    public int getMa_PT() {
        return Ma_PT;
    }

    public void setMa_PT(int Ma_PT) {
        this.Ma_PT = Ma_PT;
    }

    public Date getNgayThu() {
        return NgayThu;
    }

    public void setNgayThu(Date NgayThu) {
        this.NgayThu = NgayThu;
    }

    public String getNguoiThu() {
        return NguoiThu;
    }

    public void setNguoiThu(String NguoiThu) {
        this.NguoiThu = NguoiThu;
    }

    public float getTienThu() {
        return TienThu;
    }

    public void setTienThu(float TienThu) {
        this.TienThu = TienThu;
    }
    
    
}
