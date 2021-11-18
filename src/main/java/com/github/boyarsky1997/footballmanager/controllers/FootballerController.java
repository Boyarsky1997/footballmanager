package com.github.boyarsky1997.footballmanager.controllers;

import com.github.boyarsky1997.footballmanager.models.Footballer;
import com.github.boyarsky1997.footballmanager.repo.FootballerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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


    @GetMapping("/footballer/{id}")
    public String blogDetails(@PathVariable(value = "id") int id, Model model) {
        Optional<Footballer> footballer = footballerRepo.findById(id);
        model.addAttribute("footballer", footballer.get());
        return "footballer-details";
    }

    @GetMapping("/footballer/{id}/edit")
    public String footballerGetEdit(@PathVariable(value = "id") int id, Model model) {
        Optional<Footballer> footballerEdit = footballerRepo.findById(id);
        model.addAttribute("footballerEdit", footballerEdit.get());
        return "footballer-edit";
    }

    @PostMapping("/footballer/{id}/edit")
    public String footballerPostEdit(@PathVariable(value = "id") int id, @RequestParam String name,
                                     @RequestParam String firstName, @RequestParam String age, Model model) {
        Footballer footballer = footballerRepo.findById(id).orElseThrow();
        System.out.println(name + " " + firstName + " " + age);
        footballer.setName(name);
        footballer.setFirstName(firstName);
        footballer.setAge(age);

        footballerRepo.save(footballer);
        model.addAttribute("footballerEdit", footballer);
        return "redirect:/footballer/" + id;
    }
}
