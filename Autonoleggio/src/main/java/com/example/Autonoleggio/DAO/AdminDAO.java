package com.example.Autonoleggio.DAO;

import com.example.Autonoleggio.Model.Admin;
import com.example.Autonoleggio.Model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface AdminDAO extends CrudRepository<Admin, Integer> {
    Admin findByUsernameAndPassword(String username, String password);

}
