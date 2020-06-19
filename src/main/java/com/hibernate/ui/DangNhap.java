package com.hibernate.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.hibernate.until.HibernateUtil;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class DangNhap extends JFrame {

    private JPanel contentPane;
    private JTextField signin_tf;
    private JPasswordField password_tf;
    private JLabel lbl_id;
    private JLabel lbl_password;
    private JButton btnNewButton;
    private JLabel lbl_icon_login;
    private JLabel lbl_login;

    public DangNhap() {
        setTitle("Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 394, 411);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lbl_id = new JLabel("Tên đăng nhập");
        lbl_id.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_id.setBounds(57, 189, 106, 25);
        contentPane.add(lbl_id);

        lbl_password = new JLabel("Mặt khẩu");
        lbl_password.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_password.setBounds(56, 248, 91, 28);
        contentPane.add(lbl_password);

        signin_tf = new JTextField();
        signin_tf.setBounds(57, 220, 233, 28);
        contentPane.add(signin_tf);
        signin_tf.setColumns(10);

        password_tf = new JPasswordField();
        password_tf.setBounds(57, 275, 233, 28);
        contentPane.add(password_tf);

        btnNewButton = new JButton("Đăng nhập");

        btnNewButton.setForeground(Color.BLUE);
        btnNewButton.setBounds(184, 314, 106, 23);
        contentPane.add(btnNewButton);

        lbl_icon_login = new JLabel("");
        lbl_icon_login.setIcon(new ImageIcon(getClass().getResource("/image/login_icon.png")));
        lbl_icon_login.setBounds(57, 15, 135, 163);
        contentPane.add(lbl_icon_login);

        lbl_login = new JLabel("Log-In");
        lbl_login.setFont(new Font("Tempus Sans ITC", Font.BOLD, 26));
        lbl_login.setBounds(202, 85, 91, 76);
        contentPane.add(lbl_login);
        setLocationRelativeTo(null);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = signin_tf.getText();
                String password = String.valueOf(password_tf.getPassword());
                if (id.equals("") || password.equals(""))
                    JOptionPane.showMessageDialog(rootPane, "Tên đăng nhập và mật khẩu không được rỗng!");
                else if (new HibernateUtil().kiemTraTK(id, password) == true) {
                    dispose();
                    if (id.equals("giaovu")) {
                        new GiaoDienGiaoVu().setVisible(true);
                    } else {
                        new GiaoDienSinhVien(id).setVisible(true);
                    }
                } else
                    JOptionPane.showMessageDialog(rootPane, "Tên đăng nhập hoặc mật khẩu không đúng!");
            }
        });
    }
}
