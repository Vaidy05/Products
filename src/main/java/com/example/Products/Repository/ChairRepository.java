package com.example.Products.Repository;

import com.example.Products.Model.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairRepository extends JpaRepository<Chair,Integer>{
}