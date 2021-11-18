package com.github.boyarsky1997.footballmanager.controllers;

import com.github.boyarsky1997.footballmanager.models.Footballer;
import com.github.boyarsky1997.footballmanager.repo.FootballerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FootballerController {
    @Autowired
    private FootballerRepo footballerRepo;

    @GetMapping("/footballers")
    public String footballers(Model model) {
        Iterable<Footballer> all = footballerRepo.findAll();
        model.addAttribute("all", all);
        return "footballers";
    }
}
