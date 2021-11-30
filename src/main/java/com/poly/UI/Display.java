package com.poly.UI;

import com.poly.Jdbc.SQLConnect;
import com.poly.dao.HoaDonBanCTDAO;
import com.poly.dao.HoaDonBanHangDAO;
import com.poly.dao.NhaCungCapDAO;
import com.poly.dao.PhieuChiDAO;
import com.poly.dao.PhieuThuDAO;
import com.poly.dao.SanPhamThuocDAO;
import com.poly.dao.ThuocDAO;
import com.poly.dao.TuThuocDAO;
import com.poly.helper.ShareHepler;
import com.poly.model.HoaDonBan;
import com.poly.model.HoaDonBanCT;
import com.poly.model.HoaDonTraHang;
import com.poly.model.NhaCungCap;
import com.poly.model.PhieuChi;
import com.poly.model.Thuoc;
import com.poly.model.TuThuoc;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import com.poly.model.SanPhamThuoc;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.*;
import javax.swing.JFrame;
import com.poly.model.PhieuThu;

public class Display extends javax.swing.JFrame {

    int index2;
    TTKhachHang khachHang;
    TTNhaCungCap NhaCC;
    TTNguoiDung nguoidung;
    SanPhamThuocDAO dao = new SanPhamThuocDAO();
    HoaDonBanHangDAO dao_hdb = new HoaDonBanHangDAO();
    ThuocDAO dao_th = new ThuocDAO();
    NhaCungCapDAO dao_ncc = new NhaCungCapDAO();
    TuThuocDAO dao_tt = new TuThuocDAO();
    int index;
    DefaultComboBoxModel<NhaCungCap> cbomodel1;
    DefaultComboBoxModel<TuThuoc> cbomodel2;
    DefaultComboBoxModel<Thuoc> cbomodel3;
    int numTabs;
    frmThongTinThuoc thongtinthuoc;
    frmSanPhamThuoc sanphamthuoc;
    frmTuThuoc tuthuoc;
    frmNhomThuoc nhomthuoc;
    frmDonViTinh donvitinh;
    frmNhapHang nhaphang;
    frmBanHang banhang;
    frmDanhSachPhieuThu dsphieuthu;
    frmTraHang trahang;
    frmDanhSachPhieuChi dsphieuchi;
    static int nam;
    DefaultCategoryDataset dataset;
    frmThongKeDoanhThu1 tkdt1;
    PhieuChiDAO pcd = new PhieuChiDAO();
    PhieuThuDAO ptd = new PhieuThuDAO();
    HoaDonTraHang hdthd;
    HoaDonBanCTDAO dao_hdbct = new HoaDonBanCTDAO();
    frmDanhSachHoaDonTH dshdth;
    frmDanhSachPhieuNhap dshdnh;
    frmDanhSachHDBan dshdb;
    int index3;

    public Display() {
        initComponents();
        setLocationRelativeTo(null);
        checklogin();
        loaddata();
        loadtocbo();

    }

    private void loadtocbo() {
        try {
            Connection con = SQLConnect.getcon();
            String sql = "select DISTINCT Year(NgayLap_HD) from HoaDonBan";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cbonam1.addItem(rs.getString(1));
                cbonam2.addItem(rs.getString(1));
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Chưa Có Hóa Đơn Bán Nào");
        }
    }

    private void checklogin() {
        if (ShareHepler.User == 1) {
            setButton(true);
        } else if (ShareHepler.User == 0) {
            setButton(false);
            btndshdbh.setEnabled(true);
            btndshdth.setEnabled(true);
            btnbanhang.setEnabled(true);
            btntrahang.setEnabled(true);
            btnthongtinthuoc.setEnabled(true);
            btnspthuoc.setEnabled(true);
            btntuthuoc.setEnabled(true);
            btnnhomthuoc.setEnabled(true);
            btndonvitinh.setEnabled(true);
            
        } else {
            setButton(false);
//            jButton14.setEnabled(false);
//            jButton15.setEnabled(false);
        }

    }

    public static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU THEO THÁNG",
                "Tháng", "Tổng Doanh Thu",
                dataset, PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    public static JFreeChart createChart1(CategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH Số THEO THÁNG",
                "Tên Sản Phẩm", "Tổng Doanh Số",
                dataset, PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private void setButton(boolean tt) {
        try {
            btnnhaphang.setEnabled(tt);
            btnbanhang.setEnabled(tt);
            btndonvitinh.setEnabled(tt);
            btndshdbh.setEnabled(tt);
            btndshdnh.setEnabled(tt);
            btndshdth.setEnabled(tt);
            btnnhomthuoc.setEnabled(tt);
            btnspthuoc.setEnabled(tt);
            btnthongtinthuoc.setEnabled(tt);
            btntrahang.setEnabled(tt);
            btntuthuoc.setEnabled(tt);
            rdotheobang1.setEnabled(tt);
            rdotheobang2.setEnabled(tt);
            rdotheobd1.setEnabled(tt);
            rdotheobd2.setEnabled(tt);
            btnphieuthu.setEnabled(tt);
            btnphieuchi.setEnabled(tt);
            new TTNguoiDung().setVisible(tt);
        } catch (Exception e) {
        }
    }

    public void removeTabHH(int index) {
        tbphanghoa.removeTabAt(index);
//        numTabs--;
//
//        if (index == numTabs - 1 && index > 0) {
//            tbphanghoa.setSelectedIndex(numTabs - 2);
//        } else {
//            tbphanghoa.setSelectedIndex(index);
//        }
    }

    private void loaddata() {
        try {
            DefaultTableModel model;
            model = (DefaultTableModel) tblsanpham1.getModel();
            List<SanPhamThuoc> list = dao.checksn7();
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có thuốc nào hết hạn trong 7 ngày tới");
                return;
            }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pnlkhachhang = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanpham1 = new javax.swing.JTable();
        pnlthongke = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        cbonam1 = new javax.swing.JComboBox<>();
        pnldoanhthu1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        rdotheobang1 = new javax.swing.JRadioButton();
        rdotheobd1 = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        cbonam2 = new javax.swing.JComboBox<>();
        cbothang = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        rdotheobang2 = new javax.swing.JRadioButton();
        rdotheobd2 = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltkds = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnnhaphang = new javax.swing.JButton();
        btnbanhang = new javax.swing.JButton();
        btntrahang = new javax.swing.JButton();
        btndshdth = new javax.swing.JButton();
        btndshdnh = new javax.swing.JButton();
        btndshdbh = new javax.swing.JButton();
        tbpgiaodich = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnthongtinthuoc = new javax.swing.JButton();
        btnnhomthuoc = new javax.swing.JButton();
        btndonvitinh = new javax.swing.JButton();
        btntuthuoc = new javax.swing.JButton();
        btnspthuoc = new javax.swing.JButton();
        tbphanghoa = new javax.swing.JTabbedPane();
        btnxoatabHH = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        tbpDoiTac = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnphieuchi = new javax.swing.JButton();
        btnphieuthu = new javax.swing.JButton();
        tbpthongke = new javax.swing.JTabbedPane();
        jButton13 = new javax.swing.JButton();
        pnlnguoidung = new javax.swing.JPanel();
        tbpNguoiDung = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton14 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton15 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        pnltt = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlkhachhangMouseClicked(evt);
            }
        });
        pnlkhachhang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnlkhachhangKeyPressed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/4f7066521c39eb67b228.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1195, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlkhachhang.addTab("Trang Chủ", new javax.swing.ImageIcon(getClass().getResource("/image/baseline_home_black_24dp.png")), jPanel3); // NOI18N

        jPanel8.setLayout(null);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Sản Phẩm Thuốc Sắp Hết Hạn"));

        tblsanpham1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblsanpham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsanpham1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblsanpham1);

        jPanel8.add(jScrollPane1);
        jScrollPane1.setBounds(-1, 3, 1200, 600);

        pnlkhachhang.addTab("Thông Báo", new javax.swing.ImageIcon(getClass().getResource("/image/baseline_notifications_black_24dp.png")), jPanel8); // NOI18N

        cbonam1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbonam1ItemStateChanged(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tháng", "Tổng Tiền Bán", "Tổng Tiền Trả", "Tổng Tiền Nhập Hàng", "Doanh Thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout pnldoanhthu1Layout = new javax.swing.GroupLayout(pnldoanhthu1);
        pnldoanhthu1.setLayout(pnldoanhthu1Layout);
        pnldoanhthu1Layout.setHorizontalGroup(
            pnldoanhthu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldoanhthu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnldoanhthu1Layout.setVerticalGroup(
            pnldoanhthu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldoanhthu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Lựa Chọn Hiển Thị"));

        buttonGroup1.add(rdotheobang1);
        rdotheobang1.setText("Theo Bảng");
        rdotheobang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdotheobang1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdotheobd1);
        rdotheobd1.setText("Theo Biểu Đồ");
        rdotheobd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdotheobd1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(rdotheobang1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(rdotheobd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(rdotheobang1)
                .addGap(18, 18, 18)
                .addComponent(rdotheobd1)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbonam1, 0, 171, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnldoanhthu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(cbonam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 353, Short.MAX_VALUE))
                    .addComponent(pnldoanhthu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Thống Kê Doanh Thu Theo Tháng", jPanel11);

        cbothang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Lựa Chọn Hiển Thị"));

        buttonGroup2.add(rdotheobang2);
        rdotheobang2.setText("Theo Bảng");
        rdotheobang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdotheobang2ActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdotheobd2);
        rdotheobd2.setText("Biểu Đồ");
        rdotheobd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdotheobd2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdotheobd2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdotheobang2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(rdotheobang2)
                .addGap(18, 18, 18)
                .addComponent(rdotheobd2)
                .addGap(17, 17, 17))
        );

        tbltkds.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Thuốc", "Tên Sản Phẩm", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbltkds);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbothang, 0, 138, Short.MAX_VALUE)
                    .addComponent(cbonam2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(cbonam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(cbothang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Thống Kê Doanh Số", jPanel13);

        javax.swing.GroupLayout pnlthongkeLayout = new javax.swing.GroupLayout(pnlthongke);
        pnlthongke.setLayout(pnlthongkeLayout);
        pnlthongkeLayout.setHorizontalGroup(
            pnlthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        pnlthongkeLayout.setVerticalGroup(
            pnlthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        pnlkhachhang.addTab("Thống Kê", new javax.swing.ImageIcon(getClass().getResource("/image/baseline_auto_graph_black_24dp.png")), pnlthongke); // NOI18N

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        btnnhaphang.setBackground(new java.awt.Color(30, 165, 252));
        btnnhaphang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnnhaphang.setForeground(new java.awt.Color(255, 255, 255));
        btnnhaphang.setText("Nhập Hàng");
        btnnhaphang.setBorder(null);
        btnnhaphang.setBorderPainted(false);
        btnnhaphang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnhaphangActionPerformed(evt);
            }
        });

        btnbanhang.setBackground(new java.awt.Color(30, 165, 252));
        btnbanhang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnbanhang.setForeground(new java.awt.Color(255, 255, 255));
        btnbanhang.setText("Bán Hàng");
        btnbanhang.setBorder(null);
        btnbanhang.setBorderPainted(false);
        btnbanhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbanhangActionPerformed(evt);
            }
        });

        btntrahang.setBackground(new java.awt.Color(30, 165, 252));
        btntrahang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btntrahang.setForeground(new java.awt.Color(255, 255, 255));
        btntrahang.setText("Trả Hàng");
        btntrahang.setBorder(null);
        btntrahang.setBorderPainted(false);
        btntrahang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntrahangActionPerformed(evt);
            }
        });

        btndshdth.setBackground(new java.awt.Color(30, 165, 252));
        btndshdth.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btndshdth.setForeground(new java.awt.Color(255, 255, 255));
        btndshdth.setText("DS Hóa Đơn Trả Hàng");
        btndshdth.setBorder(null);
        btndshdth.setBorderPainted(false);
        btndshdth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndshdthActionPerformed(evt);
            }
        });

        btndshdnh.setBackground(new java.awt.Color(30, 165, 252));
        btndshdnh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btndshdnh.setForeground(new java.awt.Color(255, 255, 255));
        btndshdnh.setText("DS Hóa Đơn Nhập Hàng");
        btndshdnh.setBorder(null);
        btndshdnh.setBorderPainted(false);
        btndshdnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndshdnhActionPerformed(evt);
            }
        });

        btndshdbh.setBackground(new java.awt.Color(30, 165, 252));
        btndshdbh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btndshdbh.setForeground(new java.awt.Color(255, 255, 255));
        btndshdbh.setText("DS Hóa Đơn Bán Hàng");
        btndshdbh.setBorder(null);
        btndshdbh.setBorderPainted(false);
        btndshdbh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndshdbhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnnhaphang, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btnbanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntrahang, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndshdth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndshdnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndshdbh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnbanhang, btndshdbh, btndshdnh, btndshdth, btnnhaphang, btntrahang});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btnnhaphang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnbanhang)
                .addGap(45, 45, 45)
                .addComponent(btntrahang)
                .addGap(45, 45, 45)
                .addComponent(btndshdth)
                .addGap(45, 45, 45)
                .addComponent(btndshdnh)
                .addGap(40, 40, 40)
                .addComponent(btndshdbh)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnbanhang, btndshdbh, btndshdnh, btndshdth, btnnhaphang, btntrahang});

        tbpgiaodich.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpgiaodich, javax.swing.GroupLayout.PREFERRED_SIZE, 965, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(tbpgiaodich)
        );

        pnlkhachhang.addTab("Giao Dịch", new javax.swing.ImageIcon(getClass().getResource("/image/baseline_compare_arrows_black_24dp.png")), jPanel4); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnthongtinthuoc.setBackground(new java.awt.Color(30, 165, 252));
        btnthongtinthuoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnthongtinthuoc.setForeground(new java.awt.Color(255, 255, 255));
        btnthongtinthuoc.setText("Thông Tin Thuốc");
        btnthongtinthuoc.setBorderPainted(false);
        btnthongtinthuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthongtinthuocActionPerformed(evt);
            }
        });

        btnnhomthuoc.setBackground(new java.awt.Color(30, 165, 252));
        btnnhomthuoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnnhomthuoc.setForeground(new java.awt.Color(255, 255, 255));
        btnnhomthuoc.setText("Nhóm Thuốc");
        btnnhomthuoc.setBorderPainted(false);
        btnnhomthuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnhomthuocActionPerformed(evt);
            }
        });

        btndonvitinh.setBackground(new java.awt.Color(30, 165, 252));
        btndonvitinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btndonvitinh.setForeground(new java.awt.Color(255, 255, 255));
        btndonvitinh.setText("Đơn Vị Tính");
        btndonvitinh.setBorderPainted(false);
        btndonvitinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndonvitinhActionPerformed(evt);
            }
        });

        btntuthuoc.setBackground(new java.awt.Color(30, 165, 252));
        btntuthuoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btntuthuoc.setForeground(new java.awt.Color(255, 255, 255));
        btntuthuoc.setText("Tủ Thuốc");
        btntuthuoc.setBorderPainted(false);
        btntuthuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntuthuocActionPerformed(evt);
            }
        });

        btnspthuoc.setBackground(new java.awt.Color(30, 165, 252));
        btnspthuoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnspthuoc.setForeground(new java.awt.Color(255, 255, 255));
        btnspthuoc.setText("Sản Phẩm Thuốc");
        btnspthuoc.setBorderPainted(false);
        btnspthuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnspthuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnthongtinthuoc, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
            .addComponent(btnspthuoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnnhomthuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btndonvitinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btntuthuoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnthongtinthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnspthuoc)
                .addGap(45, 45, 45)
                .addComponent(btnnhomthuoc)
                .addGap(45, 45, 45)
                .addComponent(btndonvitinh)
                .addGap(45, 45, 45)
                .addComponent(btntuthuoc)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btndonvitinh, btnnhomthuoc, btnspthuoc, btnthongtinthuoc, btntuthuoc});

        tbphanghoa.setBackground(new java.awt.Color(255, 255, 255));

        btnxoatabHH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnxoatabHH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoatabHHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbphanghoa, javax.swing.GroupLayout.PREFERRED_SIZE, 965, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnxoatabHH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnxoatabHH, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 588, Short.MAX_VALUE))
            .addComponent(tbphanghoa)
        );

        pnlkhachhang.addTab("Hàng Hóa", new javax.swing.ImageIcon(getClass().getResource("/image/baseline_shop_2_black_24dp.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpDoiTac, javax.swing.GroupLayout.DEFAULT_SIZE, 1195, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpDoiTac, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );

        pnlkhachhang.addTab("Khách Hàng", new javax.swing.ImageIcon(getClass().getResource("/image/baseline_groups_black_24dp.png")), jPanel6); // NOI18N

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnphieuchi.setBackground(new java.awt.Color(30, 165, 252));
        btnphieuchi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnphieuchi.setForeground(new java.awt.Color(255, 255, 255));
        btnphieuchi.setText("Phiếu Chi");
        btnphieuchi.setBorderPainted(false);
        btnphieuchi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnphieuchiActionPerformed(evt);
            }
        });

        btnphieuthu.setBackground(new java.awt.Color(30, 165, 252));
        btnphieuthu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnphieuthu.setForeground(new java.awt.Color(255, 255, 255));
        btnphieuthu.setText("Phiếu Thu");
        btnphieuthu.setBorderPainted(false);
        btnphieuthu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnphieuthuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnphieuchi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnphieuthu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnphieuthu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btnphieuchi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(404, Short.MAX_VALUE))
        );

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tbpthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 965, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tbpthongke)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlkhachhang.addTab("Thu Chi", new javax.swing.ImageIcon(getClass().getResource("/image/outline_paid_black_24dp.png")), jPanel7); // NOI18N

        javax.swing.GroupLayout pnlnguoidungLayout = new javax.swing.GroupLayout(pnlnguoidung);
        pnlnguoidung.setLayout(pnlnguoidungLayout);
        pnlnguoidungLayout.setHorizontalGroup(
            pnlnguoidungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 1195, Short.MAX_VALUE)
        );
        pnlnguoidungLayout.setVerticalGroup(
            pnlnguoidungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );

        pnlkhachhang.addTab("Người Dùng", new javax.swing.ImageIcon(getClass().getResource("/image/outline_account_circle_black_24dp.png")), pnlnguoidung); // NOI18N

        jToolBar1.setRollover(true);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/outline_logout_black_24dp.png"))); // NOI18N
        jButton14.setText("Đăng Xuất");
        jButton14.setFocusable(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton14);
        jToolBar1.add(jSeparator1);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/outline_autorenew_black_24dp.png"))); // NOI18N
        jButton15.setText("Đổi Mật Khẩu");
        jButton15.setFocusable(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton15);
        jToolBar1.add(jSeparator2);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/thin.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/quan.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Danh hài :Quang Thìn ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Chuyên gia thiết kế cơ sở dữ liệu");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Danh hài :Phan Quân ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Trùm xã hội tím");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/nghia.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Danh hài :Hữu Nghĩa ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Tiên tửu đất Thái Bình");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("Đồng sáng lập phần mềm bán thuốc Tây");

        javax.swing.GroupLayout pnlttLayout = new javax.swing.GroupLayout(pnltt);
        pnltt.setLayout(pnlttLayout);
        pnlttLayout.setHorizontalGroup(
            pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlttLayout.createSequentialGroup()
                .addGroup(pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlttLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel4)
                        .addGap(111, 111, 111))
                    .addGroup(pnlttLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlttLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel9)))
                .addGroup(pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlttLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlttLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlttLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlttLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(105, 105, 105)))))
                .addGroup(pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlttLayout.createSequentialGroup()
                        .addGroup(pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7))
                        .addGap(43, 43, 43)))
                .addGap(80, 80, 80))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlttLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227))
        );
        pnlttLayout.setVerticalGroup(
            pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlttLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1195, Short.MAX_VALUE)
            .addComponent(pnltt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnltt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlkhachhang.addTab("Hệ Thống", new javax.swing.ImageIcon(getClass().getResource("/image/outline_settings_black_24dp.png")), jPanel15); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlkhachhang, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        index2 = tbpgiaodich.getSelectedIndex();
        if (index2 < 0) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Tab Cần Xóa");
            return;
        } else {
            tbpgiaodich.removeTabAt(index2);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnthongtinthuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthongtinthuocActionPerformed
        int i = tbphanghoa.indexOfComponent(thongtinthuoc);
        try {
            if (thongtinthuoc == null) {
                thongtinthuoc = new frmThongTinThuoc();
                tbphanghoa.addTab("thông tin thuốc", thongtinthuoc);
                tbphanghoa.setSelectedComponent(thongtinthuoc);
            } else if (i == -1) {
                thongtinthuoc = new frmThongTinThuoc();
                tbphanghoa.addTab("Thông Tin Thuốc", thongtinthuoc);
                tbphanghoa.setSelectedComponent(thongtinthuoc);
            } else {
                tbphanghoa.setSelectedComponent(thongtinthuoc);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnthongtinthuocActionPerformed

    private void btnspthuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnspthuocActionPerformed
        // TODO add your handling code here:
        int i = tbphanghoa.indexOfComponent(sanphamthuoc);
        try {
            if (sanphamthuoc == null) {
                sanphamthuoc = new frmSanPhamThuoc();
                tbphanghoa.addTab("Sản Phẩm thuốc", sanphamthuoc);
                tbphanghoa.setSelectedComponent(sanphamthuoc);
            } else if (i == -1) {
                sanphamthuoc = new frmSanPhamThuoc();
                tbphanghoa.addTab("Sản Phẩm Thuốc", sanphamthuoc);
                tbphanghoa.setSelectedComponent(sanphamthuoc);
            } else {
                tbphanghoa.setSelectedComponent(sanphamthuoc);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);

        }
    }//GEN-LAST:event_btnspthuocActionPerformed

    private void btnxoatabHHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoatabHHActionPerformed
        // TODO add your handling code here:
        index = tbphanghoa.getSelectedIndex();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Tab Cần Xóa");
            return;
        } else {
            tbphanghoa.removeTabAt(index);
        }

    }//GEN-LAST:event_btnxoatabHHActionPerformed

    private void btntuthuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntuthuocActionPerformed
        // TODO add your handling code here:
        int i = tbphanghoa.indexOfComponent(tuthuoc);
        try {
            if (tuthuoc == null) {
                tuthuoc = new frmTuThuoc();
                tbphanghoa.addTab("Tủ Thuốc", tuthuoc);
                tbphanghoa.setSelectedComponent(tuthuoc);
            } else if (i == -1) {
                tuthuoc = new frmTuThuoc();
                tbphanghoa.addTab("Tủ Thuốc", tuthuoc);
                tbphanghoa.setSelectedComponent(tuthuoc);
            } else {
                tbphanghoa.setSelectedComponent(tuthuoc);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btntuthuocActionPerformed

    private void btnnhomthuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhomthuocActionPerformed
        // TODO add your handling code here:
        int i = tbphanghoa.indexOfComponent(nhomthuoc);
        try {
            if (nhomthuoc == null) {
                nhomthuoc = new frmNhomThuoc();
                tbphanghoa.addTab("Nhóm Thuốc", nhomthuoc);
                tbphanghoa.setSelectedComponent(nhomthuoc);
            } else if (i == -1) {
                nhomthuoc = new frmNhomThuoc();
                tbphanghoa.addTab("Nhóm Thuốc", nhomthuoc);
                tbphanghoa.setSelectedComponent(nhomthuoc);
            } else {
                tbphanghoa.setSelectedComponent(nhomthuoc);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnnhomthuocActionPerformed

    private void btndonvitinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndonvitinhActionPerformed
        // TODO add your handling code here:
        int i = tbphanghoa.indexOfComponent(donvitinh);
        try {
            if (donvitinh == null) {
                donvitinh = new frmDonViTinh();
                tbphanghoa.addTab("Đơn Vị Tính", donvitinh);
                tbphanghoa.setSelectedComponent(donvitinh);
            } else if (i == -1) {
                donvitinh = new frmDonViTinh();
                tbphanghoa.addTab("Đơn Vị Tính", donvitinh);
                tbphanghoa.setSelectedComponent(donvitinh);
            } else {
                tbphanghoa.setSelectedComponent(donvitinh);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btndonvitinhActionPerformed

    private void btnnhaphangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhaphangActionPerformed
        int i = tbpgiaodich.indexOfComponent(nhaphang);
        try {
            if (nhaphang == null) {
                nhaphang = new frmNhapHang();
                tbpgiaodich.addTab("Nhập Hàng", nhaphang);
                tbpgiaodich.setSelectedComponent(nhaphang);
            } else if (i == -1) {
                nhaphang = new frmNhapHang();
                tbpgiaodich.addTab("Nhập Hàng", nhaphang);
                tbpgiaodich.setSelectedComponent(nhaphang);
            } else {
                tbpgiaodich.setSelectedComponent(nhaphang);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnnhaphangActionPerformed

    private void btnbanhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbanhangActionPerformed
        int i = tbpgiaodich.indexOfComponent(banhang);
        try {
            if (banhang == null) {
                banhang = new frmBanHang();
                tbpgiaodich.addTab("Bán Hàng", banhang);
                tbpgiaodich.setSelectedComponent(banhang);
            } else if (i == -1) {
                banhang = new frmBanHang();
                tbpgiaodich.addTab("Bán Hàng", banhang);
                tbpgiaodich.setSelectedComponent(banhang);
            } else {
                tbpgiaodich.setSelectedComponent(banhang);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnbanhangActionPerformed

    private void btnphieuchiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnphieuchiActionPerformed
        // TODO add your handling code here:
        int i = tbpthongke.indexOfComponent(dsphieuchi);
        try {
            if (dsphieuchi == null) {
                dsphieuchi = new frmDanhSachPhieuChi();
                tbpthongke.addTab("Phiếu Chi", dsphieuchi);
                tbpthongke.setSelectedComponent(dsphieuchi);
            } else if (i == -1) {
                dsphieuchi = new frmDanhSachPhieuChi();
                tbpthongke.addTab("Phiếu Chi", dsphieuchi);
                tbpthongke.setSelectedComponent(dsphieuchi);
            } else {
                tbpthongke.setSelectedComponent(dsphieuchi);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnphieuchiActionPerformed

    private void btntrahangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntrahangActionPerformed
        // TODO add your handling code here:

        int i = tbpgiaodich.indexOfComponent(trahang);
        try {
            if (trahang == null) {
                trahang = new frmTraHang();
                tbpgiaodich.addTab("Trả Hàng", trahang);
                tbpgiaodich.setSelectedComponent(trahang);

            } else if (i == -1) {
                trahang = new frmTraHang();
                tbpgiaodich.addTab("Trả Hàng", trahang);
                tbpgiaodich.setSelectedComponent(trahang);
            } else {
                tbpgiaodich.setSelectedComponent(trahang);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btntrahangActionPerformed

    private void btnphieuthuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnphieuthuActionPerformed
        // TODO add your handling code here:
        int i = tbpthongke.indexOfComponent(dsphieuthu);
        try {
            if (dsphieuthu == null) {
                dsphieuthu = new frmDanhSachPhieuThu();
                tbpthongke.addTab("Phiếu Thu", dsphieuthu);
                tbpthongke.setSelectedComponent(dsphieuthu);
            } else if (i == -1) {
                dsphieuthu = new frmDanhSachPhieuThu();
                tbpthongke.addTab("Phiếu Thu", dsphieuthu);
                tbpthongke.setSelectedComponent(dsphieuthu);
            } else {
                tbpthongke.setSelectedComponent(dsphieuthu);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnphieuthuActionPerformed

    private void tblsanpham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsanpham1MouseClicked
        // TODO add your handling code here:
//        index = tblsanpham1.getSelectedRow();
//        if (evt.getClickCount() == 2) {
//            
//            edit();
//        }
    }//GEN-LAST:event_tblsanpham1MouseClicked

    private void cbonam1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbonam1ItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cbonam1ItemStateChanged

    private void rdotheobd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdotheobd1ActionPerformed
        // TODO add your handling code here:
        try {
            nam = Integer.parseInt(cbonam1.getSelectedItem().toString());
            dataset = new DefaultCategoryDataset();

            for (int i = 1; i < 13; i++) {
                PhieuChi pc = pcd.select_dt(i, nam);
                PhieuThu pt = ptd.select_dt(i, nam);
                String thang = String.valueOf(i);
                String na = String.valueOf(nam);
                if (pc == null) {
                    float gia = pt.getTienThu();
                    dataset.addValue(gia, na, thang);
                } else if (pt == null) {
                    float gia = -(pc.getTienNhapthuoc() + pc.getTienTraHang());
                    dataset.addValue(gia, na, thang);
                } else if (pt == null && pc == null) {
                    dataset.addValue(0, na, thang);
                } else {
                    float gia = pt.getTienThu();
                    float gia2 = pc.getTienNhapthuoc() + pc.getTienTraHang();
                    float giacuoi = gia - gia2;
                    dataset.addValue(giacuoi, na, thang);
                }
            }
            ChartPanel chartPanel = new ChartPanel(createChart(dataset));
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            JFrame frame = new JFrame();
            frame.add(chartPanel);
            frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
            frame.setSize(700, 500);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa Có Doanh Thu Theo Năm Nào");
        }
    }//GEN-LAST:event_rdotheobd1ActionPerformed

    private void rdotheobang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdotheobang1ActionPerformed
        // TODO add your handling code here:
//        List<PhieuChi> lpc = new ArrayList<>();
//        List<PhieuThu> lpt = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try {
            nam = Integer.parseInt(cbonam1.getSelectedItem().toString());
           
            for (int i = 1; i < 13; i++) {
                PhieuChi pc = pcd.select_dt(i, nam);
                PhieuThu pt = ptd.select_dt(i, nam);
                Vector v = new Vector();
                v.add(i);
                if (pt == null) {
                    v.add(0);
                } else {
                    v.add(pt.getTienThu());
                }

                if (pc == null) {
                    v.add(0);
                    v.add(0);
                    v.add(pt.getTienThu() - 0);
                } else {
                    v.add(pc.getTienTraHang());
                    v.add(pc.getTienNhapthuoc());
                    v.add(pt.getTienThu() - pc.getTongTienChi());
                }
                model.addRow(v);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa Có Doanh Thu Theo Năm Nào");
        }
    }//GEN-LAST:event_rdotheobang1ActionPerformed

    private void rdotheobang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdotheobang2ActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = (DefaultTableModel) tbltkds.getModel();
            int t = Integer.valueOf(cbothang.getSelectedItem().toString());
            int n = Integer.valueOf(cbonam2.getSelectedItem().toString());
            List<HoaDonBanCT> list = dao_hdbct.select_tk(t, n);
            model.setRowCount(0);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có doanh số của tháng " + t + " vào năm " + n + " này");
                return;
            }
            for (HoaDonBanCT x : list) {
                Vector v = new Vector();
                v.add(x.getMa_Thuoc());
                SanPhamThuoc sp = dao.findByID(x.getMa_Thuoc());
                Thuoc thuoc = dao_th.findByID(sp.getID_Thuoc());
                v.add(thuoc.getTen_Thuoc());
                v.add(x.getSoLuong());
                model.addRow(v);
            }

        } catch (Exception e) {
          JOptionPane.showMessageDialog(this, "Chưa Có Doanh Số Theo Năm Nào");
        }
    }//GEN-LAST:event_rdotheobang2ActionPerformed

    private void rdotheobd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdotheobd2ActionPerformed
        // TODO add your handling code here:
        try {
            dataset = new DefaultCategoryDataset();
            int t = Integer.valueOf(cbothang.getSelectedItem().toString());
            int n = Integer.valueOf(cbonam2.getSelectedItem().toString());
            List<HoaDonBanCT> list = dao_hdbct.select_tk(t, n);
            if (list == null) {
                JOptionPane.showMessageDialog(this, "Không có doanh số của tháng " + t + " vào năm " + n + " này");
                return;
            }
            for (HoaDonBanCT x : list) {
                SanPhamThuoc sp = dao.findByID(x.getMa_Thuoc());
                Thuoc thuoc = dao_th.findByID(sp.getID_Thuoc());
                dataset.addValue(x.getSoLuong(), String.valueOf(x.getMa_Thuoc()), thuoc.getTen_Thuoc());
            }
            ChartPanel chartPanel = new ChartPanel(createChart1(dataset));
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            JFrame frame = new JFrame();
            frame.add(chartPanel);
            frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
            frame.setSize(700, 500);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa Có Doanh Số Theo Năm Nào");
        }
    }//GEN-LAST:event_rdotheobd2ActionPerformed

    private void btndshdthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndshdthActionPerformed
        // TODO add your handling code here:
        int i = tbpgiaodich.indexOfComponent(dshdth);
        try {
            if (dshdth == null) {
                dshdth = new frmDanhSachHoaDonTH();
                tbpgiaodich.addTab("Danh Sách HD Trả Hàng", dshdth);
                tbpgiaodich.setSelectedComponent(dshdth);
            } else if (i == -1) {
                dshdth = new frmDanhSachHoaDonTH();
                tbpgiaodich.addTab("Danh Sách HD Trả Hàng", dshdth);
                tbpgiaodich.setSelectedComponent(dshdth);
            } else {
                tbpgiaodich.setSelectedComponent(dshdth);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btndshdthActionPerformed

    private void pnlkhachhangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlkhachhangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlkhachhangKeyPressed

    private void pnlkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlkhachhangMouseClicked
        // TODO add your handling code here:
        int i = tbpDoiTac.indexOfComponent(khachHang);
        try {

            if (khachHang == null) {
                khachHang = new TTKhachHang();
                tbpDoiTac.addTab("Thông Tin khách hàng", khachHang);
            } else if (i == -1) {
                khachHang = new TTKhachHang();
                tbpDoiTac.addTab("Thông Tin khách hàng", khachHang);
            } else {
                tbpDoiTac.setSelectedComponent(khachHang);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        int i2 = tbpDoiTac.indexOfComponent(NhaCC);
        try {

            if (NhaCC == null) {
                NhaCC = new TTNhaCungCap();
                tbpDoiTac.addTab("Thông tin nhà cung cấp", NhaCC);
            } else if (i == -1) {
                NhaCC = new TTNhaCungCap();
                tbpDoiTac.addTab("Thông tin nhà cung cấp", NhaCC);
            } else {
                tbpDoiTac.setSelectedComponent(NhaCC);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        int i3 = tbpNguoiDung.indexOfComponent(nguoidung);
        try {

            if (nguoidung == null) {
                nguoidung = new TTNguoiDung();
                tbpNguoiDung.addTab("Thông Tin khách hàng", nguoidung);
            } else if (i == -1) {
                nguoidung = new TTNguoiDung();
                tbpNguoiDung.addTab("Thông Tin khách hàng", nguoidung);
            } else {
                tbpNguoiDung.setSelectedComponent(nguoidung);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        Infor ifor = new Infor(); 
        pnltt.add(ifor);
    }//GEN-LAST:event_pnlkhachhangMouseClicked

    private void btndshdnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndshdnhActionPerformed
        // TODO add your handling code here:
        int i = tbpgiaodich.indexOfComponent(dshdnh);
        try {
            if (dshdnh == null) {
                dshdnh = new frmDanhSachPhieuNhap();
                tbpgiaodich.addTab("Danh Sách HD Nhập Hàng", dshdnh);
                tbpgiaodich.setSelectedComponent(dshdnh);

            } else if (i == -1) {
                dshdnh = new frmDanhSachPhieuNhap();
                tbpgiaodich.addTab("Danh Sách HD Nhập Hàng", dshdnh);
                tbpgiaodich.setSelectedComponent(dshdnh);
            } else {
                tbpgiaodich.setSelectedComponent(dshdnh);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btndshdnhActionPerformed

    private void btndshdbhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndshdbhActionPerformed
        // TODO add your handling code here:
        int i = tbpgiaodich.indexOfComponent(dshdb);
        try {
            if (dshdb == null) {
                dshdb = new frmDanhSachHDBan();
                tbpgiaodich.addTab("Danh Sách HD Bán", dshdb);
                tbpgiaodich.setSelectedComponent(dshdb);

            } else if (i == -1) {
                dshdb = new frmDanhSachHDBan();
                tbpgiaodich.addTab("Danh Sách HD Bán", dshdb);
                tbpgiaodich.setSelectedComponent(dshdb);
            } else {
                tbpgiaodich.setSelectedComponent(dshdb);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btndshdbhActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        index3 = tbpthongke.getSelectedIndex();
        if (index3 < 0) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Tab Cần Xóa");
            return;
        } else {
            tbpthongke.removeTabAt(index3);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        ShareHepler.User = 2;
        DangNhap dn = new DangNhap(this, true);
        dn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
       new frmDoiMatKhau().setVisible(true);
    }//GEN-LAST:event_jButton15ActionPerformed
    private void setEnabel(boolean tt) {
        pnlthongke.setEnabled(tt);
    }

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
            java.util.logging.Logger.getLogger(Display.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Display.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Display.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Display.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Display().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbanhang;
    private javax.swing.JButton btndonvitinh;
    private javax.swing.JButton btndshdbh;
    private javax.swing.JButton btndshdnh;
    private javax.swing.JButton btndshdth;
    private javax.swing.JButton btnnhaphang;
    private javax.swing.JButton btnnhomthuoc;
    private javax.swing.JButton btnphieuchi;
    private javax.swing.JButton btnphieuthu;
    private javax.swing.JButton btnspthuoc;
    private javax.swing.JButton btnthongtinthuoc;
    private javax.swing.JButton btntrahang;
    private javax.swing.JButton btntuthuoc;
    private javax.swing.JButton btnxoatabHH;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbonam1;
    private javax.swing.JComboBox<String> cbonam2;
    private javax.swing.JComboBox<String> cbothang;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel pnldoanhthu1;
    private javax.swing.JTabbedPane pnlkhachhang;
    private javax.swing.JPanel pnlnguoidung;
    private javax.swing.JPanel pnlthongke;
    private javax.swing.JPanel pnltt;
    private javax.swing.JRadioButton rdotheobang1;
    private javax.swing.JRadioButton rdotheobang2;
    private javax.swing.JRadioButton rdotheobd1;
    private javax.swing.JRadioButton rdotheobd2;
    private javax.swing.JTable tblsanpham1;
    private javax.swing.JTable tbltkds;
    private javax.swing.JTabbedPane tbpDoiTac;
    private javax.swing.JTabbedPane tbpNguoiDung;
    private javax.swing.JTabbedPane tbpgiaodich;
    private javax.swing.JTabbedPane tbphanghoa;
    private javax.swing.JTabbedPane tbpthongke;
    // End of variables declaration//GEN-END:variables
}
