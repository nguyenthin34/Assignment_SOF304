/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;


public class PCTraThuocCT {
    private int Ma_PC, ID_HDTH;
    private float HDTH_Tien;

    public PCTraThuocCT() {
    }

    public PCTraThuocCT(int Ma_PC, int ID_HDTH, float HDTH_Tien) {
        this.Ma_PC = Ma_PC;
        this.ID_HDTH = ID_HDTH;
        this.HDTH_Tien = HDTH_Tien;
    }

    public int getMa_PC() {
        return Ma_PC;
    }

    public void setMa_PC(int Ma_PC) {
        this.Ma_PC = Ma_PC;
    }

    public int getID_HDTH() {
        return ID_HDTH;
    }

    public void setID_HDTH(int ID_HDTH) {
        this.ID_HDTH = ID_HDTH;
    }

    public float getHDTH_Tien() {
        return HDTH_Tien;
    }

    public void setHDTH_Tien(float HDTH_Tien) {
        this.HDTH_Tien = HDTH_Tien;
    }
    
    
}
