package com.example.Autonoleggio.Service;

import com.example.Autonoleggio.DAO.UtenteDAO;
import com.example.Autonoleggio.Model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteServiceImpl implements UtenteService {
    @Autowired
    private UtenteDAO utenteDAO;

    @Override
    public void registraUtente(Utente utente) {
        utenteDAO.save(utente);
    }

    @Override
    public boolean controlloUsername(String username) {
        if (utenteDAO.findByProfiloUsername(username) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean controllaLogin(String username, String password, HttpSession session) {
        Utente u = utenteDAO.findByProfiloUsernameAndProfiloPassword(username, password);
        if (u != null) {
            session.setAttribute("utente", u);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Utente> getAllUtenti() {
        return (List<Utente>) utenteDAO.findAll();
    }

    @Override
    public Utente getUtenteById(int id) {
        return utenteDAO.findById(id).get();
    }

}