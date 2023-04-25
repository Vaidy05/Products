package com.example.Products.Service;

import com.example.Products.DTO.PenDTO;
import com.example.Products.Model.Mobile;
import com.example.Products.Model.Pen;
import com.example.Products.Model.Product;
import com.example.Products.Repository.PenRepository;
import com.example.Products.Repository.ProductRepository;
import org.aspectj.weaver.patterns.PerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PenService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PenRepository penRepository;

    public String addPen(int productId, PenDTO penDTO){
        try {
            //Finding product from product repository
            Product product = productRepository.findById(productId).get();
            //Converting string to date to store in pen object
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(penDTO.getDate());
            Pen pen = new Pen(penDTO.getBrand(), penDTO.getQuantity(), date, penDTO.getPrice_per_unit(), penDTO.getColor(), penDTO.getInk(), penDTO.getType());
            List<Pen> penList = product.getPenList();
            //Check whether penList is empty or contains some pen object. If contains add the new object to list
            if (penList.equals(null)) {
                penList = new ArrayList<>();
            }
            penList.add(pen);
            //Update the penList and no of products for the product object and map the pen to product.
            product.setPenList(penList);
            product.setNo_of_products(penList.size());
            pen.setProduct(product);
            //Save the product and correspondingly pen object gets saved due to Cascading.
            productRepository.save(product);
            return "Pen added successfully";
        }
        catch(Exception e){
            return e.toString();
        }
    }

    public List<Object> getAllPen(){
        try {
            //Get the list of pen entries from the repository.
            List<Pen> penList = penRepository.findAll();
            List<Object> penDTOList = new ArrayList<>();
            //Convert the pen object to penDTO and add it in the DTO list.
            for (Pen pen : penList) {
                penDTOList.add(new PenDTO(pen.getBrand(), pen.getQuantity(), pen.getDate().toString(), pen.getPrice_per_unit(), pen.getColor(), pen.getInk(), pen.getType()));
            }
            return penDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public Object getPenById(int penId){
        try {
            //Get the object from repository using id.
            Pen pen = penRepository.findById(penId).get();
            //Convert the pen object to penDTO
            PenDTO penDTO = new PenDTO(pen.getBrand(), pen.getQuantity(), pen.getDate().toString(), pen.getPrice_per_unit(), pen.getColor(), pen.getInk(), pen.getType());
            return penDTO;
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public List<Object> getPenByProduct(int productId){
        try{
            //Get the product from repository using product Id.
            Product product = productRepository.findById(productId).get();
            List<Pen> penList = product.getPenList();
            //Check whether the penList is empty or not.If empty throw exception.
            if(penList.size()==0){
                throw new Exception("Please check the product id");
            }
            //If the penList is not empty create new Object List. Convert pen object to penDTO and add in the list
            List<Object> penDTOList = new ArrayList<>();
            for (Pen pen : penList) {
                penDTOList.add(new PenDTO(pen.getBrand(), pen.getQuantity(), pen.getDate().toString(), pen.getPrice_per_unit(), pen.getColor(), pen.getInk(), pen.getType()));
            }
            return penDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public List<Object> getPenByBrand(String brand){
        try{
            //Get the list of pen entries from the repository.
            List<Pen> penList = penRepository.findAll();
            //Create new Object List. If the brand equals the given brand name add in list.
            List<Object> penDTOList = new ArrayList<>();
            for(Pen pen : penList){
                if(pen.getBrand().equals(brand)){
                    penDTOList.add(new PenDTO(pen.getBrand(), pen.getQuantity(), pen.getDate().toString(), pen.getPrice_per_unit(), pen.getColor(), pen.getInk(), pen.getType()));
                }
            }
            return penDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public String updatePen(int penId, PenDTO penDTO){
        try {
            //Get the pen object from the repository using pen id.
            Pen pen = penRepository.findById(penId).get();
            //Set the properties of penDTO to pen object and save it in repository.
            pen.setBrand(penDTO.getBrand());
            pen.setQuantity(penDTO.getQuantity());
            pen.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(penDTO.getDate()));
            pen.setPrice_per_unit(penDTO.getPrice_per_unit());
            pen.setColor(penDTO.getColor());
            pen.setInk(penDTO.getInk());
            pen.setType(penDTO.getType());
            penRepository.save(pen);
            return "Updated pen details successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deletePen(int penId){
        try{
            //Get the pen object from the repository using pen id.
            Pen pen = penRepository.findById(penId).get();
            //Get the product related to given pen object.
            Product product = pen.getProduct();
            List<Pen> penList = product.getPenList();
            //Remove the pen from the list and set the list and no of products of the product object and save.
            penList.remove(pen);
            product.setPenList(penList);
            product.setNo_of_products(penList.size());
            //Delete the pen object.
            penRepository.delete(pen);
            productRepository.save(product);
            return "Pen deleted successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deletePenByProduct(int productId){
        try{
            //Get the product from repository using product Id.
            Product product = productRepository.findById(productId).get();
            List<Pen> penList = product.getPenList();
            //Check whether the list is empty or not. If empty throw exception.
            if(penList.size()==0){
                throw new Exception("Please enter the correct product Id of pen");
            }
            //If not empty set the product object's penList to null and no of products to 0.
            product.setPenList(null);
            product.setNo_of_products(0);
            //Save the product and delete all the pen objects corresponding to object.
            productRepository.save(product);
            penRepository.deleteAll(penList);
            return "Deleted all pen entries of this product successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteAllPen(){
        try{
            //Get the list of pen entries from the repository.
            List<Pen> penList = penRepository.findAll();
            //For each pen object, the corresponding product object's list is set to null and no of products to 0.
            List<Product> productList = new ArrayList<>();
            for(Pen pen : penList){
                Product product = pen.getProduct();
                //If the product not exist in the list, set penList to null and no of products to 0 and add to list.
                if(!productList.contains(product)){
                    product.setPenList(null);
                    product.setNo_of_products(0);
                    productList.add(product);
                }
            }
            //Save all the products stored in list and delete all the pen entries.
            productRepository.saveAll(productList);
            penRepository.deleteAll();
            return "Deleted all pen entries successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
