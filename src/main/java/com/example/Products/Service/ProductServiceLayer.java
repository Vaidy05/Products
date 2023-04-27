package com.example.Products.Service;

import com.example.Products.DTO.*;
import com.example.Products.Model.*;
import com.example.Products.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.ArrayList;

import java.util.List;

@Service
public class ProductServiceLayer {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ManufacturerRepository manufacturerRepository;


    public String addProduct(ProductRequestDTO productRequestDTO){
        try {
            //Create product object from DTO
            Product product = new Product(productRequestDTO.getProduct_name(),
                                          productRequestDTO.getProduct_details(),
                                          productRequestDTO.getQuantity(),
                                          productRequestDTO.getPrice_per_unit(),
                                          productRequestDTO.getProduct_status());

            //Check whether category id exist
            if(!categoryRepository.existsById(productRequestDTO.getCategory_id())){
                throw new Exception("Category does not exist with given id");
            }

            //Check whether manufacturer id exist
            if(!manufacturerRepository.existsById(productRequestDTO.getManufacturer_id())){
                throw new Exception("Manufacturer id does not exist");
            }

            //Get category and manufacturer from the repository and map it to the product.
            Category category = categoryRepository.findById(productRequestDTO.getCategory_id()).get();
            Manufacturer manufacturer = manufacturerRepository.findById(productRequestDTO.getManufacturer_id()).get();
            product.setCategory(category);
            product.setManufacturer(manufacturer);

            //Get the product list mapped to the category, add product to the list and save it in repo.
            List<Product> category_productList = category.getProductList();
            if(category_productList==null){
                category_productList = new ArrayList<>();
            }
            category_productList.add(product);
            categoryRepository.save(category);

            //Get the product list mapped to the manufacturer, add product to the list and save it in repo.
            List<Product> manufacturer_productList = manufacturer.getProductList();
            if(manufacturer_productList==null){
                manufacturer_productList = new ArrayList<>();
            }
            manufacturer_productList.add(product);
            manufacturerRepository.save(manufacturer);

            productRepository.save(product);
            return "Product added successfully";
        }
        catch(Exception e){
            return e.toString();
        }
    }

    public Object getProductById(int productId){
        try {
            if(!productRepository.existsById(productId)){
                throw new Exception("Product does not exist");
            }
            //Get the product from the repository using id.
            Product product = productRepository.findById(productId).get();

            Category category = product.getCategory();
            String categoryName ="";
            if(category!=null){
                categoryName=category.getCategory_name();
            }

            Manufacturer manufacturer = product.getManufacturer();
            String manufacturerName = "";
            if(manufacturer!=null){
                manufacturerName = manufacturer.getManufacturer_name();
            }

            //Convert the product to productResponseDTO and return.
            ProductResponseDTO productResponseDTO = new ProductResponseDTO(product.getProduct_name(),
                                                                        product.getProduct_details(),
                                                                        product.getQuantity(),
                                                                        product.getPrice_per_unit(),
                                                                        product.getProduct_status(),
                                                                        categoryName,
                                                                        manufacturerName);
            return productResponseDTO;
        }
        catch(Exception e){
            return e.toString();
        }
    }

    public List<Object> getAllProducts(){
        try {
            //Get the list of products from the repository.
            List<Product> productList = productRepository.findAll();
            List<Object> productDTOList = new ArrayList<>();

            //Covert the product to productResponseDTO and add it in the DTO list.
            if(productList.size()>0) {
                for (Product product : productList) {
                    productDTOList.add(new ProductResponseDTO(product.getProduct_name(),
                            product.getProduct_details(),
                            product.getQuantity(),
                            product.getPrice_per_unit(),
                            product.getProduct_status(),
                            product.getCategory().getCategory_name(),
                            product.getManufacturer().getManufacturer_name()));
                }
            }
            else{
                throw new Exception("Product list is empty");
            }
            return productDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public String updateProduct(int productId,ProductRequestDTO productRequestDTO){
        try {
            if(!productRepository.existsById(productId)){
                throw new Exception("Product does not exist");
            }
            //Get the product from repository using id.
            Product product = productRepository.findById(productId).get();

            //Check whether category id exist
            if(!categoryRepository.existsById(productRequestDTO.getCategory_id())){
                throw new Exception("Category does not exist with given id");
            }

            //Check whether manufacturer id exist
            if(!manufacturerRepository.existsById(productRequestDTO.getManufacturer_id())){
                throw new Exception("Manufacturer id does not exist");
            }

            //Check whether the category is changed or not. If changed remove it from the previous category and map it to new category.
            if(product.getCategory()!=null && product.getCategory().getCategory_id()!=productRequestDTO.getCategory_id()) {
                Category old_category = categoryRepository.findById(product.getCategory().getCategory_id()).get();
                List<Product> productList = old_category.getProductList();
                productList.remove(product);
                old_category.setProductList(productList);
                categoryRepository.save(old_category);
            }

            Category category = categoryRepository.findById(productRequestDTO.getCategory_id()).get();
            List<Product> categoryProductList = category.getProductList();
            if(categoryProductList==null){
                categoryProductList = new ArrayList<>();
            }
            categoryProductList.add(product);
            category.setProductList(categoryProductList);
            product.setCategory(category);
            categoryRepository.save(category);


            //Check whether the manufacturer is changed or not. If changed remove it from the previous manufacturer and map it to new manufacturer.
            if(product.getManufacturer()!=null && product.getManufacturer().getManufacturer_id()!=productRequestDTO.getManufacturer_id()) {
                Manufacturer old_manufacturer = manufacturerRepository.findById(product.getManufacturer().getManufacturer_id()).get();
                List<Product> productList = old_manufacturer.getProductList();
                productList.remove(product);
                old_manufacturer.setProductList(productList);
                manufacturerRepository.save(old_manufacturer);
            }

            Manufacturer manufacturer = manufacturerRepository.findById(productRequestDTO.getManufacturer_id()).get();
            List<Product> manufacturerProductList = manufacturer.getProductList();
            if(manufacturerProductList==null){
                manufacturerProductList = new ArrayList<>();
            }
            manufacturerProductList.add(product);
            manufacturer.setProductList(manufacturerProductList);
            product.setManufacturer(manufacturer);
            manufacturerRepository.save(manufacturer);

            //Update the product properties and save.
            product.setProduct_name(productRequestDTO.getProduct_name());
            product.setProduct_details(productRequestDTO.getProduct_details());
            product.setQuantity(productRequestDTO.getQuantity());
            product.setPrice_per_unit(productRequestDTO.getPrice_per_unit());
            product.setProduct_status(productRequestDTO.getProduct_status());

            productRepository.save(product);
            return "Updated product details successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public List<Object> getProductByManufacturer(int manufacturer_id){
        try{
            if(!manufacturerRepository.existsById(manufacturer_id)){
                throw new Exception("Manufacturer id does not exist");
            }
            //Get the manufacturer from repo using id.
            Manufacturer manufacturer = manufacturerRepository.findById(manufacturer_id).get();

            //Get the list of product associated to the manufacturer.
            List<Product> productList = manufacturer.getProductList();

            //Convert the product list to productResponseDTO and return it.
            List<Object> productDTOList = new ArrayList<>();
            if(productList.size()>0) {
                for (Product product : productList) {
                    productDTOList.add(new ProductResponseDTO(product.getProduct_name(),
                            product.getProduct_details(),
                            product.getQuantity(),
                            product.getPrice_per_unit(),
                            product.getProduct_status(),
                            product.getCategory().getCategory_name(),
                            product.getManufacturer().getManufacturer_name()));
                }
            }
            else{
                throw new Exception("No product is associated to the given Manufacturer id");
            }
            return productDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public List<Object> getProductByCategory(int category_id){
        try{
            if(!categoryRepository.existsById(category_id)){
                throw new Exception("Category does not exist with given id");
            }
            //Get the category from repo using id.
            Category category = categoryRepository.findById(category_id).get();

            //Get the list of product associated to the category.
            List<Product> productList = category.getProductList();

            //Convert the product list to productResponseDTO and return it.
            List<Object> productDTOList = new ArrayList<>();
            if(productList.size()>0) {
                for (Product product : productList) {
                    productDTOList.add(new ProductResponseDTO(product.getProduct_name(),
                            product.getProduct_details(),
                            product.getQuantity(),
                            product.getPrice_per_unit(),
                            product.getProduct_status(),
                            product.getCategory().getCategory_name(),
                            product.getManufacturer().getManufacturer_name()));
                }
            }
            else {
                throw new Exception("No product is associated to category");
            }
            return productDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }
    public String deleteProductById(int productId){
        try{
            if(!productRepository.existsById(productId)){
                throw new Exception("Product does not exist");
            }
            //Get the product from the repository using id.
            Product product = productRepository.findById(productId).get();

            //Get the corresponding category and remove the product from its list.
            if(product.getCategory()!=null) {
                Category category = product.getCategory();
                List<Product> category_productList = category.getProductList();
                category_productList.remove(product);
                category.setProductList(category_productList);
                categoryRepository.save(category);
            }

            //Get the corresponding manufacturer and remove the product from its list.
            if(product.getManufacturer()!=null) {
                Manufacturer manufacturer = product.getManufacturer();
                List<Product> manufacturer_productList = manufacturer.getProductList();
                manufacturer_productList.remove(product);
                manufacturer.setProductList(manufacturer_productList);
                manufacturerRepository.save(manufacturer);
            }

            productRepository.delete(product);
            return "Product deleted successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteAllProducts(){
        try{
            if(productRepository.findAll().size()==0){
                throw new Exception("Product table is already empty");
            }
            //Get all categories and set the product list to null.
            List<Category> categories = categoryRepository.findAll();
            if(categories!=null) {
                for (Category category : categories) {
                    category.setProductList(null);
                }
                categoryRepository.saveAll(categories);
            }

            //Get all manufacturers and set the product list to null.
            List<Manufacturer> manufacturers = manufacturerRepository.findAll();
            if(manufacturers!=null) {
                for (Manufacturer manufacturer : manufacturers) {
                    manufacturer.setProductList(null);
                }
                manufacturerRepository.saveAll(manufacturers);
            }

            //Save the categories and manufacturers and delete all products.
            productRepository.deleteAll();

            return "All products deleted successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

}
