/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;


public class Thuoc {
    private int ID_Thuoc;
    private String Ten_Thuoc, ID_NhomThuoc, ID_DVT, Cach_Dung, HoatChat,HamLuong, DongGoi, NoiSanXuat;
    private boolean TrangThai;

    public Thuoc() {
    }

    public Thuoc(int ID_Thuoc, String Ten_Thuoc, String ID_NhomThuoc, String ID_DVT, String Cach_Dung, String HoatChat, String HamLuong, String DongGoi, String NoiSanXuat, boolean TrangThai) {
        this.ID_Thuoc = ID_Thuoc;
        this.Ten_Thuoc = Ten_Thuoc;
        this.ID_NhomThuoc = ID_NhomThuoc;
        this.ID_DVT = ID_DVT;
        this.Cach_Dung = Cach_Dung;
        this.HoatChat = HoatChat;
        this.HamLuong = HamLuong;
        this.DongGoi = DongGoi;
        this.NoiSanXuat = NoiSanXuat;
        this.TrangThai = TrangThai;
    }
    
    public Thuoc( String Ten_Thuoc, String ID_NhomThuoc, String ID_DVT, String Cach_Dung, String HoatChat, String HamLuong, String DongGoi, String NoiSanXuat, boolean TrangThai) {
        this.Ten_Thuoc = Ten_Thuoc;
        this.ID_NhomThuoc = ID_NhomThuoc;
        this.ID_DVT = ID_DVT;
        this.Cach_Dung = Cach_Dung;
        this.HoatChat = HoatChat;
        this.HamLuong = HamLuong;
        this.DongGoi = DongGoi;
        this.NoiSanXuat = NoiSanXuat;
        this.TrangThai = TrangThai;
    }
    public Thuoc(int ID_Thuoc, String Ten_Thuoc, boolean TrangThai) {
        this.ID_Thuoc = ID_Thuoc;
        this.Ten_Thuoc = Ten_Thuoc;
        this.TrangThai = TrangThai;
    }

   
    
    

    public int getID_Thuoc() {
        return ID_Thuoc;
    }

    public void setID_Thuoc(int ID_Thuoc) {
        this.ID_Thuoc = ID_Thuoc;
    }

    public String getTen_Thuoc() {
        return Ten_Thuoc;
    }

    public void setTen_Thuoc(String Ten_Thuoc) {
        this.Ten_Thuoc = Ten_Thuoc;
    }

    public String getID_NhomThuoc() {
        return ID_NhomThuoc;
    }

    public void setID_NhomThuoc(String ID_NhomThuoc) {
        this.ID_NhomThuoc = ID_NhomThuoc;
    }

    public String getID_DVT() {
        return ID_DVT;
    }

    public void setID_DVT(String ID_DVT) {
        this.ID_DVT = ID_DVT;
    }

    public String getCach_Dung() {
        return Cach_Dung;
    }

    public void setCach_Dung(String Cach_Dung) {
        this.Cach_Dung = Cach_Dung;
    }

    public String getHoatChat() {
        return HoatChat;
    }

    public void setHoatChat(String HoatChat) {
        this.HoatChat = HoatChat;
    }

    public String getHamLuong() {
        return HamLuong;
    }

    public void setHamLuong(String HamLuong) {
        this.HamLuong = HamLuong;
    }

    public String getDongGoi() {
        return DongGoi;
    }

    public void setDongGoi(String DongGoi) {
        this.DongGoi = DongGoi;
    }

    public String getNoiSanXuat() {
        return NoiSanXuat;
    }

    public void setNoiSanXuat(String NoiSanXuat) {
        this.NoiSanXuat = NoiSanXuat;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return Ten_Thuoc;
    }

  
    
    
}
