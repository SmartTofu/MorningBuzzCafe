package org.example.cafe.controllers;

import lombok.AllArgsConstructor;
import org.example.cafe.entity.AppUser;
import org.example.cafe.entity.Product;
import org.example.cafe.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class CartController {

    private final ProductService productService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(@RequestParam(value="del_name", required = false) String del_name,
                       Model model, Principal principal) {
        AppUser user = productService.getUserByPrincipal(principal);
        if (user != null) {
            List<Product> orders = user.getProducts();
            if (del_name != null) {
                Product delProduct = findProductByName(del_name, orders);
                delProduct.setUser(null);
                productService.update(delProduct);
                orders.remove(delProduct);
                model.addAttribute("orders", orders);
                return "cart";
            }
            else {
                model.addAttribute("orders", orders);
            }
        }
        return "cart";
    }

    public Product findProductByName(String name, List<Product> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getName().equals(name))
                return list.get(i);
        }
        return null;
    }
}
