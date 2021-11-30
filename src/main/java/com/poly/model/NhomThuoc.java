/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;


public class NhomThuoc {
    private String ID_Nhom, Ten_Nhom;
    private boolean TrangThai;

    public NhomThuoc() {
    }

    public NhomThuoc(String ID_Nhom, String Ten_Nhom, boolean TrangThai) {
        this.ID_Nhom = ID_Nhom;
        this.Ten_Nhom = Ten_Nhom;
        this.TrangThai = TrangThai;
    }

    public String getID_Nhom() {
        return ID_Nhom;
    }

    public void setID_Nhom(String ID_Nhom) {
        this.ID_Nhom = ID_Nhom;
    }

    public String getTen_Nhom() {
        return Ten_Nhom;
    }

    public void setTen_Nhom(String Ten_Nhom) {
        this.Ten_Nhom = Ten_Nhom;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return Ten_Nhom;
    }
    
    
    
}
