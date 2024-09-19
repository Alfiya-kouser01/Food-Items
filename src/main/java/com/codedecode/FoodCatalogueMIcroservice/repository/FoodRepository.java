package com.codedecode.FoodCatalogueMIcroservice.repository;

import com.codedecode.FoodCatalogueMIcroservice.entity.FoodItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface FoodRepository extends JpaRepository<FoodItems, Integer> {

    List<FoodItems> findByRestaurantId(Integer restaurantID);
}
