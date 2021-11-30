/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.HoaDonBanCTDAO;
import com.poly.dao.HoaDonBanHangDAO;
import com.poly.dao.KhachHangDAO;
import com.poly.dao.NhaCungCapDAO;
import com.poly.dao.SanPhamThuocDAO;
import com.poly.dao.ThuocDAO;
import com.poly.dao.TuThuocDAO;
import com.poly.model.NhaCungCap;
import com.poly.model.SanPhamThuoc;
import com.poly.model.Thuoc;
import com.poly.model.TuThuoc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.poly.helper.Datehelper;
import com.poly.helper.ShareHepler;
import com.poly.model.HoaDonBan;
import com.poly.model.HoaDonBanCT;
import com.poly.model.KhachHang;

/**
 *
 * @author PC HP
 */
public class frmBanHang extends javax.swing.JPanel {

    int tong;
    SanPhamThuocDAO dao = new SanPhamThuocDAO();
    ThuocDAO dao_th = new ThuocDAO();
    NhaCungCapDAO dao_ncc = new NhaCungCapDAO();
    TuThuocDAO dao_tt = new TuThuocDAO();
    HoaDonBanHangDAO dao_hdb = new HoaDonBanHangDAO();
    HoaDonBanCTDAO dao_hdbct = new HoaDonBanCTDAO();
     DefaultTableModel model;
            
    int index;
    DefaultTableModel modelhb;
    List<SanPhamThuoc> list = new ArrayList();
    int index2 = -1;
    String sdt;
    KhachHangDAO dao_kh = new KhachHangDAO();

    public frmBanHang() {
        initComponents();
        model = (DefaultTableModel) tblsanpham.getModel();
        loadtodata();
    }

    private void loadtodata() {
        try {
           
            List<SanPhamThuoc> list = dao.findByTT();
            model.setRowCount(0);
            for (SanPhamThuoc x : list) {
                Vector v = new Vector();
                v.add(x.getMa_Thuoc());
                Thuoc thuoc = dao_th.findByID(x.getID_Thuoc());
                v.add(thuoc.getTen_Thuoc());
                NhaCungCap nhacc = dao_ncc.findByID(x.getID_NhaCC());
                v.add(nhacc.getTen_NCC());
                TuThuoc tu = dao_tt.findByID(x.getID_Tu());
                v.add(tu.getTen_Tu());
//                setHSD(x.getHanSuDung());
                v.add(x.getHanSuDung());
                v.add(x.getGiaNhap());
                v.add(x.getGiaBan());
                v.add(x.getSoLuongTon());
                v.add(x.isTrangThai() ? "Còn Hạn Sử Dụng" : "Hết Hạn Sử Dụng");
                model.addRow(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private HoaDonBan getModel_hdb(String sdt, float tien, Date NgayLapHD) {
        HoaDonBan model = new HoaDonBan();
        model.setSDT_KH(sdt);
        model.setTongTien(tien);
        model.setNgayLapHD(NgayLapHD);
        model.setNguoiTao(ShareHepler.mand);
        return model;
    }

    private HoaDonBanCT getModel_hdbct(int ma, int sl, float gia) {
        HoaDonBanCT model = new HoaDonBanCT();
        HoaDonBan hdb = dao_hdb.select();
        if (hdb != null) {
            model.setID_HDB(hdb.getID_HDB());
        } else {
            JOptionPane.showMessageDialog(this, "Hóa Đơn Chưa Tồn Tại");
        }
        model.setMa_Thuoc(ma);
        model.setSoLuong(sl);
        model.setGiaBan(gia);
        return model;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtsoluongban = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblhangban = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        dtngaytao = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        lbltongtien = new javax.swing.JLabel();
        chkchechkkh = new javax.swing.JCheckBox();
        txtsodienthoai = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        txthoten = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btntim = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanpham = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Số Lượng Bán");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 70, 25));

        txtsoluongban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsoluongbanKeyTyped(evt);
            }
        });
        add(txtsoluongban, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 150, 25));

        tblhangban.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Thuốc", "Tên Thuốc", "Giá Bán", "Số Lượng", "Hạn Sử Dụng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblhangban.setGridColor(new java.awt.Color(255, 255, 255));
        tblhangban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhangbanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblhangbanMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tblhangban);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 351, 713, 259));

        jButton2.setBackground(new java.awt.Color(30, 165, 252));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Lưu tạm");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 100, 25));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setBackground(new java.awt.Color(30, 165, 252));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Hoàn Thành");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Ngày Tạo");

        dtngaytao.setDateFormatString("yyyy-MM-dd");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel7.setText("Thành Tiền");

        lbltongtien.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbltongtien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltongtien.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltongtien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtngaytao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 39, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltongtien, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(dtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jButton3)
                .addGap(34, 34, 34))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(719, 351, -1, -1));

        chkchechkkh.setText("Khách Hàng");
        chkchechkkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkchechkkhActionPerformed(evt);
            }
        });
        add(chkchechkkh, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 90, 25));

        txtsodienthoai.setEditable(false);
        txtsodienthoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsodienthoaiActionPerformed(evt);
            }
        });
        txtsodienthoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsodienthoaiKeyTyped(evt);
            }
        });
        add(txtsodienthoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 150, 25));

        jButton4.setBackground(new java.awt.Color(30, 165, 252));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Xóa");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 100, 25));

        txthoten.setEditable(false);
        add(txthoten, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 260, 150, 25));

        jLabel8.setText("Họ Tên:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 260, 60, 25));

        btntim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btntim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimActionPerformed(evt);
            }
        });
        add(btntim, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 25, 25));

        jButton1.setBackground(new java.awt.Color(30, 165, 252));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cập Nhật");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 110, 25));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Sản Phẩm Thuốc"));

        tblsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Thuốc", "Tên Thuốc", "Nhà Cung Cấp", "Tủ", "Hạn Sử Dụng", "Giá Nhập", "Giá Bán", "Số Lượng Tồn", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsanphamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblsanpham);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 240));
    }// </editor-fold>//GEN-END:initComponents

    private void tblhangbanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhangbanMouseClicked
        // TODO add your handling code here:
        index2 = tblhangban.getSelectedRow();
        if (index2 >= 0) {
            txtsoluongban.setText(String.valueOf(list.get(index2).getSoLuongTon()));
        }
    }//GEN-LAST:event_tblhangbanMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {

            if (list.size() == 0) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Thuốc Để Bán");
                return;
            }

            Date ngaytao = dtngaytao.getDate();
            if (ngaytao == null) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Ngày Tạo Phiếu");
                dtngaytao.requestFocus();
            }

            if (chkchechkkh.isSelected()) {
                if (txtsodienthoai.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Hãy Nhập SDT Khách Hàng");
                    return;
                }
                if (txtsodienthoai.getText().length() > 10) {
                    JOptionPane.showMessageDialog(this, "Số Điện Thoại chỉ có 10 số bạn nhé");
                    return;
                }

                sdt = txtsodienthoai.getText();
                KhachHang chkkh = dao_kh.findByID(sdt);
                if (chkkh == null) {
                    JOptionPane.showMessageDialog(this, "Khách Hàng chưa tồn tại, bạn kiểm tra lại số điện thoại khách hàng");
                    return;
                }

            }

            float tongtien = Float.valueOf(lbltongtien.getText());
            HoaDonBan model = getModel_hdb(sdt, tongtien, ngaytao);
            dao_hdb.insert(model);

            for (SanPhamThuoc x : list) {
                HoaDonBanCT mode = getModel_hdbct(x.getMa_Thuoc(), x.getSoLuongTon(), x.getGiaBan());
                dao_hdbct.insert(mode);
                SanPhamThuoc spt = dao.findByIDdk(x.getMa_Thuoc());
                int updsl = spt.getSoLuongTon() - x.getSoLuongTon();
                dao.update_sl(updsl, x.getMa_Thuoc());

            }
            loadtodata();
            lbltongtien.setText("");
            txthoten.setText("");
            txtsodienthoai.setText("");
            txtsoluongban.setText("");
            int size = list.size();
            list.clear();
            dtngaytao.setDate(null);
            chkchechkkh.setSelected(false);
            modelhb.setRowCount(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + "Lỗi Hoàn Thành");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void chkchechkkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkchechkkhActionPerformed
        // TODO add your handling code here:
        if (chkchechkkh.isSelected()) {
            txtsodienthoai.setEditable(true);
        } else {
            txtsodienthoai.setEditable(false);
        }
    }//GEN-LAST:event_chkchechkkhActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "bạn chưa chọn thuốc cần nhập");
                return;
            }

            if (txtsoluongban.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Số Lượng Bán");
                txtsoluongban.requestFocus();
                return;
            }
            try {
                int slb = Integer.valueOf(txtsoluongban.getText());
                if (slb <= 0) {
                    JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Số Lượng > 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập Số Lượng Bán là số");
                return;
            }

            modelhb = (DefaultTableModel) tblhangban.getModel();
            int soluong = Integer.valueOf(txtsoluongban.getText());
            int mathuoc = Integer.valueOf(tblsanpham.getValueAt(index, 0).toString());
            int slht = Integer.valueOf(tblsanpham.getValueAt(index, 7).toString());
            SanPhamThuoc chk = dao.checksl(soluong, mathuoc);
            if (chk != null) {
                JOptionPane.showMessageDialog(this, "Số Lượng Tồn Của Thuốc Không Đủ, Hãy Nhập Lại Số Lượng Bán");
                return;
            }

            for (int i = 0; i < tblhangban.getRowCount(); i++) {
                if (mathuoc == Integer.valueOf(tblhangban.getValueAt(i, 0).toString())) {
                    JOptionPane.showMessageDialog(this, "Thuốc Cần Bán Đã Xuất Hiện, Không thể thêm , Bạn Hãy xóa hoặc cập nhật thuốc");
                    return;
                }
            }
            for (int i = 0; i < tblhangban.getRowCount(); i++) {
                if (mathuoc == Integer.valueOf(tblhangban.getValueAt(i, 0).toString())) {
                    tong = tong + Integer.valueOf(tblhangban.getValueAt(i, 3).toString());
                }
            }
            if (soluong + tong > slht) {
                JOptionPane.showMessageDialog(this, "Tổng Số Lượng Bán Lớn Hơn Số Lượng Hiện Có");
                return;
            }
            float giaban = Float.valueOf(tblsanpham.getValueAt(index, 6).toString());
            Date hsd = Datehelper.toDate(tblsanpham.getValueAt(index, 4).toString());
            list.add(new SanPhamThuoc(mathuoc, giaban, soluong, hsd));
            modelhb.addRow(new Object[]{tblsanpham.getValueAt(index, 0).toString(),
                tblsanpham.getValueAt(index, 1).toString(),
                tblsanpham.getValueAt(index, 6).toString(),
                txtsoluongban.getText(), tblsanpham.getValueAt(index, 4).toString()});
            float tiensl = giaban * soluong;
            float tongtien = Float.valueOf(lbltongtien.getText());
            tongtien = tongtien + tiensl;
            lbltongtien.setText(String.valueOf(tongtien));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + " lỗi");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblhangbanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhangbanMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblhangbanMouseEntered

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {
            if (tblhangban.getSelectedRow() < 0) {
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


    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtsodienthoaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsodienthoaiKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtsodienthoaiKeyTyped

    private void btntimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimActionPerformed
        // TODO add your handling code here:
        if (chkchechkkh.isSelected()) {
            if (txtsodienthoai.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập SDT Khách Hàng");
                return;
            }
            if (txtsodienthoai.getText().length() > 10) {
                JOptionPane.showMessageDialog(this, "Số Điện Thoại chỉ có 10 số bạn nhé");
                return;
            }

            sdt = txtsodienthoai.getText();
            KhachHang chkkh = dao_kh.findByID(sdt);
            if (chkkh == null) {
                JOptionPane.showMessageDialog(this, "Khách Hàng chưa tồn tại, bạn kiểm tra lại số điện thoại khách hàng");
                return;
            }

            txthoten.setText(chkkh.getTen_KH());
        }
    }//GEN-LAST:event_btntimActionPerformed

    private void txtsoluongbanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsoluongbanKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtsoluongbanKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            if (index2 < 0) {
                JOptionPane.showMessageDialog(this, "bạn chưa chọn thuốc cần Cập Nhật");
                return;
            }

            if (txtsoluongban.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Số Lượng Bán");
                txtsoluongban.requestFocus();
                return;
            }
            try {
                int slb = Integer.valueOf(txtsoluongban.getText());
                if (slb <= 0) {
                    JOptionPane.showMessageDialog(this, "Bạn Phải Nhập Số Lượng > 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập Số Lượng Bán là số");
                return;
            }

            modelhb = (DefaultTableModel) tblhangban.getModel();
            int soluongm = Integer.valueOf(txtsoluongban.getText());
            int mathuoc = list.get(index2).getMa_Thuoc();
            int slht = Integer.valueOf(tblsanpham.getValueAt(index, 7).toString());
            SanPhamThuoc chk = dao.checksl(soluongm, mathuoc);
            if (chk != null) {
                JOptionPane.showMessageDialog(this, "Số Lượng Tồn Của Thuốc Không Đủ, Hãy Nhập Lại Số Lượng Bán");
                return;
            }
            SanPhamThuoc chkht = dao.findbySLHT(soluongm, mathuoc);
            if (chkht != null) {
                JOptionPane.showMessageDialog(this, "Số Lượng Bán Lớn Hơn Số Lượng Tồn của Thuốc, Hãy Nhập Lại Số Lượng Bán");
                return;
            }

            float gbc = list.get(index2).getGiaBan();
            int slc = list.get(index2).getSoLuongTon();
            float tonggiacu = gbc * slc;
            float tonghientai = Float.valueOf(lbltongtien.getText());
            float tienmoi = soluongm * gbc;
            float conlai = tonghientai - tonggiacu;
            float giacuoi = conlai + tienmoi;
            lbltongtien.setText(String.valueOf(giacuoi));
            list.get(index2).setSoLuongTon(soluongm);
            modelhb.setValueAt(soluongm, index2, 3);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + " lỗi");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtsodienthoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsodienthoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsodienthoaiActionPerformed

    private void tblsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsanphamMouseClicked
        // TODO add your handling code here:
        index = tblsanpham.getSelectedRow();
        
    }//GEN-LAST:event_tblsanphamMouseClicked

    private void remove() {
        if (index2 >= 0) {
            float giaban = Float.valueOf(tblhangban.getValueAt(index2, 2).toString());
            int sl = list.get(index2).getSoLuongTon();
            float giatru = giaban * sl;
            float tonggia = Float.valueOf(lbltongtien.getText());
            float giamoi = tonggia - giatru;
            lbltongtien.setText(String.valueOf(giamoi));
            list.remove(index2);
            modelhb.removeRow(index2);
        } else {
            JOptionPane.showMessageDialog(this, "Không Còn gì để xóa");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btntim;
    private javax.swing.JCheckBox chkchechkkh;
    private com.toedter.calendar.JDateChooser dtngaytao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbltongtien;
    private javax.swing.JTable tblhangban;
    private javax.swing.JTable tblsanpham;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtsodienthoai;
    private javax.swing.JTextField txtsoluongban;
    // End of variables declaration//GEN-END:variables
}
