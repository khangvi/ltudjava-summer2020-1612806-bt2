package com.hibernate.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="sinhvien")
public class SinhVien implements Serializable {
    @Id
    private String mssv;
    private String hoten;
    private String gioitinh;
    private String cmnd;
    
    @OneToOne
    private TaiKhoan taikhoan;
    @ManyToOne
    private LopHoc lh_sv;
    @OneToMany(mappedBy="sv")
    private List<DiemSo> listds;
    @ManyToMany
    private List<MonHoc> listmh_sv;
    
    public SinhVien() {
        this.listds = new ArrayList<DiemSo>();
        this.listmh_sv = new ArrayList<MonHoc>();
    }
    
    public SinhVien(String mssv, String hoten, String gioitinh, String cmnd) {     
        this.mssv = mssv;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.cmnd = cmnd;
        this.listds = new ArrayList<DiemSo>();
        this.listmh_sv = new ArrayList<MonHoc>(); 
    }
    
    public TaiKhoan getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(TaiKhoan taikhoan) {
        this.taikhoan = taikhoan;
    }
    
    public String getMssv() {
        return mssv;
    }
    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
    public String getHoten() {
        return hoten;
    }
    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
    public String getGioitinh() {
        return gioitinh;
    }
    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    public String getCmnd() {
        return cmnd;
    }
    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }
    public LopHoc getLh_sv() {
        return lh_sv;
    }
    public void setLh_sv(LopHoc lh_sv) {
        this.lh_sv = lh_sv;
    }
    public List<DiemSo> getListds() {
        return listds;
    }
    public void setListds(List<DiemSo> listds) {
        this.listds = listds;
    }
    public List<MonHoc> getListmh_sv() {
        return listmh_sv;
    }
    public void setListmh_sv(List<MonHoc> listmh_sv) {
        this.listmh_sv = listmh_sv;
    }
    @Override
    public String toString() {
        return "SinhVien [mssv=" + mssv + ", hoten=" + hoten + ", gioitinh=" + gioitinh + ", cmnd=" + cmnd + ", lop=" + lh_sv.getMalop() + "]";
    }
    
    
    
}
