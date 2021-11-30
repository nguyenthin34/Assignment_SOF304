/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.NhaCungCapDAO;
import com.poly.model.NhaCungCap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import com.poly.Jdbc.SQLConnect;
import com.poly.helper.ShareHepler;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class TTNhaCungCap extends javax.swing.JPanel {

    NhaCungCapDAO dao_NCC = new NhaCungCapDAO();
    int index;

    public TTNhaCungCap() throws Exception {
        initComponents();
        checklogin();
        loaddata();

    }

    public void checklogin() {
        if (ShareHepler.User == 1) {
            btntimkiem.setEnabled(true);
            btnUpdateNCC.setEnabled(true);
            btnLamMoi.setEnabled(true);
            btnThemNCC.setEnabled(true);
        } else if (ShareHepler.User == 0) {
            btntimkiem.setEnabled(true);
            btnUpdateNCC.setEnabled(false);
            btnLamMoi.setEnabled(true);
            btnThemNCC.setEnabled(false);
        } else {
            btntimkiem.setEnabled(false);
            btnUpdateNCC.setEnabled(false);
            btnLamMoi.setEnabled(false);
            btnThemNCC.setEnabled(false);
        }
    }

    public void loaddata() {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblNhacungCap.getModel();
            List<NhaCungCap> list = dao_NCC.select();
            model.setRowCount(0);
            for (NhaCungCap x : list) {
                Vector v = new Vector();
                v.add(x.getID_NhaCC());
                v.add(x.getTen_NCC());
                v.add(x.getEmail_NCC());
                v.add(x.getSDT_NCC());
                v.add(x.getDiaChi());
                v.add(x.getNgayTao());
                v.add(x.isTrangThai()? "Còn Hợp Tác" : "Ngưng Hợp Tác");
                model.addRow(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void setModel(NhaCungCap model) {
        txtTenNCC.setText(model.getTen_NCC());
        txtDiachiNCC.setText(model.getDiaChi());
        txtSdtNCC.setText(model.getSDT_NCC());
        txtEmailNCC.setText(model.getEmail_NCC());
        txtID.setText(model.getID_NhaCC());
        dtNgayTao.setDate(model.getNgayTao());
        if (model.isTrangThai()) {
            rdoHoptac.setSelected(true);
        } else {
            rdoNgungHT.setSelected(true);
        }

    }

    private void reset() {
        setModel(new NhaCungCap());

    }

    private void edit() {
        try {
            NhaCungCap model = dao_NCC.findByID(tblNhacungCap.getValueAt(index, 0).toString());
            if (model != null) {
                setModel(model);
            }
        } catch (Exception e) {
        }
    }

    private void TimKiem() {
        try {
            NhaCungCap model = dao_NCC.findByID(txtTim.getText());
            setModel(model);
        } catch (Exception e) {
        }
    }

    private void insert() {
        if (txtID.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập ID nhà cung cấp ! ");
            return;
        }
        if (txtTenNCC.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên nhà cung cấp !");
            return;
        }
        if (txtDiachiNCC.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập địa chỉ nhà cung cấp !");
            return;
        }
        if (txtEmailNCC.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Email nhà cung cấp !");
            return;
        }
        if (checkemail()) {
            JOptionPane.showMessageDialog(this, "Sai Định Dạng Email");
            return;
        }
        if (txtSdtNCC.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại nhà cung cấp !");
            return;
        }
        NhaCungCap model1 = dao_NCC.findByID(txtID.getText());
        if (model1 != null) {
            JOptionPane.showMessageDialog(this, "Nhà cung cấp đã tồn tại !");
            return;
        }
        try {
            NhaCungCap model = getModel();
            dao_NCC.insert(model);
            loaddata();
            
        } catch (Exception e) {
        }
    }

    private void update() {
        if (txtID.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập ID nhà cung cấp ! ");
            return;
        }
        if (txtTenNCC.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên nhà cung cấp !");
            return;
        }
        if (txtDiachiNCC.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập địa chỉ nhà cung cấp !");
            return;
        }
        if (txtEmailNCC.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Email nhà cung cấp !");
            return;
        }
        if (checkemail()) {
            JOptionPane.showMessageDialog(this, "Sai Định Dạng Email");
            return;
        }
        if (txtSdtNCC.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại nhà cung cấp !");
            return;
        }
        try {
            NhaCungCap model = getModel();
            dao_NCC.update(model);
            loaddata();
        } catch (Exception e) {
        }
    }

    private NhaCungCap getModel() {
        NhaCungCap model = new NhaCungCap();
        model.setSDT_NCC(txtSdtNCC.getText());
        model.setTen_NCC(txtTenNCC.getText());
        model.setEmail_NCC(txtEmailNCC.getText());
        model.setDiaChi(txtDiachiNCC.getText());
        model.setID_NhaCC(txtID.getText());
        model.setNgayTao(dtNgayTao.getDate());
        if (rdoHoptac.isSelected()) {
            model.setTrangThai(true);
        } else {
            model.setTrangThai(false);
        }
        return model;
    }
    private boolean checkemail() {
        String email = "^[\\w-_\\.+]*[\\w-_\\.]+(\\@+[\\w]+\\.)+([\\w+\\.])+([\\w])$";
        if (txtEmailNCC.getText().matches(email)) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhacungCap = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTenNCC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSdtNCC = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmailNCC = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDiachiNCC = new javax.swing.JTextField();
        btntimkiem = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnThemNCC = new javax.swing.JButton();
        btnUpdateNCC = new javax.swing.JButton();
        rdoHoptac = new javax.swing.JRadioButton();
        rdoNgungHT = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        btnLamMoi = new javax.swing.JButton();
        dtNgayTao = new com.toedter.calendar.JDateChooser();

        setPreferredSize(new java.awt.Dimension(1195, 602));
        setLayout(null);

        jPanel11.setFocusable(false);

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        tblNhacungCap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblNhacungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Nhà cung cấp", "Tên nhà cùng cấp", "Email", "Số điện thoại", "Địa chỉ", "Ngày tạo", "Trạng thái "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhacungCap.setFocusable(false);
        tblNhacungCap.setGridColor(new java.awt.Color(255, 255, 255));
        tblNhacungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhacungCapMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhacungCap);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );

        add(jPanel11);
        jPanel11.setBounds(0, 0, 960, 580);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tên nhà cung cấp :");

        txtTenNCC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTenNCC.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Số điện thoại :");

        txtSdtNCC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSdtNCC.setBorder(null);
        txtSdtNCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSdtNCCKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Email :");

        txtEmailNCC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtEmailNCC.setBorder(null);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Địa chỉ :");

        txtDiachiNCC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDiachiNCC.setBorder(null);

        btntimkiem.setBackground(new java.awt.Color(255, 255, 255));
        btntimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btntimkiem.setBorderPainted(false);
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        txtTim.setText("Tìm kiếm");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Trạng thái :");

        btnThemNCC.setBackground(new java.awt.Color(30, 165, 252));
        btnThemNCC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemNCC.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNCC.setText("Thêm nhà cung cấp");
        btnThemNCC.setBorderPainted(false);
        btnThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNCCActionPerformed(evt);
            }
        });

        btnUpdateNCC.setBackground(new java.awt.Color(30, 165, 252));
        btnUpdateNCC.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnUpdateNCC.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateNCC.setText("Cập nhật");
        btnUpdateNCC.setBorderPainted(false);
        btnUpdateNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateNCCActionPerformed(evt);
            }
        });

        rdoHoptac.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoHoptac);
        rdoHoptac.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdoHoptac.setSelected(true);
        rdoHoptac.setText("Hợp tác");

        rdoNgungHT.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNgungHT);
        rdoNgungHT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdoNgungHT.setText("Ngưng hợp tác");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Ngày lập :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("ID_Nhà cung cấp :");

        txtID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtID.setBorder(null);

        btnLamMoi.setBackground(new java.awt.Color(30, 165, 252));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        dtNgayTao.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemNCC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(txtTenNCC, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSdtNCC, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmailNCC, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiachiNCC, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdateNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(rdoHoptac, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoNgungHT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                .addComponent(btntimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btntimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSdtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmailNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiachiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNgungHT)
                    .addComponent(rdoHoptac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnThemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        add(jPanel12);
        jPanel12.setBounds(960, 0, 240, 580);
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhacungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhacungCapMouseClicked
        index = tblNhacungCap.getSelectedRow();
        edit();
    }//GEN-LAST:event_tblNhacungCapMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        reset();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNCCActionPerformed
        
        insert();

    }//GEN-LAST:event_btnThemNCCActionPerformed

    private void btnUpdateNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNCCActionPerformed
          int hoi = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Cập Nhật Không?", "Hói Cập Nhật", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (hoi == JOptionPane.YES_OPTION) {
            update();
        }
    }//GEN-LAST:event_btnUpdateNCCActionPerformed

    private void txtSdtNCCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSdtNCCKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSdtNCCKeyTyped

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        // TODO add your handling code here:
        if(txtTim.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Bạn Chưa Nhập ID Nhà Cung Cấp Cần Tìm");
            return;
        }
        
        try {
            NhaCungCap model = dao_NCC.findByID(txtTim.getText());
            if(model == null){
                JOptionPane.showMessageDialog(this,"Nhà Cung Cấp Không Tồn Tại");
                return;
            }
            setModel(model);
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btntimkiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThemNCC;
    private javax.swing.JButton btnUpdateNCC;
    private javax.swing.JButton btntimkiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dtNgayTao;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JRadioButton rdoHoptac;
    private javax.swing.JRadioButton rdoNgungHT;
    private javax.swing.JTable tblNhacungCap;
    private javax.swing.JTextField txtDiachiNCC;
    private javax.swing.JTextField txtEmailNCC;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtSdtNCC;
    private javax.swing.JTextField txtTenNCC;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
