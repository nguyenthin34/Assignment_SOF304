/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.HoaDonBanCTDAO;
import com.poly.dao.HoaDonBanHangDAO;
import com.poly.dao.HoaDonTraHangDAO;
import com.poly.dao.SanPhamThuocDAO;
import com.poly.helper.ShareHepler;
import com.poly.model.HoaDonBan;
import com.poly.model.HoaDonBanCT;
import com.poly.model.HoaDonTraHang;
import com.poly.model.SanPhamThuoc;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmTraHang extends javax.swing.JPanel {

    int index;
    int index2 = - 1;
    int index3 = -1;
    int tongslt;
    HoaDonBanHangDAO dao = new HoaDonBanHangDAO();
    HoaDonBanCTDAO dao_ct = new HoaDonBanCTDAO();
    HoaDonTraHangDAO dao_th = new HoaDonTraHangDAO();
    List<HoaDonBanCT> list_hdbct = new ArrayList<>();
    SanPhamThuocDAO dao_sp = new SanPhamThuocDAO();
    DefaultTableModel model_dstra;
    float tongtientra;
    int tong = 0;
    DefaultTableModel model_dsht;

    public frmTraHang() {
        initComponents();
        loadtodata();
    }

    private void loadtodatads() {

        DefaultTableModel model = (DefaultTableModel) tblhobct.getModel();
        model.setRowCount(0);

        List<HoaDonBanCT> list = dao_ct.select(Integer.valueOf(tbltrahang.getValueAt(index, 0).toString()));
        if (list == null) {
            JOptionPane.showMessageDialog(this, "Hóa Đơn Không Tồn Tại Sản Phẩm");
            return;
        }
        try {
            for (HoaDonBanCT x : list) {
                Vector v = new Vector();
                v.add(x.getID_HDB());
                v.add(x.getMa_Thuoc());
                v.add(x.getSoLuong());
                v.add(x.getGiaBan());
                model.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    public void loadform(String id, String gia, String mathuoc, String soluong) {
        txtgiaban.setText(gia);
        txtidhdb.setText(id);
        txtmathuoc.setText(mathuoc);
        txtsoluong.setText(soluong);
    }

    private void loadtodata() {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tbltrahang.getModel();
            List<HoaDonBan> list = dao.select2();
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có Hóa Đơn Bán nào");
                return;
            }
            model.setRowCount(0);
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
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void remove() {
        if (index3 >= 0) {
            model_dstra = (DefaultTableModel) tbldstra.getModel();
            float giaban = Float.valueOf(tbldstra.getValueAt(index3, 3).toString());
            int sl = Integer.valueOf(tbldstra.getValueAt(index3, 2).toString());
            float giatru = giaban * sl;
            float tonggia = Float.valueOf(lbltienhoantra.getText());
            float giamoi = tonggia - giatru;
            lbltienhoantra.setText(String.valueOf(giamoi));
            list_hdbct.remove(index3);
            model_dstra.removeRow(index3);
        } else {
            JOptionPane.showMessageDialog(this, "Không Còn gì để xóa");
        }
    }

    private HoaDonTraHang getModel_hdth() {
        HoaDonTraHang model = new HoaDonTraHang();
        model.setID_HDB(Integer.valueOf(lblidhdb.getText()));
        model.setTienCanTra(Float.valueOf(lbltienhoantra.getText()));
        model.setNgayTao(dtngaytao.getDate());
        model.setNguoiTao(ShareHepler.mand);
        model.setMoTa(txtmota.getText());
        return model;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbltrahang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtmathuoc = new javax.swing.JTextField();
        txtidhdb = new javax.swing.JTextField();
        txtsoluong = new javax.swing.JTextField();
        txtgiaban = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldstra = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbltenkhachhang = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblidhdb = new javax.swing.JLabel();
        lbltiendathanhtoan = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbltienhoantra = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        dtngaytao = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtmota = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblhobct = new javax.swing.JTable();

        setBackground(new java.awt.Color(30, 165, 252));

        tbltrahang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Hóa Đơn Bán", "Số Điện Thoại Khách Hàng", "Tổng Tiền", "Ngày Lập Hóa Đơn", "Người Tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbltrahang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltrahangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbltrahang);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtmathuoc.setEditable(false);
        txtmathuoc.setBackground(new java.awt.Color(255, 255, 255));

        txtidhdb.setEditable(false);
        txtidhdb.setBackground(new java.awt.Color(255, 255, 255));

        txtsoluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsoluongActionPerformed(evt);
            }
        });
        txtsoluong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsoluongKeyTyped(evt);
            }
        });

        txtgiaban.setEditable(false);
        txtgiaban.setBackground(new java.awt.Color(255, 255, 255));
        txtgiaban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgiabanActionPerformed(evt);
            }
        });
        txtgiaban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtgiabanKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtgiabanKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Mã Thuốc");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID Hóa Đơn Bán");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Số Lượng");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Giá Bán");

        jButton1.setBackground(new java.awt.Color(30, 165, 252));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Lưu Tạm");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(30, 165, 252));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cập Nhật");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(30, 165, 252));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Xóa");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmathuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidhdb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmathuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidhdb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbldstra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Hóa Đơn Bán", "Mã Thuốc", "Số Lượng", "Giá Bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldstra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldstraMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbldstra);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Khách Hàng");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Tiền Đã Thanh Toán");

        lbltenkhachhang.setBackground(new java.awt.Color(255, 255, 255));
        lbltenkhachhang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbltenkhachhang.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("ID Hóa Đơn Bán");

        lblidhdb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblidhdb.setText("0");

        lbltiendathanhtoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbltiendathanhtoan.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tổng Tiền Hoàn Trả");

        lbltienhoantra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbltienhoantra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltienhoantra.setText("0");

        jButton3.setBackground(new java.awt.Color(30, 165, 252));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Hoàn Trả");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Ngày Tạo");

        dtngaytao.setDateFormatString("yyyy-MM-dd");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Mô Tả");

        txtmota.setColumns(20);
        txtmota.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtmota.setLineWrap(true);
        txtmota.setRows(5);
        txtmota.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtmota);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblidhdb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbltenkhachhang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbltiendathanhtoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbltienhoantra, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbltenkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblidhdb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltiendathanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltienhoantra, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dtngaytao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblhobct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Hóa Đơn Bán", "Mã Thuốc", "Số Lượng", "Giá Bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblhobct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhobctMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblhobctMouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(tblhobct);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbltrahangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltrahangMouseClicked
        // TODO add your handling code here:
        try {
            index = tbltrahang.getSelectedRow();
            if (evt.getClickCount() == 2) {
                loadtodatads();
                if (tbltrahang.getValueAt(index, 1) == null) {
                    lbltenkhachhang.setText("");
                } else {
                    lbltenkhachhang.setText(tbltrahang.getValueAt(index, 1).toString());
                }

                lblidhdb.setText(tbltrahang.getValueAt(index, 0).toString());
                lbltiendathanhtoan.setText(tbltrahang.getValueAt(index, 2).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_tbltrahangMouseClicked

    private void txtgiabanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgiabanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgiabanKeyPressed

    private void txtgiabanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgiabanKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtgiabanKeyTyped

    private void txtsoluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsoluongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsoluongActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            model_dsht = (DefaultTableModel) tblhobct.getModel();
            if (list_hdbct.size() == 0) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Sản Phẩm Nào Để Trả");
                return;
            }

            if (dtngaytao.getDate() == null) {
                JOptionPane.showMessageDialog(this, "hãy chọn ngày hoàn trả");
                return;
            }
            HoaDonTraHang model = getModel_hdth();
            dao_th.insert(model);
            for (HoaDonBanCT x : list_hdbct) {
                SanPhamThuoc sp = dao_sp.findByID(x.getMa_Thuoc());
                int slm = sp.getSoLuongTon() + x.getSoLuong();
                dao_sp.update_sl(slm, x.getMa_Thuoc());
            }

            lblidhdb.setText("");
            lbltenkhachhang.setText("");
            lbltiendathanhtoan.setText("0");
            lbltienhoantra.setText("0");
            txtgiaban.setText("");
            txtidhdb.setText("");
            txtmathuoc.setText("");
            txtmota.setText("");
            txtsoluong.setText("");
            dtngaytao.setDate(null);
            model_dstra.setRowCount(0);
            model_dsht.setRowCount(0);
            list_hdbct.clear();

            loadtodata();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Trả Hàng Thất Bại");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblhobctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhobctMouseClicked
        // TODO add your handling code here:
        index2 = tblhobct.getSelectedRow();
        if (evt.getClickCount() == 2) {
            String id = tblhobct.getValueAt(index2, 0).toString();
            String soluong = tblhobct.getValueAt(index2, 2).toString();
            String mathuoc = tblhobct.getValueAt(index2, 1).toString();
            String gia = tblhobct.getValueAt(index2, 3).toString();
            txtidhdb.setText(id);
            txtgiaban.setText(gia);
            txtmathuoc.setText(mathuoc);
            txtsoluong.setText(soluong);

        }
    }//GEN-LAST:event_tblhobctMouseClicked

    private void tblhobctMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhobctMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblhobctMouseEntered

    private void txtsoluongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsoluongKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtsoluongKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (index2 < 0) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Sản Phẩm Trả");
            return;
        }
        try {
            tong = 0;
            model_dstra = (DefaultTableModel) tbldstra.getModel();
            int idhdb = Integer.valueOf(txtidhdb.getText());
            int ma = Integer.valueOf(txtmathuoc.getText());
            float giaban = Float.valueOf(txtgiaban.getText());
            int slt = Integer.valueOf(txtsoluong.getText());
            int slht = Integer.valueOf(tblhobct.getValueAt(index2, 2).toString());
            if (txtsoluong.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập Số Lượng Trả");
                return;
            }
            if (slt > slht) {
                JOptionPane.showMessageDialog(this, "Số Lượng Trả Lớn Hơn Số Lượng Bán");
                return;
            }

            for (int i = 0; i < tbldstra.getRowCount(); i++) {
                if (ma == Integer.valueOf(tbldstra.getValueAt(i, 1).toString())) {
                    JOptionPane.showMessageDialog(this, "Thuốc Cần Trả Đã Xuất Hiện, Không thể thêm , Bạn Hãy xóa hoặc cập nhật thuốc");
                    return;
                }
            }
            for (int i = 0; i < tbldstra.getRowCount(); i++) {
                if (ma == Integer.valueOf(tbldstra.getValueAt(i, 1).toString())) {
                    tong = tong + Integer.valueOf(tbldstra.getValueAt(i, 2).toString());
                }
            }

            if (slt + tong > slht) {
                JOptionPane.showMessageDialog(this, "Tổng Số Lượng Trả Lớn Hơn Số Lượng Bán");
                return;
            }

            list_hdbct.add(new HoaDonBanCT(idhdb, ma, slt, giaban));
            model_dstra.addRow(new Object[]{idhdb, ma, slt, giaban});
            float tt = Float.valueOf(lbltienhoantra.getText());
            tt = tt + (giaban * slt);
            lbltienhoantra.setText(String.valueOf(tt));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + " Lỗi Thêm");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        try {
            if (index3 < 0) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Hàng Muốn Xóa");
                return;
            }
            // kiểm tra xem còn gì để xóa không
            if (list_hdbct.size() == 0) {
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
            JOptionPane.showMessageDialog(this, "lỗi xóa " + e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbldstraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldstraMouseClicked
        // TODO add your handling code here:
        index3 = tbldstra.getSelectedRow();
        if (index3 >= 0) {
            txtsoluong.setText(String.valueOf(list_hdbct.get(index3).getSoLuong()));
            txtgiaban.setText(String.valueOf(list_hdbct.get(index3).getGiaBan()));
            txtidhdb.setText(String.valueOf(list_hdbct.get(index3).getID_HDB()));
            txtmathuoc.setText(String.valueOf(list_hdbct.get(index3).getMa_Thuoc()));
        }
    }//GEN-LAST:event_tbldstraMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (index3 < 0) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Sản Phẩm Cần Cập Nhật");
            return;
        }
        try {
            tong = 0;
            model_dstra = (DefaultTableModel) tbldstra.getModel();
            int idhdb = Integer.valueOf(txtidhdb.getText());
            int ma = Integer.valueOf(txtmathuoc.getText());
            float giaban = Float.valueOf(txtgiaban.getText());
            int slm = Integer.valueOf(txtsoluong.getText());
            int slht = Integer.valueOf(tblhobct.getValueAt(index2, 2).toString());
            if (txtsoluong.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập Số Lượng Trả");
                return;
            }
            if (slm > slht) {
                JOptionPane.showMessageDialog(this, "Số Lượng Trả Lớn Hơn Số Lượng Bán");
                return;
            }

            float tt = Float.valueOf(lbltienhoantra.getText());
            float slc = list_hdbct.get(index3).getSoLuong();
            float gbc = list_hdbct.get(index3).getGiaBan();
            float tonggiacu = slc * gbc;
            float tonggiamoi = slm * giaban;
            float conlai = tt - tonggiacu;
            float tienmoi = conlai + tonggiamoi;
            lbltienhoantra.setText(String.valueOf(tienmoi));
            model_dsht = (DefaultTableModel) tbldstra.getModel();
            list_hdbct.get(index3).setSoLuong(slm);
            model_dsht.setValueAt(slm, index3, 2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + " Lỗi Cập Nhật");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtgiabanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgiabanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgiabanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dtngaytao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblidhdb;
    private javax.swing.JLabel lbltenkhachhang;
    private javax.swing.JLabel lbltiendathanhtoan;
    private javax.swing.JLabel lbltienhoantra;
    private javax.swing.JTable tbldstra;
    private javax.swing.JTable tblhobct;
    private javax.swing.JTable tbltrahang;
    private javax.swing.JTextField txtgiaban;
    private javax.swing.JTextField txtidhdb;
    private javax.swing.JTextField txtmathuoc;
    private javax.swing.JTextArea txtmota;
    private javax.swing.JTextField txtsoluong;
    // End of variables declaration//GEN-END:variables
}
