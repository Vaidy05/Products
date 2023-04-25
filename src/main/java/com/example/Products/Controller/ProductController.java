package com.example.Products.Controller;

import com.example.Products.DTO.FashionDTO;
import com.example.Products.DTO.MobileDTO;
import com.example.Products.DTO.ProductDTO;
import com.example.Products.DTO.ShoesDTO;
import com.example.Products.Service.ProductServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceLayer productService;

    @PostMapping("/add_product")
    public String addProduct(@RequestParam String productName){
        try{
            //Gets the input product name and returns whether the product is saved.
            return productService.addProduct(productName);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_all_products")
    public List<Object> getAllProduct(){
        try{
            //Return the list of objects present in the database.
            return productService.getAllProducts();
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @GetMapping("/get_product_by_id")
    public Object getProductById(@RequestParam int id){
        try{
            //Gets the input id and return the corresponding object.
            return productService.getProductById(id);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @PutMapping("/update_product/{productId}")
    public String updateProduct(@PathVariable int productId, @RequestParam String productName){
        try{
            //Gets the input id and product name and returns whether the product is updated.
            return productService.updateProduct(productId,productName);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_product")
    public String deleteProduct(@RequestParam int productId){
        try{
            //Gets the input id and returns whether the product is deleted.
            return productService.deleteProduct(productId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_all_products")
    public String deleteAllProducts(){
        try{
            //Returns whether all products are deleted or not.
            return productService.deleteAllProducts();
        }
        catch (Exception e){
            return e.toString();
        }
    }

}
