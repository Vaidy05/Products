package com.example.Products.DTO;

public class ManufacturerDTO {

    private String manufacturer_name;

    private String manufacturer_details;

    public ManufacturerDTO() {
    }

    public ManufacturerDTO(String manufacturer_name, String manufacturer_details) {
        this.manufacturer_name = manufacturer_name;
        this.manufacturer_details = manufacturer_details;
    }

    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }

    public String getManufacturer_details() {
        return manufacturer_details;
    }

    public void setManufacturer_details(String manufacturer_details) {
        this.manufacturer_details = manufacturer_details;
    }
}
