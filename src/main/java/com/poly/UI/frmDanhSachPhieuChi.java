/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.PhieuChiDAO;
import com.poly.helper.ShareHepler;
import com.poly.model.PhieuChi;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC HP
 */
public class frmDanhSachPhieuChi extends javax.swing.JPanel {

    PhieuChiDAO dao = new PhieuChiDAO();
    public frmDanhSachPhieuChi() {
        initComponents();
        loadtodata();
    }

    private void loadtodata() {
        DefaultTableModel model = (DefaultTableModel) tblphieuchi.getModel();
        model.setRowCount(0);

        List<PhieuChi> list = dao.select();
        if (list == null) {
            JOptionPane.showMessageDialog(this, "Không có Phiếu Chi nào");
            return;
        }
        try {
            for (PhieuChi x : list) {
                Vector v = new Vector();
                v.add(x.getMa_PC());
                v.add(x.getNgayLapPC());
                v.add(x.getNguoiThu());
                v.add(x.getTienNhapthuoc());
                v.add(x.getTienTraHang());
                v.add(x.getTongTienChi());
                model.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblphieuchi = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        dtnchi = new com.toedter.calendar.JDateChooser();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();

        tblphieuchi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Chi", "Ngày Lập", "Người Lập", "Tiền Nhập Thuốc", "Tiền Trả Hàng", "Tổng Tiền Chi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblphieuchi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblphieuchiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblphieuchi);

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Document.png"))); // NOI18N
        jButton1.setToolTipText("Tạo Mới");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        jButton2.setToolTipText("Nạp Lại");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator2);

        dtnchi.setDateFormatString("yyyy-MM-dd");
        jToolBar1.add(dtnchi);
        jToolBar1.add(jSeparator3);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblphieuchiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblphieuchiMouseClicked
        // TODO add your handling code here:
        ShareHepler.ma_pc = 0;
        int index = tblphieuchi.getSelectedRow();
        if (evt.getClickCount() == 2) {
            ShareHepler.ma_pc = Integer.valueOf(tblphieuchi.getValueAt(index, 0).toString());
            new frmchitietphieuchi().setVisible(true);
        }
    }//GEN-LAST:event_tblphieuchiMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new frmPhieuChi().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loadtodata();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (dtnchi.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Ngày Chi");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblphieuchi.getModel();
        model.setRowCount(0);

        List<PhieuChi> list = dao.find_ByDate(dtnchi.getDate());
        if (list == null) {
            JOptionPane.showMessageDialog(this, "Không có Phiếu Chi nào");
            return;
        }
        for (PhieuChi x : list) {
                Vector v = new Vector();
                v.add(x.getMa_PC());
                v.add(x.getNgayLapPC());
                v.add(x.getNguoiThu());
                v.add(x.getTienNhapthuoc());
                v.add(x.getTienTraHang());
                v.add(x.getTongTienChi());
                model.addRow(v);
            }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dtnchi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblphieuchi;
    // End of variables declaration//GEN-END:variables
}
