package com.example.Products.Repository;

import com.example.Products.Model.Fashion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FashionRepository extends JpaRepository<Fashion,Integer> {
}
