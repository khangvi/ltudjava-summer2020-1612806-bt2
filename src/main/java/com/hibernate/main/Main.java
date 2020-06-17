package com.hibernate.main;

import java.awt.EventQueue;
import org.hibernate.SessionFactory;
import com.hibernate.ui.DangNhap;
import com.hibernate.until.HibernateUtil;

public class Main {   
    static final SessionFactory factory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {    
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DangNhap frame = new DangNhap();                   
                    frame.setVisible(true);
                    
               } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
              
    }
}
