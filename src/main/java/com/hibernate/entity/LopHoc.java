package com.hibernate.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="lophoc")
public class LopHoc implements Serializable{
    @Id
    private String malop;
    @OneToMany(mappedBy="lh_sv")
    private List<SinhVien> listsv_lh;
    @OneToMany(mappedBy="lh_mh")
    private List<MonHoc> listmh_lh;
    
    public LopHoc() {      
    }
    
    public LopHoc(String malop) {
        this.malop = malop;    
    }
    
    public String getMalop() {
        return malop;
    }
    public void setMalop(String malop) {
        this.malop = malop;
    }
    public List<SinhVien> getListsv_lh() {
        return listsv_lh;
    }
    public void setListsv_lh(List<SinhVien> listsv_lh) {
        this.listsv_lh = listsv_lh;
    }
    public List<MonHoc> getListmh_lh() {
        return listmh_lh;
    }
    public void setListmh_lh(List<MonHoc> listmh_lh) {
        this.listmh_lh = listmh_lh;
    }
    
    
    
    
}
