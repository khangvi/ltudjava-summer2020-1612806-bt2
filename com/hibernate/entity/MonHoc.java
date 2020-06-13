package com.hibernate.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="monhoc")
public class MonHoc implements Serializable {
    @Id
    private String mamon;
    private String tenmon;
    private String phonghoc;
    private int tietbatdau;
    private int tietketthuc;
    @OneToMany(mappedBy="mh")
    private List<DiemSo> listds = new ArrayList<DiemSo>();
    @ManyToMany(mappedBy="listmh_sv")
    private List<SinhVien> listsv_mh = new ArrayList<SinhVien>();
    @ManyToOne
    private LopHoc lh_mh;
    
    public MonHoc() {
        
    }
    
    public MonHoc(String mamon, String tenmon, String phonghoc, int tietbatdau, int tietketthuc) {
        super();
        this.mamon = mamon;
        this.tenmon = tenmon;
        this.phonghoc = phonghoc;
        this.tietbatdau = tietbatdau;
        this.tietketthuc = tietketthuc;
    }
    
    public String getMamon() {
        return mamon;
    }
    public void setMamon(String mamon) {
        this.mamon = mamon;
    }
    public String getTenmon() {
        return tenmon;
    }
    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }
    public String getPhonghoc() {
        return phonghoc;
    }
    public void setPhonghoc(String phonghoc) {
        this.phonghoc = phonghoc;
    }
    public int getTietbatdau() {
        return tietbatdau;
    }
    public void setTietbatdau(int tietbatdau) {
        this.tietbatdau = tietbatdau;
    }
    public int getTietketthuc() {
        return tietketthuc;
    }
    public void setTietketthuc(int tietketthuc) {
        this.tietketthuc = tietketthuc;
    }
    public List<DiemSo> getListds() {
        return listds;
    }
    public void setListds(List<DiemSo> listds) {
        this.listds = listds;
    }
    public List<SinhVien> getListsv_mh() {
        return listsv_mh;
    }
    public void setListsv_mh(List<SinhVien> listsv_mh) {
        this.listsv_mh = listsv_mh;
    }
    public LopHoc getLh_mh() {
        return lh_mh;
    }
    public void setLh_mh(LopHoc lh_mh) {
        this.lh_mh = lh_mh;
    }
    
   
    
    
}
