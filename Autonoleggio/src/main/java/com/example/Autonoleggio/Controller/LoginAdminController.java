package com.example.Autonoleggio.Controller;
import com.example.Autonoleggio.Service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/loginadmin")
public class LoginAdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public String getPage(Model model, HttpSession session, @RequestParam(name = "errore", required = false) String errore) {
        if (session.getAttribute("admin") != null) {
            return "redirect:/riservataadmin";
        }
        model.addAttribute("errore", errore);
        return "loginadmin";


    }

    @PostMapping
    public String formManager(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        if (!adminService.controlloAdmin(username, password, session)) {
            return "redirect:/loginadmin?errore";
        }
        return "redirect:/riservataadmin";
    }
}

