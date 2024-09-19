package com.codedecode.FoodCatalogueMIcroservice.dto;

import java.util.*;

import com.codedecode.FoodCatalogueMIcroservice.entity.FoodItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// this page is responsible for showing all the food items list and the restaurant details on the frontend UI
//so when ever you click on a particular restaurant you will see the list of food items and resturant details this is done by this DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePage {
    private List<FoodItems> foodItemsList;
    private Restaurant restaurant;

}
