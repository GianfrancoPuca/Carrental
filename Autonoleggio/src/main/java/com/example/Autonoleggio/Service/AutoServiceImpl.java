package com.example.Autonoleggio.Service;

import com.example.Autonoleggio.DAO.AutoDAO;
import com.example.Autonoleggio.DAO.NoleggioDAO;
import com.example.Autonoleggio.Model.Auto;
import com.example.Autonoleggio.Model.Noleggio;
import com.example.Autonoleggio.Model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class AutoServiceImpl implements AutoService{
    @Autowired
    private AutoDAO autoDAO;
    @Autowired
    private NoleggioDAO noleggioDAO;
    @Override
    public List<Auto> getAllAuto() {
        return (List<Auto>) autoDAO.findAll();
    }

    @Override
    public Auto getDettagliAuto(int id) {
        return autoDAO.findById(id).get();
    }

    @Override
    public List<Auto> getAutoByMarca(String marca) {
        return autoDAO.findByMarca(marca);
    }

    @Override
    public List<Auto> getAutoByAlimentazione(String alimentazione) {
        return autoDAO.findByAlimentazione(alimentazione);
    }

    @Override
    public List<Auto> getAutoByNumeroPosti(int numeroPosti) {
        return autoDAO.findByNumeroPosti(numeroPosti);
    }

    @Override
    public String controlloNoleggio(HttpSession session, int idAuto, String dataInizioNoleggio, String dataFineNoleggio) {
        Auto auto = autoDAO.findById(idAuto).get();
        LocalDateTime inizio = LocalDateTime.parse(dataInizioNoleggio);
        LocalDateTime fine = LocalDateTime.parse(dataFineNoleggio);
        Utente utente = (Utente) session.getAttribute("utente");
        if(inizio.isBefore(LocalDateTime.now()) ||
                //Come altra clausola nell'if mettere se la data di inizio noleggio coincide con la data attuale
                inizio.isEqual(LocalDateTime.now()) ||
                //Altra clausola non è uguale alla data di fine noleggio del veicolo
                inizio.isEqual(fine) ||
                //Ultima clausola la data di fine noleggio deve essere successiva alla data di inizio noleggio richiesta
                inizio.isAfter(fine)
        ) {
            //In tutti questi casi gli diamo questa risposta return new ResponseManager("400", "Unacceptable Period").getResponse();
            return "Il periodo selezionato non è valido";
        } else {
            for(Noleggio n : auto.getNoleggi()) {
                if(! n.getDataFineNoleggio().isBefore(inizio) &&
                        !n.getDataInizioNoleggio().isAfter(fine)) {
                    return "Per il periodo selezionato il veicolo non è disponibile";

                }

            }
            Noleggio noleggio = new Noleggio();
            noleggio.setDataInizioNoleggio(inizio);
            noleggio.setDataFineNoleggio(fine);
            noleggio.setAuto(auto);
            noleggio.setProfilo(utente.getProfilo());
            double totale = auto.getTariffaGiornaliera() * (ChronoUnit.DAYS.between(inizio, fine) + 1);
            noleggio.setPrezzoTotale(totale);
            session.setAttribute("noleggio", noleggio);
            return "Noleggio disponibile";

        }
    }

    @Override
    public Auto getAutoById(int id) {
        return autoDAO.findById(id).get();
    }

    @Override
    public Iterable<Noleggio> getNoleggio(HttpSession session) {
        Iterable<Noleggio> noleggi = noleggioDAO.findAll();
        return noleggi;


    }

    @Override
    public void deleteAuto(Auto auto) {
        autoDAO.delete(auto);
    }

    @Override
    public void createAuto(Auto auto, String targa, String marca, String modello, int numeroPosti, String tipoCambio,
                           double tariffaGiornaliera, String alimentazione, int numeroPorte, String coperturaDanni, String coperturaFurti,
                           String noleggioLungoTermine, String descrizione, MultipartFile foto) {
        auto.setTarga(targa);
        auto.setMarca(marca);
        auto.setModello(modello);
        auto.setNumeroPosti(numeroPosti);
        auto.setTipoCambio(tipoCambio);
        auto.setTariffaGiornaliera(tariffaGiornaliera);
        auto.setAlimentazione(alimentazione);
        auto.setNumeroPorte(numeroPorte);
        auto.setCoperturaDanni(coperturaDanni);
        auto.setCoperturaFurti(coperturaFurti);
        auto.setNoleggioLungoTermine(noleggioLungoTermine);
        auto.setDescrizione(descrizione);
        if(foto != null && !foto.isEmpty()){
            try{
               String formato = foto.getContentType();
               String fotoCod = "Data:" + formato + ";base64," + Base64.getEncoder().encodeToString(foto.getBytes());
               auto.setFoto(fotoCod);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        autoDAO.save(auto);
    }

    @Override
    public Object validaAuto(Auto auto, String targa, String marca, String modello, int numeroPosti,
                             String tipoCambio, double tariffaGiornaliera, String alimentazione,
                             int numeroPorte, String coperturaDanni, String coperturaFurti,
                             String noleggioLungoTermine, String descrizione) {
        auto.setTarga(targa);
        auto.setModello(modello);
        auto.setDescrizione(descrizione);
        Map<String, String> errori = new HashMap<>();
        if(!Pattern.matches("[A-Z0-9]{1,7}", targa) && !Pattern.matches("[a-zA-Z0-9\sàèìòù,.-]{1,50}", modello) && !Pattern.matches("[a-zA-Z0-9\sàèìòù,.-]{1,255}", descrizione)) {
            errori.put("targa", "Caratteri non ammessi");
            errori.put("modello", "Caratteri non ammessi");
            errori.put("descrizione", "Caratteri non ammessi");
        }
        try{
            auto.setMarca(marca);
            auto.setNumeroPosti(numeroPosti);
            auto.setTipoCambio(tipoCambio);
            auto.setTariffaGiornaliera(tariffaGiornaliera);
            auto.setAlimentazione(alimentazione);
            auto.setNumeroPorte(numeroPorte);
            auto.setCoperturaDanni(coperturaDanni);
            auto.setCoperturaFurti(coperturaFurti);
            auto.setNoleggioLungoTermine(noleggioLungoTermine);
        } catch (Exception e){
            errori.put("marca", "Marca errata");
            errori.put("numeroPosti", "Numero posti non valido");
            errori.put("tipoCambio", "Tipo del cambio non ammesso");
            errori.put("tariffaGiornaliera", "Tariffa non ammessa");
            errori.put("alimentazione", "alimentazione");
            errori.put("numeroPorte", " Numero porte non valido");
            errori.put("coperturaDanni", "Copertura Danni non valida");
            errori.put("coperturaFurti", "Copertura Furti non ammessa");
            errori.put("noleggioLungoTermine", "Noleggio a lungo termine non valido");
        }
        if(errori.size() > 0) {
            return new Object[]{auto, errori};
        } else {
            return null;
        }
    }

    @Override
    public List<Auto> getAuto() {
        return (List<Auto>) autoDAO.findAll();
    }

    @Override
    public List<Auto> getAutoByNoleggioLungoTermine(String noleggioLungoTermine) {
        if(noleggioLungoTermine.equalsIgnoreCase("SI")) {
            return autoDAO.findByNoleggioLungoTermine(noleggioLungoTermine);
        } else {
            return null;
        }
    }

    @Override
    public void confermaNoleggio(HttpSession session) {
        Noleggio noleggio = (Noleggio) session.getAttribute("noleggio");
        noleggioDAO.save(noleggio);
        session.removeAttribute("noleggio");
    }

}

