
package com.poly.model;

import java.util.Date;


public class NhaCungCap {
    private String ID_NhaCC, Ten_NCC, Email_NCC, SDT_NCC, DiaChi;
    private Date NgayTao;
    private boolean TrangThai;  

    public NhaCungCap() {
    }

    public NhaCungCap(String ID_NhaCC, String Ten_NCC, String Email_NCC, String SDT_NCC, String DiaChi, Date NgayTao, boolean TrangThai) {
        this.ID_NhaCC = ID_NhaCC;
        this.Ten_NCC = Ten_NCC;
        this.Email_NCC = Email_NCC;
        this.SDT_NCC = SDT_NCC;
        this.DiaChi = DiaChi;
        this.NgayTao = NgayTao;
        this.TrangThai = TrangThai;
    }

    public NhaCungCap(String ID_NhaCC, String Ten_NCC, boolean TrangThai) {
        this.ID_NhaCC = ID_NhaCC;
        this.Ten_NCC = Ten_NCC;
        this.TrangThai = TrangThai;
    }

    
    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

   

    public String getID_NhaCC() {
        return ID_NhaCC;
    }

    public void setID_NhaCC(String ID_NhaCC) {
        this.ID_NhaCC = ID_NhaCC;
    }

    public String getTen_NCC() {
        return Ten_NCC;
    }

    public void setTen_NCC(String Ten_NCC) {
        this.Ten_NCC = Ten_NCC;
    }

    public String getEmail_NCC() {
        return Email_NCC;
    }

    public void setEmail_NCC(String Email_NCC) {
        this.Email_NCC = Email_NCC;
    }

    public String getSDT_NCC() {
        return SDT_NCC;
    }

    public void setSDT_NCC(String SDT_NCC) {
        this.SDT_NCC = SDT_NCC;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return Ten_NCC;
    }
    
    
}
