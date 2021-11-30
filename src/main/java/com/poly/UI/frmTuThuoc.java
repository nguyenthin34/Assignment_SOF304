/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.TuThuocDAO;
import com.poly.helper.ShareHepler;
import com.poly.model.TuThuoc;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmTuThuoc extends javax.swing.JPanel {

    TuThuocDAO dao = new TuThuocDAO();
    int index;

    public frmTuThuoc() {
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
            model = (DefaultTableModel) tbltuthuoc.getModel();
            List<TuThuoc> list = dao.select();
            model.setRowCount(0);
            for (TuThuoc x : list) {
                Vector v = new Vector();
                v.add(x.getID_Tu());
                v.add(x.getTen_Tu());
                v.add(x.isTrangThai() ? "Còn Sử Dụng" : "Không Sử Dụng");
                model.addRow(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void setModel(TuThuoc model) {
        txtidtu.setText(model.getID_Tu());
        txttentu.setText(model.getTen_Tu());
        rdocsudung.setSelected(model.isTrangThai());
        rdoksudung.setSelected(!model.isTrangThai());
    }

    private void edit() {
        try {
            TuThuoc model = dao.findByID(tbltuthuoc.getValueAt(index, 0).toString());
            if (model != null) {
                setModel(model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private TuThuoc getModel() {
        TuThuoc model = new TuThuoc();
        model.setID_Tu(txtidtu.getText());
        model.setTen_Tu(txttentu.getText());
        if (rdocsudung.isSelected()) {
            model.setTrangThai(true);
        } else {
            model.setTrangThai(false);
        }
        return model;
    }
    
    public void insert(){
        if(txtidtu.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Bạn Phải Nhập ID Tủ");
            txtidtu.requestFocus();
        }
        if(txttentu.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Tên Tủ");
            txttentu.requestFocus();
        }
        TuThuoc th = dao.findByID(txtidtu.getText());
        if(th != null){
            JOptionPane.showMessageDialog(this,"Mã Tủ Thuốc Đã Tồn Tại");
            return;
        }
        try {
            TuThuoc model = getModel();
            dao.insert(model);
            loadtodata();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,e);
        }
    }
    
    public void update(){
         if(txtidtu.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Bạn Chưa Chọn ID Tủ Cần Cập Nhật");
            txtidtu.requestFocus();
        }
         if(txttentu.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Tên Tủ");
            txttentu.requestFocus();
        }
         
         try {
            TuThuoc model = getModel();
            dao.update(model);
            loadtodata();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this,e);
        }
    }
    private void clear(){
        setModel(new TuThuoc());
        rdocsudung.setSelected(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltuthuoc = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtidtu = new javax.swing.JTextField();
        txttentu = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rdoksudung = new javax.swing.JRadioButton();
        rdocsudung = new javax.swing.JRadioButton();
        btnthem = new javax.swing.JButton();
        btncapnhat = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        rdoksudung1 = new javax.swing.JRadioButton();
        rdocsudung1 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(30, 165, 252));

        tbltuthuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Tủ", "Tên Tủ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbltuthuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltuthuocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbltuthuoc);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cập Nhật"));

        txtidtu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtidtu.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txttentu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttentu.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txttentu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttentuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("ID Tủ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Tên Tủ");

        buttonGroup1.add(rdoksudung);
        rdoksudung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoksudung.setText("Không Sử Dụng");

        buttonGroup1.add(rdocsudung);
        rdocsudung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdocsudung.setText("Còn Sử Dụng");

        btnthem.setBackground(new java.awt.Color(30, 165, 252));
        btnthem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnthem.setForeground(new java.awt.Color(255, 255, 255));
        btnthem.setText("Thêm Tủ");
        btnthem.setBorderPainted(false);
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btncapnhat.setBackground(new java.awt.Color(30, 165, 252));
        btncapnhat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncapnhat.setForeground(new java.awt.Color(255, 255, 255));
        btncapnhat.setText("Cập Nhật Tủ");
        btncapnhat.setBorderPainted(false);
        btncapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapnhatActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Trạng Thái");

        jButton3.setBackground(new java.awt.Color(30, 165, 252));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Làm Mới");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(rdocsudung)
                                .addGap(18, 18, 18)
                                .addComponent(rdoksudung))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txttentu)
                                    .addComponent(txtidtu))))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidtu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttentu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdocsudung)
                    .addComponent(rdoksudung))
                .addGap(23, 23, 23)
                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));

        buttonGroup2.add(rdoksudung1);
        rdoksudung1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoksudung1.setText("Không Sử Dụng");
        rdoksudung1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoksudung1MouseClicked(evt);
            }
        });
        rdoksudung1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoksudung1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdocsudung1);
        rdocsudung1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdocsudung1.setText("Còn Sử Dụng");
        rdocsudung1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdocsudung1MouseClicked(evt);
            }
        });
        rdocsudung1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdocsudung1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdocsudung1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdoksudung1)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoksudung1)
                    .addComponent(rdocsudung1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tủ Thuốc Của Bạn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(311, 311, 311))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel4)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txttentuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttentuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttentuActionPerformed

    private void tbltuthuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltuthuocMouseClicked
        // TODO add your handling code here:
        index = tbltuthuoc.getSelectedRow();
        if (index >= 0) {
            edit();
        }
    }//GEN-LAST:event_tbltuthuocMouseClicked

    private void rdocsudung1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdocsudung1MouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_rdocsudung1MouseClicked

    private void rdoksudung1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoksudung1MouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_rdoksudung1MouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnthemActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btncapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapnhatActionPerformed
        // TODO add your handling code here:
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Cập Nhật Không?", "Hói Cập Nhật", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (hoi == JOptionPane.YES_OPTION) {
            update();
        }
    }//GEN-LAST:event_btncapnhatActionPerformed

    private void rdocsudung1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdocsudung1ActionPerformed
        // TODO add your handling code here:
         try {
            DefaultTableModel model;
            model = (DefaultTableModel) tbltuthuoc.getModel();
            List<TuThuoc> list = dao.select_ksd();
            if(list == null){
                JOptionPane.showMessageDialog(this, "Không có Tủ Thuốc nào");
                return;
            }
            model.setRowCount(0);
            for (TuThuoc x : list) {
                Vector v = new Vector();
                v.add(x.getID_Tu());
                v.add(x.getTen_Tu());
                v.add(x.isTrangThai() ? "Còn Sử Dụng" : "Không Sử Dụng");
                model.addRow(v);

            }
//            rdoksudung1.setSelected(true);
//            rdocsudung1.setSelected(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
//            rdocsudung1.setSelected(false);
//            rdoksudung1.setSelected(false);
        }
    }//GEN-LAST:event_rdocsudung1ActionPerformed

    private void rdoksudung1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoksudung1ActionPerformed
        // TODO add your handling code here:
         try {
            DefaultTableModel model;
            model = (DefaultTableModel) tbltuthuoc.getModel();
            List<TuThuoc> list = dao.select_tt();
            if(list == null){
                JOptionPane.showMessageDialog(this, "Không có Tủ Thuốc nào");
                return;
            }
            model.setRowCount(0);
            for (TuThuoc x : list) {
                Vector v = new Vector();
                v.add(x.getID_Tu());
                v.add(x.getTen_Tu());
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
    }//GEN-LAST:event_rdoksudung1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncapnhat;
    private javax.swing.JButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdocsudung;
    private javax.swing.JRadioButton rdocsudung1;
    private javax.swing.JRadioButton rdoksudung;
    private javax.swing.JRadioButton rdoksudung1;
    private javax.swing.JTable tbltuthuoc;
    private javax.swing.JTextField txtidtu;
    private javax.swing.JTextField txttentu;
    // End of variables declaration//GEN-END:variables
}
