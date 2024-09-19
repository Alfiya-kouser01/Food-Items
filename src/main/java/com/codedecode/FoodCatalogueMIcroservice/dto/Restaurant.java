package com.codedecode.FoodCatalogueMIcroservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// this is a pojo class and not the entity the entity is present in the restaurant listing service
//we use this pojo class to show the detalis of the resturant with the food itmes this is done by calling the restaurant listing serivce
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
