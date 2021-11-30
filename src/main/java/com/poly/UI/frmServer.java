package com.poly.UI;

import com.poly.Jdbc.DatabaseHelper;
import com.poly.Jdbc.SQLConnect;
import com.poly.Jdbc.hostt;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class frmServer extends javax.swing.JFrame {

    static List<hostt> list = new ArrayList<>();

    public frmServer() {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        btnSave.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new javax.swing.JTabbedPane();
        pnlketnoi = new javax.swing.JPanel();
        pnlnhap = new javax.swing.JPanel();
        txtPort = new javax.swing.JTextField();
        txtDatabase = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        txtUserName = new javax.swing.JTextField();
        txtHost = new javax.swing.JTextField();
        lblhost = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();
        lblpassword = new javax.swing.JLabel();
        lbldatabase = new javax.swing.JLabel();
        lblport = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnTest = new javax.swing.JButton();
        lblTimeServer = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblhost2 = new javax.swing.JLabel();
        lblPort2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Khách Sạn Tokudark");
        setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tab.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tab.setName("tab"); // NOI18N

        pnlketnoi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pnlketnoi.setName("pnlketnoi"); // NOI18N

        pnlnhap.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pnlnhap.setName("pnlnhap"); // NOI18N

        txtPort.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPort.setName("txtPort"); // NOI18N
        txtPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPortActionPerformed(evt);
            }
        });
        txtPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPortKeyTyped(evt);
            }
        });

        txtDatabase.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDatabase.setName("txtDatabase"); // NOI18N

        txtPassword.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPassword.setName("txtPassword"); // NOI18N

        txtUserName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtUserName.setName("txtUserName"); // NOI18N

        txtHost.setEditable(false);
        txtHost.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtHost.setName("txtHost"); // NOI18N
        txtHost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHostKeyTyped(evt);
            }
        });

        lblhost.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblhost.setText("Host:");
        lblhost.setName("lblhost"); // NOI18N

        lbluser.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbluser.setText("UserName:");
        lbluser.setName("lbluser"); // NOI18N

        lblpassword.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblpassword.setText("Password:");
        lblpassword.setName("lblpassword"); // NOI18N

        lbldatabase.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbldatabase.setText("Database:");
        lbldatabase.setName("lbldatabase"); // NOI18N

        lblport.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblport.setText("Port:");
        lblport.setName("lblport"); // NOI18N

        javax.swing.GroupLayout pnlnhapLayout = new javax.swing.GroupLayout(pnlnhap);
        pnlnhap.setLayout(pnlnhapLayout);
        pnlnhapLayout.setHorizontalGroup(
            pnlnhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlnhapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlnhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblport)
                    .addComponent(lblhost)
                    .addComponent(lbldatabase)
                    .addComponent(lblpassword)
                    .addComponent(lbluser))
                .addGap(66, 66, 66)
                .addGroup(pnlnhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword)
                    .addComponent(txtUserName)
                    .addComponent(txtPort)
                    .addComponent(txtDatabase)
                    .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        pnlnhapLayout.setVerticalGroup(
            pnlnhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlnhapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlnhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlnhapLayout.createSequentialGroup()
                        .addComponent(lblhost)
                        .addGap(13, 13, 13)
                        .addComponent(lbluser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblpassword))
                    .addGroup(pnlnhapLayout.createSequentialGroup()
                        .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlnhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldatabase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlnhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblport)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        btnSave.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.setName("btnSave"); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnTest.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnTest.setText("Test Connect");
        btnTest.setName("btnTest"); // NOI18N
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });

        lblTimeServer.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTimeServer.setText("Time System:");
        lblTimeServer.setName("lblTimeServer"); // NOI18N

        lblStatus.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblStatus.setText("Server is stop ...");
        lblStatus.setName("lblStatus"); // NOI18N

        lblhost2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblhost2.setText("Host:");
        lblhost2.setName("lblhost2"); // NOI18N

        lblPort2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblPort2.setText("Port:");
        lblPort2.setName("lblPort2"); // NOI18N

        javax.swing.GroupLayout pnlketnoiLayout = new javax.swing.GroupLayout(pnlketnoi);
        pnlketnoi.setLayout(pnlketnoiLayout);
        pnlketnoiLayout.setHorizontalGroup(
            pnlketnoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlketnoiLayout.createSequentialGroup()
                .addGroup(pnlketnoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlketnoiLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(lblStatus))
                    .addGroup(pnlketnoiLayout.createSequentialGroup()
                        .addGroup(pnlketnoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlketnoiLayout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(btnSave)
                                .addGap(28, 28, 28)
                                .addComponent(btnTest))
                            .addGroup(pnlketnoiLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(pnlketnoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPort2)
                                    .addComponent(lblhost2))))
                        .addGap(38, 38, 38)
                        .addComponent(lblTimeServer))
                    .addGroup(pnlketnoiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlketnoiLayout.setVerticalGroup(
            pnlketnoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlketnoiLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(pnlnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlketnoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave)
                    .addComponent(btnTest)
                    .addGroup(pnlketnoiLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblTimeServer)))
                .addGap(6, 6, 6)
                .addComponent(lblStatus)
                .addGap(11, 11, 11)
                .addComponent(lblPort2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblhost2)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        tab.addTab("Kết Nối", pnlketnoi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab)
        );

        setSize(new java.awt.Dimension(507, 500));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
  public void init() {
        lblTimeServer.setVisible(false);
        lblhost2.setVisible(false);
        lblPort2.setVisible(false);
    }

    

    public void save() throws UnknownHostException {
        InetAddress inet = InetAddress.getLocalHost();
        ObjectOutputStream obj = null;
        try {
            // mở luồng
            obj = new ObjectOutputStream(new FileOutputStream("host.txt"));
            // thực hiện ghi
            list.add(new hostt(inet.getHostName(), txtUserName.getText(), txtPassword.getText(), txtDatabase.getText(), txtPort.getText()));
            obj.writeObject(list);
            JOptionPane.showMessageDialog(this, "Ghi thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Ghi" + e);
        } finally {
            try {
                // xả luồng
                obj.flush();
                // đóng luồng 
                obj.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi Đóng Luồng" + e);
            }

        }
    }

  

    public void test() {
        try {
            DatabaseHelper.host = txtHost.getText();
            DatabaseHelper.user = txtUserName.getText();
            DatabaseHelper.pass = txtPassword.getText();
            DatabaseHelper.database = txtDatabase.getText();
            DatabaseHelper.port = txtPort.getText();
            Connection _con = SQLConnect.getcon();
            PreparedStatement ps = _con.prepareStatement("SELECT CURRENT_TIMESTAMP");
            ResultSet testtime = ps.executeQuery();
            lblTimeServer.setVisible(true);
            testtime.next();
            lblTimeServer.setText(lblTimeServer.getText() + " " + testtime.getString(1));
            JOptionPane.showMessageDialog(new JFrame(), "Kết Nối Với Cơ Sở Dữ Liệu Thành Công", "Test Connect", JOptionPane.INFORMATION_MESSAGE);
            lblStatus.setText("server is connecting");
            lblhost2.setVisible(true);
            lblPort2.setVisible(true);
            lblhost2.setText("Host: " + txtHost.getText());
            lblPort2.setText("Port: " + txtPort.getText());
            btnSave.setEnabled(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Kết Nối Với Cơ Sở Dữ Liệu Thất Bại", "Test Connect", JOptionPane.INFORMATION_MESSAGE);
            btnSave.setEnabled(false);
        }
    }

  

private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

    try {
        save();
    } catch (Exception e) {
    }

}//GEN-LAST:event_btnSaveActionPerformed

private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
    test();
}//GEN-LAST:event_btnTestActionPerformed


private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown


}//GEN-LAST:event_formComponentShown

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
// TODO add your handling code here:
//    if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát không ?", "Question", JOptionPane.YES_NO_OPTION) != 1) {
//        this.setDefaultCloseOperation(frmServer.EXIT_ON_CLOSE);
//    }
    new DangNhap(this,true).setVisible(true);
}//GEN-LAST:event_formWindowClosing

private void txtPortKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPortKeyTyped
    char c = evt.getKeyChar();
    if (!Character.isDigit(c)) {
        evt.consume();
    }
    // TODO add your handling code here:
}//GEN-LAST:event_txtPortKeyTyped

private void txtHostKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHostKeyTyped
    char c = evt.getKeyChar();
    if (!Character.isDigit(c) && c != '.') {
        evt.consume();
    }
    // TODO add your handling code here:
}//GEN-LAST:event_txtHostKeyTyped

    private void txtPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPortActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            InetAddress inet = InetAddress.getLocalHost();
            txtHost.setText(inet.getHostName());
//            read();
//            init();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowOpened
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frmServer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTest;
    private javax.swing.JLabel lblPort2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTimeServer;
    private javax.swing.JLabel lbldatabase;
    private javax.swing.JLabel lblhost;
    private javax.swing.JLabel lblhost2;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel lblport;
    private javax.swing.JLabel lbluser;
    private javax.swing.JPanel pnlketnoi;
    private javax.swing.JPanel pnlnhap;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTextField txtDatabase;
    private javax.swing.JTextField txtHost;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
