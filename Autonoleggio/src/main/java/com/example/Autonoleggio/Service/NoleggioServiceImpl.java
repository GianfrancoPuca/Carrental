package com.example.Autonoleggio.Service;

import com.example.Autonoleggio.DAO.AutoDAO;
import com.example.Autonoleggio.DAO.NoleggioDAO;
import com.example.Autonoleggio.Model.Auto;
import com.example.Autonoleggio.Model.Noleggio;
import com.example.Autonoleggio.Model.Utente;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class NoleggioServiceImpl implements NoleggioService{
    @Autowired
    private NoleggioDAO noleggioDAO;
    @Override
    public List<Noleggio> getAllNoleggi() {
        return (List<Noleggio>) noleggioDAO.findAll();
    }

    @Override
    public void rimuoviNoleggio(int id) {
        Noleggio noleggio = getNoleggioById(id);
        noleggio.getAuto().getNoleggi().remove(noleggio);
        noleggio.setAuto(null);
        noleggio.getProfilo().getNoleggi().remove(noleggio);
        noleggio.setProfilo(null);
        noleggioDAO.delete(noleggio);
    }

    @Override
    public Noleggio getNoleggioById(int id) {
        return noleggioDAO.findById(id).get();
    }

    @Override
    public List<Noleggio> getAllNoleggiByUtente(int id) {
        return noleggioDAO.findNoleggiByProfiloId(id);
    }


}
