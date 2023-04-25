package com.example.Products.Service;

import com.example.Products.DTO.FashionDTO;
import com.example.Products.Model.Chair;
import com.example.Products.Model.Fashion;
import com.example.Products.Model.Product;
import com.example.Products.Repository.FashionRepository;
import com.example.Products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FashionService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FashionRepository fashionRepository;

    public String addFashion(int productId, FashionDTO fashionDTO){
        try {
            //Finding product from product repository
            Product product = productRepository.findById(productId).get();
            //Converting string to date to store in fashion object
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fashionDTO.getDate());
            Fashion fashion = new Fashion(fashionDTO.getBrand(), fashionDTO.getQuantity(), date, fashionDTO.getPrice_per_unit(), fashionDTO.getMaterial(), fashionDTO.getColor(), fashionDTO.getType(), fashionDTO.getSize());
            List<Fashion> fashionList = product.getFashionList();
            //Check whether fashionList is empty or contains some fashion object. If contains add the new object to list
            if (fashionList.equals(null)) {
                fashionList = new ArrayList<>();
            }
            fashionList.add(fashion);
            //Update the fashionList and no of products for the product object and map the fashion to product.
            product.setFashionList(fashionList);
            product.setNo_of_products(fashionList.size());
            fashion.setProduct(product);
            //Save the product and correspondingly fashion object gets saved due to Cascading.
            productRepository.save(product);
            return "Fashion added successfully";
        }
        catch(Exception e){
            return e.toString();
        }
    }

    public List<Object> getAllFashion(){
        try {
            //Get the list of fashion entries from the repository.
            List<Fashion> fashionList = fashionRepository.findAll();
            List<Object> fashionDTOList = new ArrayList<>();
            //Convert the fashion object to fashionDTO and add it in the DTO list.
            for (Fashion fashion : fashionList) {
                fashionDTOList.add(new FashionDTO(fashion.getBrand(), fashion.getQuantity(), fashion.getDate().toString(), fashion.getPrice_per_unit(), fashion.getMaterial(), fashion.getColor(), fashion.getType(), fashion.getSize()));
            }
            return fashionDTOList;
        }
        catch(Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public Object getFashionById(int fashionId){
        try {
            //Get the object from repository using id.
            Fashion fashion = fashionRepository.findById(fashionId).get();
            //Convert the fashion object to fashionDTO
            FashionDTO fashionDTO = new FashionDTO(fashion.getBrand(), fashion.getQuantity(), fashion.getDate().toString(), fashion.getPrice_per_unit(), fashion.getMaterial(), fashion.getColor(), fashion.getType(), fashion.getSize());
            return fashionDTO;
        }
        catch(Exception e){
            return e.toString();
        }
    }

    public List<Object> getFashionByProduct(int productId){
        try{
            //Get the product from repository using product Id.
            Product product = productRepository.findById(productId).get();
            List<Fashion> fashionList = product.getFashionList();
            //Check whether the fashionList is empty or not.If empty throw exception.
            if(fashionList.size()==0)
                throw new Exception("Please check the product ID");
            //If the fashionList is not empty create new Object List. Convert fashion object to fashionDTO and add in the list.
            List<Object> fashionDTOList = new ArrayList<>();
            for (Fashion fashion : fashionList) {
                fashionDTOList.add(new FashionDTO(fashion.getBrand(), fashion.getQuantity(), fashion.getDate().toString(), fashion.getPrice_per_unit(), fashion.getMaterial(), fashion.getColor(), fashion.getType(), fashion.getSize()));
            }
            return fashionDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public List<Object> getFashionByBrand(String brand){
        try{
            //Get the list of fashion entries from the repository.
            List<Fashion> fashionList = fashionRepository.findAll();
            //Create new Object List. If the brand equals the given brand name add in list.
            List<Object> fashionDTOList = new ArrayList<>();
            for(Fashion fashion : fashionList){
                if(fashion.getBrand().equals(brand))
                  fashionDTOList.add(new FashionDTO(fashion.getBrand(), fashion.getQuantity(), fashion.getDate().toString(), fashion.getPrice_per_unit(), fashion.getMaterial(), fashion.getColor(), fashion.getType(), fashion.getSize()));
            }
            return fashionDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }

    }

    public String updateFashion(int fashionId,FashionDTO fashionDTO){
        try {
            //Get the fashion object from the repository using fashion id.
            Fashion fashion = fashionRepository.findById(fashionId).get();
            //Set the properties of fashionDTO to fashion object and save it in repository.
            fashion.setBrand(fashionDTO.getBrand());
            fashion.setQuantity(fashionDTO.getQuantity());
            fashion.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(fashionDTO.getDate()));
            fashion.setPrice_per_unit(fashionDTO.getPrice_per_unit());
            fashion.setMaterial(fashionDTO.getMaterial());
            fashion.setColor(fashionDTO.getColor());
            fashion.setType(fashionDTO.getType());
            fashion.setSize(fashionDTO.getSize());
            fashionRepository.save(fashion);
            return "Updated fashion details successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteFashion(int fashionId){
        try{
            //Get the fashion object from the repository using fashion id.
            Fashion fashion = fashionRepository.findById(fashionId).get();
            //Get the product related to given fashion object.
            Product product = fashion.getProduct();
            List<Fashion> fashionList = product.getFashionList();
            //Remove the fashion from the list and set the list and no of products of the product object and save.
            fashionList.remove(fashion);
            product.setFashionList(fashionList);
            product.setNo_of_products(fashionList.size());
            //Delete the fashion object.
            fashionRepository.delete(fashion);
            productRepository.save(product);
            return "Fashion deleted successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteFashionByProduct(int productId){
        try{
            //Get the product from repository using product Id.
            Product product = productRepository.findById(productId).get();
            List<Fashion> fashionList = product.getFashionList();
            //Check whether the list is empty or not. If empty throw exception.
            if(fashionList.size()==0){
                throw new Exception("Please enter the correct product Id of fashion");
            }
            //If not empty set the product object's fashionList to null and no of products to 0.
            product.setFashionList(null);
            product.setNo_of_products(0);
            //Save the product and delete all the fashion objects corresponding to object.
            productRepository.save(product);
            fashionRepository.deleteAll(fashionList);
            return "Deleted all fashion entries of this product successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteAllFashion(){
        try{
            //Get the list of fashion entries from the repository.
            List<Fashion> fashionList = fashionRepository.findAll();
            //For each fashion object, the corresponding product object's list is set to null and no of products to 0.
            List<Product> productList = new ArrayList<>();
            for(Fashion fashion : fashionList){
                Product product = fashion.getProduct();
                //If the product not exist in the list, set fashionList to null and no of products to 0 and add to list.
                if(!productList.contains(product)){
                    product.setFashionList(null);
                    product.setNo_of_products(0);
                    productList.add(product);
                }
            }
            //Save all the products stored in list and delete all the fashion entries.
            productRepository.saveAll(productList);
            fashionRepository.deleteAll();
            return "Deleted all fashion entries successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
