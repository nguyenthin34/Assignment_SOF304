/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.PhieuNhapHangDAO;
import com.poly.helper.Datehelper;
import com.poly.helper.ShareHepler;
import com.poly.model.PhieuNhapHang;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC HP
 */
public class frmDanhSachPhieuNhap extends javax.swing.JPanel {

    PhieuNhapHangDAO dao_pn = new PhieuNhapHangDAO();
    DefaultTableModel model;

    public frmDanhSachPhieuNhap() {
        initComponents();
        model = (DefaultTableModel) tbldanhsachphieunhap.getModel();
        loadtodata2();
    }

    private void loadtodata2() {

        model.setRowCount(0);

        List<PhieuNhapHang> list = dao_pn.select_ds();
        if (list == null) {
            JOptionPane.showMessageDialog(this, "Không có Phiếu Nhập Hàng Nào");
            return;
        }
        try {
            for (PhieuNhapHang x : list) {
                Vector v = new Vector();
                v.add(x.getID_HDN());
                v.add(x.getID_NhaCC());
                v.add(x.getTongTienHang());
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
        tbldanhsachphieunhap = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtmaphieu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dtngaytao = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Phiếu Nhập Hàng"));

        tbldanhsachphieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Phiếu Nhập", "ID Nhà Cungg Cấp", "Tổng Tiền", "Ngày Lập Hóa Đơn", "Người Tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldanhsachphieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldanhsachphieunhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbldanhsachphieunhap);

        jLabel1.setText("Mã Phiếu Nhập");

        txtmaphieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmaphieuKeyTyped(evt);
            }
        });

        jLabel2.setText("Ngày Tạo Phiếu");

        dtngaytao.setDateFormatString("yyyy-MM-dd");

        jButton1.setBackground(new java.awt.Color(30, 165, 252));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 153));
        jButton1.setText("Tìm Kiếm");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(30, 165, 252));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 153));
        jButton2.setText("Cập Nhật DS");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(dtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtmaphieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addComponent(dtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loadtodata2();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (txtmaphieu.getText().equals("") && dtngaytao.getDate() != null) {
            model.setRowCount(0);
            String ngaytao = Datehelper.toString(dtngaytao.getDate(), "yyyy-MM-dd");
            List<PhieuNhapHang> list = dao_pn.find_ByDate(ngaytao);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có Phiếu Nhập Hàng Nào");
                return;
            }
            try {
                for (PhieuNhapHang x : list) {
                    Vector v = new Vector();
                    v.add(x.getID_HDN());
                    v.add(x.getID_NhaCC());
                    v.add(x.getTongTienHang());
                    v.add(x.getNgayLapHD());
                    v.add(x.getNguoiTao());

                    model.addRow(v);
                }
            } catch (Exception e) {
            }
        } else if (!txtmaphieu.getText().equals("") && dtngaytao.getDate() == null) {
            model.setRowCount(0);
            PhieuNhapHang list = dao_pn.find_ByID(Integer.valueOf(txtmaphieu.getText()));
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có Phiếu Nhập Hàng Nào");
                return;
            }
            try {
                Vector v = new Vector();
                v.add(list.getID_HDN());
                v.add(list.getID_NhaCC());
                v.add(list.getTongTienHang());
                v.add(list.getNgayLapHD());
                v.add(list.getNguoiTao());

                model.addRow(v);

            } catch (Exception e) {
            }
        } else if (!txtmaphieu.getText().equals("") && dtngaytao.getDate() != null) {
            model.setRowCount(0);
            String ngaytao = Datehelper.toString(dtngaytao.getDate(), "yyyy-MM-dd");
            List<PhieuNhapHang> list = dao_pn.find_Bytow(Integer.valueOf(txtmaphieu.getText()),ngaytao);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có Phiếu Nhập Hàng Nào");
                return;
            }
            try {
                for (PhieuNhapHang x : list) {
                    Vector v = new Vector();
                    v.add(x.getID_HDN());
                    v.add(x.getID_NhaCC());
                    v.add(x.getTongTienHang());
                    v.add(x.getNgayLapHD());
                    v.add(x.getNguoiTao());

                    model.addRow(v);
                }
            } catch (Exception e) {
            }
        }else if(txtmaphieu.getText().equals("") && dtngaytao.getDate() == null){
            JOptionPane.showMessageDialog(this,"Bạn chưa nhập điều kiện tìm kiếm nào");
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtmaphieuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmaphieuKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
    if(!Character.isDigit(c) )
    {
        evt.consume();
    }
    }//GEN-LAST:event_txtmaphieuKeyTyped

    private void tbldanhsachphieunhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldanhsachphieunhapMouseClicked
        // TODO add your handling code here:
        int index = tbldanhsachphieunhap.getSelectedRow();
        if(evt.getClickCount() == 2){
            
            ShareHepler.dshdnct = Integer.valueOf(tbldanhsachphieunhap.getValueAt(index,0).toString());
            new frmdsHDNCT().setVisible(true);
        }
    }//GEN-LAST:event_tbldanhsachphieunhapMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dtngaytao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbldanhsachphieunhap;
    private javax.swing.JTextField txtmaphieu;
    // End of variables declaration//GEN-END:variables
}
