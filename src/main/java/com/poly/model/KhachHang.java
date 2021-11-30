/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;


public class KhachHang {
    private String SDT_KH, Ten_KH, Email_KH, DiaChi;
    public KhachHang() {
    }

    public KhachHang(String SDT_KH, String Ten_KH, String Email_KH, String DiaChi) {
        this.SDT_KH = SDT_KH;
        this.Ten_KH = Ten_KH;
        this.Email_KH = Email_KH;
        this.DiaChi = DiaChi;
    }

   

    

  
    
    
    public String getSDT_KH() {
        return SDT_KH;
    }

    public void setSDT_KH(String SDT_KH) {
        this.SDT_KH = SDT_KH;
    }

    public String getTen_KH() {
        return Ten_KH;
    }

    public void setTen_KH(String Ten_KH) {
        this.Ten_KH = Ten_KH;
    }

    public String getEmail_KH() {
        return Email_KH;
    }

    public void setEmail_KH(String Email_KH) {
        this.Email_KH = Email_KH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
    
    
}
