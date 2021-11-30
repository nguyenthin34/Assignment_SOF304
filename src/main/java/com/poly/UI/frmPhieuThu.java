/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.HoaDonBanCTDAO;
import com.poly.dao.HoaDonBanHangDAO;
import com.poly.dao.NhaCungCapDAO;
import com.poly.dao.PhieuThuCTDAO;
import com.poly.dao.PhieuThuDAO;
import com.poly.helper.Datehelper;
import com.poly.helper.ShareHepler;
import com.poly.model.HoaDonBan;
import com.poly.model.PhieuThu;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.poly.model.PhieuThuCT;

/**
 *
 * @author PC HP
 */
public class frmPhieuThu extends javax.swing.JFrame {

    HoaDonBanHangDAO dao = new HoaDonBanHangDAO();
    NhaCungCapDAO dao_ncc = new NhaCungCapDAO();
    int index = -1;
    List<HoaDonBan> listhd = new ArrayList<>();
    float tong = 0;
    float tong2 = 0;
    PhieuThuDAO dao_pt = new PhieuThuDAO();
    PhieuThuCTDAO dao_ptct = new PhieuThuCTDAO();

    public frmPhieuThu() {
        initComponents();
        loadtoData();
        setLocationRelativeTo(null);
        btnxacnhan.setEnabled(false);
        btntaophieu.setEnabled(false);
        loadtoData();
    }

    private void loadtoData() {
        DefaultTableModel model = (DefaultTableModel) tblhoadonban.getModel();
        model.setRowCount(0);

        List<HoaDonBan> list = dao.select1();
        if (list == null) {
            JOptionPane.showMessageDialog(this, "Không có hóa đơn Bán Hàng Nào");
            return;
        }
        try {
            for (HoaDonBan x : list) {
                Vector v = new Vector();
                v.add(x.getID_HDB());
                v.add(x.getSDT_KH());
                v.add(x.getTongTien());
                v.add(x.getNgayLapHD());
                v.add(x.getNguoiTao());

                model.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblhoadonban = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtidphieu = new javax.swing.JTextField();
        txttienthu = new javax.swing.JTextField();
        txtnguoithu = new javax.swing.JTextField();
        dtngaythu = new com.toedter.calendar.JDateChooser();
        btnxacnhan = new javax.swing.JButton();
        btntaophieu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(30, 165, 252));

        tblhoadonban.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tblhoadonban.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Hóa Đơn Bán", "Số Điện Thoại Khách Hàng", "Tổng Tiền", "Ngày Lập Phiếu", "Người Tạo", "Lựa Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblhoadonban.setGridColor(new java.awt.Color(255, 255, 255));
        tblhoadonban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhoadonbanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblhoadonban);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtidphieu.setEditable(false);
        txtidphieu.setBackground(new java.awt.Color(255, 255, 255));
        txtidphieu.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtidphieu.setText("Mã Phiếu Thu Tự Tăng");

        txttienthu.setEditable(false);
        txttienthu.setBackground(new java.awt.Color(255, 255, 255));

        txtnguoithu.setEditable(false);
        txtnguoithu.setBackground(new java.awt.Color(255, 255, 255));

        dtngaythu.setDateFormatString("yyyy-MM-dd");

        btnxacnhan.setBackground(new java.awt.Color(30, 165, 252));
        btnxacnhan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnxacnhan.setForeground(new java.awt.Color(255, 255, 255));
        btnxacnhan.setText("Xác Nhận Chọn Dòng");
        btnxacnhan.setBorderPainted(false);
        btnxacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxacnhanActionPerformed(evt);
            }
        });

        btntaophieu.setBackground(new java.awt.Color(30, 165, 252));
        btntaophieu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btntaophieu.setForeground(new java.awt.Color(255, 255, 255));
        btntaophieu.setText("Tạo Phiếu");
        btntaophieu.setBorderPainted(false);
        btntaophieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaophieuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Mã Phiếu Thu");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Ngày Thu");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Người Thu");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Tiền Thu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttienthu)
                    .addComponent(txtidphieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtnguoithu)
                    .addComponent(dtngaythu, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnxacnhan, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(btntaophieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnxacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btntaophieu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtidphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttienthu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnguoithu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtngaythu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblhoadonbanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhoadonbanMouseClicked
        // TODO add your handling code here:
        int in = tblhoadonban.getRowCount();
        for (int i = 0; i < in; i++) {
            String matKhau = String.valueOf(tblhoadonban.getValueAt(i, 5));
            boolean mk = Boolean.valueOf(matKhau);
            if (mk == true) {
                btnxacnhan.setEnabled(true);
                return;
            } else {
                btnxacnhan.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tblhoadonbanMouseClicked

    private void btnxacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhanActionPerformed
        // TODO add your handling code here:
        try {
            int in = tblhoadonban.getRowCount();
            listhd.clear();
            tong = 0;
            for (int i = 0; i < in; i++) {
                String matKhau = String.valueOf(tblhoadonban.getValueAt(i, 5));
                boolean mk = Boolean.valueOf(matKhau);
                if (mk == true) {
                    if (tblhoadonban.getValueAt(i, 1) == null) {
                        int id = Integer.valueOf(tblhoadonban.getValueAt(i, 0).toString());
                        String sdt = null;
                        float tongtien = Float.valueOf(tblhoadonban.getValueAt(i, 2).toString());
                        Date NgayLap = Datehelper.toDate(tblhoadonban.getValueAt(i, 3).toString());
                        String NguoiTao = tblhoadonban.getValueAt(i, 4).toString();
                        listhd.add(new HoaDonBan(id, sdt, tongtien, NgayLap, NguoiTao));
                    } else {
                        int id = Integer.valueOf(tblhoadonban.getValueAt(i, 0).toString());
                        String sdt = tblhoadonban.getValueAt(i, 1).toString();
                        float tongtien = Float.valueOf(tblhoadonban.getValueAt(i, 2).toString());
                        Date NgayLap = Datehelper.toDate(tblhoadonban.getValueAt(i, 3).toString());
                        String NguoiTao = tblhoadonban.getValueAt(i, 4).toString();
                        listhd.add(new HoaDonBan(id, sdt, tongtien, NgayLap, NguoiTao));
                    }
                }
            }

            JOptionPane.showMessageDialog(this, listhd.size());

            for (HoaDonBan x : listhd) {
                tong += x.getTongTien();
            }
            txttienthu.setText(String.valueOf(tong));
            btntaophieu.setEnabled(true);
            txtnguoithu.setText(ShareHepler.mand);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Xác Nhận " + e);
        }
    }//GEN-LAST:event_btnxacnhanActionPerformed

    private void btntaophieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaophieuActionPerformed
        // TODO add your handling code here:
        try {
            if (dtngaythu.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập Ngày Thu");
                return;
            }
            PhieuThu model = getModel_pt();
            dao_pt.insert(model);
            for (HoaDonBan x : listhd) {
                PhieuThuCT md = getModel_ptct(x.getID_HDB(), x.getTongTien());
                dao_ptct.insert(md);
            }
            loadtoData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tạo Phiếu Thu Thất Bại");
        }

    }//GEN-LAST:event_btntaophieuActionPerformed

    private PhieuThu getModel_pt() {
        PhieuThu model = new PhieuThu();
        model.setNgayThu(dtngaythu.getDate());
        model.setNguoiThu(txtnguoithu.getText());
        model.setTienThu(Float.valueOf(txttienthu.getText()));
        return model;
    }

    private PhieuThuCT getModel_ptct(int idhd, float tien) {
        PhieuThuCT model = new PhieuThuCT();
        PhieuThu md = dao_pt.select1();
        model.setMa_PT(md.getMa_PT());
        model.setID_HDB(idhd);
        model.setHDB_Tien(tien);
        return model;
    }

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
            java.util.logging.Logger.getLogger(frmPhieuThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPhieuThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPhieuThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPhieuThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPhieuThu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btntaophieu;
    private javax.swing.JButton btnxacnhan;
    private com.toedter.calendar.JDateChooser dtngaythu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblhoadonban;
    private javax.swing.JTextField txtidphieu;
    private javax.swing.JTextField txtnguoithu;
    private javax.swing.JTextField txttienthu;
    // End of variables declaration//GEN-END:variables
}
