package com.example.Products.Controller;

import com.example.Products.DTO.ChairDTO;
import com.example.Products.Repository.ChairRepository;
import com.example.Products.Service.ChairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chair")
public class ChairController {

    @Autowired
    ChairService chairService;

    @PostMapping("/add_chair/{productId}")
    public String addChair(@PathVariable int productId, @RequestBody ChairDTO chairDTO){
        try{
            //Gets the input product Id and DTO and returns the whether the object is successfully added or not.
            return chairService.addChair(productId,chairDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_all_chairs")
    public List<Object> getAllChairs(){
        try{
            //Returns the list of objects
            return chairService.getAllChair();
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @GetMapping("/get_chair_by_id")
    public Object getChairById(@RequestParam int chairId){
        try{
            //Gets the input chair Id and returns the object.
            return chairService.getChairById(chairId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_chair_by_product")
    public List<Object> getChairByProduct(@RequestParam int productId){
        try{
            //Gets the input product Id and returns the list of corresponding objects.
            return chairService.getChairByProduct(productId);
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @GetMapping("/get_chair_by_brand")
    public List<Object> getChairByBrand(@RequestParam String brand){
        try{
            //Gets the input brand and returns the list of objects having that brand.
            return chairService.getChairByBrand(brand);
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @PutMapping("/update_chair/{chairId}")
    public String updateChair(@PathVariable int chairId, @RequestBody ChairDTO chairDTO){
        try {
            //Gets the input Id and DTO and returns whether the object is updated or not.
            return chairService.updateChair(chairId, chairDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_chair")
    public String deleteChair(@RequestParam int chairId){
        try{
            //Gets the input id and returns whether the object is deleted or not.
            return chairService.deleteChair(chairId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_chair_by_product")
    public String deleteChairByProduct(@RequestParam int productId){
        try{
            //Gets the input product id and returns whether the objects corresponding to the product is deleted or not.
            return chairService.deleteChairByProduct(productId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_all_chairs")
    public String deleteAllChair(){
        try{
            //Returns whether all the object entries are deleted or not.
            return chairService.deleteAllChair();
        }
        catch (Exception e){
            return e.toString();
        }
    }

}
