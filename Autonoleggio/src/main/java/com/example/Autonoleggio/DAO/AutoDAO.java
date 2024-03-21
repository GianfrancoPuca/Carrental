package com.example.Autonoleggio.DAO;

import com.example.Autonoleggio.Model.Auto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutoDAO extends CrudRepository<Auto, Integer> {
    List<Auto> findByMarca (String marca);
    List<Auto> findByAlimentazione (String alimentazione);
    List<Auto> findByNumeroPosti(int numeroPosti);
    List<Auto> findByNoleggioLungoTermine (String noleggioLungoTermine);
}
