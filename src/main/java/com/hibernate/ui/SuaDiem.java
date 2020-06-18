package com.hibernate.ui;

import java.awt.BorderLayout;
import com.hibernate.until.HibernateUtil;

import com.hibernate.entity.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SuaDiem extends JDialog {

    private GiaoDienGiaoVu home;
    private final JPanel contentPanel = new JPanel();
    private JTextField txt_hoten;
    private JTextField txt_mssv;
    private JTextField txt_gk;
    private JTextField txt_tong;
    private JTextField txt_khac;
    private JTextField txt_ck;

    public SuaDiem(Frame parent, boolean modal, String id) {
        super(parent, modal);
        home = (GiaoDienGiaoVu) parent;
        setTitle("Sửa điểm sinh viên");
        setBounds(100, 100, 425, 461);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Họ tên:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel.setBounds(35, 136, 46, 14);
        contentPanel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Giữa kỳ:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1.setBounds(35, 186, 46, 14);
        contentPanel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Cuối kỳ:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_2.setBounds(35, 236, 46, 14);
        contentPanel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Điểm khác:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_3.setBounds(35, 286, 63, 14);
        contentPanel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Mssv:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_4.setBounds(35, 86, 46, 14);
        contentPanel.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Điểm tổng:");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_5.setBounds(35, 336, 66, 14);
        contentPanel.add(lblNewLabel_5);

        txt_hoten = new JTextField();
        txt_hoten.setBounds(111, 129, 235, 29);
        contentPanel.add(txt_hoten);
        txt_hoten.setColumns(10);
        txt_hoten.setEditable(false);

        txt_mssv = new JTextField();
        txt_mssv.setBounds(111, 79, 235, 29);
        contentPanel.add(txt_mssv);
        txt_mssv.setColumns(10);
        txt_mssv.setEditable(false);

        txt_gk = new JTextField();
        txt_gk.setBounds(111, 179, 235, 29);
        contentPanel.add(txt_gk);
        txt_gk.setColumns(10);

        txt_tong = new JTextField();
        txt_tong.setBounds(111, 329, 235, 29);
        contentPanel.add(txt_tong);
        txt_tong.setColumns(10);

        txt_khac = new JTextField();
        txt_khac.setBounds(111, 279, 235, 29);
        contentPanel.add(txt_khac);
        txt_khac.setColumns(10);

        txt_ck = new JTextField();
        txt_ck.setBounds(111, 229, 235, 29);
        contentPanel.add(txt_ck);
        txt_ck.setColumns(10);

        txt_mssv.setText(id.split("-")[2]);
        DiemSo diem = new HibernateUtil().layDiemTheoID(id);
        txt_hoten.setText(diem.getHoten());
        txt_gk.setText(String.valueOf(diem.getDiemgk()));
        txt_ck.setText(String.valueOf(diem.getDiemck()));
        txt_khac.setText(String.valueOf(diem.getDiemkhac()));
        txt_tong.setText(String.valueOf(diem.getDiemtong()));

        JLabel lblNewLabel_6 = new JLabel("Sửa điểm");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_6.setBounds(174, 19, 99, 49);
        contentPanel.add(lblNewLabel_6);

        JButton btn_confirm = new JButton("Xác nhận");
        btn_confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mssv = txt_mssv.getText();
                String gk = txt_gk.getText();
                String ck = txt_ck.getText();
                String khac = txt_khac.getText();
                String tong = txt_tong.getText();

                if (mssv.equals("") || gk.equals("") || ck.equals("") || khac.equals("") || tong.equals(""))
                    JOptionPane.showMessageDialog(rootPane, "Điểm không được trống");
                else {
                    try {
                        float diemgk = Float.parseFloat(gk);
                        float diemck = Float.parseFloat(ck);
                        float diemkhac = Float.parseFloat(khac);
                        float diemtong = Float.parseFloat(tong);
                        if (new HibernateUtil().suaDiemSV(id, diemgk, diemck, diemkhac, diemtong)) {
                            JOptionPane.showMessageDialog(rootPane, "Sửa điểm thành công!");
                            home.btn_XemDanhSachDiem();
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Có lỗi! Điểm chưa cập nhật thành công");
                        }
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(rootPane, "Điểm phải là số thực");
                    }
                }

            }
        });
        btn_confirm.setBounds(184, 369, 89, 23);
        contentPanel.add(btn_confirm);

        JButton btn_cancel = new JButton("Hủy");
        btn_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btn_cancel.setBounds(278, 369, 66, 23);
        contentPanel.add(btn_cancel);
        setLocationRelativeTo(null);
    }
}
