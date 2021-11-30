/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;

import com.poly.dao.NhaCungCapDAO;
import com.poly.dao.SanPhamThuocDAO;
import com.poly.dao.ThuocDAO;
import com.poly.dao.TuThuocDAO;
import com.poly.helper.Datehelper;
import com.poly.model.NhaCungCap;
import com.poly.model.NhomThuoc;
import com.poly.model.SanPhamThuoc;
import com.poly.model.Thuoc;
import com.poly.model.TuThuoc;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.poly.helper.ShareHepler;
import com.sun.source.tree.BreakTree;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class frmSanPhamThuoc extends javax.swing.JPanel {

    SanPhamThuocDAO dao = new SanPhamThuocDAO();
    ThuocDAO dao_th = new ThuocDAO();
    NhaCungCapDAO dao_ncc = new NhaCungCapDAO();
    TuThuocDAO dao_tt = new TuThuocDAO();
    int index;
    List combo = new ArrayList<>();
    List combo1 = new ArrayList<>();
    List combo2 = new ArrayList<>();
    DefaultComboBoxModel<NhaCungCap> cbomodel1;
    DefaultComboBoxModel<TuThuoc> cbomodel2;
    DefaultComboBoxModel<Thuoc> cbomodel3;

    public frmSanPhamThuoc() {
        initComponents();
        checklogin();
        loadtodata();
        loadtocbo_ncc();
        loadtocbo_tu();
        loatocbo_thuoc();

    }

    public void checklogin() {
        if (ShareHepler.User == 1) {
            btnthem.setEnabled(true);
            btncapnhat.setEnabled(true);
        } else if (ShareHepler.User == 0) {
            btnthem.setEnabled(false);
            btncapnhat.setEnabled(false);
        } else {
            btnthem.setEnabled(false);
            btncapnhat.setEnabled(false);
        }
    }

    private void edit() {
        try {
            SanPhamThuoc model = dao.findByID(Integer.valueOf(tblsanpham.getValueAt(index, 0).toString()));
            if (model != null) {
                setModel(model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void conhan() {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblsanpham.getModel();
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
                setHSD(x.getHanSuDung());
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

    private void soLuongTon(int soluong) {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblsanpham.getModel();
            List<SanPhamThuoc> list = dao.findBySlT(soluong);
            model.setRowCount(0);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào theo yêu cầu định mức");
                return;
            }
            for (SanPhamThuoc x : list) {
                Vector v = new Vector();
                v.add(x.getMa_Thuoc());
                Thuoc thuoc = dao_th.findByID(x.getID_Thuoc());
                v.add(thuoc.getTen_Thuoc());
                NhaCungCap nhacc = dao_ncc.findByID(x.getID_NhaCC());
                v.add(nhacc.getTen_NCC());
                TuThuoc tu = dao_tt.findByID(x.getID_Tu());
                v.add(tu.getTen_Tu());
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

    private void conhang() {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblsanpham.getModel();
            List<SanPhamThuoc> list = dao.findByconhang();
            model.setRowCount(0);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào theo yêu cầu định mức");
                return;
            }
            for (SanPhamThuoc x : list) {
                Vector v = new Vector();

                v.add(x.getMa_Thuoc());
                Thuoc thuoc = dao_th.findByID(x.getID_Thuoc());
                v.add(thuoc.getTen_Thuoc());
                NhaCungCap nhacc = dao_ncc.findByID(x.getID_NhaCC());
                v.add(nhacc.getTen_NCC());
                TuThuoc tu = dao_tt.findByID(x.getID_Tu());
                v.add(tu.getTen_Tu());

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

    private void duoislt(int soluong) {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblsanpham.getModel();
            List<SanPhamThuoc> list = dao.findbySLH(soluong);
            model.setRowCount(0);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào theo yêu cầu định mức");
                return;
            }
            for (SanPhamThuoc x : list) {
                Vector v = new Vector();

                v.add(x.getMa_Thuoc());
                Thuoc thuoc = dao_th.findByID(x.getID_Thuoc());
                v.add(thuoc.getTen_Thuoc());
                NhaCungCap nhacc = dao_ncc.findByID(x.getID_NhaCC());
                v.add(nhacc.getTen_NCC());
                TuThuoc tu = dao_tt.findByID(x.getID_Tu());
                v.add(tu.getTen_Tu());
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

    private void setModel(SanPhamThuoc model) {
        txtmathuoc.setText(String.valueOf(model.getMa_Thuoc()));
        Thuoc thuoc = dao_th.findByID(model.getID_Thuoc());
        cbonhacungcap.setSelectedIndex(combo.indexOf(tblsanpham.getValueAt(index, 2).toString()));
        cbotuthuoc.setSelectedIndex(combo1.indexOf(tblsanpham.getValueAt(index, 3).toString()));
        cbotenthuoc.setSelectedIndex(combo2.indexOf(tblsanpham.getValueAt(index, 1).toString()));

        dthansudung.setDate(model.getHanSuDung());
        txtgiaban.setText(String.valueOf(model.getGiaBan()));
        txtgianhap.setText(String.valueOf(model.getGiaNhap()));
        txtsoluongton.setText(String.valueOf(model.getSoLuongTon()));
        if (model.isTrangThai()) {
            rdoconhan.setSelected(true);
        } else {
            rdohethan.setSelected(true);
        }
    }

    private void clear() {
        setModel(new SanPhamThuoc());
        txtmathuoc.setText(null);
        cbonhacungcap.setSelectedIndex(0);
        cbotenthuoc.setSelectedIndex(0);
        cbotuthuoc.setSelectedIndex(0);
    }

    private SanPhamThuoc getModel_edit() {
        SanPhamThuoc model = new SanPhamThuoc();

        int i = cbonhacungcap.getSelectedIndex();
        int i2 = cbotuthuoc.getSelectedIndex();
        int i3 = cbotenthuoc.getSelectedIndex();

        model.setID_Thuoc(cbomodel3.getElementAt(i3).getID_Thuoc());
        model.setID_NhaCC(cbomodel1.getElementAt(i).getID_NhaCC());
        model.setID_Tu(cbomodel2.getElementAt(i2).getID_Tu());
        model.setHanSuDung(dthansudung.getDate());
        model.setGiaNhap(Float.valueOf(txtgianhap.getText()));
        model.setGiaBan(Float.valueOf(txtgiaban.getText()));
        model.setSoLuongTon(Integer.valueOf(txtsoluongton.getText()));
        if (rdoconhan.isSelected()) {
            model.setTrangThai(true);
        } else {
            model.setTrangThai(false);
        }
        model.setMa_Thuoc(Integer.valueOf(txtmathuoc.getText()));

        return model;
    }

    private SanPhamThuoc getModel() {
        SanPhamThuoc model = new SanPhamThuoc();

        int i = cbonhacungcap.getSelectedIndex();
        int i2 = cbotuthuoc.getSelectedIndex();
        int i3 = cbotenthuoc.getSelectedIndex();

        model.setID_Thuoc(cbomodel3.getElementAt(i3).getID_Thuoc());
        model.setID_NhaCC(cbomodel1.getElementAt(i).getID_NhaCC());
        model.setID_Tu(cbomodel2.getElementAt(i2).getID_Tu());
        model.setHanSuDung(dthansudung.getDate());
        model.setGiaNhap(Float.valueOf(txtgianhap.getText()));
        model.setGiaBan(Float.valueOf(txtgiaban.getText()));
        model.setSoLuongTon(Integer.valueOf(txtsoluongton.getText()));
        if (rdoconhan.isSelected()) {
            model.setTrangThai(true);
        } else {
            model.setTrangThai(false);
        }
//        model.setMa_Thuoc(Integer.valueOf(txtmathuoc.getText()));

        return model;
    }

    private void insert() {
        if (checkrongHSD()) {
            return;
        }

        if (checkrongGiaNhap()) {
            return;
        }

        if (checkrongGiaBan()) {
            return;
        }
        if (checkrongsoluong()) {
            return;
        }
        try {
            SanPhamThuoc model = getModel();
            dao.insert(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        loadtodata();
    }

    private void update() {
        if (checkrongMaThuoc()) {
            return;
        }
        if (checkrongHSD()) {
            return;
        }

        if (checkrongGiaNhap()) {
            return;
        }

        if (checkrongGiaBan()) {
            return;
        }
        if (checkrongsoluong()) {
            return;
        }
        try {
            SanPhamThuoc model = getModel_edit();
            dao.update(model);
            loadtodata();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void loadtocbo_ncc() {
        cbonhacungcap.removeAllItems();
        cbomodel1 = (DefaultComboBoxModel) cbonhacungcap.getModel();
        List<NhaCungCap> list = dao_ncc.select_tt();
        for (NhaCungCap x : list) {
            NhaCungCap ph = new NhaCungCap(x.getID_NhaCC(), x.getTen_NCC(), x.isTrangThai());
            combo.add(x.getTen_NCC());
            cbomodel1.addElement(ph);
        }
    }

    private void loadtocbo_tu() {
        cbotuthuoc.removeAllItems();
        cbomodel2 = (DefaultComboBoxModel) cbotuthuoc.getModel();
        List<TuThuoc> list = dao_tt.select_tt();
        for (TuThuoc x : list) {
            TuThuoc ph = new TuThuoc(x.getID_Tu(), x.getTen_Tu(), x.isTrangThai());
            combo1.add(x.getTen_Tu());
            cbomodel2.addElement(ph);
        }
    }

    private void loatocbo_thuoc() {
        cbotenthuoc.removeAllItems();
        cbomodel3 = (DefaultComboBoxModel) cbotenthuoc.getModel();
        List<Thuoc> list = dao_th.select();
        for (Thuoc x : list) {
            Thuoc ph = new Thuoc(x.getID_Thuoc(), x.getTen_Thuoc(), x.isTrangThai());
            combo2.add(x.getTen_Thuoc());
            cbomodel3.addElement(ph);
        }
    }

    private void loadtodata() {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblsanpham.getModel();
            List<SanPhamThuoc> list = dao.select();
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
                setHSD(x.getHanSuDung());
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

    private void timspngay(int songay) {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblsanpham.getModel();
            List<SanPhamThuoc> list = dao.checksn(songay);
            model.setRowCount(0);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào theo yêu cầu Ngày Tháng");
                return;
            }
            for (SanPhamThuoc x : list) {
                Vector v = new Vector();

                v.add(x.getMa_Thuoc());
                Thuoc thuoc = dao_th.findByID(x.getID_Thuoc());
                v.add(thuoc.getTen_Thuoc());
                NhaCungCap nhacc = dao_ncc.findByID(x.getID_NhaCC());
                v.add(nhacc.getTen_NCC());
                TuThuoc tu = dao_tt.findByID(x.getID_Tu());
                v.add(tu.getTen_Tu());
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


    private void setHSD(Date hsd) {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date todayD = new java.util.Date(System.currentTimeMillis());
        String today = dayFormat.format(todayD.getTime());

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(hsd);
        cal2.setTime(Datehelper.now());
        boolean samday = cal1.get(Calendar.DAY_OF_YEAR) <= cal2.get(Calendar.DAY_OF_YEAR);
        boolean samday2 = cal1.get(Calendar.MONTH) < cal2.get(Calendar.MONTH);
        boolean samday3 = cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR);
        boolean samday4 = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean samday5 =cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        if (samday3 == true) {
            dao.update_hsd(hsd);
        } else if (samday4 == true && samday2 == true) {
            dao.update_hsd(hsd);
        }else if(samday4 == true && samday5 == true && samday == true){
             dao.update_hsd(hsd);
        }

    }

    private boolean checkrongMaThuoc() {
        if (txtmathuoc.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Mã Thuốc Để Cập Nhật");
            txtmathuoc.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkrongHSD() {
        if (dthansudung.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Hạn Sử Dụng");
            dthansudung.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkrongGiaNhap() {
        if (txtgianhap.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Giá Nhập");
            txtgianhap.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkrongGiaBan() {
        if (txtgiaban.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Giá Bán");
            txtgiaban.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkrongsoluong() {
        if (txtsoluongton.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Số Lượng Tồn");
            txtsoluongton.requestFocus();
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        tbpsanphamthuoc = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanpham = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        rdoduoi = new javax.swing.JRadioButton();
        rdotren = new javax.swing.JRadioButton();
        txtdinhmuc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rdoconhang = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        rdohethansd = new javax.swing.JRadioButton();
        rdoconhansd = new javax.swing.JRadioButton();
        rdosaphethan = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        txtsongay = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dthansudung = new com.toedter.calendar.JDateChooser();
        txtmathuoc = new javax.swing.JTextField();
        txtgiaban = new javax.swing.JTextField();
        rdoconhan = new javax.swing.JRadioButton();
        rdohethan = new javax.swing.JRadioButton();
        cbotuthuoc = new javax.swing.JComboBox<>();
        txtgianhap = new javax.swing.JTextField();
        txtsoluongton = new javax.swing.JTextField();
        cbonhacungcap = new javax.swing.JComboBox<>();
        btnthem = new javax.swing.JButton();
        btncapnhat = new javax.swing.JButton();
        cbotenthuoc = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        tbpsanphamthuoc.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(30, 165, 252));

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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tồn Kho"));

        rdoduoi.setText("Dưới Định Mức Tồn");
        rdoduoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoduoiMouseClicked(evt);
            }
        });
        rdoduoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoduoiActionPerformed(evt);
            }
        });

        rdotren.setText("Trên Định Mức Tồn");
        rdotren.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdotrenMouseClicked(evt);
            }
        });
        rdotren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdotrenActionPerformed(evt);
            }
        });

        txtdinhmuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdinhmucKeyTyped(evt);
            }
        });

        jLabel10.setText("Định Mức Tồn");

        rdoconhang.setText("Còn Hàng");
        rdoconhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoconhangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoconhang)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoduoi)
                            .addComponent(rdotren))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(18, 18, 18)
                            .addComponent(txtdinhmuc))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdinhmuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoconhang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(rdotren)
                .addGap(18, 18, 18)
                .addComponent(rdoduoi)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hạn Sử Dụng"));

        rdohethansd.setText("Hết Hạn Sử Dụng");
        rdohethansd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdohethansdMouseClicked(evt);
            }
        });

        rdoconhansd.setText("Còn Hạn Sử Dụng");
        rdoconhansd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoconhansdMouseClicked(evt);
            }
        });

        rdosaphethan.setBackground(new java.awt.Color(255, 255, 255));
        rdosaphethan.setText("Sắp Hết Hạn");
        rdosaphethan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdosaphethanMouseClicked(evt);
            }
        });

        jLabel13.setText("Số Ngày Sắp Hết hạn");

        txtsongay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsongayKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsongay)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdohethansd)
                            .addComponent(rdoconhansd)
                            .addComponent(rdosaphethan)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtsongay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(rdosaphethan)
                .addGap(18, 18, 18)
                .addComponent(rdoconhansd)
                .addGap(18, 18, 18)
                .addComponent(rdohethansd)
                .addContainerGap())
        );

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton4.setForeground(new java.awt.Color(30, 165, 252));
        jButton4.setText("Cập Nhật Sản Phẩm");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))))
        );

        tbpsanphamthuoc.addTab("Thông Tin", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mã Thuốc");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nhà Cung Cấp");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Hạn Sử Dụng");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Giá Bán");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Trạng Thái :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tên Thuốc");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Tủ Thuốc");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Giá Nhập");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Số Lượng Tồn");

        dthansudung.setDateFormatString("yyyy-MM-dd");

        txtmathuoc.setEditable(false);
        txtmathuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtgiaban.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtgiaban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgiabanActionPerformed(evt);
            }
        });
        txtgiaban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtgiabanKeyTyped(evt);
            }
        });

        buttonGroup1.add(rdoconhan);
        rdoconhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoconhan.setSelected(true);
        rdoconhan.setText("Còn Hạn Sử Dụng");

        buttonGroup1.add(rdohethan);
        rdohethan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdohethan.setText("Hết Hạn Sử Dụng");

        cbotuthuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtgianhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtgianhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtgianhapKeyTyped(evt);
            }
        });

        txtsoluongton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsoluongton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsoluongtonActionPerformed(evt);
            }
        });
        txtsoluongton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsoluongtonKeyTyped(evt);
            }
        });

        cbonhacungcap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnthem.setBackground(new java.awt.Color(30, 165, 252));
        btnthem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnthem.setForeground(new java.awt.Color(255, 255, 255));
        btnthem.setText("Thêm");
        btnthem.setBorderPainted(false);
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btncapnhat.setBackground(new java.awt.Color(30, 165, 252));
        btncapnhat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncapnhat.setForeground(new java.awt.Color(255, 255, 255));
        btncapnhat.setText("Cập Nhật");
        btncapnhat.setBorderPainted(false);
        btncapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapnhatActionPerformed(evt);
            }
        });

        cbotenthuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton3.setBackground(new java.awt.Color(30, 165, 252));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Làm Mới");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 37, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dthansudung, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtmathuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbonhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoconhan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdohethan)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbotuthuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbotenthuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtgianhap)
                            .addComponent(txtsoluongton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btncapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbonhacungcap, dthansudung, txtgiaban, txtmathuoc, txtsoluongton});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbotenthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(40, 40, 40)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbotuthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(dthansudung, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtsoluongton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmathuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbonhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rdoconhan)
                    .addComponent(rdohethan)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(204, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbonhacungcap, dthansudung, txtgiaban, txtmathuoc});

        tbpsanphamthuoc.addTab("Cập Nhật", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpsanphamthuoc)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpsanphamthuoc)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoduoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoduoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoduoiActionPerformed

    private void rdotrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdotrenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdotrenActionPerformed

    private void tblsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsanphamMouseClicked
        // TODO add your handling code here:
        index = tblsanpham.getSelectedRow();
        if (evt.getClickCount() == 2) {
            tbpsanphamthuoc.setSelectedIndex(1);
            edit();
        }
    }//GEN-LAST:event_tblsanphamMouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnthemActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btncapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapnhatActionPerformed
        // TODO add your handling code here:
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Cập Nhật Không?", "Hói Cập Nhật", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (hoi == JOptionPane.YES_OPTION) {
            update();
        }
    }//GEN-LAST:event_btncapnhatActionPerformed

    private void txtsoluongtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsoluongtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsoluongtonActionPerformed

    private void txtgiabanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgiabanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgiabanActionPerformed

    private void rdotrenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdotrenMouseClicked
        // TODO add your handling code here:
        try {
            int soluong = Integer.valueOf(txtdinhmuc.getText());
            if (soluong < 0) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập Định Mức >= 0");
                return;
            } else {
                soLuongTon(soluong);
                btn(false);
                rdotren.setSelected(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Định Mức");
            btn(false);
        }
    }//GEN-LAST:event_rdotrenMouseClicked

    private void rdoduoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoduoiMouseClicked
        try {
            int soluong = Integer.valueOf(txtdinhmuc.getText());
            if (soluong < 0) {
                JOptionPane.showMessageDialog(this, "Hãy Nhập Định Mức >= 0");
                return;
            } else {
                duoislt(soluong);
                btn(false);
                rdoduoi.setSelected(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Định Mức");
            btn(false);

        }
    }//GEN-LAST:event_rdoduoiMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        loadtodata();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void rdosaphethanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdosaphethanMouseClicked
        // TODO add your handling code here:
        if (txtsongay.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Số Ngày Rỗng");
            return;
        }

        int songay = Integer.valueOf(txtsongay.getText());
        if (songay <= 0) {
            JOptionPane.showMessageDialog(this, "Hãy nhập số ngày > 0");
            return;
        }

        try {

            timspngay(songay);

            btn(false);
            rdosaphethan.setSelected(true);
        } catch (Exception e) {
            btn(false);
        }
    }//GEN-LAST:event_rdosaphethanMouseClicked

    private void rdoconhansdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoconhansdMouseClicked
        // TODO add your handling code here:
        try {
            conhan();
            btn(false);
            rdoconhansd.setSelected(true);
        } catch (Exception e) {
            btn(false);
        }
    }//GEN-LAST:event_rdoconhansdMouseClicked

    private void rdohethansdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdohethansdMouseClicked
        // TODO add your handling code here:
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblsanpham.getModel();
            List<SanPhamThuoc> list = dao.findByHH();
            model.setRowCount(0);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không Có Sản Phẩm Hết Hạn");
                return;
            }
            for (SanPhamThuoc x : list) {
                Vector v = new Vector();
                v.add(x.getMa_Thuoc());
                Thuoc thuoc = dao_th.findByID(x.getID_Thuoc());
                v.add(thuoc.getTen_Thuoc());
                NhaCungCap nhacc = dao_ncc.findByID(x.getID_NhaCC());
                v.add(nhacc.getTen_NCC());
                TuThuoc tu = dao_tt.findByID(x.getID_Tu());
                v.add(tu.getTen_Tu());
                setHSD(x.getHanSuDung());
                v.add(x.getHanSuDung());
                v.add(x.getGiaNhap());
                v.add(x.getGiaBan());
                v.add(x.getSoLuongTon());
                v.add(x.isTrangThai() ? "Còn Hạn Sử Dụng" : "Hết Hạn Sử Dụng");
                model.addRow(v);
            }
            btn(false);
            rdohethansd.setSelected(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            btn(false);
        }
    }//GEN-LAST:event_rdohethansdMouseClicked

    private void txtgianhapKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgianhapKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtgianhapKeyTyped

    private void txtgiabanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgiabanKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtgiabanKeyTyped

    private void txtsoluongtonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsoluongtonKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtsoluongtonKeyTyped

    private void rdoconhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoconhangMouseClicked
        // TODO add your handling code here:
        try {

            conhang();
            btn(false);
            rdoconhang.setSelected(true);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_rdoconhangMouseClicked

    private void txtdinhmucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdinhmucKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtdinhmucKeyTyped

    private void txtsongayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsongayKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtsongayKeyTyped

    private void btn(boolean tt) {
        rdotren.setSelected(tt);
        rdoduoi.setSelected(tt);
        rdoconhang.setSelected(tt);
        rdosaphethan.setSelected(tt);
        rdoconhansd.setSelected(tt);
        rdohethansd.setSelected(tt);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncapnhat;
    private javax.swing.JButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbonhacungcap;
    private javax.swing.JComboBox<String> cbotenthuoc;
    private javax.swing.JComboBox<String> cbotuthuoc;
    private com.toedter.calendar.JDateChooser dthansudung;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoconhan;
    private javax.swing.JRadioButton rdoconhang;
    private javax.swing.JRadioButton rdoconhansd;
    private javax.swing.JRadioButton rdoduoi;
    private javax.swing.JRadioButton rdohethan;
    private javax.swing.JRadioButton rdohethansd;
    private javax.swing.JRadioButton rdosaphethan;
    private javax.swing.JRadioButton rdotren;
    private javax.swing.JTable tblsanpham;
    private javax.swing.JTabbedPane tbpsanphamthuoc;
    private javax.swing.JTextField txtdinhmuc;
    private javax.swing.JTextField txtgiaban;
    private javax.swing.JTextField txtgianhap;
    private javax.swing.JTextField txtmathuoc;
    private javax.swing.JTextField txtsoluongton;
    private javax.swing.JTextField txtsongay;
    // End of variables declaration//GEN-END:variables
}
