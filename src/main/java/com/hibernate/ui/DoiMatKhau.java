package com.hibernate.ui;

import com.hibernate.entity.TaiKhoan;
import com.hibernate.until.HibernateUtil;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DoiMatKhau extends JFrame {

    private JPanel contentPane;
    private JPasswordField password_old;
    private JPasswordField password_new;
    private JPasswordField password_confirm;

    public DoiMatKhau(final String msg) { 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Đổi mật khẩu");
        setBounds(100, 100, 408, 329);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl_changepw = new JLabel("Đổi mật khẩu");
        lbl_changepw.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_changepw.setBounds(168, 76, 150, 31);
        contentPane.add(lbl_changepw);

        JLabel lbl_oldpassword = new JLabel("Mật khẩu hiện tại");
        lbl_oldpassword.setBounds(10, 142, 119, 14);
        contentPane.add(lbl_oldpassword);

        JLabel lbl_newpassword = new JLabel("Mật khẩu mới");
        lbl_newpassword.setBounds(10, 170, 84, 14);
        contentPane.add(lbl_newpassword);

        password_old = new JPasswordField();
        password_old.setBounds(138, 139, 195, 20);
        contentPane.add(password_old);

        JLabel lbl_icon = new JLabel("");
        lbl_icon.setIcon(new ImageIcon(getClass().getResource("/image/icon_changepw.png")));
        lbl_icon.setBounds(41, 11, 100, 100);
        contentPane.add(lbl_icon);

        password_new = new JPasswordField();
        password_new.setBounds(138, 167, 195, 20);
        contentPane.add(password_new);

        JButton btn_changepw = new JButton("Đổi mật khẩu");
        btn_changepw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String oldpw = String.valueOf(password_old.getPassword());
                String newpw = String.valueOf(password_new.getPassword());
                String confirmpw = String.valueOf(password_confirm.getPassword());

                if (oldpw.equals("") || newpw.equals("") || confirmpw.equals(""))
                    JOptionPane.showMessageDialog(rootPane, "Không được bỏ trống các dòng thông tin");
                    
                else if (!newpw.equals(confirmpw)) {
                    JOptionPane.showMessageDialog(rootPane, "Nhập lại mật khẩu mới không đúng");
                } 
                else {
                    TaiKhoan taikhoan = new HibernateUtil().layThongTinTaiKhoan(msg);

                    if (taikhoan.getPass_word().equals(oldpw)) {
                        new HibernateUtil().doiMatKhauTK(msg, newpw);
                        JOptionPane.showMessageDialog(rootPane, "Đổi mật khẩu thành công");
                        setVisible(false);
                    }
                    else
                        JOptionPane.showMessageDialog(rootPane, "Mật khẩu hiện tại không đúng");
                }
            }
        });
        btn_changepw.setBounds(138, 226, 113, 23);
        contentPane.add(btn_changepw);

        JButton btn_cancel = new JButton("Hủy bỏ");
        btn_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btn_cancel.setBounds(261, 226, 72, 23);
        contentPane.add(btn_cancel);

        JLabel lbl_confirmpw = new JLabel("Nhập lại mật khẩu mới");
        lbl_confirmpw.setBounds(10, 195, 133, 14);
        contentPane.add(lbl_confirmpw);

        password_confirm = new JPasswordField();
        password_confirm.setBounds(138, 195, 195, 20);
        contentPane.add(password_confirm);

        setLocationRelativeTo(null);
    }
}
