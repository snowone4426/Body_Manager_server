package net.ict.bodymanager.service;


import net.ict.bodymanager.dto.FoodModifyRequestDTO;
import net.ict.bodymanager.dto.FoodRequestDTO;
import net.ict.bodymanager.entity.Food;
import net.ict.bodymanager.entity.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface FoodService {
    void register(FoodRequestDTO foodRequestDTO);

    String readOne(String date);

    void remove(Long food_id);

    void modify(FoodModifyRequestDTO foodDTO);

    default Food dtoToEntity(FoodRequestDTO foodRequestDTO, Member member){
        List<String> img =  new ArrayList<>();
        img.add(foodRequestDTO.getFood_img());

        Food food = Food.builder()
                .member(member)
                .time(foodRequestDTO.getTime())
                .content(foodRequestDTO.getContent())
                .created_at(LocalDateTime.now())
                .build();

        if(foodRequestDTO.getFood_img() !=null){
            img.forEach(fileName -> {
                String[] arr = fileName.split("_");
                food.addImage(arr[0], arr[1]);
        });
    }
        return food;
}

//    default FoodDTO entityToDTO(Food food, Member member){
//        FoodDTO foodDTO = FoodDTO.builder()
//                .member_id(food.getMember().getMember_id())
//                .time(food.getTime())
//                .content(food.getContent())
//                .created_at(LocalDateTime.now())
//                .build();
//
//        List<String> fileName=
//                food.getFood_img().stream().sorted().map(file ->
//                        file.getFile_id()+"_"+file.getFileName()).collect(Collectors.toList());
//        foodDTO.setFood_img(fileName);
//        return foodDTO;
//    }


}
