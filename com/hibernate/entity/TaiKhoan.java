package com.hibernate.entity;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="taikhoan")
public class TaiKhoan implements Serializable{
    @Id
    private String id;
    private String pass_word;
    
    public TaiKhoan(String id, String pass_word) {
        this.id = id;
        this.pass_word = pass_word;
    }

    public TaiKhoan() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }
    
    
    
}
