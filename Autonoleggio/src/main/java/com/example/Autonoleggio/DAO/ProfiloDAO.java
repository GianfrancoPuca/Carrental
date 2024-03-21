package com.example.Autonoleggio.DAO;

import com.example.Autonoleggio.Model.Profilo;
import com.example.Autonoleggio.Model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfiloDAO extends CrudRepository<Profilo, Integer> {


}
