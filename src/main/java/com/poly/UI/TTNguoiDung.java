/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.NguoiDungDAO;
import com.poly.model.NguoiDung;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import com.poly.Jdbc.SQLConnect;
import com.poly.helper.ShareHepler;
import com.poly.model.NhaCungCap;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class TTNguoiDung extends javax.swing.JPanel {

    NguoiDungDAO NguoiDungDAO = new NguoiDungDAO();
    int index;

    public TTNguoiDung() throws Exception {
        initComponents();
        checklogin();
    }

    public void checklogin() {
        if (ShareHepler.User == 1) {
            btnAdd.setEnabled(true);
            btnReset.setEnabled(true);
            btnUpdate.setEnabled(true);
            btnsearch.setEnabled(true);
            loaddata();
        } else if (ShareHepler.User == 0) {
            btnAdd.setEnabled(false);
            btnReset.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnsearch.setEnabled(false);
        } else {
            btnAdd.setEnabled(false);
            btnReset.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnsearch.setEnabled(false);
        }
    }

    private void edit() {
        try {
            String manv = tblNguoiung.getValueAt(index, 0).toString();
            NguoiDung model = NguoiDungDAO.findbyID(manv);
            if (model != null) {
                setModel(model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void loaddata() {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblNguoiung.getModel();
            List<NguoiDung> list = NguoiDungDAO.select();
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có người Dùng Nào");
                return;
            }
            model.setRowCount(0);
            for (NguoiDung x : list) {
                Vector v = new Vector();
                v.add(x.getMa_ND());
                v.add(x.getTen_ND());
                v.add(x.getSdt());
                v.add(x.getEmail());
                v.add(x.isVaiTro() ? "Quản Lý" : "Nhân Viên");
                v.add(x.getMatKhau());
                v.add(x.isTrangThai() ? "Làm Việc" : "Nghỉ Làm");
                model.addRow(v);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void setModel(NguoiDung model) {
        txtMaND.setText(model.getMa_ND());
        txtTenND.setText(model.getTen_ND());
        txtSdtND.setText(model.getSdt());
        txtEmailND.setText(model.getEmail());
        txtMatkhauND.setText(model.getMatKhau());
        if (model.isVaiTro()) {
            rdoQL.setSelected(true);
        } else {
            rdoNv.setSelected(true);
        }
        if (model.isTrangThai()) {
            rdoLam.setSelected(true);
        } else {
            rdoNghi.setSelected(true);
        }

    }

    private NguoiDung getModel() {
        NguoiDung model = new NguoiDung();
        model.setMa_ND(txtMaND.getText());
        model.setTen_ND(txtTenND.getText());
        model.setSdt(txtSdtND.getText());
        model.setEmail(txtEmailND.getText());
        if (rdoNv.isSelected()) {
            model.setVaiTro(true);
        } else {
            model.setVaiTro(false);
        }
        model.setMatKhau(String.valueOf(txtMatkhauND.getText()));
        if (rdoLam.isSelected()) {
            model.setTrangThai(true);
        } else {
            model.setTrangThai(false);
        }

        return model;
    }

    private void insert() {
        if (checkrongmanv()) {
            return;
        }

        if (checkronghoten()) {
            return;
        }

        if (checkrongsdt()) {
            return;
        }

        if (checkrongemail()) {
            return;
        }
        if (checkrongmakhau()) {
            return;
        }

        if (checktrungmanv(txtMaND.getText())) {
            return;
        }

        if (checkemail()) {
            JOptionPane.showMessageDialog(this, "Sai Định Dạng Email \nHãy để mail @fpt.edu.vn");
            return;
        }
        try {
            NguoiDung model = getModel();
            NguoiDungDAO.insert(model);
            loaddata();
            setModel(new NguoiDung());
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
        }
    }

    private void update() {
        if (checkrongmanv()) {
            return;
        }

        if (checkronghoten()) {
            return;
        }

        if (checkrongsdt()) {
            return;
        }

        if (checkrongemail()) {
            return;
        }
        if (checkrongmakhau()) {
            return;
        }
        if (checkemail()) {
            JOptionPane.showMessageDialog(this, "Sai Định Dạng Email \nHãy để mail @fpt.edu.vn");
            return;
        }
        try {
            NguoiDung model = getModel();
            NguoiDungDAO.update(model);
            loaddata();
            JOptionPane.showMessageDialog(this, "Cập Nhật Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập Nhật Thất Bại");
        }
    }

    private boolean checkrongmanv() {
        if (txtMaND.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Mã Nhân Viên?");
            txtMaND.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkronghoten() {
        if (txtTenND.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Họ và Tên?");
            txtTenND.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkrongsdt() {
        if (txtSdtND.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Số Điện Thoại?");
            txtSdtND.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkrongemail() {
        if (txtEmailND.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Email?");
            txtEmailND.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkrongmakhau() {
        if (txtMatkhauND.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Mật Khẩu?");
            txtMatkhauND.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean checktrungmanv(String manv) {
        boolean check = false;
        List<NguoiDung> list = NguoiDungDAO.select();
        for (NguoiDung x : list) {
            if (x.getMa_ND().equalsIgnoreCase(manv)) {
                JOptionPane.showMessageDialog(this, "Mã Người Dùng Đã Tồn Tại");
                check = true;
                break;
            }
        }
        return check;
    }

    private boolean checkemail() {
        String email = "^[\\w-_\\.+]*[\\w-_\\.]+(\\@+[\\w]+\\.)+([\\w+\\.])+([\\w])$";
        if (txtEmailND.getText().matches(email)) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNguoiung = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTenND = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSdtND = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmailND = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMatkhauND = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        rdoQL = new javax.swing.JRadioButton();
        rdoNv = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        txtMaND = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        btnReset = new javax.swing.JButton();
        rdoLam = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        rdoNghi = new javax.swing.JRadioButton();

        setLayout(null);

        jPanel11.setFocusable(false);

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        tblNguoiung.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblNguoiung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã người dùng", "Tên người dùng", "Số điện thoại", "Email", "Vai trò ", "Mật khẩu", "Trạng thái "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguoiung.setFocusable(false);
        tblNguoiung.setGridColor(new java.awt.Color(255, 255, 255));
        tblNguoiung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguoiungMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNguoiung);

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
        jLabel6.setText("Tên người dùng");

        txtTenND.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTenND.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Số điện thoại :");

        txtSdtND.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSdtND.setBorder(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Email :");

        txtEmailND.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtEmailND.setBorder(null);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Mật khẩu :");

        txtMatkhauND.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMatkhauND.setBorder(null);

        btnsearch.setBackground(new java.awt.Color(255, 255, 255));
        btnsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnsearch.setBorderPainted(false);
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        txtTim.setText("Tìm kiếm");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Vai trò");

        btnAdd.setBackground(new java.awt.Color(30, 165, 252));
        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("THÊM NGƯỜI DÙNG");
        btnAdd.setBorderPainted(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(30, 165, 252));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("CẬP NHẬT");
        btnUpdate.setBorderPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        rdoQL.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoQL);
        rdoQL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdoQL.setSelected(true);
        rdoQL.setText("Quản lý");

        rdoNv.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNv);
        rdoNv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdoNv.setText("Nhân viên");
        rdoNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNvActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Mã người dùng:");

        txtMaND.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMaND.setBorder(null);

        btnReset.setBackground(new java.awt.Color(30, 165, 252));
        btnReset.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("LÀM MỚI");
        btnReset.setBorderPainted(false);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        rdoLam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoLam);
        rdoLam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdoLam.setSelected(true);
        rdoLam.setText("Đang làm");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Trạng thái :");

        rdoNghi.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoNghi);
        rdoNghi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdoNghi.setText("Nghỉ việc");
        rdoNghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNghiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaND)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTenND)
                    .addComponent(txtSdtND)
                    .addComponent(txtEmailND)
                    .addComponent(txtMatkhauND)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(rdoQL, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoNv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(rdoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoNghi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate))
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaND, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenND, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSdtND, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmailND, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatkhauND, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNv)
                    .addComponent(rdoQL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNghi)
                    .addComponent(rdoLam))
                .addGap(19, 19, 19)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        add(jPanel12);
        jPanel12.setBounds(960, 0, 242, 580);
    }// </editor-fold>//GEN-END:initComponents

    private void tblNguoiungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiungMouseClicked
        index = tblNguoiung.getSelectedRow();
        edit();
    }//GEN-LAST:event_tblNguoiungMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // index = tblNhacungCap.getSelectedRow();
        insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void rdoNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNvActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Cập Nhật Không?", "Hói Cập Nhật", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (hoi == JOptionPane.YES_OPTION) {
            update();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        setModel(new NguoiDung());
    }//GEN-LAST:event_btnResetActionPerformed

    private void rdoNghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNghiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNghiActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        if (txtTim.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Mã Người Dùng Muốn Tìm");
            return;
        }

        try {
            NguoiDung model = NguoiDungDAO.findbyID(txtTim.getText());
            if (model == null) {
                JOptionPane.showMessageDialog(this, "Người Dùng Không Tồn Tại");
                return;
            }
            setModel(model);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnsearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnsearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
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
    private javax.swing.JRadioButton rdoLam;
    private javax.swing.JRadioButton rdoNghi;
    private javax.swing.JRadioButton rdoNv;
    private javax.swing.JRadioButton rdoQL;
    private javax.swing.JTable tblNguoiung;
    private javax.swing.JTextField txtEmailND;
    private javax.swing.JTextField txtMaND;
    private javax.swing.JTextField txtMatkhauND;
    private javax.swing.JTextField txtSdtND;
    private javax.swing.JTextField txtTenND;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
