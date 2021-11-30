package com.poly.UI;

import com.poly.Jdbc.DatabaseHelper;
import com.poly.dao.NguoiDungDAO;
import com.poly.helper.ShareHepler;
import com.poly.model.NguoiDung;
import java.io.ObjectInputStream;
import javax.swing.JOptionPane;
import java.util.List;
import java.io.FileInputStream;
import com.poly.Jdbc.hostt;
import java.util.ArrayList;
import java.sql.*;
import com.poly.Jdbc.SQLConnect;


public class DangNhap extends javax.swing.JFrame {

    NguoiDungDAO dao = new NguoiDungDAO();
    static List<hostt> list = new ArrayList<>();
    Connection con;

    public DangNhap(java.awt.Frame parent, boolean modal) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);

        read();
        btnDangnhap.setEnabled(false);
        try {
            con = SQLConnect.getcon();
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Káº¿t Ná»‘i CÆ¡ Sá»Ÿ Dá»¯ Liá»‡u Tháº¥t Báº¡i");
                return;
            } else {
                btnDangnhap.setEnabled(true);
            }
        } catch (Exception e) {
        }
    }

    private void read() {
        ObjectInputStream obj = null; //khá»Ÿi táº¡o luá»“ng vĂ o ra
        try {
            // má»Ÿ luá»“ng
            obj = new ObjectInputStream(new FileInputStream("host.txt"));
            // thá»±c hiá»‡n Ä‘á»�c
            list = (List<hostt>) obj.readObject();
            // xá»­ lĂ½ sau khi Ä‘á»�c
            for (hostt x : list) {
                DatabaseHelper.host = x.getHost();
                DatabaseHelper.user = x.getUser();
                DatabaseHelper.pass = x.getPass();
                DatabaseHelper.database = x.getDb();
                DatabaseHelper.port = x.getPort();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "KhĂ´ng cĂ³ thĂ´ng tin cÆ¡ sá»Ÿ dá»¯ liá»‡u Ä‘Æ°á»£c lÆ°u");
        } finally {
            try {
                // Ä‘Ă³ng luá»“ng
                obj.close();
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Lá»—i Ä�Ă³ng Host");
            }
        }
    }

    private void DangNhap() {
        String mand = txttaikhoan.getText();
        String matKhau = String.valueOf(txtmatkhau.getPassword());
        try {
            NguoiDung model = dao.findbyID(mand);
            if (model != null) {
                String mkcheck = model.getMatKhau();
                if (matKhau.equals(mkcheck)) {
                    if (model.isTrangThai() == true) {
                        ShareHepler.User = model.isVaiTro() ? 1 : 0;
                        ShareHepler.mand = txttaikhoan.getText();
                        JOptionPane.showMessageDialog(this, "Ä�Äƒng Nháº­p ThĂ nh CĂ´ng");
                        new Display().setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "TĂ i Khoáº£n Cá»§a Báº¡n Ä�Ă£ Bá»‹ KhĂ³a");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Sai Máº­t Kháº©u");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sai TĂªn Ä�Äƒng Nháº­p");
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbltittle = new javax.swing.JLabel();
        lbltk = new javax.swing.JLabel();
        lblmk = new javax.swing.JLabel();
        txttaikhoan = new javax.swing.JTextField();
        btnDangnhap = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        txtmatkhau = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ä�Ä‚NG NHáº¬P");

        lbltittle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltittle.setForeground(new java.awt.Color(102, 204, 255));
        lbltittle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/outline_verified_user_black_24dp.png"))); // NOI18N
        lbltittle.setText("Ä�Ä‚NG NHáº¬P");

        lbltk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbltk.setText("TĂ i khoáº£n");

        lblmk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblmk.setText("Máº­t kháº©u");

        txttaikhoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttaikhoan.setText("PH12915");

        btnDangnhap.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnDangnhap.setForeground(new java.awt.Color(51, 204, 255));
        btnDangnhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/outline_lock_open_black_24dp.png"))); // NOI18N
        btnDangnhap.setText("Ä�Äƒng nháº­p");
        btnDangnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangnhapActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(153, 0, 0));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/outline_cancel_black_24dp.png"))); // NOI18N
        btnThoat.setText("ThoĂ¡t");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        txtmatkhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmatkhau.setText("123456");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Thiáº¿t Láº­p CÆ¡ Sá»Ÿ Dá»¯ Liá»‡u");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/outline_policy_black_24dp.png"))); // NOI18N
        jButton1.setText("QuĂªn Máº­t Kháº©u?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDangnhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbltk)
                    .addComponent(lblmk)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txttaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                        .addComponent(txtmatkhau)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(lbltittle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblmk, lbltk});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltittle)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbltk))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmk)
                    .addComponent(txtmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangnhap)
                    .addComponent(btnThoat))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangnhapActionPerformed
        DangNhap();
    }//GEN-LAST:event_btnDangnhapActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        int question = JOptionPane.showConfirmDialog(this, "Báº¡n cĂ³ cháº¯c cháº¯n thoĂ¡t khĂ´ng?", "ThoĂ¡t", JOptionPane.YES_NO_OPTION);
        if (question != JOptionPane.YES_OPTION) {
            return;
        }
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        new frmServer().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new frmQuenMatKhau().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DangNhap dialog = new DangNhap(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangnhap;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblmk;
    private javax.swing.JLabel lbltittle;
    private javax.swing.JLabel lbltk;
    private javax.swing.JPasswordField txtmatkhau;
    private javax.swing.JTextField txttaikhoan;
    // End of variables declaration//GEN-END:variables
}
