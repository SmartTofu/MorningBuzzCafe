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

    // Обработчик страницы с каталогом
    @GetMapping("/menu")
    public String menu(Model model) {
        Iterable<Product> positions = productService.readAll();
        model.addAttribute("positions", positions);
        return "menu";
    }

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

    // ПОКА ЧТО ПО АДРЕСУ "/admin" нет обработки PUT и DELETE запросов
//    @RequestMapping(value = "/admin", method = RequestMethod.PUT)
//    public ResponseEntity<Product> update(@RequestBody Product product) {
//        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
//    public HttpStatus delete(@PathVariable Long id) {
//        productService.delete(id);
//        return HttpStatus.OK;
//    }

    //Обработчики запросов для работы с API приложеня при помощи Postman
    @RequestMapping(value = "/postman", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> readAll() {
        return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/postman", method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.create(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/postman", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @DeleteMapping("/postman/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        productService.delete(id);
        return HttpStatus.OK;
    }
}