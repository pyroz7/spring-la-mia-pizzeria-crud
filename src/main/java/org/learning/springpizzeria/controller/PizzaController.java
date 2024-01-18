package org.learning.springpizzeria.controller;

import org.learning.springpizzeria.model.Pizza;
import org.learning.springpizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class PizzaController {

//Per collegare automaticare le relazioni nelle classi che vengono create
    @Autowired
    private PizzaRepository pizzaRepository;

    //Usiamo RequestParam per dire al controller che può eseguire il parametro da noi utilizzato(in questo caso "keyword") per eseguire la richiesta di HTTTP.
    //Poi forniamo una parola chiave in modo che possiamo cercare facilmente il nostro elenco di pizze
    @GetMapping
    public String index(@RequestParam (name = "keyword",required = false) String searchKeyword , Model model) {
        List<Pizza> pizzaList;
        if (searchKeyword != null) {
            pizzaList = pizzaRepository.findByNameContainingOrDescriptionContaining(searchKeyword,searchKeyword);

        } else {
            pizzaList = pizzaRepository.findAll();

        } model.addAttribute("pizzaList", pizzaList);

        model.addAttribute("preloadSearch",searchKeyword);
        return "pizzas/list";
    }

    @GetMapping
    public String show(@PathVariable Integer id,Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            Pizza pizza = result.get();
            model.addAttribute("pizza",pizza);
            return "pizzas/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pizza" + id + "not found");
        }
    }
}
