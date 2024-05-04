package org.example.cafe.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.example.cafe.entity.Product;
import org.example.cafe.service.ProductService;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class MenuController {

    private final ProductService productService;

    @PersistenceContext
    private EntityManager entityManager;

    // Обработчик страницы с каталогом
    @GetMapping("/menu")
    public String menu(@RequestParam(value="ask_name", required = false) String ask_name,
                       @RequestParam(value="filter", required = false) String filter,
                       @RequestParam(value="card_name", required = false) String card_name,
                       Principal principal, Model model) {
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

        if (filter != null) {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> productCriteriaQuery = builder.createQuery(Product.class);
            Root<Product> root = productCriteriaQuery.from(Product.class);
            productCriteriaQuery.select(root).orderBy(builder.asc(root.get("price")));
            Query<Product> query = (Query<Product>) entityManager.createQuery(productCriteriaQuery);
            positions = query.getResultList();
            model.addAttribute("positions", positions);
        }

        if (card_name != null) {
            Product product = findProductByName(card_name, positions);
            productService.addToOrder(product, principal);
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
