package com.hibernate.entity;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="diemso")
public class DiemSo implements Serializable{
    @Id
    private int id;
    private float diemgk;
    private float diemck;
    private float diemkhac;
    private float diemtong;
    @ManyToOne
    private SinhVien sv;
    @ManyToOne
    private MonHoc mh;
       
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public SinhVien getSv() {
        return sv;
    }
    public void setSv(SinhVien sv) {
        this.sv = sv;
    }
    public MonHoc getMh() {
        return mh;
    }
    public void setMh(MonHoc mh) {
        this.mh = mh;
    }
    public float getDiemgk() {
        return diemgk;
    }
    public void setDiemgk(float diemgk) {
        this.diemgk = diemgk;
    }
    public float getDiemck() {
        return diemck;
    }
    public void setDiemck(float diemck) {
        this.diemck = diemck;
    }
    public float getDiemkhac() {
        return diemkhac;
    }
    public void setDiemkhac(float diemkhac) {
        this.diemkhac = diemkhac;
    }
    public float getDiemtong() {
        return diemtong;
    }
    public void setDiemtong(float diemtong) {
        this.diemtong = diemtong;
    }
    
    
}
