package com.example.Products.Controller;

import com.example.Products.DTO.ManufacturerDTO;
import com.example.Products.Service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    @Autowired
    ManufacturerService manufacturerService;

    @PostMapping("/add_manufacturer")
    public String addManufacturer(@RequestBody ManufacturerDTO manufacturerDTO){
        try{
            //Adds the manufacturer to repo and returns whether added successfully or not.
            return manufacturerService.addManufacturer(manufacturerDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_manufactuer_by_id")
    public Object getManufacturerById(@RequestParam int manufacturer_id){
        try{
            //Returns the manufacturer with the given id.
            return manufacturerService.getManufacturerById(manufacturer_id);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_all_manufacturer")
    public List<Object> getAllManufacturer (){
        try{
            //Returns the list of manufactures present in repo.
            return manufacturerService.getAllManufacturer();
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @PutMapping("/update_manufacturer/{manufacturer_id}")
    public String updateManufacturer(@PathVariable int manufacturer_id,@RequestBody ManufacturerDTO manufacturerDTO){
        try{
            //Updates the manufacturer and returns whether updated or not.
            return manufacturerService.updateManufacturer(manufacturer_id,manufacturerDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_manufacturer_by_id")
    public String deleteManufacturerById(@RequestParam int manufacturer_id){
        try{
            //Deletes the manufacturer with given id and returns whether deleted or not.
            return manufacturerService.deleteManufacturerById(manufacturer_id);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_all_manufacturer")
    public String deleteAllManufacturer(){
        try{
            //Deletes all the manufactures and returns whether deleted or not.
            return manufacturerService.deleteAllManufacturer();
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
