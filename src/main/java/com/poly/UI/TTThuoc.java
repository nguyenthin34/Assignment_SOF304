/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.Jdbc.SQLConnect;
import com.poly.dao.DonViTinhDAO;
import com.poly.dao.NhomThuocDAO;
import com.poly.dao.ThuocDAO;
import com.poly.helper.ShareHepler;
import com.poly.model.DonViTinh;
import com.poly.model.NhomThuoc;
import com.poly.model.Thuoc;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TTThuoc extends javax.swing.JFrame {

    ThuocDAO dao = new ThuocDAO();
    NhomThuocDAO dao_NT = new NhomThuocDAO();
    DefaultTableModel model;
    DonViTinhDAO dao_dvt = new DonViTinhDAO();
    DefaultComboBoxModel<NhomThuoc> modelcbo1;
    int index;

    public TTThuoc() {
        initComponents();
        loadtocbo1();
    }

    private void loadtocbo1() {
        cbonhomthuoc.removeAllItems();
        modelcbo1 = (DefaultComboBoxModel) cbonhomthuoc.getModel();
        List<NhomThuoc> list = dao_NT.findByTT();
        for (NhomThuoc x : list) {
            NhomThuoc ph = new NhomThuoc(x.getID_Nhom(), x.getTen_Nhom(), x.isTrangThai());
            modelcbo1.addElement(ph);
        }
    }

    private void loadData() {
        try {

            model = (DefaultTableModel) tblttthuoc.getModel();
            List<Thuoc> list = dao.select();
            model.setRowCount(0);
            for (Thuoc x : list) {
                Vector v = new Vector();
                v.add(x.getID_Thuoc());
                v.add(x.getTen_Thuoc());
                NhomThuoc nthuoc = dao_NT.findByID(x.getID_NhomThuoc());
                v.add(nthuoc);
                DonViTinh dvt = dao_dvt.findByID(x.getID_DVT());
                v.add(dvt);
                v.add(x.getCach_Dung());
                v.add(x.getHoatChat());
                v.add(x.getHamLuong());
                v.add(x.getDongGoi());
                v.add(x.getNoiSanXuat());
                v.add(x.isTrangThai() ? "Còn Hạn Sử Dụng" : "Hết Hạn Sử Dụng");
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
        tblttthuoc = new javax.swing.JTable();
        cbonhomthuoc = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btnlaydanhsachThuoc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblttthuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Thuốc", "Tên Thuốc", "Nhóm Thuốc", "Đơn Vị Tính", "Cách Dùng", "Hoạt Chất", "Hàm Lương", "Đóng Gói", "Nơi Sản Xuất", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblttthuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblttthuocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblttthuoc);

        cbonhomthuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbonhomthuoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbonhomthuocItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Tìm Kiếm theo nhóm Thuốc");

        btnlaydanhsachThuoc.setText("Lấy Danh Sách Thuốc");
        btnlaydanhsachThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlaydanhsachThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(cbonhomthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnlaydanhsachThuoc)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                    .addGap(13, 13, 13)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbonhomthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btnlaydanhsachThuoc))
                .addContainerGap(456, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(33, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_formWindowOpened

    private void cbonhomthuocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbonhomthuocItemStateChanged
        // TODO add your handling code here:
        NhomThuoc nt = new NhomThuoc();
        nt = (NhomThuoc) cbonhomthuoc.getSelectedItem();
        List<Thuoc> list = dao.findByIDNhomThuoc(nt.getID_Nhom());
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblttthuoc.getModel();
            model.setRowCount(0);
            for (Thuoc x : list) {
                Vector v = new Vector();
                v.add(x.getID_Thuoc());
                v.add(x.getTen_Thuoc());
                NhomThuoc nthuoc = dao_NT.findByID(x.getID_NhomThuoc());
                v.add(nthuoc.getTen_Nhom());
                DonViTinh dvt = dao_dvt.findByID(x.getID_DVT());
                v.add(dvt.getTen_DVT());
                v.add(x.getCach_Dung());
                v.add(x.getHoatChat());
                v.add(x.getHamLuong());
                v.add(x.getDongGoi());
                v.add(x.getNoiSanXuat());
                v.add(x.isTrangThai() ? "Đang Kinh Doanh" : "Ngừng Kinh Doanh");
                model.addRow(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_cbonhomthuocItemStateChanged

    private void btnlaydanhsachThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlaydanhsachThuocActionPerformed
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_btnlaydanhsachThuocActionPerformed

    private void tblttthuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblttthuocMouseClicked
        // TODO add your handling code here:
        index = tblttthuoc.getSelectedRow();
        if (evt.getClickCount() == 2) {
            ShareHepler.thuoc = null;
            int id = Integer.valueOf(tblttthuoc.getValueAt(index, 0).toString());
            String ten = tblttthuoc.getValueAt(index, 1).toString();
            ShareHepler.thuoc(id, ten);
            this.dispose();
        }

    }//GEN-LAST:event_tblttthuocMouseClicked

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
            java.util.logging.Logger.getLogger(TTThuoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TTThuoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TTThuoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TTThuoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TTThuoc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlaydanhsachThuoc;
    private javax.swing.JComboBox<String> cbonhomthuoc;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblttthuoc;
    // End of variables declaration//GEN-END:variables
}
