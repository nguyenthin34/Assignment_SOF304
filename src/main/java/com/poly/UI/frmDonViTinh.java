/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.DonViTinhDAO;
import com.poly.helper.ShareHepler;
import com.poly.model.DonViTinh;
import com.poly.model.NhomThuoc;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmDonViTinh extends javax.swing.JPanel {
    
    int index;
    DonViTinhDAO dao = new DonViTinhDAO();

    public frmDonViTinh() {
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
    private void loadtodata() {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tbldonvitinh.getModel();
            List<DonViTinh> list = dao.select();
            if(list == null){
                JOptionPane.showMessageDialog(this,"Không có đơn vị tính nào");
                return;
            }
            model.setRowCount(0);
            for (DonViTinh x : list) {
                Vector v = new Vector();
                v.add(x.getID_DonViTinh());
                v.add(x.getTen_DVT());
                v.add(x.isTrangThai() ? "Còn Sử Dụng" : "Không Sử Dụng");
                model.addRow(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void setModel(DonViTinh model) {
        txtiddvt.setText(model.getID_DonViTinh());
        txttendvt.setText(model.getTen_DVT());
        rdoconsd.setSelected(model.isTrangThai());
        rdokhongsd.setSelected(!model.isTrangThai());
    }
    
    private DonViTinh getModel() {
        DonViTinh model = new DonViTinh();
        model.setID_DonViTinh(txtiddvt.getText());
        model.setTen_DVT(txttendvt.getText());
        if (rdoconsd.isSelected()) {
            model.setTrangThai(true);
        } else {
            model.setTrangThai(false);
        }
        return model;
    }

    private void insert() {
        if (txtiddvt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Phải Nhập ID Đơn Vị Tính");
            txtiddvt.requestFocus();
        }
        if (txttendvt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Tên Đơn Vị Tính");
            txttendvt.requestFocus();
        }
        DonViTinh dvt = dao.findByID(txtiddvt.getText());
        if(dvt != null){
            JOptionPane.showMessageDialog(this,"Mã Đơn Vị Tính Đã Tồn Tại");
            return;
        }
        try {
            DonViTinh model = getModel();
            dao.insert(model);
            loadtodata();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void update() {
        if (txtiddvt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Phải Chưa Chọn ID Đơn Vị Tính Cần Cập Nhật");
            txtiddvt.requestFocus();
        }
        if (txttendvt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Tên Đơn Vị Tính");
            txttendvt.requestFocus();
        }
        
        try {
            DonViTinh model = getModel();
            dao.update(model);
            loadtodata();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void clear() {
        setModel(new DonViTinh());
        rdoconsd.setSelected(true);
    }
    
    private void edit() {
        try {
            DonViTinh model = dao.findByID(tbldonvitinh.getValueAt(index, 0).toString());
            if (model != null) {
                setModel(model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldonvitinh = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rdoconsd1 = new javax.swing.JRadioButton();
        rdokhongsd1 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtiddvt = new javax.swing.JTextField();
        txttendvt = new javax.swing.JTextField();
        rdoconsd = new javax.swing.JRadioButton();
        rdokhongsd = new javax.swing.JRadioButton();
        btnthem = new javax.swing.JButton();
        btncapnhat = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();

        setBackground(new java.awt.Color(30, 165, 252));

        tbldonvitinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Đơn Vị Tính", "Tên Đơn Vị Tính", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldonvitinh.setGridColor(new java.awt.Color(255, 255, 255));
        tbldonvitinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldonvitinhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbldonvitinh);

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
            .addGap(0, 337, Short.MAX_VALUE)
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
        jLabel1.setText("ID Đơn Vị Tính");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên Đơn Vị Tính");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Trạng Thái");

        txtiddvt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txttendvt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        btncapnhat.setBackground(new java.awt.Color(30, 165, 252));
        btncapnhat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncapnhat.setForeground(new java.awt.Color(255, 255, 255));
        btncapnhat.setText("Cập Nhật");
        btncapnhat.setBorderPainted(false);
        btncapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapnhatActionPerformed(evt);
            }
        });

        btnlammoi.setBackground(new java.awt.Color(30, 165, 252));
        btnlammoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnlammoi.setForeground(new java.awt.Color(255, 255, 255));
        btnlammoi.setText("Làm Mới");
        btnlammoi.setBorderPainted(false);
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdokhongsd))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttendvt)
                            .addComponent(txtiddvt)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnlammoi)
                                .addGap(18, 18, 18)
                                .addComponent(btncapnhat)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtiddvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txttendvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoconsd)
                    .addComponent(rdokhongsd))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btncapnhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnlammoi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        insert();

    }//GEN-LAST:event_btnthemActionPerformed

    private void btncapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapnhatActionPerformed
        // TODO add your handling code here:
          int hoi = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Cập Nhật Không?", "Hói Cập Nhật", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (hoi == JOptionPane.YES_OPTION) {
            update();
        }
    }//GEN-LAST:event_btncapnhatActionPerformed

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void tbldonvitinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldonvitinhMouseClicked
        // TODO add your handling code here:
        index = tbldonvitinh.getSelectedRow();
        if( index >= 0){
             edit();
        }
    }//GEN-LAST:event_tbldonvitinhMouseClicked

    private void rdoconsd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoconsd1ActionPerformed
        // TODO add your handling code here:
          try {
            DefaultTableModel model;
            model = (DefaultTableModel) tbldonvitinh.getModel();
            List<DonViTinh> list = dao.findByTT();
            
          if(list == null){
                JOptionPane.showMessageDialog(this, "Không có Đơn Vị Tính nào");
                return;
            }
            model.setRowCount(0);
            for (DonViTinh x : list) {
                Vector v = new Vector();
                v.add(x.getID_DonViTinh());
                v.add(x.getTen_DVT());
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
            model = (DefaultTableModel) tbldonvitinh.getModel();
            List<DonViTinh> list = dao.findByTT2();
            if(list == null){
                JOptionPane.showMessageDialog(this, "Không có Đơn Vị Tính nào");
                return;
            }
            model.setRowCount(0);
            for (DonViTinh x : list) {
                Vector v = new Vector();
                v.add(x.getID_DonViTinh());
                v.add(x.getTen_DVT());
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
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
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
    private javax.swing.JTable tbldonvitinh;
    private javax.swing.JTextField txtiddvt;
    private javax.swing.JTextField txttendvt;
    // End of variables declaration//GEN-END:variables
}
