package com.example.Autonoleggio.Controller;

import com.example.Autonoleggio.Model.Admin;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/riservataadmin")

public class RiservataAdminController {
    @Autowired
    private AutoService autoService;
    private Auto auto;
    private Map<String, String> errori;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private NoleggioService noleggioService;
    @GetMapping
    public String getPage(Model model, @RequestParam(name = "id", required = false) Integer id, HttpSession session){
        if(session.getAttribute("admin") == null) {
            return "redirect:/loginadmin";
        }
        if(errori == null){
            auto = id == null ? new Auto() : autoService.getAutoById(id);
        }
        model.addAttribute("noleggi", noleggioService.getAllNoleggi());
        model.addAttribute("utenti", utenteService.getAllUtenti());
        model.addAttribute("auto", auto);
        model.addAttribute("automobili", autoService.getAllAuto());
        model.addAttribute("errori", errori);
        model.addAttribute("targa", auto.getTarga());
        model.addAttribute("marca", auto.getMarca());
        model.addAttribute("modello", auto.getModello());
        model.addAttribute("numeroPosti", auto.getNumeroPosti());
        model.addAttribute("tipoCambio", auto.getTipoCambio());
        model.addAttribute("tariffaGiornaliera", auto.getTariffaGiornaliera());
        model.addAttribute("alimentazione", auto.getAlimentazione());
        model.addAttribute("numeroPorte", auto.getNumeroPorte());
        model.addAttribute("coperturaDanni", auto.getCoperturaDanni());
        model.addAttribute("coperturaFurti", auto.getCoperturaFurti());
        model.addAttribute("noleggioLungoTermine", auto.getNoleggioLungoTermine());
        model.addAttribute("descrizione", auto.getDescrizione());
        model.addAttribute("foto", auto.getFoto());
        return "riservataadmin";
    }
    @GetMapping("/logout")
    public String adminLogout(HttpSession session)
    {
        session.removeAttribute("admin");
        return "redirect:/";
    }
    @GetMapping("/elimina")
    public String eliminaAuto(@RequestParam("id") int id){
        autoService.deleteAuto(autoService.getAutoById(id));
        return "redirect:/riservataadmin";
    }
    @PostMapping
    public String formManager(@RequestParam("targa") String targa,
                              @RequestParam("marca") String marca,
                              @RequestParam("modello") String modello,
                              @RequestParam("numeroPosti") int numeroPosti,
                              @RequestParam("tipoCambio") String tipoCambio,
                              @RequestParam("tariffaGiornaliera") double tariffaGiornaliera,
                              @RequestParam("alimentazione") String alimentazione,
                              @RequestParam("numeroPorte") int numeroPorte,
                              @RequestParam("coperturaDanni") String coperturaDanni,
                              @RequestParam("coperturaFurti") String coperturaFurti,
                              @RequestParam("noleggioLungoTermine") String noleggioLungoTermine,
                              @RequestParam(value = "descizione", required = false) String descrizione,
                              @RequestParam(value = "foto", required = false) MultipartFile foto) {
        Object risultatoValidazione = autoService.validaAuto(auto, targa, marca, modello, numeroPosti, tipoCambio, tariffaGiornaliera, alimentazione, numeroPorte, coperturaDanni, coperturaFurti, noleggioLungoTermine, descrizione);
        if(risultatoValidazione != null){
            auto = (Auto) ((Object[]) risultatoValidazione) [0];
            errori = (Map<String, String>) ((Object[]) risultatoValidazione)[1];
            return "redirect:/riservataadmin";
        }
        autoService.createAuto(auto, targa, marca, modello, numeroPosti, tipoCambio, tariffaGiornaliera, alimentazione, numeroPorte, coperturaDanni, coperturaFurti, noleggioLungoTermine, descrizione, foto);
        auto = null;
        errori = null;
        return "redirect:/riservataadmin";
    }
}

