package org.example.cafe.controllers;

import lombok.AllArgsConstructor;
import org.example.cafe.entity.Product;
import org.example.cafe.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MenuController {

    private final ProductService productService;

    // Обработчик страницы с каталогом
    @GetMapping("/menu")
    public String menu(Model model) {
        Iterable<Product> positions = productService.readAll();
        model.addAttribute("positions", positions);
        return "menu";
    }
}
