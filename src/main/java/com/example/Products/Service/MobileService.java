package com.example.Products.Service;

import com.example.Products.DTO.MobileDTO;
import com.example.Products.Model.Fashion;
import com.example.Products.Model.Mobile;
import com.example.Products.Model.Product;
import com.example.Products.Repository.MobileRepository;
import com.example.Products.Repository.ProductRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MobileService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MobileRepository mobileRepository;

    public String addMobile(int productId, MobileDTO mobileDTO){
        try {
            //Finding product from product repository
            Product product = productRepository.findById(productId).get();
            //Converting string to date to store in mobile object
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(mobileDTO.getDate());
            Mobile mobile = new Mobile(mobileDTO.getBrand(), mobileDTO.getQuantity(), date, mobileDTO.getPrice_per_unit(), mobileDTO.getModel(), mobileDTO.getColor(), mobileDTO.getRAM(), mobileDTO.getInternalStorage(), mobileDTO.getProcessor(), mobileDTO.getCamera_spec(), mobileDTO.getBattery());
            List<Mobile> mobileList = product.getMobileList();
            //Check whether mobileList is empty or contains some mobile object. If contains add the new object to list
            if (mobileList.equals(null)) {
                mobileList = new ArrayList<>();
            }
            mobileList.add(mobile);
            //Update the mobileList and no of products for the product object and map the mobile to product.
            product.setMobileList(mobileList);
            product.setNo_of_products(mobileList.size());
            mobile.setProduct(product);
            //Save the product and correspondingly mobile object gets saved due to Cascading.
            productRepository.save(product);
            return "Mobile added successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public List<Object> getAllMobiles(){
        try {
            //Get the list of mobile entries from the repository.
            List<Mobile> mobileList = mobileRepository.findAll();
            List<Object> mobileDTOList = new ArrayList<>();
            //Convert the mobile object to mobileDTO and add it in the DTO list.
            for (Mobile mobile : mobileList) {
                mobileDTOList.add(new MobileDTO(mobile.getBrand(), mobile.getQuantity(), mobile.getDate().toString(), mobile.getPrice_per_unit(), mobile.getModel(), mobile.getColor(), mobile.getRAM(), mobile.getInternalStorage(), mobile.getProcessor(), mobile.getCamera_spec(), mobile.getBattery()));
            }
            return mobileDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public Object getMobileById(int mobileId){
        try {
            //Get the object from repository using id.
            Mobile mobile = mobileRepository.findById(mobileId).get();
            //Convert the mobile object to mobileDTO
            MobileDTO mobileDTO = new MobileDTO(mobile.getBrand(), mobile.getQuantity(), mobile.getDate().toString(), mobile.getPrice_per_unit(), mobile.getModel(), mobile.getColor(), mobile.getRAM(), mobile.getInternalStorage(), mobile.getProcessor(), mobile.getCamera_spec(), mobile.getBattery());
            return mobileDTO;
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public List<Object> getMobileByProduct(int productId){
        try{
            //Get the product from repository using product Id.
            Product product = productRepository.findById(productId).get();
            List<Mobile> mobileList = product.getMobileList();
            //Check whether the mobileList is empty or not.If empty throw exception.
            if(mobileList.size()==0){
                throw new Exception("Please check the product Id");
            }
            //If the mobileList is not empty create new Object List. Convert mobile object to mobileDTO and add in the list
            List<Object> mobileDTOList = new ArrayList<>();
            for (Mobile mobile : mobileList) {
                mobileDTOList.add(new MobileDTO(mobile.getBrand(), mobile.getQuantity(), mobile.getDate().toString(), mobile.getPrice_per_unit(), mobile.getModel(), mobile.getColor(), mobile.getRAM(), mobile.getInternalStorage(), mobile.getProcessor(), mobile.getCamera_spec(), mobile.getBattery()));
            }
            return mobileDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public List<Object> getMobileByBrand(String brand){
        try{
            //Get the list of mobile entries from the repository.
            List<Mobile> mobileList = mobileRepository.findAll();
            //Create new Object List. If the brand equals the given brand name add in list.
            List<Object> mobileDTOList = new ArrayList<>();
            for(Mobile mobile : mobileList){
                if(mobile.getBrand().equals(brand)){
                    mobileDTOList.add(new MobileDTO(mobile.getBrand(), mobile.getQuantity(), mobile.getDate().toString(), mobile.getPrice_per_unit(), mobile.getModel(), mobile.getColor(), mobile.getRAM(), mobile.getInternalStorage(), mobile.getProcessor(), mobile.getCamera_spec(), mobile.getBattery()));
                }
            }
            return mobileDTOList;
        }
        catch (Exception e){
            List<Object> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    public String updateMobile(int mobileId, MobileDTO mobileDTO){
        try {
            //Get the mobile object from the repository using mobile id.
            Mobile mobile = mobileRepository.findById(mobileId).get();
            //Set the properties of mobileDTO to mobile object and save it in repository.
            mobile.setBrand(mobileDTO.getBrand());
            mobile.setQuantity(mobileDTO.getQuantity());
            mobile.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(mobileDTO.getDate()));
            mobile.setPrice_per_unit(mobileDTO.getPrice_per_unit());
            mobile.setModel(mobileDTO.getModel());
            mobile.setColor(mobileDTO.getColor());
            mobile.setRAM(mobileDTO.getRAM());
            mobile.setInternalStorage(mobileDTO.getInternalStorage());
            mobile.setProcessor(mobileDTO.getProcessor());
            mobile.setCamera_spec(mobileDTO.getCamera_spec());
            mobile.setBattery(mobileDTO.getBattery());
            mobileRepository.save(mobile);
            return "Updated mobile details successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteMobile(int mobileId){
        try{
            //Get the mobile object from the repository using mobile id.
            Mobile mobile = mobileRepository.findById(mobileId).get();
            //Get the product related to given mobile object.
            Product product = mobile.getProduct();
            List<Mobile> mobileList = product.getMobileList();
            //Remove the mobile from the list and set the list and no of products of the product object and save.
            mobileList.remove(mobile);
            product.setMobileList(mobileList);
            product.setNo_of_products(mobileList.size());
            //Delete the mobile object.
            mobileRepository.delete(mobile);
            productRepository.save(product);
            return "Mobile deleted successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteMobileByProduct(int productId){
        try{
            //Get the product from repository using product Id.
            Product product = productRepository.findById(productId).get();
            List<Mobile> mobileList = product.getMobileList();
            //Check whether the list is empty or not. If empty throw exception.
            if(mobileList.size()==0){
                throw new Exception("Please enter the correct product Id of mobile");
            }
            //If not empty set the product object's mobileList to null and no of products to 0.
            product.setMobileList(null);
            product.setNo_of_products(0);
            //Save the product and delete all the mobile objects corresponding to object.
            productRepository.save(product);
            mobileRepository.deleteAll(mobileList);
            return "Deleted all mobile entries of this product successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String deleteAllMobile(){
        try{
            //Get the list of mobile entries from the repository.
            List<Mobile> mobileList = mobileRepository.findAll();
            //For each mobile object, the corresponding product object's list is set to null and no of products to 0.
            List<Product> productList = new ArrayList<>();
            for(Mobile mobile : mobileList){
                Product product = mobile.getProduct();
                //If the product not exist in the list, set mobileList to null and no of products to 0 and add to list.
                if(!productList.contains(product)){
                    product.setMobileList(null);
                    product.setNo_of_products(0);
                    productList.add(product);
                }
            }
            //Save all the products stored in list and delete all the mobile entries.
            productRepository.saveAll(productList);
            mobileRepository.deleteAll();
            return "Deleted all mobile entries successfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
