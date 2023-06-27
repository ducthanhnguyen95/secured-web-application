package com.example.secured.web.application.repositories;

import com.example.secured.web.application.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
