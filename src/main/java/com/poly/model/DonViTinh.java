/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;


public class DonViTinh {
    private String ID_DonViTinh, Ten_DVT;
    private boolean TrangThai;

    public DonViTinh() {
    }

    public DonViTinh(String ID_DonViTinh, String Ten_DVT, boolean TrangThai) {
        this.ID_DonViTinh = ID_DonViTinh;
        this.Ten_DVT = Ten_DVT;
        this.TrangThai = TrangThai;
    }

    public String getID_DonViTinh() {
        return ID_DonViTinh;
    }

    public void setID_DonViTinh(String ID_DonViTinh) {
        this.ID_DonViTinh = ID_DonViTinh;
    }

    public String getTen_DVT() {
        return Ten_DVT;
    }

    public void setTen_DVT(String Ten_DVT) {
        this.Ten_DVT = Ten_DVT;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return Ten_DVT;
    }
    
    
}
