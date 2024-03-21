package com.example.Autonoleggio.Controller;

import com.example.Autonoleggio.Model.Profilo;
import com.example.Autonoleggio.Model.Utente;
import com.example.Autonoleggio.Service.ProfiloService;
import com.example.Autonoleggio.Service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registrazioneutente")
public class RegistrazioneUtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(Model model) {
        Utente utente = new Utente();
        model.addAttribute("utente", utente);
        return "registrazioneutente";
    }

    @PostMapping
    public String formManager(@Valid @ModelAttribute("utente") Utente utente,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            return "registrazioneutente";
        }
        if (!utenteService.controlloUsername(utente.getProfilo().getUsername())) {
            model.addAttribute("duplicato", "Username non disponibile");
            return "registrazioneutente";
        }
        utenteService.registraUtente(utente);
        return "redirect:/loginutente";
    }
}
