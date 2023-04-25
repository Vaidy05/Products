package com.example.Products.Service;

import com.example.Products.DTO.*;
import com.example.Products.Model.*;
import com.example.Products.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.ArrayList;

import java.util.List;

@Service
public class ProductServiceLayer {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MobileRepository mobileRepository;

    @Autowired
    FashionRepository fashionRepository;

    @Autowired
    ShoesRepository shoesRepository;

    @Autowired
    ChairRepository chairRepository;

    @Autowired
    PenRepository penRepository;

    public String addProduct(String productName){
        try {
            //Check whether the product name already exist. If exist throw exception.
            if (productRepository.findByProductName(productName.toUpperCase())!=null) {
                throw new Exception("Product already existing");
            }
            //If not exist, create new product and save.
            Product product = new Product(productName.toUpperCase());
            productRepository.save(product);
            return "Product added successfully";
        }
        catch(Exception e){
            return e.toString();
        }
    }

    public List<Object> getAllProducts(){
        try {
            //Get the list of products from the repository.
            List<Product> productList = productRepository.findAll();
            List<Object> productDTOList = new ArrayList<>();
            //Covert the product to productDTO and add it in the DTO list.
            for (Product product : productList) {
                productDTOList.add(new ProductDTO(product.getProductName(), product.getNo_of_products()));
            }
            return productDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public Object getProductById(int productId){
        try {
            //Get the product from the repository using id.
            Product product = productRepository.findById(productId).get();
            //Convert the product to productDTO and return.
            ProductDTO productDTO = new ProductDTO(product.getProductName(), product.getNo_of_products());
            return productDTO;
        }
        catch(Exception e){
            return e.toString();
        }
    }

    public String updateProduct(int productId,String productName){
        try {
            //Get the product from repository using id.
            Product product = productRepository.findById(productId).get();
            //Update the product properties and save.
            product.setProductName(productName.toUpperCase());
            productRepository.save(product);
            return "Updated product details successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }
    public String deleteProduct(int productId){
        try{
            //Get the product from the repository using id.
            Product product = productRepository.findById(productId).get();
            List<Mobile> mobileList = product.getMobileList();
            List<Fashion> fashionList = product.getFashionList();
            List<Shoes> shoesList = product.getShoesList();
            List<Chair> chairList = product.getChairList();
            //Check which is not empty and delete the objects in the list from repository.
            List<Pen> penList = product.getPenList();
            if(mobileList.size()>0){
                mobileRepository.deleteAll(mobileList);
            }
            else if(fashionList.size()>0){
                fashionRepository.deleteAll(fashionList);
            }
            else if(shoesList.size()>0){
                shoesRepository.deleteAll(shoesList);
            }
            else if(chairList.size()>0){
                chairRepository.deleteAll(chairList);
            }
            else if(penList.size()>0){
                penRepository.deleteAll(penList);
            }
            //Delete the product from repository.
            productRepository.delete(product);
            return "Product deleted successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteAllProducts(){
        try{
            //Delete all entries from all the tables.
            mobileRepository.deleteAll();
            fashionRepository.deleteAll();
            shoesRepository.deleteAll();
            chairRepository.deleteAll();
            penRepository.deleteAll();
            productRepository.deleteAll();

            return "All products deleted successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

}
