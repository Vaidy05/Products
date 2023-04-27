package com.example.Products.Service;

import com.example.Products.DTO.ManufacturerDTO;
import com.example.Products.Model.Manufacturer;
import com.example.Products.Model.Product;
import com.example.Products.Repository.ManufacturerRepository;
import com.example.Products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManufacturerService {

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Autowired
    ProductRepository productRepository;

    public String addManufacturer(ManufacturerDTO manufacturerDTO){
        try{
            //Check whether the manufacturer name already exist. If exist throw exception.
            if(manufacturerRepository.findByManufacturerName(manufacturerDTO.getManufacturer_name())!=null){
                throw new Exception("Manufacturer name already exist!");
            }
            //If not exist create new manufacturer and save.
            Manufacturer manufacturer = new Manufacturer(manufacturerDTO.getManufacturer_name(),manufacturerDTO.getManufacturer_details());
            manufacturerRepository.save(manufacturer);
            return "Manufacturer saved successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public Object getManufacturerById(int manufacturer_id){
        try{
            if(!manufacturerRepository.existsById(manufacturer_id)){
                throw new Exception("Manufacturer id does not exist");
            }
            //Get the manufacturer from the repo using id.
            Manufacturer manufacturer = manufacturerRepository.findById(manufacturer_id).get();

            //Convert the manufacturer to DTO and return.
            ManufacturerDTO manufacturerDTO = new ManufacturerDTO(manufacturer.getManufacturer_name(),manufacturer.getManufacturer_details());
            return manufacturerDTO;
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public List<Object> getAllManufacturer(){
        try{
            //Get all the manufactures from repo.
            List<Manufacturer> manufacturerList = manufacturerRepository.findAll();

            //Convert the manufacturer to DTO and add it to the DTO list.
            List<Object> manufacturerDTOList = new ArrayList<>();
            if(manufacturerList.size()>0) {
                for (Manufacturer manufacturer : manufacturerList) {
                    manufacturerDTOList.add(new ManufacturerDTO(manufacturer.getManufacturer_name(), manufacturer.getManufacturer_details()));
                }
            }
            else{
                throw new Exception("Manufacturer list is empty");
            }
            return manufacturerDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public String updateManufacturer(int manufacturer_id,ManufacturerDTO manufacturerDTO){
        try{
            if(!manufacturerRepository.existsById(manufacturer_id)){
                throw new Exception("Manufacturer id does not exist");
            }
            //Get the manufacturer from repo using id.
            Manufacturer manufacturer = manufacturerRepository.findById(manufacturer_id).get();
            //Check whether manufacturer name is changed. If changed check whether the new name already exist.
            if(manufacturer.getManufacturer_name()!=manufacturerDTO.getManufacturer_name() && manufacturerRepository.findByManufacturerName(manufacturerDTO.getManufacturer_name())!=null){
                throw new Exception("Manufacturer name already exist");
            }
            //Update the manufacturer name and details and save.
            manufacturer.setManufacturer_name(manufacturerDTO.getManufacturer_name());
            manufacturer.setManufacturer_details(manufacturerDTO.getManufacturer_details());
            manufacturerRepository.save(manufacturer);
            return "Updated manufacturer successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteManufacturerById(int manufacturer_id){
        try{
            if(!manufacturerRepository.existsById(manufacturer_id)){
                throw new Exception("Manufacturer id does not exist");
            }
            Manufacturer manufacturer = manufacturerRepository.findById(manufacturer_id).get();
            List<Product> productList = manufacturer.getProductList();
            if(productList.size()>0){
                for(Product product : productList){
                    product.setManufacturer(null);
                }
                productRepository.saveAll(productList);
            }
            manufacturer.setProductList(null);
            manufacturerRepository.delete(manufacturer);
            return "Deleted manufacturer successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteAllManufacturer(){
        try{
            List<Product> productList = productRepository.findAll();
            if(productList.size()>0){
                for (Product product : productList){
                    product.setManufacturer(null);
                }
                productRepository.saveAll(productList);
            }

            List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
            if(manufacturerList.size()>0){
                for (Manufacturer manufacturer : manufacturerList){
                    manufacturer.setProductList(null);
                }
                manufacturerRepository.deleteAll();
            }
            else{
                throw new Exception("Manufacturer table is already empty");
            }


            return "Deleted all manufacturers";
        }
        catch (Exception e){
            return e.toString();
        }
    }

}
