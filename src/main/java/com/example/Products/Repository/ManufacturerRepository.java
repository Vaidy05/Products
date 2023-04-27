package com.example.Products.Repository;

import com.example.Products.Model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Integer>{
    Manufacturer findByManufacturerName(String manufacturerName);
}
