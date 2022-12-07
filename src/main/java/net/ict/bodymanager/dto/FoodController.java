package net.ict.bodymanager.dto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.FoodModifyRequestDTO;
import net.ict.bodymanager.service.FoodService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/food")
@Log4j2
@RequiredArgsConstructor
public class FoodController {

    @Value("${net.ict.upload.path}")// import 시에 springframework으로 시작하는 Value
    private String uploadPath;

    private final FoodService foodService;

    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> registerPost(@RequestBody @Valid FoodRequestDTO foodRequestDTO){

        foodService.register(foodRequestDTO);
        Map<String, Object> result = Map.of("message","ok");
        log.info(result);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/list", produces = "application/text;charset=UTF-8")
    public String foodlist(@RequestBody Map<String,Object> map){
        return foodService.readOne(String.valueOf(map.get("date")));
    }

    @PostMapping(value = "/modify", produces = "application/text;charset=UTF-8")
    public ResponseEntity<Map<String,Object>> modify(@RequestBody @Valid FoodModifyRequestDTO foodDTO){

        foodService.modify(foodDTO);
        Map<String, Object> result = Map.of("message","ok");
        return ResponseEntity.ok(result);

    }


//    @DeleteMapping("/delete")

}
