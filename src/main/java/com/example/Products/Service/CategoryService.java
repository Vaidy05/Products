package com.example.Products.Service;

import com.example.Products.Model.Category;
import com.example.Products.Model.Manufacturer;
import com.example.Products.Model.Product;
import com.example.Products.Repository.CategoryRepository;
import com.example.Products.Repository.ManufacturerRepository;
import com.example.Products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Autowired
    ProductRepository productRepository;

    public String addCategory(String categoryName){
        try{
            //Check whether the given category name already exist. If exist throw exception.
            if(categoryRepository.findByCategoryName(categoryName)!=null){
                throw new Exception("The category already exist!");
            }

            //If not exist create new category and save it to repo.
            Category category = new Category(categoryName);
            categoryRepository.save(category);
            return "Category added successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String getCategoryById(int category_id){
        try{
            //Check whether the id exist in repo.
            if(!categoryRepository.existsById(category_id)){
                throw new Exception("Category does not exist with given id");
            }
            //Get the category from the repo using id and return the list of category names.
            Category category = categoryRepository.findById(category_id).get();
            return category.getCategory_name();
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public List<String> getAllCategory(){
        try{
            //Get all the categories from repo and return the list of category names.
            List<Category> categories = categoryRepository.findAll();
            List<String> category_names = new ArrayList<>();
            //If no category found throw exception
            if(categories.size()>0) {
                for (Category category : categories) {
                    category_names.add(category.getCategory_name());
                }
            }
            else{
                throw new Exception("Category list is empty");
            }
            return category_names;
        }
        catch (Exception e){
            List<String> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public String updateCategory(int category_id,String category_name){
        try{
            //Check whether the id exist in repo.
            if(!categoryRepository.existsById(category_id)){
                throw new Exception("Category does not exist with given id");
            }
            //Check whether the given category name already exist. If exist throw exception.
            if(categoryRepository.findByCategoryName(category_name)!=null){
                throw new Exception("The category already exist!");
            }

            //If not exist get category from repo and update it.
            Category category = categoryRepository.findById(category_id).get();
            category.setCategory_name(category_name);
            categoryRepository.save(category);
            return "Category updated successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteCategoryById(int category_id){
        try{
            //Check whether the id exist in repo.
            if(!categoryRepository.existsById(category_id)){
                throw new Exception("Category does not exist with given id");
            }
            //Get category from repo using id.
            Category category = categoryRepository.findById(category_id).get();

            //Get the product list associated with the category and set the category of each product to null.
            List<Product> productList = category.getProductList();
            if(productList.size()>0) {
                for (Product product : productList) {
                    product.setCategory(null);
                }
                productRepository.saveAll(productList);
            }
            //set the productList associated to category to null.
            category.setProductList(null);
            categoryRepository.delete(category);

            return "Category deleted successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteAllCategory(){
        try{
            //Get all the products and set category to null.
            List<Product> productList = productRepository.findAll();
            if(productList.size()>0) {
                for (Product product : productList) {
                    product.setCategory(null);
                }
                productRepository.saveAll(productList);
            }

            //Get all the categories and set productList to null.
            List<Category> categoryList = categoryRepository.findAll();
            if(categoryList.size()>0) {
                for (Category category : categoryList) {
                    category.setProductList(null);
                }
                categoryRepository.deleteAll();
            }
            //If no category found throw exception
            else {
                throw new Exception("Category table is already empty");
            }

             return "Deleted all categories";
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
