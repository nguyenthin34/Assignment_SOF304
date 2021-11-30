/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.HoaDonTraHangDAO;
import com.poly.helper.Datehelper;
import com.poly.model.HoaDonTraHang;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC HP
 */
public class frmDanhSachHoaDonTH extends javax.swing.JPanel {

    HoaDonTraHangDAO dao = new HoaDonTraHangDAO();
    DefaultTableModel model;

    public frmDanhSachHoaDonTH() {
        initComponents();
        model = (DefaultTableModel) tbldsth.getModel();
        loaddata();
    }

    private void loaddata() {
        try {

            List<HoaDonTraHang> list = dao.selectall();
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có hóa đơn trả hàng nào");
                return;
            }
            model.setRowCount(0);
            for (HoaDonTraHang x : list) {
                Vector v = new Vector();
                v.add(x.getID_HDTH());
                v.add(x.getID_HDB());
                v.add(x.getTienCanTra());
                v.add(x.getNgayTao());
                v.add(x.getNguoiTao());
                v.add(x.getMoTa());
                model.addRow(v);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbldsth = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        dtngaytao = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtmaphieu = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        tbldsth.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_HDTH", "ID Hóa Đơn Bán", "Tiền Trả", "Ngày Lập Hóa Đơn", "Người Lập", "Mô Tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbldsth);

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

        dtngaytao.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("Ngày Tạo Phiếu");

        txtmaphieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmaphieuKeyTyped(evt);
            }
        });

        jLabel1.setText("Mã Hóa Đơn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(dtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jButton1)
                        .addGap(45, 45, 45)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loaddata();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (txtmaphieu.getText().equals("") && dtngaytao.getDate() != null) {
            String ngaynhap = Datehelper.toString(dtngaytao.getDate(), "yyyy-MM-dd");
            model.setRowCount(0);
            List<HoaDonTraHang> list = dao.find_ByDate(ngaynhap);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có Phiếu Nhập Hàng Nào");
                return;
            }
            try {
                for (HoaDonTraHang x : list) {
                    Vector v = new Vector();
                    v.add(x.getID_HDTH());
                    v.add(x.getID_HDB());
                    v.add(x.getTienCanTra());
                    v.add(x.getNgayTao());
                    v.add(x.getNguoiTao());
                    v.add(x.getMoTa());
                    model.addRow(v);
                }
            } catch (Exception e) {
            }
        } else if (!txtmaphieu.getText().equals("") && dtngaytao.getDate() == null) {
            model.setRowCount(0);
            HoaDonTraHang list = dao.find_ByID(Integer.valueOf(txtmaphieu.getText()));
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có Phiếu Nhập Hàng Nào");
                return;
            }
            try {
                Vector v = new Vector();
                v.add(list.getID_HDTH());
                v.add(list.getID_HDB());
                v.add(list.getTienCanTra());
                v.add(list.getNgayTao());
                v.add(list.getNguoiTao());
                v.add(list.getMoTa());

                model.addRow(v);

            } catch (Exception e) {
            }
        } else if (!txtmaphieu.getText().equals("") && dtngaytao.getDate() != null) {
             String ngaynhap = Datehelper.toString(dtngaytao.getDate(), "yyyy-MM-dd");
            model.setRowCount(0);
            List<HoaDonTraHang> list = dao.find_Bytow(Integer.valueOf(txtmaphieu.getText()),ngaynhap);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có Phiếu Nhập Hàng Nào");
                return;
            }
            try {
                for (HoaDonTraHang x : list) {
                    Vector v = new Vector<>();
                    v.add(x.getID_HDTH());
                    v.add(x.getID_HDB());
                    v.add(x.getTienCanTra());
                    v.add(x.getNgayTao());
                    v.add(x.getNguoiTao());
                    v.add(x.getMoTa());
                    model.addRow(v);
                }
            } catch (Exception e) {
            }
        } else if (txtmaphieu.getText().equals("") && dtngaytao.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập điều kiện tìm kiếm nào");
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtmaphieuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmaphieuKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtmaphieuKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dtngaytao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbldsth;
    private javax.swing.JTextField txtmaphieu;
    // End of variables declaration//GEN-END:variables
}
