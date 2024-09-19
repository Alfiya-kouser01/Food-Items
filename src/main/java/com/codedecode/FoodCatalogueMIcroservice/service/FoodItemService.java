package com.codedecode.FoodCatalogueMIcroservice.service;
import com.codedecode.FoodCatalogueMIcroservice.dto.FoodCataloguePage;
import com.codedecode.FoodCatalogueMIcroservice.dto.FoodItemDTO;
import com.codedecode.FoodCatalogueMIcroservice.dto.Restaurant;
import com.codedecode.FoodCatalogueMIcroservice.entity.FoodItems;
import com.codedecode.FoodCatalogueMIcroservice.mapper.FoodItemsMapper;
import com.codedecode.FoodCatalogueMIcroservice.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.env.Environment;

@Service
@Slf4j
public class FoodItemService {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    public  List<FoodItemDTO> fetchFoodItems() {
        List<FoodItems> foodItemsList =  foodRepository.findAll();
        List<FoodItemDTO>  foodItemDTOList = foodItemsList.stream().map(foodItems -> FoodItemsMapper.INSTANCE.mapFoodItemTOFoodItemDTO(foodItems)).collect(Collectors.toList());
        return foodItemDTOList;
    }

    public FoodCataloguePage fetchFoodCatalogueDetails(Integer restaurantID) {
        //food item list
        //restaurantdetails
        List<FoodItems> foodItemsList = fetchFoodItemList(restaurantID);
        Restaurant restaurant = fetchRestauDetailsFromRestaurantMS(restaurantID);
        // passing both such that a combined respose is created and sent to the front end
        return createFoodCataloguePage(foodItemsList, restaurant);
        //create this three private methods in the service layer
    }

    // creating combined object for both of them
    private FoodCataloguePage createFoodCataloguePage(List<FoodItems> foodItemsList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemsList);
        foodCataloguePage.setRestaurant(restaurant);
        log.info("final catalogue for restaurantID: {} is {} ",restaurant.getId(), foodCataloguePage);
        return foodCataloguePage;
    }

    private Restaurant fetchRestauDetailsFromRestaurantMS(Integer restaurantID) {
        String serviceName = environment.getProperty("spring.application.name"); // Get the service name
        log.info("Servie Name: {}", serviceName);
        String url = "http://localhost:9091/restaurant/fetchById/" + restaurantID; // Directly call the service
        log.info("Making API call to URL: {}", url);

        try {
            Restaurant restaurantDetails = restTemplate.getForObject(url, Restaurant.class);
            log.info("API call successful, restaurant details: {}", restaurantDetails);
            return restaurantDetails;
        } catch (Exception e) {
            log.error("API call failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }


    private List<FoodItems> fetchFoodItemList(Integer restaurantID) {
        return foodRepository.findByRestaurantId(restaurantID);
    }


    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItems savedFood = foodRepository.save(FoodItemsMapper.INSTANCE.mapFoodItemsDTOTOFoodItems(foodItemDTO));
        return FoodItemsMapper.INSTANCE.mapFoodItemTOFoodItemDTO(savedFood);
    }
}
