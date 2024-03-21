package com.example.Autonoleggio.Controller;

import com.example.Autonoleggio.Model.Auto;
import com.example.Autonoleggio.Service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dettaglioalimentazione")
public class DettaglioAlimentazioneController {
    @Autowired
    private AutoService autoService;
    @GetMapping
    public String getPage(Model model, @RequestParam("alimentazione") String alimentazione){
        List<Auto> auto = autoService.getAutoByAlimentazione(alimentazione);
        model.addAttribute("automobili", auto);
        return "dettaglioalimentazione";
    }
}
