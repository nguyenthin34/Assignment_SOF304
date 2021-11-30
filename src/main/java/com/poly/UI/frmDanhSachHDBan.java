/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.HoaDonBanHangDAO;
import com.poly.helper.Datehelper;
import com.poly.helper.ShareHepler;
import com.poly.model.HoaDonBan;
import java.sql.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC HP
 */
public class frmDanhSachHDBan extends javax.swing.JPanel {

    HoaDonBanHangDAO dao = new HoaDonBanHangDAO();
    DefaultTableModel model;

    public frmDanhSachHDBan() {
        initComponents();
        model = (DefaultTableModel) tblhoadonban.getModel();
        loadtoData();
    }

    private void loadtoData() {

        model.setRowCount(0);

        List<HoaDonBan> list = dao.select_nam();
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
        jLabel1 = new javax.swing.JLabel();
        txtmaphieu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dtngaytao = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        tblhoadonban.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tblhoadonban.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Hóa Đơn Bán", "Số Điện Thoại Khách Hàng", "Tổng Tiền", "Ngày Lập Phiếu", "Người Tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mã Phiếu Nhập");

        txtmaphieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmaphieuKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Ngày Tạo Phiếu");

        dtngaytao.setDateFormatString("yyyy-MM-dd");

        jButton1.setBackground(new java.awt.Color(30, 165, 252));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tìm Kiếm");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(30, 165, 252));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cập Nhật DS");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(dtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(27, 27, 27))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addComponent(dtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addContainerGap(543, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(85, 85, 85)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                    .addGap(86, 86, 86)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblhoadonbanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhoadonbanMouseClicked
      int    index = tblhoadonban.getSelectedRow();
        if(evt.getClickCount() == 2){
            ShareHepler.dshdbct = Integer.valueOf(tblhoadonban.getValueAt(index, 0).toString());
             new frmdsHDBCT().setVisible(true);
        }
    }//GEN-LAST:event_tblhoadonbanMouseClicked

    private void txtmaphieuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmaphieuKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtmaphieuKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (txtmaphieu.getText().equals("") && dtngaytao.getDate() != null) {
            model.setRowCount(0);
             String he = Datehelper.toString(dtngaytao.getDate(), "yyyy-MM-dd");
            List<HoaDonBan> list = dao.find_ByDate(he);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có Hóa Đơn Bán Hàng Nào");
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
        } else if (!txtmaphieu.getText().equals("") && dtngaytao.getDate() == null) {
            model.setRowCount(0);
            HoaDonBan list = dao.find_ByID(Integer.valueOf(txtmaphieu.getText()));
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có Hóa Đơn Bán Hàng Nào");
                return;
            }
            Vector v = new Vector();
            v.add(list.getID_HDB());
            v.add(list.getSDT_KH());
            v.add(list.getTongTien());
            v.add(list.getNgayLapHD());
            v.add(list.getNguoiTao());
            model.addRow(v);
        } else if (!txtmaphieu.getText().equals("") && dtngaytao.getDate() != null) {
            model.setRowCount(0);
             String he = Datehelper.toString(dtngaytao.getDate(), "yyyy-MM-dd");
            List<HoaDonBan> list = dao.find_Bytow(Integer.valueOf(txtmaphieu.getText()), he);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có Hóa Đơn Bán Hàng Nào");
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
        } else if (txtmaphieu.getText().equals("") && dtngaytao.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập điều kiện tìm kiếm nào");
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loadtoData();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dtngaytao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblhoadonban;
    private javax.swing.JTextField txtmaphieu;
    // End of variables declaration//GEN-END:variables
}
