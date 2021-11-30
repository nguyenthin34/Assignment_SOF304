/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.NhomThuocDAO;
import com.poly.helper.ShareHepler;
import com.poly.model.NhomThuoc;
import com.poly.model.TuThuoc;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC HP
 */
public class frmNhomThuoc extends javax.swing.JPanel {

    NhomThuocDAO dao = new NhomThuocDAO();
    int index;

    public frmNhomThuoc() {
        initComponents();
        checklogin();
        loadtodata();
    }
    public void checklogin(){
        if(ShareHepler.User == 1){
            btnthem.setEnabled(true);
            btncapnhat.setEnabled(true);
        }else if(ShareHepler.User == 0){
            btnthem.setEnabled(false);
            btncapnhat.setEnabled(false);
        }else {
              btnthem.setEnabled(false);
            btncapnhat.setEnabled(false);
        }
    }
    private void edit() {
        try {
            NhomThuoc model = dao.findByID(tblnhomthuoc.getValueAt(index, 0).toString());
            if (model != null) {
                setModel(model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void loadtodata() {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblnhomthuoc.getModel();
            List<NhomThuoc> list = dao.select();
            model.setRowCount(0);
            for (NhomThuoc x : list) {
                Vector v = new Vector();
                v.add(x.getID_Nhom());
                v.add(x.getTen_Nhom());
                v.add(x.isTrangThai() ? "Còn Sử Dụng" : "Không Sử Dụng");
                model.addRow(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void setModel(NhomThuoc model) {
        txtidnhomthuoc.setText(model.getID_Nhom());
        txttennhom.setText(model.getTen_Nhom());
        rdoconsd.setSelected(model.isTrangThai());
        rdokhongsd.setSelected(!model.isTrangThai());
    }

    private NhomThuoc getModel() {
        NhomThuoc model = new NhomThuoc();
        model.setID_Nhom(txtidnhomthuoc.getText());
        model.setTen_Nhom(txttennhom.getText());
        if (rdoconsd.isSelected()) {
            model.setTrangThai(true);
        } else {
            model.setTrangThai(false);
        }
        return model;
    }

    private void insert() {
        
        if (txtidnhomthuoc.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Phải Nhập ID Nhóm Thuốc");
            txtidnhomthuoc.requestFocus();
        }
        if (txttennhom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Tên Nhóm Thuốc");
            txttennhom.requestFocus();
        }
        NhomThuoc checktung = dao.findByID(txtidnhomthuoc.getText());
        if(checktung != null){
            JOptionPane.showMessageDialog(this,"Mã Nhóm Thuốc Đã Tồn Tại");
            return;
        }
        try {
            NhomThuoc model = getModel();
            dao.insert(model);
            loadtodata();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void update() {
        if(checkrongIDnhomThuoc()){
            return;
        }
        if (txttennhom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Tên Nhóm Thuốc");
            txttennhom.requestFocus();
        }

        try {
            NhomThuoc model = getModel();
            dao.update(model);
            loadtodata();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void clear(){
        setModel(new NhomThuoc());
        rdoconsd.setSelected(true);
        
    }
    
   private boolean checkrongIDnhomThuoc() {
        if (txtidnhomthuoc.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn ID Nhóm Thuốc Cần Cập Nhật");
            txtidnhomthuoc.requestFocus();
            return true;
        } else {
            return false;
        }
    }
   
   private boolean checkrongtenNhomThuoc() {
        if (txttennhom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Tên Nhóm Thuốc");
            txttennhom.requestFocus();
            return true;
        } else {
            return false;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnhomthuoc = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rdoconsd1 = new javax.swing.JRadioButton();
        rdokhongsd1 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtidnhomthuoc = new javax.swing.JTextField();
        txttennhom = new javax.swing.JTextField();
        rdoconsd = new javax.swing.JRadioButton();
        rdokhongsd = new javax.swing.JRadioButton();
        btnthem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btncapnhat = new javax.swing.JButton();

        setBackground(new java.awt.Color(30, 165, 252));

        tblnhomthuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Nhóm Thuốc", "Tên Nhóm Thuốc", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblnhomthuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnhomthuocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblnhomthuoc);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));

        buttonGroup2.add(rdoconsd1);
        rdoconsd1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoconsd1.setText("Còn Sử Dụng");
        rdoconsd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoconsd1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdokhongsd1);
        rdokhongsd1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdokhongsd1.setText("Không Sử Dụng");
        rdokhongsd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdokhongsd1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(rdoconsd1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                    .addComponent(rdokhongsd1)
                    .addGap(10, 10, 10)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoconsd1)
                        .addComponent(rdokhongsd1))
                    .addContainerGap(38, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cập Nhật"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID Nhóm Thuốc");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên Nhóm");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Trạng Thái");

        txtidnhomthuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txttennhom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonGroup1.add(rdoconsd);
        rdoconsd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoconsd.setText("Còn Sử Dụng");

        buttonGroup1.add(rdokhongsd);
        rdokhongsd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdokhongsd.setText("Không Sử Dụng");

        btnthem.setBackground(new java.awt.Color(30, 165, 252));
        btnthem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnthem.setForeground(new java.awt.Color(255, 255, 255));
        btnthem.setText("Thêm");
        btnthem.setBorderPainted(false);
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(30, 165, 252));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cập Nhật");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btncapnhat.setBackground(new java.awt.Color(30, 165, 252));
        btncapnhat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncapnhat.setForeground(new java.awt.Color(255, 255, 255));
        btncapnhat.setText("Làm Mới");
        btncapnhat.setBorderPainted(false);
        btncapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapnhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoconsd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(rdokhongsd))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttennhom)
                            .addComponent(txtidnhomthuoc)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncapnhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtidnhomthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txttennhom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoconsd)
                    .addComponent(rdokhongsd))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblnhomthuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnhomthuocMouseClicked
        // TODO add your handling code here:
        index = tblnhomthuoc.getSelectedRow();
        if (index >= 0) {
            edit();
        }
    }//GEN-LAST:event_tblnhomthuocMouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
    insert();
    
    }//GEN-LAST:event_btnthemActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         int hoi = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Cập Nhật Không?", "Hói Cập Nhật", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (hoi == JOptionPane.YES_OPTION) {
            update();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btncapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapnhatActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btncapnhatActionPerformed

    private void rdoconsd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoconsd1ActionPerformed
        // TODO add your handling code here:
         try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblnhomthuoc.getModel();
            List<NhomThuoc> list = dao.findByTT();
            if(list == null){
                JOptionPane.showMessageDialog(this, "Không có nhóm thuốc nào");
                return;
            }
            model.setRowCount(0);
            for (NhomThuoc x : list) {
                Vector v = new Vector();
                v.add(x.getID_Nhom());
                v.add(x.getTen_Nhom());
                v.add(x.isTrangThai() ? "Còn Sử Dụng" : "Không Sử Dụng");
                model.addRow(v);

            }
//            rdoksudung1.setSelected(false);
//            rdocsudung1.setSelected(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
//            rdocsudung1.setSelected(false);
//            rdoksudung1.setSelected(false);
        }
    }//GEN-LAST:event_rdoconsd1ActionPerformed

    private void rdokhongsd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdokhongsd1ActionPerformed
        // TODO add your handling code here:
         try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblnhomthuoc.getModel();
            List<NhomThuoc> list = dao.findByTT2();
            if(list == null){
                JOptionPane.showMessageDialog(this, "Không có Tủ Thuốc nào");
                return;
            }
            model.setRowCount(0);
            for (NhomThuoc x : list) {
                Vector v = new Vector();
                v.add(x.getID_Nhom());
                v.add(x.getTen_Nhom());
                v.add(x.isTrangThai() ? "Còn Sử Dụng" : "Không Sử Dụng");
                model.addRow(v);

            }
//            rdoksudung1.setSelected(false);
//            rdocsudung1.setSelected(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
//            rdocsudung1.setSelected(false);
//            rdoksudung1.setSelected(false);
        }
        
    }//GEN-LAST:event_rdokhongsd1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncapnhat;
    private javax.swing.JButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoconsd;
    private javax.swing.JRadioButton rdoconsd1;
    private javax.swing.JRadioButton rdokhongsd;
    private javax.swing.JRadioButton rdokhongsd1;
    private javax.swing.JTable tblnhomthuoc;
    private javax.swing.JTextField txtidnhomthuoc;
    private javax.swing.JTextField txttennhom;
    // End of variables declaration//GEN-END:variables
}
