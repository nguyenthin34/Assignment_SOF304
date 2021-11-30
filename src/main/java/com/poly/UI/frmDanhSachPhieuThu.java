/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.PhieuThuDAO;
import com.poly.helper.ShareHepler;
import com.poly.model.HoaDonBan;
import com.poly.model.PhieuThu;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC HP
 */
public class frmDanhSachPhieuThu extends javax.swing.JPanel {

    PhieuThuDAO dao = new PhieuThuDAO();

    public frmDanhSachPhieuThu() {
        initComponents();
        loadtodata();

    }

    private void loadtodata() {
        DefaultTableModel model = (DefaultTableModel) tblphieuthu.getModel();
        model.setRowCount(0);

        List<PhieuThu> list = dao.select();
        if (list == null) {
            JOptionPane.showMessageDialog(this, "Không có Phiếu Thu nào");
            return;
        }
        try {
            for (PhieuThu x : list) {
                Vector v = new Vector();
                v.add(x.getMa_PT());
                v.add(x.getNgayThu());
                v.add(x.getNguoiThu());
                v.add(x.getTienThu());
                model.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblphieuthu = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        dtnthu = new com.toedter.calendar.JDateChooser();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();

        tblphieuthu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Thu", "Ngày Thu", "Người Thu", "Tiền Thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblphieuthu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblphieuthuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblphieuthu);

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

        dtnthu.setDateFormatString("yyyy-MM-dd");
        jToolBar1.add(dtnthu);
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new frmPhieuThu().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loadtodata();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblphieuthuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblphieuthuMouseClicked
        // TODO add your handling code here:
        ShareHepler.ma_pt = 0;
        int index = tblphieuthu.getSelectedRow();
        if (evt.getClickCount() == 2) {
            ShareHepler.ma_pt = Integer.valueOf(tblphieuthu.getValueAt(index, 0).toString());
            new frmChiTietPhieuThu().setVisible(true);
        }
    }//GEN-LAST:event_tblphieuthuMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (dtnthu.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Ngày Thu");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblphieuthu.getModel();
        model.setRowCount(0);

        List<PhieuThu> list = dao.find_ByDate(dtnthu.getDate());
        if (list == null) {
            JOptionPane.showMessageDialog(this, "Không có Phiếu Thu nào");
            return;
        }
        try {
            for (PhieuThu x : list) {
                Vector v = new Vector();
                v.add(x.getMa_PT());
                v.add(x.getNgayThu());
                v.add(x.getNguoiThu());
                v.add(x.getTienThu());
                model.addRow(v);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dtnthu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblphieuthu;
    // End of variables declaration//GEN-END:variables
}
