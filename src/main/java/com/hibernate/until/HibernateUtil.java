package com.hibernate.until;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.*;

public class HibernateUtil {
    static final SessionFactory factory = HibernateUtil.getSessionFactory();

    public static SessionFactory getSessionFactory() {
        try {
            Configuration con = new Configuration();
            con.configure();
            con.addAnnotatedClass(SinhVien.class);
            con.addAnnotatedClass(LopHoc.class);
            con.addAnnotatedClass(MonHoc.class);
            con.addAnnotatedClass(DiemSo.class);
            con.addAnnotatedClass(TaiKhoan.class);
            //ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            return con.buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Fail Configuration");
        }
        return null;
    }

    public boolean kiemTraTK(String id, String password) {
        Session session = factory.openSession();
        TaiKhoan tk = session.get(TaiKhoan.class, id);

        if (tk == null)
            return false;
        else {
            if (tk.getPass_word().equals(password))
                return true;
            else
                return false;
        }
    }

    public TaiKhoan taoTaiKhoan(String id, String password) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        TaiKhoan tk = new TaiKhoan(id, password);
        session.save(tk);
        tx.commit();

        return tk;
    }

    public TaiKhoan layThongTinTaiKhoan(String id) {
        Session session = factory.openSession();
        TaiKhoan tk = session.get(TaiKhoan.class, id);

        return tk;
    }

    public List<DiemSo> layDanhSachDiemSV(String id) {
        Session session = factory.openSession();
        List<DiemSo> list = session.get(SinhVien.class, id).getListds();
        return list;
    }

    public void doiMatKhauTK(String id, String newpassword) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        TaiKhoan tk = session.get(TaiKhoan.class, id);
        tk.setPass_word(newpassword);
        session.update(tk);
        tx.commit();
    }

    @SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
    public List<LopHoc> layDanhSachLopHoc() {
        Session session = factory.openSession();
        String hql = "from lophoc";
        Query q = session.createQuery(hql);
        List<LopHoc> list = q.list();

        return list;
    }

    public List<SinhVien> layDanhSachSinhVienTrongLop(String malop) {
        Session session = factory.openSession();
        List<SinhVien> list = session.get(LopHoc.class, malop).getListsv_lh();
        return list;
    }

    public boolean importDanhSachLop(String filePath) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        FileReader file;
        String line;

        try {
            file = new FileReader(filePath);
            BufferedReader csvReader = new BufferedReader(file);
            String malop = csvReader.readLine();
            LopHoc lophoc = new LopHoc(malop);
            if (session.get(LopHoc.class, malop) != null) {
                csvReader.close();
                return false;
            }
            session.save(lophoc);
            while ((line = csvReader.readLine()) != null) {
                if (line.equals(""))
                    break;

                String[] sv = line.split(",");
                SinhVien sinhvien = new SinhVien(sv[1], sv[2], sv[3], sv[4]);
                sinhvien.setLh_sv(lophoc);
                TaiKhoan tk = taoTaiKhoan(sinhvien.getMssv(), sinhvien.getMssv());
                sinhvien.setTaikhoan(tk);
                session.save(sinhvien);
            }
            csvReader.close();

        } catch (IOException e) {
            return false;
        }
        tx.commit();
        return true;
    }

    public boolean importThoiKhoaBieu(String filePath) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        FileReader file;
        String line;
        BufferedReader csvReader;

        try {
            file = new FileReader(filePath);
            csvReader = new BufferedReader(file);
            String malop = csvReader.readLine();
            LopHoc lophoc = session.get(LopHoc.class, malop);
            if (lophoc == null) {
                csvReader.close();
                return false;
            }
            while ((line = csvReader.readLine()) != null) {
                if (line.equals(""))
                    break;

                String[] string = line.split(",");
                MonHoc monhoc = new MonHoc(malop + "-" + string[1], string[2], string[3], Integer.parseInt(string[4]),
                        Integer.parseInt(string[5]));
                monhoc.setLh_mh(lophoc);
                List<SinhVien> listsv = lophoc.getListsv_lh();
                for (var sv : listsv) {
                    DiemSo d = new DiemSo(monhoc.getMamon() + "-" + sv.getMssv(), sv.getHoten(), 0, 0, 0, 0);
                    d.setSv(sv);
                    d.setMh(monhoc);
                    sv.getListmh_sv().add(monhoc);
                    session.save(d);
                }
                session.save(monhoc);
            }
            csvReader.close();
        } catch (IOException e) {
            return false;
        }
        tx.commit();
        return true;
    }

    public boolean import_BangDiem(String filePath) {
        Session session = factory.openSession();

        FileReader file;
        String line;
        BufferedReader csvReader;

        try {
            file = new FileReader(filePath);
            csvReader = new BufferedReader(file);
            String mamon = csvReader.readLine();
            MonHoc monhoc = session.get(MonHoc.class, mamon);
            if (monhoc == null) {
                csvReader.close();
                return false;
            }

            while ((line = csvReader.readLine()) != null) {
                if (line.equals(""))
                    break;

                String[] split = line.split(",");

                SinhVien sv = session.get(SinhVien.class, split[1]);
                if (sv == null) {
                    csvReader.close();
                    return false;
                }
                suaDiemSV(mamon + "-" + split[1], Float.parseFloat(split[3]), Float.parseFloat(split[4]),
                        Float.parseFloat(split[5]), Float.parseFloat(split[6]));
            }
            csvReader.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean suaDiemSV(String id, Float diemgk, Float diemck, Float diemkhac, Float diemtong) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        DiemSo diem = session.get(DiemSo.class, id);
        if (diem != null) {
            diem.setDiemgk(diemgk);
            diem.setDiemck(diemck);
            diem.setDiemkhac(diemkhac);
            diem.setDiemtong(diemtong);
            session.update(diem);
        } else
            return false;
        tx.commit();
        return true;
    }

    public void xoaSVKhoiMonHoc(String id, String mamon) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        SinhVien sv = session.get(SinhVien.class, id);
        MonHoc mh = session.get(MonHoc.class, mamon);
        DiemSo d = session.get(DiemSo.class, mamon + "-" + id);

        if (d != null)
            session.delete("diemso", d);
        sv.getListmh_sv().remove(mh);
        tx.commit();
    }

    @SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
    public List<MonHoc> layDanhSachMonHoc() {
        Session session = factory.openSession();
        String hql = "from monhoc";
        Query q = session.createQuery(hql);
        List<MonHoc> list = q.list();
        return list;
    }

    public List<MonHoc> layDanhSachMonHoc(String malop) {
        Session session = factory.openSession();
        List<MonHoc> list = session.get(LopHoc.class, malop).getListmh_lh();
        return list;
    }

    public List<SinhVien> layDanhSachSVTrongMonHoc(String mamon) {
        Session session = factory.openSession();
        List<SinhVien> list = session.get(MonHoc.class, mamon).getListsv_mh();
        return list;
    }

    public List<DiemSo> layDanhSachDiemSo(String mamon) {
        Session session = factory.openSession();
        List<DiemSo> list = session.get(MonHoc.class, mamon).getListds();
        return list;
    }

    public SinhVien themSinhVienVaoLop(String malop, String mssv, String hoten, String gioitinh, String cmnd) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        if (session.get(SinhVien.class, mssv) != null)
            return null;

        LopHoc lop = session.get(LopHoc.class, malop);
        SinhVien sv = new SinhVien(mssv, hoten, gioitinh, cmnd);
        sv.setTaikhoan(taoTaiKhoan(mssv, mssv));
        sv.setLh_sv(lop);
        session.save(sv);
        tx.commit();
        return sv;
    }

    public DiemSo layDiemTheoID(String id) {
        Session session = factory.openSession();
        DiemSo diem = session.get(DiemSo.class, id);
        return diem;
    }

    public int themSinhVienVaoMonHoc(String mssv, String mamon) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        SinhVien sv = session.get(SinhVien.class, mssv);
        if (sv == null)
            return -1;
        else {
            if (session.get(DiemSo.class, mamon + "-" + mssv) != null)
                return 0;
            else {
                MonHoc monhoc = session.get(MonHoc.class, mamon);
                sv.getListmh_sv().add(monhoc);
                DiemSo diem = new DiemSo(mamon + "-" + mssv, sv.getHoten(), 0, 0, 0, 0);
                diem.setSv(sv);
                diem.setMh(monhoc);
                session.save(diem);
                tx.commit();
                return 1;
            }
        }
    }
}