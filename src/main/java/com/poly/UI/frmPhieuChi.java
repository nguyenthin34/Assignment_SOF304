/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.HoaDonTraHangDAO;
import com.poly.dao.PCNhapHangCTDAO;
import com.poly.dao.PCTraThuocCTDAO;
import com.poly.dao.PhieuChiDAO;
import com.poly.model.PhieuChi;
import com.poly.dao.PhieuNhapHangDAO;
import com.poly.helper.Datehelper;
import com.poly.helper.ShareHepler;
import com.poly.model.HoaDonTraHang;
import com.poly.model.PCNhapHangCT;
import com.poly.model.PCTraThuocCT;
import com.poly.model.PhieuNhapHang;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author PC HP
 */
public class frmPhieuChi extends javax.swing.JFrame {

    HoaDonTraHangDAO dao_hdt = new HoaDonTraHangDAO();
    PhieuNhapHangDAO dao_pn = new PhieuNhapHangDAO();
    PCTraThuocCTDAO dao_pcttct = new PCTraThuocCTDAO();
    PCNhapHangCTDAO dao_pcnhct = new PCNhapHangCTDAO();
    PhieuChiDAO dao_pc = new PhieuChiDAO();
    List<PhieuNhapHang> list_nh = new ArrayList<>();
    List<HoaDonTraHang> list_th = new ArrayList<>();
    int tong;
    int tong2;

    public frmPhieuChi() {
        initComponents();
        setLocationRelativeTo(null);
        loadtodata1();
        loadtodata2();
        btnxacnhan.setEnabled(false);
        btnxacnhan1.setEnabled(false);
        txtnguoitao.setText(ShareHepler.mand);
    }

    private void loadtodata1() {
        DefaultTableModel model = (DefaultTableModel) tblhoadontra.getModel();
        model.setRowCount(0);

        List<HoaDonTraHang> list = dao_hdt.select();
        if (list == null) {
            JOptionPane.showMessageDialog(this, "Không có hóa đơn nào");
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
    }

    private void loadtodata2() {
        DefaultTableModel model = (DefaultTableModel) tblphieunhap.getModel();
        model.setRowCount(0);

        List<PhieuNhapHang> list = dao_pn.select1();
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

    private PCTraThuocCT getModel_pcth(int id, float tien) {
        PhieuChi pc = dao_pc.select1();
        PCTraThuocCT model = new PCTraThuocCT();
        model.setMa_PC(pc.getMa_PC());
        model.setID_HDTH(id);
        model.setHDTH_Tien(tien);
        return model;
    }

    private PCNhapHangCT getModel_pcnh(int id, float tien) {
        PhieuChi pc = dao_pc.select1();
        PCNhapHangCT model = new PCNhapHangCT();
        model.setMa_PC(pc.getMa_PC());
        model.setID_HDN(id);
        model.setHDN_Tien(tien);
        return model;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblhoadontra = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblphieunhap = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txttienchinhap = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txttienchitra = new javax.swing.JTextField();
        btnxacnhan = new javax.swing.JButton();
        btnxacnhan1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        dtngaytao = new com.toedter.calendar.JDateChooser();
        txtnguoitao = new javax.swing.JTextField();
        txttongtienchi = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(30, 165, 252));
        setForeground(new java.awt.Color(255, 255, 255));

        tblhoadontra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID HD Trả Hàng", "ID Hóa Đơn Bán", "Tiền Trả", "Ngày Lập Phiếu", "Người Tạo", "Mô Tả", "Lựa Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblhoadontra.setGridColor(new java.awt.Color(255, 255, 255));
        tblhoadontra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhoadontraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblhoadontra);

        tblphieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Phiếu Nhập", "ID Nhà Cung Cấp", "Tổng Tiền", "Ngày Lập Phiếu", "Người Tạo", "Lựa Chọn"
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
        tblphieunhap.setGridColor(new java.awt.Color(255, 255, 255));
        tblphieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblphieunhapMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblphieunhap);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Tiền Chi Nhập Hàng");

        txttienchinhap.setEditable(false);
        txttienchinhap.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Tiền Chi Trả Hàng");

        txttienchitra.setEditable(false);
        txttienchitra.setBackground(new java.awt.Color(255, 255, 255));
        txttienchitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttienchitraActionPerformed(evt);
            }
        });

        btnxacnhan.setBackground(new java.awt.Color(30, 165, 252));
        btnxacnhan.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnxacnhan.setForeground(new java.awt.Color(255, 255, 255));
        btnxacnhan.setText("Xác Nhận Chọn Dòng");
        btnxacnhan.setBorderPainted(false);
        btnxacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxacnhanActionPerformed(evt);
            }
        });

        btnxacnhan1.setBackground(new java.awt.Color(30, 165, 252));
        btnxacnhan1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnxacnhan1.setForeground(new java.awt.Color(255, 255, 255));
        btnxacnhan1.setText("Xác Nhận Chọn Dòng");
        btnxacnhan1.setBorderPainted(false);
        btnxacnhan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxacnhan1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dtngaytao.setDateFormatString("yyyy-MM-dd");

        txtnguoitao.setEditable(false);
        txtnguoitao.setBackground(new java.awt.Color(255, 255, 255));

        txttongtienchi.setEditable(false);
        txttongtienchi.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(30, 165, 252));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tạo Phiếu");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Ngày Tạo");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Người Tạo");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tổng Tiền Chi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnguoitao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txttongtienchi, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtngaytao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnguoitao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttongtienchi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttienchinhap, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138)
                                .addComponent(btnxacnhan))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttienchitra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138)
                                .addComponent(btnxacnhan1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttienchinhap, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnxacnhan1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttienchitra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblhoadontraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhoadontraMouseClicked
        // TODO add your handling code here:
        int in = tblhoadontra.getRowCount();
        for (int i = 0; i < in; i++) {
            String matKhau = String.valueOf(tblhoadontra.getValueAt(i, 6));
            boolean mk = Boolean.valueOf(matKhau);
            if (mk == true) {
                btnxacnhan1.setEnabled(true);
                return;
            } else {
                btnxacnhan1.setEnabled(false);
                txttienchitra.setText("");
                txttongtienchi.setText("");

            }
        }
    }//GEN-LAST:event_tblhoadontraMouseClicked

    private void tblphieunhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblphieunhapMouseClicked
        // TODO add your handling code here:
        int in = tblphieunhap.getRowCount();
        for (int i = 0; i < in; i++) {
            String matKhau = String.valueOf(tblphieunhap.getValueAt(i, 5));
            boolean mk = Boolean.valueOf(matKhau);
            if (mk == true) {
                btnxacnhan.setEnabled(true);
                return;
            } else {
                btnxacnhan.setEnabled(false);
                txttienchinhap.setText("");
                txttongtienchi.setText("");

            }
        }
    }//GEN-LAST:event_tblphieunhapMouseClicked

    private void btnxacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhanActionPerformed
        // TODO add your handling code here:
        try {
            int in = tblphieunhap.getRowCount();
            list_nh.clear();
            tong = 0;
            for (int i = 0; i < in; i++) {
                String matKhau = String.valueOf(tblphieunhap.getValueAt(i, 5));
                boolean mk = Boolean.valueOf(matKhau);
                if (mk == true) {
                    int id = Integer.valueOf(tblphieunhap.getValueAt(i, 0).toString());
                    String idncc = tblphieunhap.getValueAt(i, 1).toString();
                    float tongtien = Float.valueOf(tblphieunhap.getValueAt(i, 2).toString());
                    Date NgayLap = Datehelper.toDate(tblphieunhap.getValueAt(i, 3).toString());
                    String NguoiTao = tblphieunhap.getValueAt(i, 4).toString();
                    list_nh.add(new PhieuNhapHang(id, idncc, tongtien, NgayLap, NguoiTao));

                }
            }

            for (PhieuNhapHang x : list_nh) {
                tong += x.getTongTienHang();
            }
            txttienchinhap.setText(String.valueOf(tong));
            if (txttienchitra.getText().equals("")) {
                tong2 = 0;
                float tongall = tong2 + tong;
                txttongtienchi.setText(String.valueOf(tongall));
            } else {
                float tongall = tong2 + tong;
                txttongtienchi.setText(String.valueOf(tongall));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Xác Nhận " + e);
        }
    }//GEN-LAST:event_btnxacnhanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {

            if (txttienchinhap.getText().equals("") && txttienchitra.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Phải có ít nhất 1 trong 2 loại tiền chi có giá trị > 0");
                return;
            }
            if (dtngaytao.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Ngày tạo");
                return;
            }

            if (txttongtienchi.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Tổng Tiền Chi Rỗng");
                return;
            }
            if (!txttienchinhap.getText().equals("") && !txttienchitra.getText().equals("")) {
                try {
                    float tiennhap = Float.valueOf(txttienchinhap.getText());
                    float tientra = Float.valueOf(txttienchitra.getText());
                    Date ngaytao = dtngaytao.getDate();
                    String nguoilap = txtnguoitao.getText();
                    float tongtienchi = tiennhap + tientra;
                    PhieuChi md = new PhieuChi();
                    md.setNgayLapPC(ngaytao);
                    md.setNguoiThu(nguoilap);
                    md.setTienNhapthuoc(tiennhap);
                    md.setTienTraHang(tientra);
                    md.setTongTienChi(tongtienchi);
                    dao_pc.insert(md);
                    for (PhieuNhapHang x : list_nh) {
                        PCNhapHangCT pcn = getModel_pcnh(x.getID_HDN(), x.getTongTienHang());
                        dao_pcnhct.insert(pcn);
                    }
                    for (HoaDonTraHang x : list_th) {
                        PCTraThuocCT ptt = getModel_pcth(x.getID_HDTH(), x.getTienCanTra());
                        dao_pcttct.insert(ptt);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
            } else if (txttienchinhap.getText().equals("") && !txttienchitra.getText().equals("")) {
                try {
                    float tiennhap = Float.valueOf(txttienchinhap.getText());
                    float tientra = Float.valueOf(txttienchitra.getText());
                    Date ngaytao = dtngaytao.getDate();
                    String nguoilap = txtnguoitao.getText();
                    float tongtienchi = tiennhap + tientra;
                    PhieuChi md = new PhieuChi();
                    md.setNgayLapPC(ngaytao);
                    md.setNguoiThu(nguoilap);
                    md.setTienNhapthuoc(tiennhap);
                    md.setTienTraHang(tientra);
                    md.setTongTienChi(tongtienchi);
                    dao_pc.insert(md);

                    for (HoaDonTraHang x : list_th) {
                        PCTraThuocCT ptt = getModel_pcth(x.getID_HDTH(), x.getTienCanTra());
                        dao_pcttct.insert(ptt);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
            } else if (!txttienchinhap.getText().equals("") && txttienchitra.getText().equals("")) {
                try {
                    float tiennhap = Float.valueOf(txttienchinhap.getText());
                    float tientra = Float.valueOf(txttienchitra.getText());
                    Date ngaytao = dtngaytao.getDate();
                    String nguoilap = txtnguoitao.getText();
                    float tongtienchi = tiennhap + tientra;
                    PhieuChi md = new PhieuChi();
                    md.setNgayLapPC(ngaytao);
                    md.setNguoiThu(nguoilap);
                    md.setTienNhapthuoc(tiennhap);
                    md.setTienTraHang(tientra);
                    md.setTongTienChi(tongtienchi);
                    dao_pc.insert(md);

                    for (PhieuNhapHang x : list_nh) {
                        PCNhapHangCT pcn = getModel_pcnh(x.getID_HDN(), x.getTongTienHang());
                        dao_pcnhct.insert(pcn);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,e);
                }

            }
            loadtodata1();
            loadtodata1();
            txttienchinhap.setText("");
            txttienchitra.setText("");
            txttongtienchi.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnxacnhan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxacnhan1ActionPerformed
        // TODO add your handling code here:
        try {
            int in = tblhoadontra.getRowCount();
            list_th.clear();
            tong2 = 0;
            for (int i = 0; i < in; i++) {
                String matKhau = String.valueOf(tblhoadontra.getValueAt(i, 6));
                boolean mk = Boolean.valueOf(matKhau);
                if (mk == true) {
                    if (tblhoadontra.getValueAt(i, 5) == null) {
                        int id = Integer.valueOf(tblhoadontra.getValueAt(i, 0).toString());
                        int idhdb = Integer.valueOf(tblhoadontra.getValueAt(i, 1).toString());
                        float tongtien = Float.valueOf(tblhoadontra.getValueAt(i, 2).toString());
                        Date NgayLap = Datehelper.toDate(tblhoadontra.getValueAt(i, 3).toString());
                        String NguoiTao = tblhoadontra.getValueAt(i, 4).toString();
                        String mota = null;
                        list_th.add(new HoaDonTraHang(id, idhdb, tongtien, NgayLap, NguoiTao, mota));
                    } else {
                        int id = Integer.valueOf(tblhoadontra.getValueAt(i, 0).toString());
                        int idhdb = Integer.valueOf(tblhoadontra.getValueAt(i, 1).toString());
                        float tongtien = Float.valueOf(tblhoadontra.getValueAt(i, 2).toString());
                        Date NgayLap = Datehelper.toDate(tblhoadontra.getValueAt(i, 3).toString());
                        String NguoiTao = tblhoadontra.getValueAt(i, 4).toString();
                        String mota = tblhoadontra.getValueAt(i, 5).toString();
                        list_th.add(new HoaDonTraHang(id, idhdb, tongtien, NgayLap, NguoiTao, mota));
                    }

                }
            }

            for (HoaDonTraHang x : list_th) {
                tong2 += x.getTienCanTra();
            }
            txttienchitra.setText(String.valueOf(tong2));

            if (txttienchinhap.getText().equals("")) {
                tong = 0;
                float tongall = tong2 + tong;
                txttongtienchi.setText(String.valueOf(tongall));
            } else {
                float tongall = tong2 + tong;
                txttongtienchi.setText(String.valueOf(tongall));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Xác Nhận " + e);
        }
    }//GEN-LAST:event_btnxacnhan1ActionPerformed

    private void txttienchitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttienchitraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttienchitraActionPerformed

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
            java.util.logging.Logger.getLogger(frmPhieuChi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPhieuChi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPhieuChi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPhieuChi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPhieuChi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnxacnhan;
    private javax.swing.JButton btnxacnhan1;
    private com.toedter.calendar.JDateChooser dtngaytao;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblhoadontra;
    private javax.swing.JTable tblphieunhap;
    private javax.swing.JTextField txtnguoitao;
    private javax.swing.JTextField txttienchinhap;
    private javax.swing.JTextField txttienchitra;
    private javax.swing.JTextField txttongtienchi;
    // End of variables declaration//GEN-END:variables
}
