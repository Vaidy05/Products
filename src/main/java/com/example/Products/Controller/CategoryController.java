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
            //Adds the category to repo and returns whether added or not.
            return categoryService.addCategory(category_name);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_category_by_id")
    public String getCategoryById(@RequestParam int category_id){
        try{
            //Returns the category with given id.
            return categoryService.getCategoryById(category_id);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/get_all_categories")
    public List<String> getAllCategory(){
        try{
            //Returns all the categories in repo.
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
            //Updates the category and returns whether updated or not.
            return categoryService.updateCategory(category_id,category_name);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_category_by_id")
    public String deleteCategoryById(@RequestParam int category_id){
        try{
            //Deletes the category with given id and returns whether deleted or not.
            return categoryService.deleteCategoryById(category_id);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_all_category")
    public String deleteAllCategory(){
        try{
            //Deletes all the categories and returns whether deleted or not.
            return categoryService.deleteAllCategory();
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
