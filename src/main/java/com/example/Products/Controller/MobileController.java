package com.example.Products.Controller;

import com.example.Products.DTO.MobileDTO;
import com.example.Products.Service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mobile")
public class MobileController {

    @Autowired
    MobileService mobileService;

    @PostMapping("/add_mobile/{productId}")
    public String addMobile(@PathVariable int productId, @RequestBody MobileDTO mobileDTO){
        try{
            //Gets the input product Id and DTO and returns the whether the object is successfully added or not.
            return mobileService.addMobile(productId,mobileDTO);
        }
        catch (Exception e){
            return e.toString();
        }

    }

    @GetMapping("/get_all_mobiles")
    public List<Object> getAllMobiles(){
        try{
            //Returns the list of objects
            return mobileService.getAllMobiles();
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @GetMapping("/get_mobile_by_id")
    public Object getMobileById(@RequestParam int mobileId){
        try{
            //Gets the input mobile Id and returns the object.
            return mobileService.getMobileById(mobileId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_mobile_by_product")
    public List<Object> getMobileByProduct(@RequestParam int productId){
        try{
            //Gets the input product Id and returns the list of corresponding objects.
            return mobileService.getMobileByProduct(productId);
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @GetMapping("/get_mobile_by_brand")
    public List<Object> getMobileByBrand(@RequestParam String brand){
        try{
            //Gets the input brand and returns the list of objects having that brand.
            return mobileService.getMobileByBrand(brand);
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @PutMapping("/update_mobile/{mobileId}")
    public String updateMobile(@PathVariable int mobileId, @RequestBody MobileDTO mobileDTO){
        try{
            //Gets the input Id and DTO and returns whether the object is updated or not.
            return mobileService.updateMobile(mobileId, mobileDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_mobile")
    public String deleteMobile(@RequestParam int mobileId){
        try{
            //Gets the input id and returns whether the object is deleted or not.
            return mobileService.deleteMobile(mobileId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_mobile_by_product")
    public String deleteMobileByProduct(@RequestParam int productId){
        try{
            //Gets the input product id and returns whether the objects corresponding to the product is deleted or not.
            return mobileService.deleteMobileByProduct(productId);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_all_mobile")
    public String deleteAllMobile(){
        try{
            //Returns whether all the object entries are deleted or not.
            return mobileService.deleteAllMobile();
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
