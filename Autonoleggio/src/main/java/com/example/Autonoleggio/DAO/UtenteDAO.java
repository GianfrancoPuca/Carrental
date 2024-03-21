package com.example.Autonoleggio.DAO;

import com.example.Autonoleggio.Model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UtenteDAO extends CrudRepository<Utente,Integer> {
    Utente findByProfiloUsername(String username);
    @Query(
            value = "SELECT * FROM utenti WHERE nome =:n",
            nativeQuery = true
    )
    List<Utente> trovaPerNome (@Param("n") String nome);

    Utente findByProfiloUsernameAndProfiloPassword(String username, String password);
    @Query(
            value = "SELECT * FROM profilo WHERE username =:n & password=:p",
            nativeQuery = true
    )
    List<Utente> trovaPerUsernameEPassword (@Param("n") String username, @Param("p") String password);
    //Pensa a modifica dati utenti

}
