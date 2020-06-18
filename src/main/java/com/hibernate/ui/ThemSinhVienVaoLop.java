package com.hibernate.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import com.hibernate.until.HibernateUtil;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ThemSinhVienVaoLop extends JDialog {

    private GiaoDienGiaoVu home;
    private final JPanel contentPanel = new JPanel();
    private JTextField txt_id;
    private JTextField txt_name;
    private JTextField txt_gender;
    private JTextField txt_personid;

    public ThemSinhVienVaoLop(Frame parent, boolean modal, String malop) {
        super(parent, modal);
        home = (GiaoDienGiaoVu) parent;
        setTitle("Thêm sinh viên");
        setBounds(100, 100, 405, 357);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            txt_id = new JTextField();
            txt_id.setBounds(105, 106, 199, 25);
            contentPanel.add(txt_id);
            txt_id.setColumns(10);
        }
        {
            txt_name = new JTextField();
            txt_name.setBounds(105, 145, 199, 25);
            contentPanel.add(txt_name);
            txt_name.setColumns(10);
        }
        {
            txt_gender = new JTextField();
            txt_gender.setBounds(105, 185, 199, 25);
            contentPanel.add(txt_gender);
            txt_gender.setColumns(10);
        }
        {
            txt_personid = new JTextField();
            txt_personid.setBounds(105, 225, 199, 25);
            contentPanel.add(txt_personid);
            txt_personid.setColumns(10);
        }
        {
            JLabel lblNewLabel = new JLabel("MSSV:");
            lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
            lblNewLabel.setBounds(39, 106, 78, 20);
            contentPanel.add(lblNewLabel);
        }
        {
            JLabel lblNewLabel_1 = new JLabel("Họ tên:");
            lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
            lblNewLabel_1.setBounds(39, 145, 78, 20);
            contentPanel.add(lblNewLabel_1);
        }
        {
            JLabel lblNewLabel_2 = new JLabel("Giới tính: ");
            lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
            lblNewLabel_2.setBounds(39, 185, 67, 20);
            contentPanel.add(lblNewLabel_2);
        }
        {
            JLabel lblNewLabel_3 = new JLabel("CMND:");
            lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
            lblNewLabel_3.setBounds(39, 225, 86, 20);
            contentPanel.add(lblNewLabel_3);
        }
        {
            JButton btn_add = new JButton("Thêm");
            btn_add.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    themSinhVien(malop);
                }
            });
            btn_add.setBounds(157, 264, 78, 23);
            contentPanel.add(btn_add);
        }
        {
            JButton btn_cancel = new JButton("Hủy");
            btn_cancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            btn_cancel.setForeground(Color.RED);
            btn_cancel.setBounds(237, 264, 67, 23);
            contentPanel.add(btn_cancel);
        }
        {
            JLabel lblNewLabel_4 = new JLabel("NHẬP ĐẦY ĐỦ VÀ CHÍNH XÁC CÁC THÔNG TIN");
            lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
            lblNewLabel_4.setBounds(72, 48, 296, 47);
            contentPanel.add(lblNewLabel_4);
        }
        {
            JLabel lblNewLabel_5 = new JLabel("");
            lblNewLabel_5.setIcon(new ImageIcon("image\\add_icon.jpg"));
            lblNewLabel_5.setBounds(24, 48, 57, 47);
            contentPanel.add(lblNewLabel_5);
        }
        setLocationRelativeTo(null);
    }

    public void themSinhVien(String malop) {
        String mssv = txt_id.getText();
        String hoten = txt_name.getText();
        String gioitinh = txt_gender.getText();
        String cmnd = txt_personid.getText();

        if (mssv.equals("") || hoten.equals("") || gioitinh.equals("") || cmnd.equals(""))
            JOptionPane.showMessageDialog(rootPane, "Không được để trống thông tin");
        else if (gioitinh.length() > 3)
            JOptionPane.showMessageDialog(rootPane, "Giới tính phải là nam hoặc nữ");
        else if (cmnd.length() != 9)
            JOptionPane.showMessageDialog(rootPane, "CMND phải có 9 chữ số");
        else {
            if (new HibernateUtil().themSinhVienVaoLop(malop, mssv, hoten, gioitinh, cmnd) == null)
                JOptionPane.showMessageDialog(rootPane, "Sinh vien: " + mssv + " đã tồn tại");
            else {
                JOptionPane.showMessageDialog(rootPane, "Thêm sinh viên thành công!");
                home.btn_xemDanhSachLop();
                dispose();
            }
        }
    }
}
