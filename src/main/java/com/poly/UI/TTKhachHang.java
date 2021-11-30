/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.UI;
import com.poly.dao.KhachHangDAO;
import com.poly.model.KhachHang;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import com.poly.Jdbc.SQLConnect;
import com.poly.helper.ShareHepler;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Quan_d_bon
 */
public class TTKhachHang extends javax.swing.JPanel {

    KhachHangDAO dao = new KhachHangDAO();
    int index;
    public TTKhachHang()throws Exception {
        initComponents();
        checklogin();
        loaddata1();
        
    }

    public void checklogin(){
        if(ShareHepler.User == 1){
            btnTimkiemKH.setEnabled(true);
            btnUpdateKH.setEnabled(true);
            btnlammoi.setEnabled(true);
            btnthemkh.setEnabled(true);
        }else if(ShareHepler.User == 0){
            btnTimkiemKH.setEnabled(true);
            btnUpdateKH.setEnabled(false);
            btnlammoi.setEnabled(true);
            btnthemkh.setEnabled(true);
        }else {
             btnTimkiemKH.setEnabled(false);
            btnUpdateKH.setEnabled(false);
            btnlammoi.setEnabled(false);
            btnthemkh.setEnabled(false);
        }
    }
    public void loaddata1(){
        try {
            DefaultTableModel model;
            model =(DefaultTableModel) tblKhachHang.getModel();
            List<KhachHang> list = dao.select();
            model.setRowCount(0);
            for (KhachHang x : list){
                Vector v = new Vector();
                v.add(x.getSDT_KH());
                v.add(x.getTen_KH());
                v.add(x.getEmail_KH());
                v.add(x.getDiaChi());
                model.addRow(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void setModel(KhachHang model){
        txtTenKH.setText(model.getTen_KH());
        txtSDT.setText(model.getSDT_KH());
        txtEmail.setText(model.getEmail_KH());
        txtDiachi.setText(model.getDiaChi());
        
    }
    private void reset(){
        setModel(new KhachHang());
        
    }
    private void edit(){
        try {
            KhachHang model = dao.findByID(tblKhachHang.getValueAt(index,0).toString());
            if(model != null){
                setModel(model);
            }
        } catch (Exception e) {
        }
    }
    private void TimKiem(){
        if(txtTimkhiemKH.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Bạn Chưa Nhập SDT Cần Tìm");
            return;
        }
        try {
            KhachHang model = dao.findByID(txtTimkhiemKH.getText());
            if(model == null){
                JOptionPane.showMessageDialog(this,"Khách Hàng Không Tồn Tại");
                return;
            }
            setModel(model);
        } catch (Exception e) {
        }
    }
    private void insert(){
         if(txtSDT.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Chưa nhập số điện thoại khách hàng ");
            return;
        }
        if(txtTenKH.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Chưa nhập tên khách hàng ");
            return;
        }
        if(txtDiachi.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Chưa nhập địa chỉ khách hàng ");
            return;
        }
        if(txtEmail.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Chưa nhập Email khách hàng ");
            return;
        }
        if (checkemail()) {
            JOptionPane.showMessageDialog(this, "Sai Định Dạng Email");
            return;
        }
        KhachHang model1=dao.findByID(txtSDT.getText());
        if(model1 !=null){
            JOptionPane.showMessageDialog(this,"Khách hàng đã tồn tại");
            return;
        }
        try {
            
            KhachHang model = getModel();
            dao.insert(model);
            loaddata1();
        } catch (Exception e) {
        }
    }
    private void update(){
        if(txtSDT.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Chưa nhập số điện thoại khách hàng ");
            return;
        }
        if(txtTenKH.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Chưa nhập tên khách hàng ");
            return;
        }
        if(txtDiachi.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Chưa nhập địa chỉ khách hàng ");
            return;
        }
        if(txtEmail.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Chưa nhập Email khách hàng ");
            return;
        }
        if (checkemail()) {
            JOptionPane.showMessageDialog(this, "Sai Định Dạng Email");
            return;
        }
        try {
            KhachHang model = getModel();
            dao.update(model);
            loaddata1();
        } catch (Exception e) {
        }
    }
    private KhachHang getModel(){
        KhachHang model = new KhachHang();
        model.setSDT_KH(txtSDT.getText());
        model.setTen_KH(txtTenKH.getText());
        model.setEmail_KH(txtEmail.getText());
        model.setDiaChi(txtDiachi.getText());
        return model;
    }
    private boolean checkemail() {
        String email = "^[\\w-_\\.+]*[\\w-_\\.]+(\\@+[\\w]+\\.)+([\\w+\\.])+([\\w])$";
        if (txtEmail.getText().matches(email)) {
            return false;
        } else {
            return true;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        txtTimkhiemKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDiachi = new javax.swing.JTextField();
        btnUpdateKH = new javax.swing.JButton();
        btnTimkiemKH = new javax.swing.JButton();
        btnthemkh = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();

        setLayout(null);

        jPanel10.setLayout(null);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblKhachHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số điện thoại", "Tên", "Email", "Đia chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setAlignmentX(1.0F);
        tblKhachHang.setAlignmentY(1.0F);
        tblKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblKhachHang.setFocusable(false);
        tblKhachHang.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhachHang.setSelectionBackground(new java.awt.Color(51, 153, 255));
        tblKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel15);
        jPanel15.setBounds(0, 0, 960, 580);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        txtTimkhiemKH.setBorder(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên khách hàng :");

        txtTenKH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTenKH.setBorder(null);
        txtTenKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTenKHKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Số điện thoại :");

        txtSDT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSDT.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Email :");

        txtEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtEmail.setBorder(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Địa chỉ :");

        txtDiachi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDiachi.setBorder(null);

        btnUpdateKH.setBackground(new java.awt.Color(30, 165, 252));
        btnUpdateKH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdateKH.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateKH.setText("Cập nhật");
        btnUpdateKH.setBorderPainted(false);
        btnUpdateKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateKHActionPerformed(evt);
            }
        });

        btnTimkiemKH.setBackground(new java.awt.Color(255, 255, 255));
        btnTimkiemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnTimkiemKH.setBorderPainted(false);
        btnTimkiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemKHActionPerformed(evt);
            }
        });

        btnthemkh.setBackground(new java.awt.Color(30, 165, 252));
        btnthemkh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnthemkh.setForeground(new java.awt.Color(255, 255, 255));
        btnthemkh.setText("Thêm khách hàng");
        btnthemkh.setBorderPainted(false);
        btnthemkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemkhActionPerformed(evt);
            }
        });

        btnlammoi.setBackground(new java.awt.Color(30, 165, 252));
        btnlammoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnlammoi.setForeground(new java.awt.Color(255, 255, 255));
        btnlammoi.setText("Làm mới");
        btnlammoi.setBorderPainted(false);
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKH)
                            .addComponent(txtSDT)
                            .addComponent(txtEmail)
                            .addComponent(txtDiachi)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(btnTimkiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2)
                                    .addComponent(txtTimkhiemKH)))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUpdateKH, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(btnlammoi, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(btnthemkh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 30, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator5)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimkhiemKH, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnTimkiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnthemkh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdateKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel10.add(jPanel16);
        jPanel16.setBounds(960, 0, 240, 580);

        add(jPanel10);
        jPanel10.setBounds(0, 0, 1200, 590);
    }// </editor-fold>//GEN-END:initComponents

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed
       reset();
       loaddata1();
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        index = tblKhachHang.getSelectedRow();
        edit();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnTimkiemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemKHActionPerformed

       TimKiem();
    }//GEN-LAST:event_btnTimkiemKHActionPerformed

    private void btnthemkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemkhActionPerformed
        insert();
    }//GEN-LAST:event_btnthemkhActionPerformed

    private void btnUpdateKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateKHActionPerformed
         int hoi = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Cập Nhật Không?", "Hói Cập Nhật", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (hoi == JOptionPane.YES_OPTION) {
            update();
        }
    }//GEN-LAST:event_btnUpdateKHActionPerformed

    private void txtTenKHKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKHKeyTyped
        // TODO add your handling code here:
//         char c = evt.getKeyChar();
//        if (!Character.isDigit(c)) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txtTenKHKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimkiemKH;
    private javax.swing.JButton btnUpdateKH;
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnthemkh;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimkhiemKH;
    // End of variables declaration//GEN-END:variables
}
