package com.example.Autonoleggio.Service;

import com.example.Autonoleggio.Model.Auto;
import com.example.Autonoleggio.Model.Noleggio;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AutoService {
    List<Auto> getAllAuto();
    Auto getDettagliAuto (int id);
    List<Auto> getAutoByMarca(String marca);
    List<Auto> getAutoByAlimentazione (String alimentazione);
    List<Auto> getAutoByNumeroPosti (int numeroPosti);
    String controlloNoleggio (HttpSession session, int idAuto, String dataInizioNoleggio, String dataFineNoleggio);
    Auto getAutoById (int id);
    Iterable<Noleggio> getNoleggio (HttpSession session);
    void deleteAuto(Auto auto);
    void createAuto (Auto auto, String targa, String marca, String modello, int numeroPosti, String tipoCambio, double tariffaGiornaliera, String alimentazione, int numeroPorte, String coperturaDanni, String coperturaFurti, String noleggioLungoTermine, String descrizione, MultipartFile foto);
    Object validaAuto (Auto auto, String targa, String marca, String modello, int numeroPosti, String tipoCambio, double tariffaGiornaliera, String alimentazione, int numeroPorte, String coperturaDanni, String coperturaFurti, String noleggioLungoTermine, String descrizione);
    List<Auto> getAuto();
    List<Auto> getAutoByNoleggioLungoTermine (String noleggioLungoTermine);
    void confermaNoleggio (HttpSession session);
}
