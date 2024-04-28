package org.example.cafe.controllers;

import lombok.AllArgsConstructor;
import org.example.cafe.entity.Product;
import org.example.cafe.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class MenuController {

    private final ProductService productService;

    // Обработчик страницы с каталогом
    @GetMapping("/menu")
    public String menu(@RequestParam(value="ask_name", required = false) String ask_name, Model model) {
        List<Product> positions = productService.readAll();
        if (ask_name == null) {
            model.addAttribute("positions", positions);
        }
        else {
            Product product = findProductByName(ask_name, positions);
            if (product == null) {
                model.addAttribute("positions", positions);
            }
            else {
                model.addAttribute("positions", product);
            }
        }
        return "menu";
    }

    public Product findProductByName(String name, List<Product> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getName().equals(name))
                return list.get(i);
        }
        return null;
    }
}
