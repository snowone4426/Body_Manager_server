package net.ict.bodymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodModifyRequestDTO {

    private Long food_id;
    private String time;
    private String content;
    private MultipartFile[] food_img;
//    private List<String> food_img;



}

