package org.example.cafe.controllers;

import lombok.AllArgsConstructor;
import org.example.cafe.dto.ProductDTO;
import org.example.cafe.entity.Product;
import org.example.cafe.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private final ProductService productService;

    // Обработчики GET и POST запросов по адресу "/admin"
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String addMenu(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestParam String name, @RequestParam String price,
                                          @RequestParam String text, Model model) {
        ProductDTO dto = new ProductDTO(name, price, text);
        return new ResponseEntity<>(productService.create(dto), HttpStatus.OK);
    }

    // ПОКА ЧТО ПО АДРЕСУ "/admin" нет обработки PUT запросов
//    @RequestMapping(value = "/admin", method = RequestMethod.PUT)
//    public ResponseEntity<Product> update(@RequestBody Product product) {
//        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
//    }

    @RequestMapping(value = "/admin", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long delete_id, Model model) {
        productService.delete(delete_id);
    }

}