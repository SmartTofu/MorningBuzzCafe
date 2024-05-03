package org.example.cafe.controllers;

import lombok.AllArgsConstructor;
import org.example.cafe.entity.AppUser;
import org.example.cafe.entity.Product;
import org.example.cafe.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class CartController {

    private final ProductService productService;
    @GetMapping("/cart")
    public String cart(Model model, Principal principal) {
        AppUser user = productService.getUserByPrincipal(principal);
        List<Product> orders = user.getProducts();
        model.addAttribute("orders", orders);
        return "cart";
    }
}
