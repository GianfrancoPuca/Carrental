package com.example.Autonoleggio.Service;

import com.example.Autonoleggio.Model.Utente;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

public interface UtenteService {
    void registraUtente (Utente utente);
    boolean controlloUsername(String username);
    boolean controllaLogin(String username, String password, HttpSession session);
    List<Utente> getAllUtenti ();
    Utente getUtenteById(int id);

}
