/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.DonViTinhDAO;
import com.poly.dao.NhaCungCapDAO;
import com.poly.dao.NhomThuocDAO;
import com.poly.dao.PhieuNhapHangCTDAO;
import com.poly.dao.PhieuNhapHangDAO;
import com.poly.dao.SanPhamThuocDAO;
import com.poly.dao.ThuocDAO;
import com.poly.dao.TuThuocDAO;
import com.poly.helper.Datehelper;
import com.poly.helper.ShareHepler;
import com.poly.model.DonViTinh;
import com.poly.model.NhaCungCap;
import com.poly.model.NhomThuoc;
import com.poly.model.PhieuNhapHang;
import com.poly.model.PhieuNhapHangCT;
import com.poly.model.SanPhamThuoc;
import com.poly.model.Thuoc;
import com.poly.model.ThuocTam;
import com.poly.model.TuThuoc;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmNhapHang extends javax.swing.JPanel {

    ThuocDAO dao = new ThuocDAO();
    SanPhamThuocDAO dao_sp = new SanPhamThuocDAO();
    NhomThuocDAO dao_NT = new NhomThuocDAO();
    DonViTinhDAO dao_dvt = new DonViTinhDAO();
    int index = -1;
    DefaultComboBoxModel<NhaCungCap> cbomodel1;
    NhaCungCapDAO dao_ncc = new NhaCungCapDAO();
    List<SanPhamThuoc> list = new ArrayList();
    List combo = new ArrayList<>();
    PhieuNhapHangDAO dao_pn = new PhieuNhapHangDAO();
    PhieuNhapHangCTDAO dao_pnct = new PhieuNhapHangCTDAO();
    DefaultTableModel modelhn;
    int index2;

    public frmNhapHang() {
        initComponents();
        loadtodata();
        loadtocbo_ncc();
    }

    private void loadtocbo_ncc() {
        cbonhacungcap.removeAllItems();
        cbomodel1 = (DefaultComboBoxModel) cbonhacungcap.getModel();
        List<NhaCungCap> list = dao_ncc.select_tt();
        for (NhaCungCap x : list) {
            NhaCungCap ph = new NhaCungCap(x.getID_NhaCC(), x.getTen_NCC(), x.isTrangThai());
            combo.add(x.getID_NhaCC());
            cbomodel1.addElement(ph);
        }
    }

    private void loadtodata() {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblttthuoc.getModel();
            List<Thuoc> list = dao.select();
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
    }

    private void remove() {
        if (index2 >= 0) {
            Float gia = Float.valueOf(lblthanhtien.getText());
            Float giatru = list.get(index2).getGiaNhap() * list.get(index2).getSoLuongTon();
            Float giamoi = gia - giatru;
            lblthanhtien.setText(String.valueOf(giamoi));
            list.remove(index2);
            modelhn.removeRow(index2);
        } else {
            JOptionPane.showMessageDialog(this, "Không Còn gì để xóa");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblttthuoc = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblhangnhap = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblthanhtien = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        dtngaytao = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtsoluongnhap = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtgianhap = new javax.swing.JTextField();
        dthansudung = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbonhacungcap = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

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
        tblttthuoc.setGridColor(new java.awt.Color(255, 255, 255));
        tblttthuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblttthuocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblttthuoc);

        tblhangnhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Thuốc", "Tên Thuốc", "Giá Nhập", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblhangnhap.setGridColor(new java.awt.Color(255, 255, 255));
        tblhangnhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhangnhapMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblhangnhap);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Thành Tiền");

        lblthanhtien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblthanhtien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblthanhtien.setText("0");

        jButton2.setBackground(new java.awt.Color(30, 165, 252));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Hoàn Thành");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Ngày Tạo");

        dtngaytao.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtngaytao, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblthanhtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(lblthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(dtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Số Lượng Nhập");

        txtsoluongnhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsoluongnhapKeyTyped(evt);
            }
        });

        jLabel2.setText("Giá Nhập");

        txtgianhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtgianhapKeyTyped(evt);
            }
        });

        dthansudung.setDateFormatString("yyyy-MM-dd");

        jLabel3.setText("Hạn Sử Dụng");

        jLabel4.setText("Nhà Cung Cấp");

        jButton1.setBackground(new java.awt.Color(30, 165, 252));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Lưu Tạm");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(30, 165, 252));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Xóa");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(30, 165, 252));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cập Nhật");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtsoluongnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(dthansudung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbonhacungcap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(25, 25, 25)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtsoluongnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbonhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dthansudung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblttthuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblttthuocMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 1) {
            index = tblttthuoc.getSelectedRow();
        }
    }//GEN-LAST:event_tblttthuocMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        try {
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "bạn chưa chọn thuốc cần nhập");
                return;
            }

            if (txtgianhap.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Giá Nhập Của Thuốc");
                txtgianhap.requestFocus();
                return;
            }

            if (txtsoluongnhap.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Số Lượng");
                txtsoluongnhap.requestFocus();
                return;
            }

            try {
                int sln = Integer.valueOf(txtsoluongnhap.getText());
                if (sln <= 0) {
                    JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Số Lượng > 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập Số Lượng Nhập là số");
                return;
            }

            try {
                int gianhap = Integer.valueOf(txtgianhap.getText());
                if (gianhap <= 0) {
                    JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Giá Nhập > 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập Giá Nhập là số");
                return;
            }
            if (dthansudung.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Ngày Hết Hạn cho Thuốc");
                return;
            }
            int idthuoc = Integer.valueOf(tblttthuoc.getValueAt(index, 0).toString());
            for (SanPhamThuoc x : list) {
                if (idthuoc == x.getID_Thuoc()) {
                    JOptionPane.showMessageDialog(this, "Thuốc đã tồn tại, bạn hãy xóa đi và cập nhật lại thuốc");
                    return;
                }
            }
            modelhn = (DefaultTableModel) tblhangnhap.getModel();
            int i = cbonhacungcap.getSelectedIndex();
            String ncc = cbomodel1.getElementAt(i).getID_NhaCC();

            String tenthuoc = tblttthuoc.getValueAt(index, 1).toString();
            float gianhap = Float.valueOf(txtgianhap.getText());
            int soluong = Integer.valueOf(txtsoluongnhap.getText());
            Date hsd = dthansudung.getDate();
            list.add(new SanPhamThuoc(idthuoc, ncc, gianhap, soluong, hsd));
            modelhn.addRow(new Object[]{idthuoc, tenthuoc, gianhap, soluong});
            float tien = Float.valueOf(txtgianhap.getText());
            float tiensl = tien * soluong;
            float tongtien = Float.valueOf(lblthanhtien.getText());
            tongtien = tongtien + tiensl;
            lblthanhtien.setText(String.valueOf(tongtien));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
//            SanPhamThuoc model = dao_sp.findBydk();
            int i = cbonhacungcap.getSelectedIndex();
            String ncc = cbomodel1.getElementAt(i).getID_NhaCC();
            if (list.size() == 0) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Thuốc Để Nhập");
                return;
            }
            for (SanPhamThuoc x : list) {
                if (!ncc.contains(x.getID_NhaCC())) {
                    JOptionPane.showMessageDialog(this, "Nhà Cung Cấp Không Trùng Khớp Với Nhà Cung Cấp Của Thuốc");
                    return;
                }
            }

            Date ngaytao = dtngaytao.getDate();
            if (ngaytao == null) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Ngày Tạo Phiếu");
                dtngaytao.requestFocus();
            }
            float tien = Float.valueOf(lblthanhtien.getText());

            PhieuNhapHang model = getModel_PN(ncc, ngaytao, tien);
            JOptionPane.showMessageDialog(this, ShareHepler.mand);
            dao_pn.insert(model);
            for (SanPhamThuoc x : list) {
                dao_pnct.insert(getModel_PNCT(x.getID_Thuoc(), x.getSoLuongTon(), x.getGiaNhap(), x.getHanSuDung()));
                SanPhamThuoc li1 = dao_sp.findBydk(x.getID_Thuoc(), ncc, x.getHanSuDung(), x.getGiaNhap());

                if (li1 != null) {
                    dao_sp.update(x.getSoLuongTon(), x.getID_Thuoc(), x.getID_NhaCC(), x.getHanSuDung(), x.getGiaNhap());
                } else {
                    SanPhamThuoc md = getModel_spt(x.getID_Thuoc(), ncc, x.getGiaNhap(), x.getSoLuongTon(), x.getHanSuDung());
                    dao_sp.insert2(md);
                }
            }
            loadtodata();
            txtgianhap.setText("");
            txtsoluongnhap.setText("");
            dthansudung.setDate(null);
            dtngaytao.setDate(null);
            cbonhacungcap.setSelectedIndex(0);
            lblthanhtien.setText("0");
            modelhn.setRowCount(0);
            list.clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + "Lỗi Hoàn Thành");
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblhangnhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhangnhapMouseClicked
        // TODO add your handling code here:
        index2 = tblhangnhap.getSelectedRow();
        if (index2 >= 0) {
            txtsoluongnhap.setText(String.valueOf(list.get(index2).getSoLuongTon()));
            txtgianhap.setText(String.valueOf(list.get(index2).getGiaNhap()));
            dthansudung.setDate(list.get(index2).getHanSuDung());
            cbonhacungcap.setSelectedIndex(combo.indexOf(list.get(index2).getID_NhaCC()));
        }
    }//GEN-LAST:event_tblhangnhapMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            if (tblhangnhap.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Hàng Muốn Xóa");
                return;
            }
            // kiểm tra xem còn gì để xóa không
            if (list.size() == 0) {
                JOptionPane.showMessageDialog(this, "Không còn gì để xóa");
                return;
            }

            // hỏi xóa
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xóa Không");
            if (hoi != JOptionPane.YES_OPTION) {
                return;// nếu chọn khác yes thì dừng lại, chọn yes thì xóa
            }

            //thực hiện xóa
            remove();

        } catch (Exception e) {
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
              if (index2 < 0) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Sản Phẩm Để Cập Nhật");
            return;
        }
        if (txtgianhap.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Giá Nhập Của Thuốc");
            txtgianhap.requestFocus();
            return;
        }

        if (txtsoluongnhap.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Số Lượng");
            txtsoluongnhap.requestFocus();
            return;
        }

        try {
            int sln = Integer.valueOf(txtsoluongnhap.getText());
            if (sln <= 0) {
                JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Số Lượng > 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hãy Nhập Số Lượng Nhập là số");
            return;
        }
        try {
            int gianhap = Integer.valueOf(txtgianhap.getText());
            if (gianhap <= 0) {
                JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Giá Nhập > 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hãy Nhập Giá Nhập là số");
            return;
        }
        if (dthansudung.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Ngày Hết Hạn cho Thuốc");
            return;
        }
        
        float giacu = list.get(index2).getGiaNhap();
        int slc = list.get(index2).getSoLuongTon();
        float tonggiacu = giacu * slc;
        float gianhapm = Float.valueOf(txtgianhap.getText());
        int slm = Integer.valueOf(txtsoluongnhap.getText());
        Date hsdm = dthansudung.getDate();
        String idtuoc = tblhangnhap.getValueAt(index2, 0).toString();
        String tenthuoc = tblhangnhap.getValueAt(index2, 1).toString();
        list.get(index2).setGiaNhap(gianhapm);
        list.get(index2).setSoLuongTon(slm);
        list.get(index2).setHanSuDung(hsdm);
        int i = cbonhacungcap.getSelectedIndex();
        String nccm = cbomodel1.getElementAt(i).getID_NhaCC();
        list.get(index2).setID_NhaCC(nccm);

        modelhn.setValueAt(idtuoc, index2, 0);
        modelhn.setValueAt(tenthuoc, index2, 1);
        modelhn.setValueAt(gianhapm, index2, 2);
        modelhn.setValueAt(slm, index2, 3);
        float tonghientai = Float.valueOf(lblthanhtien.getText());
        float tienmoi = gianhapm * slm;
        float conlai = tonghientai - tonggiacu;
        float giacuoi = conlai + tienmoi;
        lblthanhtien.setText(String.valueOf(giacuoi));
            JOptionPane.showMessageDialog(this,"Cập Nhật Thành Công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Lỗi Cập Nhật");
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtgianhapKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgianhapKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
    if(!Character.isDigit(c) )
    {
        evt.consume();
    }
    }//GEN-LAST:event_txtgianhapKeyTyped

    private void txtsoluongnhapKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsoluongnhapKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
    if(!Character.isDigit(c) )
    {
        evt.consume();
    }
    }//GEN-LAST:event_txtsoluongnhapKeyTyped

    private PhieuNhapHang getModel_PN(String ncc, Date NgayLapHD, float tien) {
        PhieuNhapHang model = new PhieuNhapHang();
        model.setID_NhaCC(ncc);
        model.setTongTienHang(tien);
        model.setNgayLapHD(NgayLapHD);
        model.setNguoiTao(ShareHepler.mand);

        return model;
    }

    private PhieuNhapHangCT getModel_PNCT(int idthuoc, int soluong, float gianhap, Date hsd) {
        PhieuNhapHang phieuNhapHang = dao_pn.select();
        PhieuNhapHangCT model = new PhieuNhapHangCT();
        model.setID_HDN(phieuNhapHang.getID_HDN());
        model.setID_Thuoc(idthuoc);
        model.setSoluong(soluong);
        model.setGiaNhap(gianhap);
        model.setHanSuDung(hsd);
        return model;
    }

    private SanPhamThuoc getModel_spt(int idthuoc, String ncc, float gianhap, int soluong, Date hsd) {
        SanPhamThuoc model = new SanPhamThuoc();
        model.setID_Thuoc(idthuoc);
        model.setID_NhaCC(ncc);
        model.setID_Tu("T1");
        model.setSoLuongTon(soluong);
        model.setGiaNhap(gianhap);
        model.setHanSuDung(hsd);
        model.setTrangThai(true);
        return model;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbonhacungcap;
    private com.toedter.calendar.JDateChooser dthansudung;
    private com.toedter.calendar.JDateChooser dtngaytao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblthanhtien;
    private javax.swing.JTable tblhangnhap;
    private javax.swing.JTable tblttthuoc;
    private javax.swing.JTextField txtgianhap;
    private javax.swing.JTextField txtsoluongnhap;
    // End of variables declaration//GEN-END:variables
}
