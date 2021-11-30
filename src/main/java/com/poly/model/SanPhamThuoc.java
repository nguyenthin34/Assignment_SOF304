/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;

import java.util.Date;

public class SanPhamThuoc {

    private int Ma_Thuoc, ID_Thuoc;
    private String ID_NhaCC, ID_Tu;
    private Date HanSuDung;
    private float GiaNhap, GiaBan;
    private int SoLuongTon;
    private boolean TrangThai;

    public SanPhamThuoc() {
    }

    public SanPhamThuoc(int Ma_Thuoc, int ID_Thuoc, String ID_NhaCC, String ID_Tu, Date HanSuDung, float GiaNhap, float GiaBan, int SoLuongTon, boolean TrangThai) {
        this.Ma_Thuoc = Ma_Thuoc;
        this.ID_Thuoc = ID_Thuoc;
        this.ID_NhaCC = ID_NhaCC;
        this.ID_Tu = ID_Tu;
        this.HanSuDung = HanSuDung;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
        this.SoLuongTon = SoLuongTon;
        this.TrangThai = TrangThai;
    }

    public SanPhamThuoc(int ID_Thuoc, String ID_NhaCC, float GiaNhap, int SoLuongTon, Date HanSuDung) {
        this.ID_Thuoc = ID_Thuoc;
        this.ID_NhaCC = ID_NhaCC;

        this.GiaNhap = GiaNhap;
        this.SoLuongTon = SoLuongTon;
        this.HanSuDung = HanSuDung;
    }
    
    public SanPhamThuoc(int ID_Thuoc, String ID_NhaCC, String ID_Tu, float GiaNhap, float GiaBan, int SoLuongTon, Date HanSuDung, boolean trangthai) {
        this.ID_Thuoc = ID_Thuoc;
        this.ID_NhaCC = ID_NhaCC;
        this.ID_Tu = ID_Tu;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
        this.SoLuongTon = SoLuongTon;
        this.HanSuDung = HanSuDung;
        this.TrangThai = trangthai;
    }


    public SanPhamThuoc(int Ma_Thuoc, float GiaBan, int SoLuongTon, Date HanSuDung) {
        this.Ma_Thuoc = Ma_Thuoc;
        this.GiaBan = GiaBan;
        this.SoLuongTon = SoLuongTon;

        this.HanSuDung = HanSuDung;
    }

    public int getMa_Thuoc() {
        return Ma_Thuoc;
    }

    public void setMa_Thuoc(int Ma_Thuoc) {
        this.Ma_Thuoc = Ma_Thuoc;
    }

    public int getID_Thuoc() {
        return ID_Thuoc;
    }

    public void setID_Thuoc(int ID_Thuoc) {
        this.ID_Thuoc = ID_Thuoc;
    }

    public String getID_NhaCC() {
        return ID_NhaCC;
    }

    public void setID_NhaCC(String ID_NhaCC) {
        this.ID_NhaCC = ID_NhaCC;
    }

    public String getID_Tu() {
        return ID_Tu;
    }

    public void setID_Tu(String ID_Tu) {
        this.ID_Tu = ID_Tu;
    }

    public Date getHanSuDung() {
        return HanSuDung;
    }

    public void setHanSuDung(Date HanSuDung) {
        this.HanSuDung = HanSuDung;
    }

    public float getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(float GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public float getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(float GiaBan) {
        this.GiaBan = GiaBan;
    }

    public int getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(int SoLuongTon) {
        this.SoLuongTon = SoLuongTon;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

}
