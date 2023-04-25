package com.example.Products.Controller;

import com.example.Products.DTO.PenDTO;
import com.example.Products.Service.PenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pen")
public class PenController {

    @Autowired
    PenService penService;

    @PostMapping("/add_pen/{productId}")
    public String addPen(@PathVariable int productId, @RequestBody PenDTO penDTO){
        try{
            //Gets the input product Id and DTO and returns the whether the object is successfully added or not.
            return penService.addPen(productId,penDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_all_pen")
    public List<Object> getAllPen(){
        try{
            //Returns the list of objects
            return penService.getAllPen();
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @GetMapping("/get_pen_by_id")
    public Object getPenById(@RequestParam int penId){
        try{
            //Gets the input pen Id and returns the object.
            return penService.getPenById(penId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_pen_by_product")
    public List<Object> getPenByProduct(@RequestParam int productId){
        try{
            //Gets the input product Id and returns the list of corresponding objects.
            return penService.getPenByProduct(productId);
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @GetMapping("/get_pen_by_brand")
    public List<Object> getPenByBrand(@RequestParam String brand){
        try{
            //Gets the input brand and returns the list of objects having that brand.
            return penService.getPenByBrand(brand);
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @PutMapping("/update_pen/{penId}")
    public String updatePen(@PathVariable int penId, @RequestBody PenDTO penDTO){
        try{
            //Gets the input Id and DTO and returns whether the object is updated or not.
            return penService.updatePen(penId, penDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_pen")
    public String deletePen(@RequestParam int penId){
        try{
            //Gets the input id and returns whether the object is deleted or not.
            return penService.deletePen(penId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_pen_by_product")
    public String deletePenByProduct(@RequestParam int productId){
        try{
            //Gets the input product id and returns whether the objects corresponding to the product is deleted or not.
            return penService.deletePenByProduct(productId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_all_pen")
    public String deleteAllPen(){
        try{
            //Returns whether all the object entries are deleted or not.
            return penService.deleteAllPen();
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
