package com.example.Autonoleggio.Controller;

import com.example.Autonoleggio.DAO.UtenteDAO;
import com.example.Autonoleggio.Model.Utente;
import com.example.Autonoleggio.Service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/loginutente")
public class LogInUtenteController {
    @Autowired
    private UtenteService utenteService;
    @GetMapping
    public String getPage(Model model, HttpSession session, @RequestParam(name = "errore", required = false) String errore){
        if(session.getAttribute("utente") != null){
            return "redirect:/riservatautente";
        }
        model.addAttribute("errore", errore);
        return "loginutente";
    }
    @PostMapping
    public String formManager(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        if(!utenteService.controllaLogin(username, password, session)) {
            return "redirect:/loginutente?errore";
        }
        return "redirect:/riservatautente";
    }
}


