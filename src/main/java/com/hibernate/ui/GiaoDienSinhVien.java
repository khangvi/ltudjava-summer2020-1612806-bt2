package com.hibernate.ui;

import com.hibernate.entity.*;
import com.hibernate.until.HibernateUtil;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class GiaoDienSinhVien extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public GiaoDienSinhVien(final String msg) {
        setTitle("Giao diện sinh vien");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl_showid = new JLabel("New label");
        lbl_showid.setForeground(Color.BLUE);
        lbl_showid.setBounds(476, 0, 96, 25);
        contentPane.add(lbl_showid);
        lbl_showid.setText(msg);

        JButton btn_signout = new JButton("Đăng xuất");
        btn_signout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new DangNhap().setVisible(true);
            }
        });
        btn_signout.setForeground(Color.RED);
        btn_signout.setBounds(685, 1, 95, 23);
        contentPane.add(btn_signout);

        JButton btn_changepw = new JButton("Đổi mật khẩu");
        btn_changepw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DoiMatKhau(msg).setVisible(true);
            }
        });
        btn_changepw.setBounds(561, 1, 114, 23);
        contentPane.add(btn_changepw);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 193, 778, 318);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {},
                new String[] { "STT", "Mã môn", "Tên môn", "Điểm giữa kỳ",
                        "Điểm cuối kỳ", "Điểm khác", "Điểm tổng" }) {
            boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(35);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.getColumnModel().getColumn(6).setResizable(false);

        int i = 0;
        List<DiemSo> list = new HibernateUtil().layDanhSachDiemSV(msg);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        for (DiemSo ds : list) {
            model.addRow(new Object[] { ++i, ds.getMh().getMamon(), ds.getMh().getTenmon(), ds.getDiemgk(),
                    ds.getDiemck(), ds.getDiemkhac(), ds.getDiemtong() });
        }

        scrollPane.setViewportView(table);

        JLabel lbl_scoretable = new JLabel("Bảng điểm");
        lbl_scoretable.setFont(new Font("Tahoma", Font.BOLD, 26));
        lbl_scoretable.setBounds(304, 129, 184, 53);
        contentPane.add(lbl_scoretable);

        JLabel lblNewLabel = new JLabel("Xin chào");
        lblNewLabel.setBounds(403, 5, 63, 14);
        contentPane.add(lblNewLabel);
        
        JLabel lbl_icon = new JLabel("");
        lbl_icon.setIcon(new ImageIcon(getClass().getResource("/image/icon_portal.png")));
        lbl_icon.setBounds(10, 5, 209, 177);
        contentPane.add(lbl_icon);
        setLocationRelativeTo(null);
    }
}
