package org.example.cafe.service;

import lombok.AllArgsConstructor;
import org.example.cafe.dto.ProductDTO;
import org.example.cafe.entity.AppUser;
import org.example.cafe.entity.Product;
import org.example.cafe.repository.ProductRepository;
import org.example.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Product create(ProductDTO dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .text(dto.getText())
                .build();
        return productRepository.save(product);
    }

    public List<Product> readAll() {
        return productRepository.findAll();
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product addToOrder(Product product, Principal principal) {
        product.setUser(getUserByPrincipal(principal));
        return productRepository.save(product);
    }

    public AppUser getUserByPrincipal(Principal principal) {
        if (principal == null) return null;
        return userRepository.findByName(principal.getName()).get();
    }
}
