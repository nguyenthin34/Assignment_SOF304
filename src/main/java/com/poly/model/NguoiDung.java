/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;


public class NguoiDung {
    private String Ma_ND, Ten_ND, sdt,Email, MatKhau;
    private boolean VaiTro, TrangThai;

    public NguoiDung(String Ma_ND, String Ten_ND, String sdt, String Email, String MatKhau, boolean VaiTro, boolean TrangThai) {
        this.Ma_ND = Ma_ND;
        this.Ten_ND = Ten_ND;
        this.sdt = sdt;
        this.Email = Email;
        this.MatKhau = MatKhau;
        this.VaiTro = VaiTro;
        this.TrangThai = TrangThai;
    }

    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    

    public NguoiDung() {
    }

    public String getMa_ND() {
        return Ma_ND;
    }

    public void setMa_ND(String Ma_ND) {
        this.Ma_ND = Ma_ND;
    }

    public String getTen_ND() {
        return Ten_ND;
    }

    public void setTen_ND(String Ten_ND) {
        this.Ten_ND = Ten_ND;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public boolean isVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
