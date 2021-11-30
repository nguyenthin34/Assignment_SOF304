/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.helper;

import com.poly.model.Thuoc;


public class ShareHepler {
//   public static String host, user, pass, database, port;
    public static int User = 2;
    public static int dshdbct;
    public static int dshdnct;
    public static int idhdb;
    public static String mand;
    public static Thuoc thuoc = new Thuoc();
    public static String nd, email, pas;
    public static int ma_pt;
    public static int ma_pc;
    public static Thuoc thuoc(int Id, String ten ){
        thuoc.setID_Thuoc(Id);
        thuoc.setTen_Thuoc(ten);
        return thuoc;
    }
}
