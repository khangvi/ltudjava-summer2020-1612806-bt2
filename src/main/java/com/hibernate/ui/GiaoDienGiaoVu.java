package com.hibernate.ui;

import com.hibernate.entity.*;
import com.hibernate.until.HibernateUtil;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GiaoDienGiaoVu extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JComboBox<String> combo_classID;
    private JTextField txt_filepath;
    private JComboBox<String> combo_subjectID;
    private JTable table_1;

    public GiaoDienGiaoVu() {
        setTitle("Giao diện giáo vụ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 735, 516);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 729, 477);
        contentPane.add(tabbedPane);

        JPanel tab_home = new JPanel();
        tab_home.setBackground(Color.WHITE);
        tabbedPane.addTab("Home", null, tab_home, null);
        tab_home.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("Xin chào");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        lblNewLabel_2.setBounds(333, 11, 75, 25);
        tab_home.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("<Giáo vụ>");
        lblNewLabel_3.setForeground(Color.BLUE);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_3.setBounds(412, 12, 69, 25);
        tab_home.add(lblNewLabel_3);
        
        JButton btn_changepassword = new JButton("Đổi mật khẩu");
        btn_changepassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DoiMatKhau("giaovu").setVisible(true);
            }
        });
        btn_changepassword.setBounds(494, 14, 110, 23);
        tab_home.add(btn_changepassword);
        
        JButton btn_signout = new JButton("Đăng xuất");
        btn_signout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DangNhap().setVisible(true);
            }
        });
        btn_signout.setBounds(614, 13, 100, 25);
        tab_home.add(btn_signout);

        JPanel tab_classmanager = new JPanel();
        tabbedPane.addTab("Quản lý lớp học", null, tab_classmanager, null);
        tab_classmanager.setLayout(null);

        combo_classID = new JComboBox<String>();
        combo_classID.setFont(new Font("Tahoma", Font.PLAIN, 11));
        cbb_themMaLopVaoCBB();
        combo_classID.setBounds(64, 60, 75, 30);
        tab_classmanager.add(combo_classID);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        scrollPane.setBounds(0, 103, 724, 304);
        tab_classmanager.add(scrollPane);

        table = new JTable();
        table.setBorder(null);
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        scrollPane.setViewportView(table);
        // table.setVisible(false);

        JLabel lbl_class = new JLabel("Lớp học:");
        lbl_class.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbl_class.setBounds(0, 68, 55, 14);
        tab_classmanager.add(lbl_class);

        JButton btn_add = new JButton("Thêm sinh viên");
        btn_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String malop = (String) combo_classID.getSelectedItem();
                if (malop.equals("----"))
                    JOptionPane.showMessageDialog(rootPane, "Chưa chọn lớp");
                else {
                    btn_them(malop);
                }
            }
        });
        btn_add.setBounds(583, 413, 131, 32);
        tab_classmanager.add(btn_add);

        JButton btn_showlist = new JButton("Xem danh sách lớp");
        btn_showlist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_xemDanhSachLop();
                btn_add.setEnabled(true);

            }
        });
        btn_showlist.setBounds(147, 59, 153, 32);
        tab_classmanager.add(btn_showlist);

        JButton btn_importclass = new JButton("Import danh sách lớp");
        btn_importclass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_ImportLopHoc();
            }
        });
        btn_importclass.setBounds(562, 11, 162, 32);
        tab_classmanager.add(btn_importclass);

        JButton btn_importschedule = new JButton("Import thời khóa biểu");
        btn_importschedule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_ImportTKB();
            }
        });
        btn_importschedule.setBounds(391, 11, 161, 32);
        tab_classmanager.add(btn_importschedule);

        JButton btnNewButton = new JButton("Xem thời khóa biểu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_xemThoiKhoaBieu();
                btn_add.setEnabled(false);
            }
        });
        btnNewButton.setBounds(309, 60, 146, 31);
        tab_classmanager.add(btnNewButton);

        JPanel tab_subject = new JPanel();
        tabbedPane.addTab("Quản lý môn học", null, tab_subject, null);
        tab_subject.setLayout(null);

        JScrollPane scrollpane_1 = new JScrollPane();
        scrollpane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        scrollpane_1.setBounds(0, 103, 724, 304);
        tab_subject.add(scrollpane_1);

        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        scrollpane_1.setViewportView(table_1);

        JLabel lblNewLabel = new JLabel("Môn học:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(0, 68, 66, 26);
        tab_subject.add(lblNewLabel);

        combo_subjectID = new JComboBox<String>();
        combo_subjectID.setBounds(63, 71, 117, 26);
        cbb_themMaMonHocVaoCBB();
        tab_subject.add(combo_subjectID);

        JButton btn_addstu = new JButton("Thêm");
        btn_addstu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_ThemSinhVienVaoMonHoc();
            }
        });
        btn_addstu.setBounds(10, 415, 82, 23);
        tab_subject.add(btn_addstu);

        JButton btn_delete = new JButton("Xóa");
        btn_delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_XoaSinhVienKhoiMonHoc();
            }
        });
        btn_delete.setBounds(102, 415, 89, 23);
        tab_subject.add(btn_delete);

        JButton btn_changemark = new JButton("Sửa điểm");
        btn_changemark.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_SuaDiem();
            }
        });
        btn_changemark.setBounds(625, 415, 89, 23);
        tab_subject.add(btn_changemark);

        txt_filepath = new JTextField();
        txt_filepath.setBounds(433, 7, 155, 23);
        tab_subject.add(txt_filepath);
        txt_filepath.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nhập đường dẫn:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
        lblNewLabel_1.setBounds(342, 11, 89, 14);
        tab_subject.add(lblNewLabel_1);

        JButton btn_importmark = new JButton("Import điểm");
        btn_importmark.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_ImportDiem();
            }
        });
        btn_importmark.setBounds(598, 5, 108, 26);
        tab_subject.add(btn_importmark);

        btn_changemark.setEnabled(false);
        btn_addstu.setEnabled(false);
        btn_delete.setEnabled(false);

        JButton btn_showsubjectlist = new JButton("Xem danh sách");
        btn_showsubjectlist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_XemDanhSachMon();
                btn_changemark.setEnabled(false);
                btn_addstu.setEnabled(true);
                btn_delete.setEnabled(true);
            }
        });
        btn_showsubjectlist.setBounds(190, 71, 127, 26);
        tab_subject.add(btn_showsubjectlist);

        JButton btn_showmark = new JButton("Xem điểm");
        btn_showmark.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_XemDanhSachDiem();
                btn_changemark.setEnabled(true);
                btn_addstu.setEnabled(false);
                btn_delete.setEnabled(false);
            }
        });
        btn_showmark.setBounds(321, 71, 95, 26);
        tab_subject.add(btn_showmark);

        setLocationRelativeTo(null);
    }

    public void btn_xemDanhSachLop() {
        String malop = (String) combo_classID.getSelectedItem();
        if (!malop.equals("----")) {
            List<SinhVien> list = new HibernateUtil().layDanhSachSinhVienTrongLop(malop);

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setColumnIdentifiers(new String[] { "STT", "MSSV", "Họ tên", "Giới tính", "CMND" });
            model.setRowCount(0);
            int i = 0;
            for (SinhVien sv : list) {
                model.addRow(new Object[] { ++i, sv.getMssv(), sv.getHoten(), sv.getGioitinh(), sv.getCmnd() });
            }
        } else
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn lớp cần xem");
    }

    public void btn_XemDanhSachMon() {
        String mamon = (String) combo_subjectID.getSelectedItem();
        if (!mamon.equals("----")) {
            List<SinhVien> list = new HibernateUtil().layDanhSachSVTrongMonHoc(mamon);

            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
            model.setColumnIdentifiers(new String[] { "STT", "MSSV", "Họ tên", "Giới tính", "CMND" });
            model.setRowCount(0);
            table_1.getColumnModel().getColumn(2).setPreferredWidth(100);

            int i = 0;
            for (SinhVien sv : list) {
                model.addRow(new Object[] { ++i, sv.getMssv(), sv.getHoten(), sv.getGioitinh(), sv.getCmnd() });
            }
        } else
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn lớp cần xem");
    }

    public void btn_xemThoiKhoaBieu() {
        String malop = (String) combo_classID.getSelectedItem();
        if (!malop.equals("----")) {
            List<MonHoc> list = new HibernateUtil().layDanhSachMonHoc(malop);

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setColumnIdentifiers(
                    new String[] { "STT", "Mã môn", "Tên môn", "Phòng học", "Tiết bắt đầu", "Tiết kết thúc" });
            model.setRowCount(0);
            table.getColumnModel().getColumn(2).setPreferredWidth(140);
            int i = 0;
            for (var mh : list) {
                model.addRow(new Object[] { ++i, mh.getMamon(), mh.getTenmon(), mh.getPhonghoc(), mh.getTietbatdau(),
                        mh.getTietketthuc() });
            }
        } else
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn lớp cần xem");
    }

    public void btn_XemDanhSachDiem() {
        String mamon = (String) combo_subjectID.getSelectedItem();
        if (!mamon.equals("----")) {
            List<DiemSo> list = new HibernateUtil().layDanhSachDiemSo(mamon);

            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
            model.setColumnIdentifiers(
                    new String[] { "STT", "MSSV", "Họ tên", "Điểm giữa kỳ", "Điểm cuối kỳ", "Điểm khác", "Điểm tổng" });
            model.setRowCount(0);
            table_1.getColumnModel().getColumn(2).setPreferredWidth(120);

            int stt = 0;
            for (var ds : list) {
                model.addRow(new Object[] { ++stt, ds.getSv().getMssv(), ds.getHoten(), ds.getDiemgk(), ds.getDiemck(),
                        ds.getDiemkhac(), ds.getDiemtong() });
            }
        } else
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn môn cần xem");
    }

    public void btn_XoaSinhVienKhoiMonHoc() {
        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
        int row = table_1.getSelectedRow();
        if (row != -1) {
            int confirm = JOptionPane.showConfirmDialog(rootPane, "Chắc chắn xóa?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String mssv = (String) table_1.getValueAt(row, 1);
                new HibernateUtil().xoaSVKhoiMonHoc(mssv, (String) combo_subjectID.getSelectedItem());
                model.removeRow(row);
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
            }
        } else
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn dòng xóa!");
    }

    public void cbb_themMaLopVaoCBB() {
        combo_classID.removeAllItems();
        combo_classID.addItem("----");
        List<LopHoc> list = new HibernateUtil().layDanhSachLopHoc();
        for (LopHoc l : list) {
            combo_classID.addItem(l.getMalop());
        }
    }

    public void cbb_themMaMonHocVaoCBB() {
        combo_subjectID.removeAllItems();
        combo_subjectID.addItem("----");
        List<MonHoc> list = new HibernateUtil().layDanhSachMonHoc();
        for (MonHoc mh : list) {
            combo_subjectID.addItem(mh.getMamon());
        }
    }

    public void btn_them(String malop) {
        ThemSinhVienVaoLop add = new ThemSinhVienVaoLop(this, rootPaneCheckingEnabled, malop);
        add.setVisible(true);
    }

    public void btn_ImportLopHoc() {
        ImportLopHoc imp = new ImportLopHoc(this, rootPaneCheckingEnabled);
        imp.setVisible(true);
    }

    public void btn_ImportTKB() {
        ImportThoiKhoaBieu imp = new ImportThoiKhoaBieu(this, rootPaneCheckingEnabled);
        imp.setVisible(true);
    }

    public void btn_ImportDiem() {
        String filePath = txt_filepath.getText();
        if (filePath.equals(""))
            JOptionPane.showMessageDialog(rootPane, "Đường dẫn không được rỗng");
        else {
            if (new HibernateUtil().import_BangDiem(filePath)) {
                JOptionPane.showMessageDialog(rootPane, "Thêm bảng điểm thành công");
                cbb_themMaMonHocVaoCBB();
                txt_filepath.setText("");
            } else
                JOptionPane.showMessageDialog(rootPane, "Đường dẫn hoặc file không đúng định dạng");
        }
    }

    public void btn_SuaDiem() {
        // DefaultTableModel model = (DefaultTableModel) table_1.getModel();
        int row = table_1.getSelectedRow();
        if (row != -1) {
            String id = combo_subjectID.getSelectedItem() + "-" + (String) table_1.getValueAt(row, 1);
            SuaDiem dialog = new SuaDiem(this, rootPaneCheckingEnabled, id);
            dialog.setVisible(true);
        } else
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn dòng để sửa");
    }
    
    public void btn_ThemSinhVienVaoMonHoc() {
        String mamon = (String) combo_subjectID.getSelectedItem();
        ThemSinhVienVaoMonHoc dialog = new ThemSinhVienVaoMonHoc(this, rootPaneCheckingEnabled, mamon);
        dialog.setVisible(true);
    }
}
