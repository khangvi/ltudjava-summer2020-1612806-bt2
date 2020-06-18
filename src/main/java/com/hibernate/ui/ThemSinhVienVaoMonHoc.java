package com.hibernate.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hibernate.until.HibernateUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ThemSinhVienVaoMonHoc extends JDialog {

    private GiaoDienGiaoVu home;
    private final JPanel contentPanel = new JPanel();
    private JTextField textField;

    public ThemSinhVienVaoMonHoc(Frame parent, boolean modal, String mamon) {
        super(parent, modal);
        home = (GiaoDienGiaoVu) parent;
        setTitle("Thêm sinh viên");
        setBounds(100, 100, 293, 131);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Mssv:");
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblNewLabel.setBounds(10, 28, 46, 14);
        contentPanel.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(52, 26, 215, 20);
        contentPanel.add(textField);
        textField.setColumns(10);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Xác nhận");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String mssv = textField.getText();
                        if(mssv.equals(""))
                            JOptionPane.showMessageDialog(rootPane, "MSSV không được trống");
                        else {
                            int result = new HibernateUtil().themSinhVienVaoMonHoc(mssv, mamon);
                            if(result==-1)
                                JOptionPane.showMessageDialog(rootPane, "Sinh viên không tồn tại");
                            else if(result ==0)
                                JOptionPane.showMessageDialog(rootPane, "Sinh viên đã được thêm trước đó");
                            else {
                                home.btn_XemDanhSachMon();
                                JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
                                dispose();
                            }
                        }
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Hủy");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        setLocationRelativeTo(null);
    }
}
