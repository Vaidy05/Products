package com.example.Products.DTO;



public class MobileDTO {

    private String brand;

    private int quantity;

    private String date;

    private int price_per_unit;

    private String model;

    private String color;

    private String ram;

    private String internalStorage;

    private String processor;

    private String camera_spec;

    private String battery;

    public MobileDTO() {
    }

    public MobileDTO(String brand, int quantity, String date, int price_per_unit, String model, String color, String ram, String internalStorage, String processor, String camera_spec, String battery) {
        this.brand = brand;
        this.quantity = quantity;
        this.date = date;
        this.price_per_unit = price_per_unit;
        this.model = model;
        this.color = color;
        this.ram = ram;
        this.internalStorage = internalStorage;
        this.processor = processor;
        this.camera_spec = camera_spec;
        this.battery = battery;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(int price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRAM() {
        return ram;
    }

    public void setRAM(String ram) {
        this.ram = ram;
    }

    public String getInternalStorage() {
        return internalStorage;
    }

    public void setInternalStorage(String internalStorage) {
        this.internalStorage = internalStorage;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getCamera_spec() {
        return camera_spec;
    }

    public void setCamera_spec(String camera_spec) {
        this.camera_spec = camera_spec;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }
}
