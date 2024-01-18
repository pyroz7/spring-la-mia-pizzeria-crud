package org.learning.springpizzeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//Per prima cosa impostiamo le funzioni Controller e Request mapping ,per gestire le richieste HTTP (Controller) e mapping di base (RequestMapping)

@Controller
@RequestMapping
public class HomeController {

    //Poi usiamo il comando GetMapping per reindirizzare le stringa "home" alla pagina "pizza"
    @GetMapping
    public String home() {
        return "redirect:/pizza";
    }
}
