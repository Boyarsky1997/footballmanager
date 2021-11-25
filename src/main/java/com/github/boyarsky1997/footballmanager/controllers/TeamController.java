package com.github.boyarsky1997.footballmanager.controllers;

import com.github.boyarsky1997.footballmanager.models.Footballer;
import com.github.boyarsky1997.footballmanager.models.Team;
import com.github.boyarsky1997.footballmanager.repo.FootballerRepo;
import com.github.boyarsky1997.footballmanager.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class TeamController {
    @Autowired
    private FootballerRepo footballerRepo;
    @Autowired
    private TeamRepo teamRepo;

    @GetMapping("/")
    public String main(Model model) {
        return "main.html";
    }

    @GetMapping("/teams")
    public String teams(Model model) {
        Iterable<Team> teams = teamRepo.findAll();
        model.addAttribute("teams", teams);
        return "teams";
    }

    @GetMapping("/team/{id}/footballer/add")
    public String footballerGetAdd(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("id", id);
        return "footballer-add";
    }

    @PostMapping("team/{id}/footballer/add")
    public String footballerPostAdd(@PathVariable(value = "id") int id, @RequestParam String name,
                                    @RequestParam String last_name, @RequestParam String age,
                                    @RequestParam String transferPrice, Model model) throws IOException {
        model.addAttribute("id", id);
        Footballer footballer = new Footballer();
        footballer.setName(name);
        footballer.setFirstName(last_name);
        footballer.setAge(age);
        footballer.setEarlyCareer(LocalDate.now());
        footballer.setTransferPrice(Integer.parseInt(transferPrice));
        if (teamRepo.existsById(id)) {
            footballer.setTeam(teamRepo.findById(id).get());
        }
        footballerRepo.save(footballer);
        return "redirect:/team/" + id;
    }

    @GetMapping("/team/{id}/buy")
    public String footballerBuyGet(@PathVariable(value = "id") int id, Model model) {
        List<Team> teams = StreamSupport.stream(teamRepo.findAll().spliterator(), false)
                .filter(team -> !team.equals(teamRepo.findById(id).get()))
                .collect(Collectors.toList());
        model.addAttribute("teams", teams);
        model.addAttribute("id", id);
        return "footballer-buy";
    }

    @GetMapping("/team/{id1}/buy/{id2}/footballers")
    public String footballerBuyGet1(@PathVariable(value = "id1") int id1, @PathVariable(value = "id2") int id2, Model model) {
        List<Footballer> collect = StreamSupport.stream(footballerRepo.findAll().spliterator(), false)
                .filter(footballer -> footballer.getTeam().getTeam_id() == id2)
                .collect(Collectors.toList());
        model.addAttribute("collect", collect);
        model.addAttribute("id", id1);
        return "footballer-buy-footballers";
    }

    @PostMapping("/team/{id1}/buy/{id2}/footballers/{id3}")
    public String footballerBuyPost1(@PathVariable(value = "id1") int id1,
                                     @PathVariable(value = "id2") int id2,
                                     @PathVariable(value = "id3") int id3, Model model) {
        Team team1 = teamRepo.findById(id1).get();
        Team team2 = teamRepo.findById(id2).get();
        Footballer footballer = footballerRepo.findById(id3).get();
        if (team1.getTeamAccount() > footballer.getTransferPrice()) {
            double v = team1.getTeamAccount() - footballer.getTransferPrice();
            team1.setTeamAccount(v);
            double v1 = team2.getTeamAccount() + footballer.getTransferPrice();
            team2.setTeamAccount(v1);
            teamRepo.save(team1);
            teamRepo.save(team2);
            footballer.setTeam(team1);
            footballerRepo.save(footballer);
            return "redirect:/team/" + team1.getTeam_id();
        }
        model.addAttribute("message",true);
        return "redirect:/team/" + team1.getTeam_id() + "/buy/" + team2.getTeam_id() + "/footballers";
    }

    @GetMapping("/team/{id}")
    public String blogDetails(@PathVariable(value = "id") int id, Model model) {
        Optional<Team> team = teamRepo.findById(id);
        List<Footballer> footballers = new ArrayList<>();
        for (Footballer footballer : footballerRepo.findAll()) {
            if (footballer.getTeam().getTeam_id() == id) {
                footballers.add(footballer);
            }
        }
        model.addAttribute("team", team.get());
        model.addAttribute("footballers", footballers);
        return "team-details";
    }

    @GetMapping("/team/add")
    public String teamGetAdd(Model model) {

        return "team-add";
    }

    @PostMapping("team/add")
    public String teamPostAdd(@RequestParam String name, @RequestParam String city, @RequestParam String country,
                              @RequestParam String team_account, Model model) throws IOException {
        Team team = new Team();
        team.setName(name);
        team.setCity(city);
        team.setCountry(country);
        team.setTeamAccount(Double.parseDouble(team_account));
        teamRepo.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/team/{id}/edit")
    public String teamGetEdit(@PathVariable(value = "id") int id, Model model) {
        Optional<Team> team = teamRepo.findById(id);
        model.addAttribute("teamEdit", team.get());
        return "team-edit";
    }


    @PostMapping("/team/{id}/edit")
    public String teamPostEdit(@PathVariable(value = "id") int id, @RequestParam String name,
                               @RequestParam String city, @RequestParam String country, @RequestParam String teamAccount, Model model) {
        Team team = teamRepo.findById(id).orElseThrow();
        team.setName(name);
        team.setCity(city);
        team.setCountry(country);
        team.setTeamAccount(Double.parseDouble(teamAccount));
        teamRepo.save(team);
        model.addAttribute("teamEdit", team);
        return "redirect:/team/" + id;
    }
}
