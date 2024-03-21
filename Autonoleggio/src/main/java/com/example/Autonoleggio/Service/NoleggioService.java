package com.example.Autonoleggio.Service;

import com.example.Autonoleggio.DAO.NoleggioDAO;
import com.example.Autonoleggio.Model.Noleggio;
import com.example.Autonoleggio.Model.Utente;

import java.util.List;

public interface NoleggioService  {
    List<Noleggio> getAllNoleggi();
    void rimuoviNoleggio (int id);
    Noleggio getNoleggioById(int id);
    List<Noleggio> getAllNoleggiByUtente (int id);
}
