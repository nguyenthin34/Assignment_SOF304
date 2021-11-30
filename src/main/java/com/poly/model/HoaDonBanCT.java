
package com.poly.model;


public class HoaDonBanCT {
    private int ID_HDB, Ma_Thuoc, SoLuong;
    private float GiaBan;

    public HoaDonBanCT() {
    }

    public HoaDonBanCT(int ID_HDB, int Ma_Thuoc, int SoLuong, float GiaBan) {
        this.ID_HDB = ID_HDB;
        this.Ma_Thuoc = Ma_Thuoc;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan;
    }

    public int getID_HDB() {
        return ID_HDB;
    }

    public void setID_HDB(int ID_HDB) {
        this.ID_HDB = ID_HDB;
    }

    public int getMa_Thuoc() {
        return Ma_Thuoc;
    }

    public void setMa_Thuoc(int Ma_Thuoc) {
        this.Ma_Thuoc = Ma_Thuoc;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(float GiaBan) {
        this.GiaBan = GiaBan;
    }
    
    
}
