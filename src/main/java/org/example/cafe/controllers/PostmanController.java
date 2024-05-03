package org.example.cafe.controllers;

import lombok.AllArgsConstructor;
import org.example.cafe.dto.ProductDTO;
import org.example.cafe.entity.Product;
import org.example.cafe.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class PostmanController {

    private final ProductService productService;

    //Обработчики запросов для работы с API приложеня при помощи Postman
    @RequestMapping(value = "/postman", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> readAll() {
        return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/postman", method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.create(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/postman/add", method = RequestMethod.POST)
    public ResponseEntity<Product> add(@RequestParam(value="name", required = false) String name,
                                       Principal principal) {
        List<Product> positions = productService.readAll();
        Product product = findProductByName(name, positions);
        return new ResponseEntity<>(productService.addToOrder(product, principal), HttpStatus.OK);
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

    public Product findProductByName(String name, List<Product> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getName().equals(name))
                return list.get(i);
        }
        return null;
    }
}
