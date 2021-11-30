package com.poly.dao;

import com.poly.model.TuThuoc;
import com.poly.Jdbc.SQLConnect;
import com.poly.model.DonViTinh;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TuThuocDAO {

    public void insert(TuThuoc model) throws Exception {
        try {
             String sql = "insert into TuThuoc values(?,?,?)";
        SQLConnect.executeUpdate(sql, model.getID_Tu(), model.getTen_Tu(), model.isTrangThai());
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"Thêm Thất Bại");
        }
    }
    public boolean Insert(TuThuoc model) throws Exception {
       if(model== null || model.getID_Tu() == null) {
    	   return false;
       }else {
    	   try {
               String sql = "insert into TuThuoc values(?,?,?)";
               SQLConnect.executeUpdate(sql, model.getID_Tu(), model.getTen_Tu(), model.isTrangThai());
              return true;
          } catch (Exception e) {
//              JOptionPane.showMessageDialog(null,"Thêm Thất Bại");
          }
       }
        return false;
    }

    public void update(TuThuoc model) throws Exception {
    	  try {
              String sql = "update TuThuoc set Ten_Tu = ?, TrangThai = ?"
                  + " where ID_Tu = ?";
          SQLConnect.executeUpdate(sql, model.getTen_Tu(),
                  model.isTrangThai() ? 1 : 0,
                  model.getID_Tu());
          } catch (Exception e) {
//              JOptionPane.showMessageDialog(null, "Cập Nhật Thất Bại");
          }
    }
    
    public boolean Update(TuThuoc model) throws Exception {
    	 if(model == null || model.getID_Tu() == null) {
      	   return false;
         }else {
      	   try {
                 String sql = "update TuThuoc set Ten_Tu = ?, TrangThai = ?"
                     + " where ID_Tu = ?";
             SQLConnect.executeUpdate(sql, model.getTen_Tu(),
                     model.isTrangThai() ? 1 : 0,
                     model.getID_Tu());
             return true;
//                 JOptionPane.showMessageDialog(null, "Cập Nhật Thành Công");
             } catch (Exception e) {
//                 JOptionPane.showMessageDialog(null, "Cập Nhật Thất Bại");
             }
         }
         return false;
      
    }
    
    public List<TuThuoc> select() {
        String sql = "select * from TuThuoc";
        return select(sql);
    }
    public List<TuThuoc> select_tt() {
        String sql = "select * from TuThuoc where TrangThai = 1";
        return select(sql);
    }
     public List<TuThuoc> select_tt2() {
        String sql = "select * from TuThuoc where TrangThai = 0";
        return select(sql);
    }
    public List<TuThuoc> select_ksd() {
        String sql = "select * from TuThuoc where TrangThai = 0";
        return select(sql);
    }
    public TuThuoc findByID(String ID_Tu) {
        String sql = "select * from TuThuoc where ID_Tu = ?";
        List<TuThuoc> list = select(sql, ID_Tu);
        return list.size() > 0 ? list.get(0) : null;
    }
    private List<TuThuoc>select(String sql, Object... args) {
        List<TuThuoc> list = new ArrayList<>();
        
        try {
            ResultSet rs = null;
            try {
                rs = SQLConnect.excuteQuery(sql, args);
                while (rs.next()) {
                    TuThuoc model = readFromResult(rs);
                    list.add(model);
                }
            } finally {
                rs.close();
            }
            
        } catch (Exception e) {
            
        }
        return list;
    }
    
    private TuThuoc readFromResult(ResultSet rs) throws SQLException {
        TuThuoc model = new TuThuoc();
        model.setID_Tu(rs.getString(1));
        model.setTen_Tu(rs.getString(2));
        model.setTrangThai(rs.getBoolean(3));
        return model;
    }
}
