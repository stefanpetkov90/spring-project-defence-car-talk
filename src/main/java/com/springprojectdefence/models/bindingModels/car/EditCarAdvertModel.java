package com.springprojectdefence.models.bindingModels.car;

import javax.validation.constraints.*;

public class EditCarAdvertModel {
    private Long id;
    @NotNull
    @Size(min = 3, max = 15, message = "Sorry, the make is invalid!(Make must be between 3 and 15 characters)")
    private String name;
    @NotNull
    @Size(min = 2, max = 15, message = "Sorry, the model is invalid!(Model must be between 3 and 15 characters)")
    private String model;
    @Max(value = Integer.MAX_VALUE, message = "Invalid price (Maximum price is 100000000 leva)")
    @Min(value = 1, message = "Invalid price (Minimum price is 1 leva)")
    private Integer price;
    @NotNull
    @Pattern(regexp = "(\\b(https?|ftp|file)://)?[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]", message = "Please insert a valid image URL!")
    private String imgUrl;
    @Min(value = 1, message = "Invalid distance (Minimum distance is 1 km)")
    private Integer travelledDistance;
    private String engineType;
    @NotNull
    @Size(min = 3, max = 15, message = "Sorry, the color is invalid!(Color must be between 3 and 15 characters)")
    private String color;
    @NotNull
    @Pattern(regexp = "^[0-9\\-\\+]{9,15}$", message = "Please insert a valid phone number format!")
    private String sellerPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Integer travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }
}
