package ru.job4j.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;
import ru.job4j.service.AccidentRuleService;
import ru.job4j.service.AccidentService;
import ru.job4j.service.AccidentTypeService;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidents;
    private final AccidentTypeService types;
    private final AccidentRuleService rules;

    @GetMapping("/addAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", types.findAll());
        model.addAttribute("rules", rules.findAll());

        return "accidents/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req,
                       @RequestParam(required = false) Set<Integer> rIds) {
        accidents.create(accident, rIds);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        var accidentOptional = accidents.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Ошибка обновления инцидента");
            return "errors/404";
        }
        model.addAttribute("accident", accidentOptional.get());
        model.addAttribute("types", types.findAll());
        model.addAttribute("rules", rules.findAll());
        return "accidents/editAccident";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, Model model,
                         @RequestParam(required = false) Set<Integer> rIds) {
        var isUpdated = accidents.update(accident, rIds);
        if (!isUpdated) {
            model.addAttribute("message", "Ошибка обновления инцидента");
            return "errors/404";
        }
        return "index";
    }
}