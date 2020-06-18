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
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ImportLopHoc extends JDialog {

    private GiaoDienGiaoVu home;
    private final JPanel contentPanel = new JPanel();
    private JTextField txt_filepath;

    public ImportLopHoc(Frame parent, boolean modal) {
        super(parent, modal);
        home = (GiaoDienGiaoVu) parent;
        setTitle("Import lớp học");
        setBounds(100, 100, 375, 138);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nhập đường dẫn:");
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
        lblNewLabel.setBounds(10, 33, 98, 20);
        contentPanel.add(lblNewLabel);

        txt_filepath = new JTextField();
        txt_filepath.setBounds(102, 32, 231, 23);
        contentPanel.add(txt_filepath);
        txt_filepath.setColumns(10);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        fun_btnOK();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
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

    public void fun_btnOK() {
        String filePath = txt_filepath.getText();
        if (filePath.equals(""))
            JOptionPane.showMessageDialog(rootPane, "Đường dẫn không được bỏ trống");
        else {
            if (new HibernateUtil().importDanhSachLop(filePath)) {
                home.cbb_themMaLopVaoCBB();
                JOptionPane.showMessageDialog(rootPane, "Import thành công!");
                dispose();               
            } else
                JOptionPane.showMessageDialog(rootPane, "Đường dẫn hoặc file không đúng định dạng");
        }
    }
}
