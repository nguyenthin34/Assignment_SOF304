/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;


public class PhieuThuCT {
    private int Ma_PT, ID_HDB;
    private float HDB_Tien;

    public PhieuThuCT() {
    }

    public PhieuThuCT(int Ma_PT, int ID_HDB, float HDB_Tien) {
        this.Ma_PT = Ma_PT;
        this.ID_HDB = ID_HDB;
        this.HDB_Tien = HDB_Tien;
    }

    public int getMa_PT() {
        return Ma_PT;
    }

    public void setMa_PT(int Ma_PT) {
        this.Ma_PT = Ma_PT;
    }

    public int getID_HDB() {
        return ID_HDB;
    }

    public void setID_HDB(int ID_HDB) {
        this.ID_HDB = ID_HDB;
    }

    public float getHDB_Tien() {
        return HDB_Tien;
    }

    public void setHDB_Tien(float HDB_Tien) {
        this.HDB_Tien = HDB_Tien;
    }
    
    
}
