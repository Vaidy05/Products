package com.example.Products.Controller;

import com.example.Products.DTO.FashionDTO;
import com.example.Products.Model.Chair;
import com.example.Products.Model.Fashion;
import com.example.Products.Model.Product;
import com.example.Products.Service.FashionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fashion")
public class FashionController {

    @Autowired
    FashionService fashionService;

    @PostMapping("/add_fashion/{productId}")
    public String addFashion(@PathVariable int productId, @RequestBody FashionDTO fashionDTO){
        try{
            //Gets the input product Id and DTO and returns the whether the object is successfully added or not.
            return fashionService.addFashion(productId,fashionDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_all_fashion")
    public List<Object> getAllFashion(){
        try{
            //Returns the list of objects
            return fashionService.getAllFashion();
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @GetMapping("/get_fashion_by_product")
    public List<Object> getFashionByProduct(@RequestParam int productId){
        try{
            //Gets the input fashion Id and returns the object.
            return fashionService.getFashionByProduct(productId);
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @GetMapping("/get_fashion_by_id")
    public Object getFashionById(@RequestParam int fashionId){
        try{
            //Gets the input product Id and returns the list of corresponding objects.
            return fashionService.getFashionById(fashionId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public List<Object> getFashionByBrand(@RequestParam String brand){
        try{
            //Gets the input brand and returns the list of objects having that brand.
            return fashionService.getFashionByBrand(brand);
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @PutMapping("/update_fashion/{fashionId}")
    public String updateFashion(@PathVariable int fashionId, @RequestBody FashionDTO fashionDTO){
        try{
            //Gets the input Id and DTO and returns whether the object is updated or not.
            return fashionService.updateFashion(fashionId,fashionDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_fashion")
    public String deleteFashion(@RequestParam int fashionId){
        try{
            //Gets the input id and returns whether the object is deleted or not.
            return fashionService.deleteFashion(fashionId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_fashion_by_product")
    public String deleteFashionByProduct(@RequestParam int productId){
        try{
            //Gets the input product id and returns whether the objects corresponding to the product is deleted or not.
            return fashionService.deleteFashionByProduct(productId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("delete_all_fashion")
    public String deleteAllFashion(){
        try{
            //Returns whether all the object entries are deleted or not.
            return fashionService.deleteAllFashion();
        }
        catch (Exception e){
            return e.toString();
        }
    }


}
