package net.ict.bodymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.controller.advice.ApiControllerAdvice;
import net.ict.bodymanager.dto.InbodyDTO;
import net.ict.bodymanager.dto.InbodyRequestDTO;
import net.ict.bodymanager.service.InbodyService;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/inbody")
@Log4j2
@RequiredArgsConstructor
public class InbodyController {

    private final ApiControllerAdvice apiControllerAdvice;
    private final InbodyService inbodyService;
    private final ModelMapper modelMapper;

    @GetMapping("/in")
    public ResponseEntity<Map<String, String >> select(){
        Map<String , String> resultMap = Map.of("message", "ok");
        return ResponseEntity.ok(resultMap);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String >> register(@RequestBody InbodyDTO inbodyDTO) {

        LocalDate today = LocalDate.now();

        if(inbodyService.check().contains(today)){
            inbodyService.modify(inbodyDTO);
            Map<String , String> resultMap = Map.of("message", "ok");
            return ResponseEntity.ok(resultMap);
        }else {
            inbodyService.register(inbodyDTO);
            Map<String , String> resultMap = Map.of("message", "ok");
            return ResponseEntity.ok(resultMap);
        }

    }

    @PostMapping(value = "/partmass", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String muscle(@RequestBody @Valid InbodyRequestDTO inbodyRequestDTO){

        JSONObject object = new JSONObject();

        if(inbodyRequestDTO.getType().equals("muscle")){
            return inbodyService.musclePart(inbodyRequestDTO);
        }else if(inbodyRequestDTO.getType().equals("fat")){
            return inbodyService.fatPart(inbodyRequestDTO);
        }else {
            return object.put("message", "insufficient request data").toString();
        }
    }

    @Valid
    @GetMapping("/analysis")
    public String info(){
        return inbodyService.inbodyInfo();
    }

    @Valid
    @GetMapping("/physical")
    public String change(){
        return inbodyService.bodyChangeFlow();
    }

    @GetMapping(value = "/data")
    public String todayList(){
        return inbodyService.inbodyList();
    }

}
