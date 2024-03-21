package com.example.Autonoleggio.Service;

import com.example.Autonoleggio.DAO.AdminDAO;
import com.example.Autonoleggio.Model.Admin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDAO adminDAO;
    @Override
    public boolean controlloAdmin(String username, String password, HttpSession session) {
        Admin admin = adminDAO.findByUsernameAndPassword(username, password);
        if(admin != null){
            session.setAttribute("admin", admin);
            return true;
        }
        return false;
    }
}
