package com.example.Products.Controller;

import com.example.Products.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add_category")
    public String addCategory(@RequestParam String category_name){
        try{
            return categoryService.addCategory(category_name);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_category_by_id")
    public String getCategoryById(@RequestParam int category_id){
        try{
            return categoryService.getCategoryById(category_id);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_all_categories")
    public List<String> getAllCategory(){
        try{
            return categoryService.getAllCategory();
        }
        catch (Exception e){
            List<String> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    @PutMapping("/update_category")
    public String updateCategory(@RequestParam int category_id, @RequestParam String category_name){
        try{
            return categoryService.updateCategory(category_id,category_name);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_category_by_id")
    public String deleteCategoryById(@RequestParam int category_id){
        try{
            return categoryService.deleteCategoryById(category_id);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_all_category")
    public String deleteAllCategory(){
        try{
            return categoryService.deleteAllCategory();
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
