package org.example.cafe.controllers;

import lombok.AllArgsConstructor;
import org.example.cafe.dto.ProductDTO;
import org.example.cafe.entity.Product;
import org.example.cafe.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestParam String name, @RequestParam String price,
                                          @RequestParam String text, Model model) {
        ProductDTO dto = new ProductDTO(name, price, text);
        return new ResponseEntity<>(productService.create(dto), HttpStatus.OK);
    }



    // ГОВНОКОД ИЗ ЮТУБА - ПЕРЕДЕЛАТЬ!!!



    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> readAll() {
        return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public HttpStatus delete(@PathVariable Long id) {
        productService.delete(id);
        return HttpStatus.OK;
    }
}
