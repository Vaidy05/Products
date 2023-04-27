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
            return manufacturerService.addManufacturer(manufacturerDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_manufactuer_by_id")
    public Object getManufacturerById(@RequestParam int manufacturer_id){
        try{
            return manufacturerService.getManufacturerById(manufacturer_id);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_all_manufacturer")
    public List<Object> getAllManufacturer (){
        try{
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
            return manufacturerService.updateManufacturer(manufacturer_id,manufacturerDTO);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_manufacturer_by_id")
    public String deleteManufacturerById(@RequestParam int manufacturer_id){
        try{
            return manufacturerService.deleteManufacturerById(manufacturer_id);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_all_manufacturer")
    public String deleteAllManufacturer(){
        try{
            return manufacturerService.deleteAllManufacturer();
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
