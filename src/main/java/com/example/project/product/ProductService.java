package com.example.project.product;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found"));
    }

    public List<String> getAllProduct() {

        return productRepository.findAll().stream()
                .map(product ->"product id: " + product.getId() +
                        "; product name: " + product.getProductName() +
                        "; product price: " + product.getPrice_each() +
                        "; product quantity: " + product.getQuantity() +
                        "; product category: " + product.getCategory().getName()
                ).toList();
    }
}
