/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;


public class TuThuoc {
    private String ID_Tu, Ten_Tu;
    private boolean TrangThai;

    public TuThuoc() {
    }

    public TuThuoc(String ID_Tu, String Ten_Tu, boolean TrangThai) {
        this.ID_Tu = ID_Tu;
        this.Ten_Tu = Ten_Tu;
        this.TrangThai = TrangThai;
    }

    public String getID_Tu() {
        return ID_Tu;
    }

    public void setID_Tu(String ID_Tu) {
        this.ID_Tu = ID_Tu;
    }

    public String getTen_Tu() {
        return Ten_Tu;
    }

    public void setTen_Tu(String Ten_Tu) {
        this.Ten_Tu = Ten_Tu;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return Ten_Tu;
    }
    
    
}
