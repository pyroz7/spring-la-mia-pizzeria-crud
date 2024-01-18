package org.learning.springpizzeria.repository;

import org.learning.springpizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza,Integer> {

    List<Pizza> findByNameContaining(String search);
    List<Pizza> findByNameContainingOrDescriptionContaining(String searchName, String searchDescription);
}
