package com.codedecode.FoodCatalogueMIcroservice.mapper;


import com.codedecode.FoodCatalogueMIcroservice.dto.FoodItemDTO;
import com.codedecode.FoodCatalogueMIcroservice.entity.FoodItems;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemsMapper {

    FoodItemsMapper INSTANCE = Mappers.getMapper(FoodItemsMapper.class);

    FoodItems mapFoodItemsDTOTOFoodItems(FoodItemDTO foodItemDTO);
    FoodItemDTO mapFoodItemTOFoodItemDTO(FoodItems foodItems);


}
