package com.codedecode.FoodCatalogueMIcroservice.controller;

import com.codedecode.FoodCatalogueMIcroservice.dto.FoodCataloguePage;
import com.codedecode.FoodCatalogueMIcroservice.dto.FoodItemDTO;
import com.codedecode.FoodCatalogueMIcroservice.dto.Restaurant;
import com.codedecode.FoodCatalogueMIcroservice.entity.FoodItems;
import com.codedecode.FoodCatalogueMIcroservice.service.FoodItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/foodCatalogue")
public class FoodItemController {
    @Autowired
    FoodItemService foodItemService;

    @PostMapping("/addfoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO foodItemDTO1 = foodItemService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(foodItemDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/fetchFoodItems")
    public ResponseEntity<List<FoodItemDTO>> fetchFoodItems() {
        List<FoodItemDTO> allfoodItems = foodItemService.fetchFoodItems();
        return new ResponseEntity<>(allfoodItems, HttpStatus.OK);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantID}")
    public ResponseEntity<FoodCataloguePage> fetchRestauDetailsWithFoodMenu(@PathVariable Integer restaurantID) {
        FoodCataloguePage foodCataloguePage = foodItemService.fetchFoodCatalogueDetails(restaurantID);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }
}
