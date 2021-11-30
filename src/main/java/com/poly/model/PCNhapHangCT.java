/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;


public class PCNhapHangCT {
    private int Ma_PC,ID_HDN;
    private float HDN_Tien;

    public PCNhapHangCT() {
    }

    public PCNhapHangCT(int Ma_PC, int ID_HDN, float HDN_Tien) {
        this.Ma_PC = Ma_PC;
        this.ID_HDN = ID_HDN;
        this.HDN_Tien = HDN_Tien;
    }

    public int getMa_PC() {
        return Ma_PC;
    }

    public void setMa_PC(int Ma_PC) {
        this.Ma_PC = Ma_PC;
    }

    public int getID_HDN() {
        return ID_HDN;
    }

    public void setID_HDN(int ID_HDN) {
        this.ID_HDN = ID_HDN;
    }

    public float getHDN_Tien() {
        return HDN_Tien;
    }

    public void setHDN_Tien(float HDN_Tien) {
        this.HDN_Tien = HDN_Tien;
    }
    
    
}
