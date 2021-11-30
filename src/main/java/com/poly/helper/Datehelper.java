/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author PC HP
 */
public class Datehelper {

    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                sdf.applyPattern(pattern[0]);
            }

            if (date == null) {
                return Datehelper.now();
            }
            return sdf.parse(date);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static String toString(Date date, String... partern) {
        try {
            if (partern.length > 0) {
                sdf.applyPattern(partern[0]);
            }
            if (date == null) {
                date = Datehelper.now();
            }
            return sdf.format(date);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static Date now() {
        Date date = new Date();
        Date nd = toDate(toString(date, "yyyy-MM-dd"), "yyyy-MM-dd");
        return nd;
    }

    /**
     * Bổ sung số ngày vào thời gian
     *
     * @param date thời gian hiện có
     * @param days số ngày cần bổ sung váo date
     * @return Date kết quả
     */
    public static Date adddays(Date date, int days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    /**
     * Bổ sung số ngày vào thời gian hiện hành
     *
     * @param days số ngày cần bổ sung vào thời gian hiện tại
     * @return Date kết quả
     */
    public static Date add(int days) {
        Date now = Datehelper.now();
        now.setTime(now.getTime() + days * 24 * 60 * 60 * 1000);
        return now;
    }

  
}
