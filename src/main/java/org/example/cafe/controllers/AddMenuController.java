package org.example.cafe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddMenuController {
    @GetMapping("/addMenu")
    public String addMenu(Model model) {
        return "addMenu";
    }
}
