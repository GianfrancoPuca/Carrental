package com.example.Autonoleggio.Service;

import com.example.Autonoleggio.DAO.NoleggioDAO;
import jakarta.servlet.http.HttpSession;

public interface AdminService {
    boolean controlloAdmin(String username, String password, HttpSession session);
}
