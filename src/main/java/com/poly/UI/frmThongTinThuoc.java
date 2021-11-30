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
import com.poly.model.Thuoc;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import com.poly.model.NhomThuoc;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class frmThongTinThuoc extends javax.swing.JPanel {

    ThuocDAO dao = new ThuocDAO();
    NhomThuocDAO dao_NT = new NhomThuocDAO();
    DonViTinhDAO dao_dvt = new DonViTinhDAO();
    DefaultComboBoxModel<NhomThuoc> modelcbo1;
    DefaultComboBoxModel<NhomThuoc> modelcbo2;
    DefaultComboBoxModel<DonViTinh> modelcbo;
    int index;
    List combo = new ArrayList();
    List combo2 = new ArrayList();

    public frmThongTinThuoc() throws Exception {
        initComponents();
        checklogin();
        loadtocbo1();
        loadtocbo();
    }

    public void checklogin() {
        if (ShareHepler.User == 1) {
            btnthem.setEnabled(true);
            btncapnhat.setEnabled(true);
            btnlammoi.setEnabled(true);
            btnlaydanhsachThuoc.setEnabled(true);
        } else if (ShareHepler.User == 0) {
            btnthem.setEnabled(false);
            btncapnhat.setEnabled(false);
            btnlammoi.setEnabled(true);
            btnlaydanhsachThuoc.setEnabled(true);
        } else {
            btnthem.setEnabled(false);
            btncapnhat.setEnabled(false);
            btnlammoi.setEnabled(false);
            btnlaydanhsachThuoc.setEnabled(false);
        }
    }

    private void loadtocbo1() {
        cbonhomthuoc.removeAllItems();
        modelcbo1 = (DefaultComboBoxModel) cbonhomthuoc.getModel();
        modelcbo2 = (DefaultComboBoxModel) cbonhomthuoc2.getModel();
        List<NhomThuoc> list = dao_NT.findByTT();
        for (NhomThuoc x : list) {
            NhomThuoc ph = new NhomThuoc(x.getID_Nhom(), x.getTen_Nhom(), x.isTrangThai());
            combo.add(x.getTen_Nhom());
            modelcbo1.addElement(ph);
            modelcbo2.addElement(ph);
        }
    }

    private void insert() {
        if (checkrongTenThuoc()) {
            return;
        }
        if (checkrongHamLuong()) {
            return;
        }
        if (checkrongHoatChat()) {
            return;
        }
        if (checkrongCachDung()) {
            return;
        }
        if (checkrongTNoiSanXuat()) {
            return;
        }
        if (checkrongDongGoi()) {
            return;
        }
        
        try {
            Thuoc model = getModel();
            dao.insert(model);
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void update() {
        if (checkrongIDThuoc()) {
            return;
        }
        if (checkrongTenThuoc()) {
            return;
        }
        try {
            Thuoc model = getModel_update();
            dao.update(model);
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void edit_HH() {
        try {
            Thuoc model = dao.findByID(Integer.valueOf(tblttthuoc.getValueAt(index, 0).toString()));
            if (model != null) {
                setModel(model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void loadtocbo() {
        cbodonvitinh.removeAllItems();
        modelcbo = (DefaultComboBoxModel) cbodonvitinh.getModel();
        List<DonViTinh> list = dao_dvt.findByTT();
        for (DonViTinh x : list) {
            DonViTinh ph = new DonViTinh(x.getID_DonViTinh(), x.getTen_DVT(), x.isTrangThai());
            combo2.add(x.getTen_DVT());
            modelcbo.addElement(ph);
        }
    }

    private void loadData() {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblttthuoc.getModel();
            List<Thuoc> list = dao.select();
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có thông tin thuốc nào");
                return;
            }
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

    private void setModel(Thuoc model) {
        txtIDThuoc.setText(String.valueOf(model.getID_Thuoc()));
        txttenthuoc.setText(model.getTen_Thuoc());
        cbodonvitinh.setSelectedIndex(combo2.indexOf(tblttthuoc.getValueAt(index, 3).toString()));
       
        cbonhomthuoc.setSelectedIndex(combo.indexOf(tblttthuoc.getValueAt(index, 2).toString()));
        txthamluong.setText(model.getHamLuong());
        txthoatchat.setText(model.getHoatChat());
        txtcachdung.setText(model.getCach_Dung());
        txtnoisanxuat.setText(model.getNoiSanXuat());
        txtdonggoi.setText(model.getDongGoi());
        if (model.isTrangThai()) {
            rdocon.setSelected(true);
        } else {
            rdohet.setSelected(true);
        }
    }

    private void reset() {
        setModel(new Thuoc());
        txtIDThuoc.setText(null);
        cbonhomthuoc.setSelectedItem(0);
        cbodonvitinh.setSelectedIndex(0);
    }

    public Thuoc getModel() {
        Thuoc model = new Thuoc();
        model.setTen_Thuoc(txttenthuoc.getText());
        int i = cbonhomthuoc.getSelectedIndex();
        model.setID_NhomThuoc(modelcbo1.getElementAt(i).getID_Nhom());
        int i1 = cbodonvitinh.getSelectedIndex();
        model.setID_DVT(modelcbo.getElementAt(i1).getID_DonViTinh());
        model.setHamLuong(txthamluong.getText());
        model.setHoatChat(txthoatchat.getText());
        model.setCach_Dung(txtcachdung.getText());
        model.setNoiSanXuat(txtnoisanxuat.getText());
        model.setDongGoi(txtdonggoi.getText());
        if (rdocon.isSelected()) {
            model.setTrangThai(true);
        } else {
            model.setTrangThai(false);
        }
       
        return model;
    }
    
     public Thuoc getModel_update() {
        Thuoc model = new Thuoc();
        model.setTen_Thuoc(txttenthuoc.getText());
        int i = cbonhomthuoc.getSelectedIndex();
        model.setID_NhomThuoc(modelcbo1.getElementAt(i).getID_Nhom());
        int i1 = cbodonvitinh.getSelectedIndex();
        model.setID_DVT(modelcbo.getElementAt(i1).getID_DonViTinh());
        model.setHamLuong(txthamluong.getText());
        model.setHoatChat(txthoatchat.getText());
        model.setCach_Dung(txtcachdung.getText());
        model.setNoiSanXuat(txtnoisanxuat.getText());
        model.setDongGoi(txtdonggoi.getText());
        if (rdocon.isSelected()) {
            model.setTrangThai(true);
        } else {
            model.setTrangThai(false);
        }
        model.setID_Thuoc(Integer.valueOf(txtIDThuoc.getText()
        ));
        return model;
    }
    public Thuoc getModel_edit() {
        Thuoc model = new Thuoc();
        model.setID_Thuoc(Integer.valueOf(txtIDThuoc.getText()));
        model.setTen_Thuoc(txttenthuoc.getText());
        int i = cbonhomthuoc.getSelectedIndex();
        model.setID_NhomThuoc(modelcbo1.getElementAt(i).getID_Nhom());
        int i1 = cbodonvitinh.getSelectedIndex();
        model.setID_DVT(modelcbo.getElementAt(i1).getID_DonViTinh());
        model.setHamLuong(txthamluong.getText());
        model.setHoatChat(txthoatchat.getText());
        model.setCach_Dung(txtcachdung.getText());
        model.setNoiSanXuat(txtnoisanxuat.getText());
        model.setDongGoi(txtdonggoi.getText());
        if (rdocon.isSelected()) {
            model.setTrangThai(true);
        } else {
            model.setTrangThai(false);
        }
        return model;
    }

    private boolean checkrongIDThuoc() {
        if (txtIDThuoc.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn ID Thuốc Để Cập Nhật");
            txtIDThuoc.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkrongTenThuoc() {
        if (txttenthuoc.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Tên Thuốc");
            txttenthuoc.requestFocus();
            return true;
        } else {
            return false;
        }
    }
    
     private boolean checkrongHamLuong() {
        if (txthamluong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Hàm Lượng");
            txthamluong.requestFocus();
            return true;
        } else {
            return false;
        }
    }
      private boolean checkrongHoatChat() {
        if (txthoatchat.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Hoạt Chất");
            txthoatchat.requestFocus();
            return true;
        } else {
            return false;
        }
    }
       private boolean checkrongCachDung() {
        if (txtcachdung.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Cách Dùng");
            txtcachdung.requestFocus();
            return true;
        } else {
            return false;
        }
    }
        private boolean checkrongTNoiSanXuat() {
        if (txtnoisanxuat.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Nơi Sản Xuất");
            txtnoisanxuat.requestFocus();
            return true;
        } else {
            return false;
        }
    }
    
      private boolean checkrongDongGoi() {
        if (txtdonggoi.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Cách Đóng Gói");
            txtdonggoi.requestFocus();
            return true;
        } else {
            return false;
        }
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tbpThongtinthuoc = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblttthuoc = new javax.swing.JTable();
        cbonhomthuoc2 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btnlaydanhsachThuoc = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtIDThuoc = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txttenthuoc = new javax.swing.JTextField();
        txtcachdung = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txthoatchat = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txthamluong = new javax.swing.JTextArea();
        txtdonggoi = new javax.swing.JTextField();
        txtnoisanxuat = new javax.swing.JTextField();
        cbonhomthuoc = new javax.swing.JComboBox<>();
        cbodonvitinh = new javax.swing.JComboBox<>();
        rdocon = new javax.swing.JRadioButton();
        rdohet = new javax.swing.JRadioButton();
        btnthem = new javax.swing.JButton();
        btncapnhat = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(30, 165, 252));

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

        cbonhomthuoc2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbonhomthuoc2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbonhomthuoc2ItemStateChanged(evt);
            }
        });
        cbonhomthuoc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbonhomthuoc2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tìm Kiếm theo nhóm Thuốc");

        btnlaydanhsachThuoc.setBackground(new java.awt.Color(255, 255, 255));
        btnlaydanhsachThuoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnlaydanhsachThuoc.setForeground(new java.awt.Color(30, 165, 252));
        btnlaydanhsachThuoc.setText("Lấy Danh Sách Thuốc");
        btnlaydanhsachThuoc.setBorderPainted(false);
        btnlaydanhsachThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlaydanhsachThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel11)
                        .addGap(49, 49, 49)
                        .addComponent(cbonhomthuoc2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(btnlaydanhsachThuoc)
                        .addGap(0, 61, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbonhomthuoc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlaydanhsachThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbpThongtinthuoc.addTab("Thông Tin", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtIDThuoc.setEditable(false);
        txtIDThuoc.setBackground(new java.awt.Color(255, 255, 255));
        txtIDThuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIDThuoc.setText("ID Thuốc Tự Tăng");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID Thuốc");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên Thuốc(*)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nhóm Thuốc(*)");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Đơn Vị Tính(*)");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Cách Dùng(*)");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Hoạt Chất(*)");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Hàm Lượng(*)");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Đóng Gói(*)");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Nơi Sản Xuất(*)");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Trạng Thái");

        txttenthuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtcachdung.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txthoatchat.setColumns(20);
        txthoatchat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txthoatchat.setLineWrap(true);
        txthoatchat.setRows(5);
        txthoatchat.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txthoatchat);

        txthamluong.setColumns(20);
        txthamluong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txthamluong.setLineWrap(true);
        txthamluong.setRows(5);
        txthamluong.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txthamluong);

        txtdonggoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtnoisanxuat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbonhomthuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbodonvitinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonGroup1.add(rdocon);
        rdocon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdocon.setText("Đang Kinh Doanh");

        buttonGroup1.add(rdohet);
        rdohet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdohet.setText("Ngừng Kinh Doanh");

        btnthem.setBackground(new java.awt.Color(30, 165, 252));
        btnthem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnthem.setForeground(new java.awt.Color(255, 255, 255));
        btnthem.setText("Thêm");
        btnthem.setBorderPainted(false);
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btncapnhat.setBackground(new java.awt.Color(30, 165, 252));
        btncapnhat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btncapnhat.setForeground(new java.awt.Color(255, 255, 255));
        btncapnhat.setText("Cập Nhật");
        btncapnhat.setBorderPainted(false);
        btncapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapnhatActionPerformed(evt);
            }
        });

        btnlammoi.setBackground(new java.awt.Color(30, 165, 252));
        btnlammoi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnlammoi.setForeground(new java.awt.Color(255, 255, 255));
        btnlammoi.setText("Làm Mới");
        btnlammoi.setBorderPainted(false);
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(btnlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(btncapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4)
                            .addComponent(txtIDThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(cbonhomthuoc, 0, 250, Short.MAX_VALUE)
                            .addComponent(txtcachdung, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtdonggoi, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdocon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdohet))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))
                                        .addGap(25, 25, 25)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3)
                                    .addComponent(txttenthuoc)
                                    .addComponent(cbodonvitinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtnoisanxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(53, 53, 53))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbonhomthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtcachdung, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtdonggoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txttenthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbodonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtnoisanxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdocon)
                            .addComponent(rdohet))))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        tbpThongtinthuoc.addTab("Cập Nhật", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpThongtinthuoc)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpThongtinthuoc)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbonhomthuoc2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbonhomthuoc2ItemStateChanged
        // TODO add your handling code here:
        NhomThuoc nt = new NhomThuoc();
        nt = (NhomThuoc) cbonhomthuoc2.getSelectedItem();
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


    }//GEN-LAST:event_cbonhomthuoc2ItemStateChanged

    private void btnlaydanhsachThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlaydanhsachThuocActionPerformed
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_btnlaydanhsachThuocActionPerformed

    private void tblttthuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblttthuocMouseClicked
        // TODO add your handling code here:
        index = tblttthuoc.getSelectedRow();
        if (evt.getClickCount() == 2) {
            tbpThongtinthuoc.setSelectedIndex(1);
            edit_HH();
        }
    }//GEN-LAST:event_tblttthuocMouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnthemActionPerformed

    private void btncapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapnhatActionPerformed
        // TODO add your handling code here:
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Cập Nhật Không?", "Hói Cập Nhật", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (hoi == JOptionPane.YES_OPTION) {
            update();
        }

    }//GEN-LAST:event_btncapnhatActionPerformed

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void cbonhomthuoc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbonhomthuoc2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbonhomthuoc2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncapnhat;
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnlaydanhsachThuoc;
    private javax.swing.JButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbodonvitinh;
    private javax.swing.JComboBox<String> cbonhomthuoc;
    private javax.swing.JComboBox<String> cbonhomthuoc2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton rdocon;
    private javax.swing.JRadioButton rdohet;
    private javax.swing.JTable tblttthuoc;
    private javax.swing.JTabbedPane tbpThongtinthuoc;
    private javax.swing.JTextField txtIDThuoc;
    private javax.swing.JTextField txtcachdung;
    private javax.swing.JTextField txtdonggoi;
    private javax.swing.JTextArea txthamluong;
    private javax.swing.JTextArea txthoatchat;
    private javax.swing.JTextField txtnoisanxuat;
    private javax.swing.JTextField txttenthuoc;
    // End of variables declaration//GEN-END:variables
}
