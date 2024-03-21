package com.example.Autonoleggio.Controller;

import com.example.Autonoleggio.Model.Auto;
import com.example.Autonoleggio.Model.Noleggio;
import com.example.Autonoleggio.Model.Utente;
import com.example.Autonoleggio.Service.AutoService;
import com.example.Autonoleggio.Service.NoleggioService;
import com.example.Autonoleggio.Service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/riservatautente")
public class RiservataUtenteController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private AutoService autoService;
    @Autowired
    private NoleggioService noleggioService;
    @GetMapping
    public String getPage(HttpSession session, Model model){
        if(session.getAttribute("utente") == null) {
            return "redirect:/loginutente";
        }
        Utente utente = (Utente) session.getAttribute("utente");
        model.addAttribute("noleggio", noleggioService.getAllNoleggiByUtente(utente.getProfilo().getId()));
        model.addAttribute("utente", utente);
        model.addAttribute("nome", utente.getNome());
        model.addAttribute("cognome", utente.getCognome());
        model.addAttribute("numeroTelefono", utente.getNumeroTelefono());
        model.addAttribute("username", utente.getProfilo().getUsername());
        model.addAttribute("password", utente.getProfilo().getPassword());
        model.addAttribute("idUtente", utente.getProfilo().getId());
        return "riservatautente";
    }
    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("utente");
        return "redirect:/";
    }
    @PostMapping("/modifica")
    public String formManager(@Valid @ModelAttribute("utente") Utente utente,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            return "riservatautente";
        }
        Utente utenteCorrente = utenteService.getUtenteById(utente.getId());
        if (utenteCorrente == null || utenteCorrente.getId() != utente.getId()) {
            model.addAttribute("duplicato", "Utente non trovato");
            return "riservatautente";
        }
        utenteCorrente.setNome(utente.getNome());
        utenteCorrente.setCognome(utente.getCognome());
        utenteCorrente.setNumeroTelefono(utente.getNumeroTelefono());
        utenteCorrente.getProfilo().setUsername(utente.getProfilo().getUsername());
        utenteCorrente.getProfilo().setPassword(utente.getProfilo().getPassword());
        utenteService.registraUtente(utenteCorrente);
        boolean modificaSuccesso = true;
        model.addAttribute("modificaSuccesso", modificaSuccesso);
        return "redirect:/riservatautente";
    }
    @GetMapping("/elimina")
    public String eliminaNoleggio(@RequestParam("id") int id){

        noleggioService.rimuoviNoleggio(id);
        return "redirect:/riservatautente";
    }

}
