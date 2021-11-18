package com.github.boyarsky1997.footballmanager.controllers;

import com.github.boyarsky1997.footballmanager.models.Team;
import com.github.boyarsky1997.footballmanager.repo.FootballerRepo;
import com.github.boyarsky1997.footballmanager.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamController {
    @Autowired
    private FootballerRepo footballerRepo;
    @Autowired
    private TeamRepo teamRepo;

    @GetMapping("/")
    public String main(Model model) {
        Iterable<Team> teams = teamRepo.findAll();
        model.addAttribute("teams", teams);
        return "main";
    }

    @GetMapping("/teams")
    public String teams(Model model) {
        Iterable<Team> teams = teamRepo.findAll();
        model.addAttribute("teams", teams);
        return "teams";
    }
}
