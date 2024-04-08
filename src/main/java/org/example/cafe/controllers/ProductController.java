package org.example.cafe.controllers;

import lombok.AllArgsConstructor;
import org.example.cafe.dto.ProductDTO;
import org.example.cafe.entity.Product;
import org.example.cafe.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> readAll() {
        return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        productService.delete(id);
        return HttpStatus.OK;
    }
}
