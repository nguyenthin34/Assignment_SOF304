/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.NguoiDungDAO;
import com.poly.helper.ShareHepler;
import com.poly.model.NguoiDung;
import javax.swing.JOptionPane;

/**
 *
 * @author PC HP
 */
public class frmDoiMatKhau extends javax.swing.JFrame {

    NguoiDungDAO dao = new NguoiDungDAO();
    public frmDoiMatKhau() {
        initComponents();
        load();
        setLocationRelativeTo(null);
    }

    private void load() {
        String manv = ShareHepler.mand;
        if (manv.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this,"Bạn phải Đăng Nhập");
            return;
        } else {
            try {
                NguoiDung model = dao.findbyID(manv);
                boolean vaitro = model.isVaiTro();
                txttaikhoan.setText(manv);
                if (vaitro) {
                    txtvaitro.setText("Nhân Viên");
                } else {
                    txtvaitro.setText("Quản Lý");
                }
            } catch (Exception e) {
            }

        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_TK_fThongTin = new javax.swing.JLabel();
        jLabel_Quyen_fThongTin = new javax.swing.JLabel();
        txttaikhoan = new javax.swing.JTextField();
        txtvaitro = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jRB_Them_fNguoiDung = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtmatkhaucu = new javax.swing.JPasswordField();
        txtmatkhaulan1 = new javax.swing.JPasswordField();
        btncapnhat = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtmatkhaulan2 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        jLabel_TK_fThongTin.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_TK_fThongTin.setText("Tài khoản");

        jLabel_Quyen_fThongTin.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_Quyen_fThongTin.setText("Quyền");

        txttaikhoan.setEditable(false);

        txtvaitro.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_TK_fThongTin)
                    .addComponent(jLabel_Quyen_fThongTin))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttaikhoan)
                    .addComponent(txtvaitro, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_TK_fThongTin)
                    .addComponent(txttaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Quyen_fThongTin)
                    .addComponent(txtvaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        jRB_Them_fNguoiDung.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jRB_Them_fNguoiDung.setText("Đổi mật khẩu");
        jRB_Them_fNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_Them_fNguoiDungActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel5.setText("Mật khẩu cũ:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setText("Mật khẩu mới:");

        txtmatkhaucu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtmatkhaucu.setEnabled(false);
        txtmatkhaucu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmatkhaucuKeyTyped(evt);
            }
        });

        txtmatkhaulan1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtmatkhaulan1.setEnabled(false);
        txtmatkhaulan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmatkhaulan1KeyTyped(evt);
            }
        });

        btncapnhat.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btncapnhat.setText("Cập nhật");
        btncapnhat.setBorder(null);
        btncapnhat.setContentAreaFilled(false);
        btncapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapnhatActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel7.setText("Nhập lại mật khẩu:");

        txtmatkhaulan2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtmatkhaulan2.setEnabled(false);
        txtmatkhaulan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmatkhaulan2KeyTyped(evt);
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
                        .addComponent(jRB_Them_fNguoiDung)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtmatkhaulan2, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(txtmatkhaucu, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmatkhaulan1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncapnhat)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jRB_Them_fNguoiDung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtmatkhaucu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtmatkhaulan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btncapnhat)
                    .addComponent(txtmatkhaulan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRB_Them_fNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_Them_fNguoiDungActionPerformed
        // TODO add your handling code here:
        if (jRB_Them_fNguoiDung.isSelected()) {
            txtmatkhaulan1.setEnabled(true);
            txtmatkhaucu.setEnabled(true);
            btncapnhat.setEnabled(true);
            txtmatkhaulan2.setEnabled(true);
        } else {
            txtmatkhaulan1.setEnabled(false);
            txtmatkhaucu.setEnabled(false);
            btncapnhat.setEnabled(false);
            txtmatkhaulan2.setEnabled(false);
        }
    }//GEN-LAST:event_jRB_Them_fNguoiDungActionPerformed

    private void txtmatkhaucuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmatkhaucuKeyTyped

    }//GEN-LAST:event_txtmatkhaucuKeyTyped

    private void txtmatkhaulan1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmatkhaulan1KeyTyped

    }//GEN-LAST:event_txtmatkhaulan1KeyTyped

    private void btncapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapnhatActionPerformed
        String mk1 = String.valueOf(txtmatkhaulan1.getPassword());
        String mk2 = String.valueOf(txtmatkhaulan2.getPassword());
        String mkc = String.valueOf(txtmatkhaucu.getPassword());
        if (mkc.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Mật Khẩu Cũ");
            return;
        }
        if (mk1.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Mật Khẩu Mới Lần 1");
            return;
        }

        if (mk2.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this,"Bạn Chưa Nhập Mật Khẩu Mới Lần 2");
            return;
        }

        try {
            String manv = txttaikhoan.getText();
            NguoiDung model = dao.findbyID(manv);
            String matKhau = model.getMatKhau();
            NguoiDung md = new NguoiDung();
            md.setMatKhau(mk2);
            md.setMa_ND(manv);

            if (matKhau.equals(mkc)) {
                if (mk1.equalsIgnoreCase(mk2)) {
                    dao.update_pass(md);
                    JOptionPane.showMessageDialog(this, "Đổi Mật Khẩu Ok");
                } else {
                    JOptionPane.showMessageDialog(this, "Mật Khẩu Mới Không Khớp");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sai Mật Khẩu");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btncapnhatActionPerformed

    private void txtmatkhaulan2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmatkhaulan2KeyTyped

    }//GEN-LAST:event_txtmatkhaulan2KeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmDoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDoiMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncapnhat;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_Quyen_fThongTin;
    private javax.swing.JLabel jLabel_TK_fThongTin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRB_Them_fNguoiDung;
    private javax.swing.JPasswordField txtmatkhaucu;
    private javax.swing.JPasswordField txtmatkhaulan1;
    private javax.swing.JPasswordField txtmatkhaulan2;
    private javax.swing.JTextField txttaikhoan;
    private javax.swing.JTextField txtvaitro;
    // End of variables declaration//GEN-END:variables
}
