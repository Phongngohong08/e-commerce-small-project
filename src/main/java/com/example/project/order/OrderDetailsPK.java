package com.example.project.order;

import com.example.project.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsPK implements Serializable {
    private Order order;
    private Product product;
}
