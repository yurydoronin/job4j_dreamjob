package ru.job4j.spring.police.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.spring.police.model.Accident;
import ru.job4j.spring.police.service.AccidentService;
import ru.job4j.spring.police.service.AccidentServiceImpl;

/**
 * Class IndexController.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 19.04.2020
 */
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final AccidentService service;

    @GetMapping(path = "/")
    public String showAccidents(Model model) {
        model.addAttribute("accidents", this.service.getAll());
        return "index";
    }

    @GetMapping(path = "/accident.do")
    public String addAccident() {
        return "accident";
    }

    @PostMapping(path = "/add.do")
    public String addAccident(@ModelAttribute Accident accident) {
        this.service.save(accident);
        return "redirect:/";
    }

    @GetMapping(path = "/edit.do")
    public String updateAccident(@RequestParam int id, Model model) {
        model.addAttribute("accident", service.findById(id));
        return "edit";
    }

    @PostMapping(path = "/delete.do")
    public String deleteAccident(@ModelAttribute Accident accident) {
        this.service.delete(accident);
        return "redirect:/";
    }
}
