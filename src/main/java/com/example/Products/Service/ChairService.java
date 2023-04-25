package com.example.Products.Service;

import com.example.Products.DTO.ChairDTO;
import com.example.Products.Model.Chair;
import com.example.Products.Model.Product;
import com.example.Products.Repository.ChairRepository;
import com.example.Products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChairService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ChairRepository chairRepository;

    public String addChair(int productId, ChairDTO chairDTO){
        try {
            //Finding product from product repository
            Product product = productRepository.findById(productId).get();

            //Converting string to date to store in chair object
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(chairDTO.getDate());
            //Creating chair object
            Chair chair = new Chair(chairDTO.getBrand(), chairDTO.getQuantity(), date, chairDTO.getPrice_per_unit(), chairDTO.getMaterial(), chairDTO.getColor(), chairDTO.getDimensions());
            List<Chair> chairList = product.getChairList();
            //Check whether chairList is empty or contains some chair object. If contains add the new object to list.
            if (chairList.equals(null)) {
                chairList = new ArrayList<>();
            }
            chairList.add(chair);
            //Update the chairList and no of products for the product object and map the chair to product.
            product.setChairList(chairList);
            product.setNo_of_products(chairList.size());
            chair.setProduct(product);
            //Save the product and correspondingly chair object gets saved due to Cascading.
            productRepository.save(product);
            return "Chair added successfully";
        }
        catch(Exception e){
            return e.toString();
        }
    }

    public List<Object> getAllChair(){
        try {
            //Get all the chair entries from the table.
            List<Chair> chairList = chairRepository.findAll();
            List<Object> chairDTOList = new ArrayList<>();
            //Convert the chair object to chairDTO and add it in the DTO list.
            for (Chair chair : chairList) {
                chairDTOList.add(new ChairDTO(chair.getBrand(), chair.getQuantity(), chair.getDate().toString(), chair.getPrice_per_unit(), chair.getMaterial(), chair.getColor(), chair.getDimensions()));
            }
            return chairDTOList;
        }
        catch(Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public Object getChairById(int chairId){
        try {
            //Get the object from repository using id.
            Chair chair = chairRepository.findById(chairId).get();
            //Convert the chair object to chairDTO
            ChairDTO chairDTO = new ChairDTO(chair.getBrand(), chair.getQuantity(), chair.getDate().toString(), chair.getPrice_per_unit(), chair.getMaterial(), chair.getColor(), chair.getDimensions());
            return chairDTO;
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public List<Object> getChairByProduct(int productId){
        try{
            //Get the product from repository using product Id.
            Product product = productRepository.findById(productId).get();
            List<Chair> chairList = product.getChairList();
            //Check whether the chairList is empty or not.If empty throw exception.
            if(chairList.size()==0){
                throw new Exception("Please check the product ID");
            }
            //If the chairList is not empty create new Object List. Convert chair object to chairDTO and add in the list.
            List<Object> chairDTOList = new ArrayList<>();
            for (Chair chair : chairList) {
                chairDTOList.add(new ChairDTO(chair.getBrand(), chair.getQuantity(), chair.getDate().toString(), chair.getPrice_per_unit(), chair.getMaterial(), chair.getColor(), chair.getDimensions()));
            }
            return chairDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public List<Object> getChairByBrand(String brand){
        try{
            //Get the list of chair entries from the repository.
            List<Chair> chairList = chairRepository.findAll();
            //Create new Object List. If the brand equals the given brand name add in list.
            List<Object> chairDTOList = new ArrayList<>();
            for(Chair chair : chairList){
                if(chair.getBrand().equals(brand)){
                    chairDTOList.add(new ChairDTO(chair.getBrand(), chair.getQuantity(), chair.getDate().toString(), chair.getPrice_per_unit(), chair.getMaterial(), chair.getColor(), chair.getDimensions()));
                }
            }
            return chairDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public String updateChair(int chairId, ChairDTO chairDTO){
        try{
            //Get the chair object from the repository using chair id.
            Chair chair = chairRepository.findById(chairId).get();
            //Set the properties of chairDTO to chair object and save it in repository.
            chair.setBrand(chairDTO.getBrand());
            chair.setQuantity(chairDTO.getQuantity());
            chair.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(chairDTO.getDate()));
            chair.setPrice_per_unit(chairDTO.getPrice_per_unit());
            chair.setMaterial(chairDTO.getMaterial());
            chair.setColor(chairDTO.getColor());
            chair.setDimensions(chairDTO.getDimensions());

            chairRepository.save(chair);
            return "Updated chair details successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteChair(int chairId){
        try {
            //Get the chair object from the repository using chair id.
            Chair chair = chairRepository.findById(chairId).get();
            //Get the product related to given chair object.
            Product product = chair.getProduct();
            List<Chair> chairList = product.getChairList();
            //Remove the chair from the list and set the list and no of products of the product object and save.
            chairList.remove(chair);
            product.setChairList(chairList);
            product.setNo_of_products(chairList.size());
            //Delete the chair object.
            chairRepository.delete(chair);
            productRepository.save(product);
            return "Chair deleted successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteChairByProduct(int productId){
        try{
            //Get the product from repository using product Id.
            Product product = productRepository.findById(productId).get();
            //Check whether the list is empty or not. If empty throw exception.
            List<Chair> chairList = product.getChairList();
            if(chairList.size()==0){
                throw new Exception("Please enter the correct product Id of chair");
            }
            //If not empty set the product object's chairList to null and no of products to 0.
            product.setChairList(null);
            product.setNo_of_products(0);
            //Save the product and delete all the chair objects corresponding to object.
            productRepository.save(product);
            chairRepository.deleteAll(chairList);
            return "Deleted all chair entries of this product successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteAllChair(){
        try{
            //Get the list of chair entries from the repository.
            List<Chair> chairList = chairRepository.findAll();
            //For each chair object, the corresponding product object's list is set to null and no of products to 0.
            List<Product> productList = new ArrayList<>();
            for(Chair chair : chairList){
                Product product = chair.getProduct();
                //If the product not exist in the list, set chairList to null and no of products to 0 and add to list.
                if(!productList.contains(product)){
                    product.setChairList(null);
                    product.setNo_of_products(0);
                    productList.add(product);
                }
            }
            //Save all the products stored in list and delete all the chair entries.
            productRepository.saveAll(productList);
            chairRepository.deleteAll();
            return "Deleted all chair entries successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
