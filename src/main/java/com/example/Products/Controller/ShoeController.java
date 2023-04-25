package com.example.Products.Controller;

import com.example.Products.DTO.ShoesDTO;
import com.example.Products.Service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shoes")
public class ShoeController {

    @Autowired
    ShoeService shoeService;

    @PostMapping("/add_shoes/{productId}")
    public String addShoes(@PathVariable int productId, @RequestBody ShoesDTO shoesDTO){
        try{
            //Gets the input product Id and DTO and returns the whether the object is successfully added or not.
            return shoeService.addShoes(productId,shoesDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_all_shoes")
    public List<Object> getAllShoes(){
        try{
            //Returns the list of objects
            return shoeService.getAllShoes();
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @GetMapping("/get_shoe_by_id")
    public Object getShoesById(@RequestParam int shoeId){
        try{
            //Gets the input shoes Id and returns the object.
            return shoeService.getShoeById(shoeId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_shoe_by_product")
    public List<Object> getShoeByProduct(@RequestParam int productId){
        try{
            //Gets the input product Id and returns the list of corresponding objects.
            return shoeService.getShoeByProduct(productId);
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @GetMapping("/get_shoe_by_brand")
    public List<Object> getShoesByBrand(@RequestParam String brand){
        try{
            //Gets the input brand and returns the list of objects having that brand.
            return shoeService.getShoeByBrand(brand);
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @PutMapping("/update_shoes/{shoesId}")
    public String updateShoes(@PathVariable int shoesId, @RequestBody ShoesDTO shoesDTO){
        try{
            //Gets the input Id and DTO and returns whether the object is updated or not.
            return shoeService.updateShoes(shoesId,shoesDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_shoe")
    public String deleteShoe(@RequestParam int shoeId){
        try{
            //Gets the input id and returns whether the object is deleted or not.
            return shoeService.deleteShoes(shoeId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_shoe_by_product")
    public String deleteShoeByProduct(@RequestParam int productId){
        try{
            //Gets the input product id and returns whether the objects corresponding to the product is deleted or not.
            return shoeService.deleteShoeByProduct(productId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_all_shoe")
    public String deleteAllShoes(){
        try{
            //Returns whether all the object entries are deleted or not.
            return shoeService.deleteAllShoes();
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
