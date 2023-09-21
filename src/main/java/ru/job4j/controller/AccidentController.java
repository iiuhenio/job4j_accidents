package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.model.Accident;
import ru.job4j.service.AccidentService;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/accidents") /* Работать с ДТП будем по URI /accidents/** */
public class AccidentController {

    private final AccidentService accidentService;

    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "accidents/list";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        return "accidents/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Accident accident, @RequestParam MultipartFile file, Model model) {
        try {
            accidentService.save(accident);
            return "redirect:/accidents";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id, HttpSession session) {
        var accidentOptional = accidentService.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "ДТП с указанным идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute("candidate", accidentOptional.get());
        return "accidents/one";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, @RequestParam MultipartFile file, Model model) {
        try {
            var isUpdated = accidentService.update(accident);
            if (!isUpdated) {
                model.addAttribute("message", "ДТП с указанным идентификатором не найдено");
                return "errors/404";
            }
            return "redirect:/accidents";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = accidentService.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "ДТП с указанным идентификатором не найдено");
            return "errors/404";
        }
        return "redirect:/accidents";
    }
}