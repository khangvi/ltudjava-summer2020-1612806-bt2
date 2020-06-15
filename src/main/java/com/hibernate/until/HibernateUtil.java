package com.hibernate.until;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            return con.buildSessionFactory(reg);
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
    
    public SinhVien themSinhVienVaoLop(String malop, String mssv, String hoten, String gioitinh, String cmnd) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        if(session.get(SinhVien.class, mssv)!=null)
            return null;
        
        LopHoc lop = session.get(LopHoc.class, malop);
        SinhVien sv = new SinhVien(mssv, hoten, gioitinh, cmnd);
        sv.setTaikhoan(taoTaiKhoan(mssv, mssv));
        sv.setLh_sv(lop);
        session.save(sv);         
        tx.commit();
        return sv;
    }
}