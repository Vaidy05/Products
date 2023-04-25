package com.example.Products.Service;

import com.example.Products.DTO.ShoesDTO;
import com.example.Products.Model.Pen;
import com.example.Products.Model.Product;
import com.example.Products.Model.Shoes;
import com.example.Products.Repository.ProductRepository;
import com.example.Products.Repository.ShoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShoeService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShoesRepository shoesRepository;

    public String addShoes(int productId, ShoesDTO shoesDTO){
        try {
            //Finding product from product repository
            Product product = productRepository.findById(productId).get();
            //Converting string to date to store in shoes object
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(shoesDTO.getDate());
            Shoes shoes = new Shoes(shoesDTO.getBrand(), shoesDTO.getQuantity(), date, shoesDTO.getPrice_per_unit(), shoesDTO.getMaterial(), shoesDTO.getColor(), shoesDTO.getType(), shoesDTO.getSize(), shoesDTO.getHeelType());
            List<Shoes> shoesList = product.getShoesList();
            //Check whether shoesList is empty or contains some shoes object. If contains add the new object to list
            if (shoesList.equals(shoesList)) {
                shoesList = new ArrayList<>();
            }
            shoesList.add(shoes);
            //Update the shoesList and no of products for the product object and map the shoes to product.
            product.setShoesList(shoesList);
            product.setNo_of_products(shoesList.size());
            shoes.setProduct(product);
            //Save the product and correspondingly shoes object gets saved due to Cascading.
            productRepository.save(product);
            return "Shoes added successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public List<Object> getAllShoes(){
        try {
            //Get the list of shoe entries from the repository.
            List<Shoes> shoesList = shoesRepository.findAll();
            List<Object> shoesDTOList = new ArrayList<>();
            //Convert the shoes object to shoesDTO and add it in the DTO list.
            for (Shoes shoes : shoesList) {
                shoesDTOList.add(new ShoesDTO(shoes.getBrand(), shoes.getQuantity(), shoes.getDate().toString(), shoes.getPrice_per_unit(), shoes.getMaterial(), shoes.getColor(), shoes.getType(), shoes.getSize(), shoes.getHeelType()));
            }
            return shoesDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public Object getShoeById(int shoeId){
        try {
            //Get the object from repository using id.
            Shoes shoes = shoesRepository.findById(shoeId).get();
            //Convert the shoes object to shoesDTO
            ShoesDTO shoesDTO = new ShoesDTO(shoes.getBrand(), shoes.getQuantity(), shoes.getDate().toString(), shoes.getPrice_per_unit(), shoes.getMaterial(), shoes.getColor(), shoes.getType(), shoes.getSize(), shoes.getHeelType());
            return shoesDTO;
        }
        catch(Exception e){
            return e.toString();
        }
    }

    public List<Object> getShoeByProduct(int productId){
        try{
            //Get the product from repository using product Id.
            Product product = productRepository.findById(productId).get();
            List<Shoes> shoesList = product.getShoesList();
            //Check whether the shoesList is empty or not.If empty throw exception.
            if(shoesList.size()==0){
                throw new Exception("Please check the Product Id");
            }
            //If the shoesList is not empty create new Object List. Convert shoes object to shoesDTO and add in the list
            List<Object> shoesDTOList = new ArrayList<>();
            for (Shoes shoes : shoesList) {
                shoesDTOList.add(new ShoesDTO(shoes.getBrand(), shoes.getQuantity(), shoes.getDate().toString(), shoes.getPrice_per_unit(), shoes.getMaterial(), shoes.getColor(), shoes.getType(), shoes.getSize(), shoes.getHeelType()));
            }
            return shoesDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public List<Object> getShoeByBrand(String brand){
        try{
            //Get the list of shoe entries from the repository.
            List<Shoes> shoesList = shoesRepository.findAll();
            //Create new Object List. If the brand equals the given brand name add in list.
            List<Object> shoesDTOList = new ArrayList<>();
            for(Shoes shoes : shoesList){
                if(shoes.getBrand().equals(brand)){
                    shoesDTOList.add(new ShoesDTO(shoes.getBrand(), shoes.getQuantity(), shoes.getDate().toString(), shoes.getPrice_per_unit(), shoes.getMaterial(), shoes.getColor(), shoes.getType(), shoes.getSize(), shoes.getHeelType()));
                }
            }
            return shoesDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public String updateShoes(int shoesId, ShoesDTO shoesDTO){
        try{
            //Get the shoe object from the repository using shoe id.
            Shoes shoes = shoesRepository.findById(shoesId).get();
            //Set the properties of shoesDTO to shoes object and save it in repository.
            shoes.setBrand(shoesDTO.getBrand());
            shoes.setQuantity(shoesDTO.getQuantity());
            shoes.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(shoesDTO.getDate()));
            shoes.setPrice_per_unit(shoesDTO.getPrice_per_unit());
            shoes.setMaterial(shoesDTO.getMaterial());
            shoes.setColor(shoesDTO.getColor());
            shoes.setType(shoesDTO.getType());
            shoes.setSize(shoesDTO.getSize());
            shoes.setHeelType(shoesDTO.getHeelType());
            shoesRepository.save(shoes);
            return "Updated shoe details successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteShoes(int shoeId){
        try{
            //Get the shoe object from the repository using shoe id.
            Shoes shoes = shoesRepository.findById(shoeId).get();
            //Get the product related to given shoe object.
            Product product = shoes.getProduct();
            List<Shoes> shoesList = product.getShoesList();
            //Remove the shoe from the list and set the list and no of products of the product object and save.
            shoesList.remove(shoes);
            product.setShoesList(shoesList);
            product.setNo_of_products(shoesList.size());
            //Delete the shoe object.
            shoesRepository.delete(shoes);
            productRepository.save(product);
            return "Shoe deleted successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteShoeByProduct(int productId){
        try{
            //Get the product from repository using product Id.
            Product product = productRepository.findById(productId).get();
            List<Shoes> shoesList = product.getShoesList();
            //Check whether the list is empty or not. If empty throw exception.
            if(shoesList.size()==0){
                throw new Exception("Please enter the correct product Id of shoe");
            }
            //If not empty set the product object's shoeList to null and no of products to 0.
            product.setShoesList(null);
            product.setNo_of_products(0);
            //Save the product and delete all the shoe objects corresponding to object.
            productRepository.save(product);
            shoesRepository.deleteAll(shoesList);
            return "Deleted all shoe entries of this product successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteAllShoes(){
        try{
            //Get the list of shoe entries from the repository.
            List<Shoes> shoesList = shoesRepository.findAll();
            //For each shoe object, the corresponding product object's list is set to null and no of products to 0.
            List<Product> productList = new ArrayList<>();
            for(Shoes shoes : shoesList){
                Product product = shoes.getProduct();
                //If the product not exist in the list, set shoeList to null and no of products to 0 and add to list.
                if(!productList.contains(product)){
                    product.setShoesList(null);
                    product.setNo_of_products(0);
                    productList.add(product);
                }
            }
            //Save all the products stored in list and delete all the shoe entries.
            productRepository.saveAll(productList);
            shoesRepository.deleteAll();
            return "Deleted all shoe entries successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
