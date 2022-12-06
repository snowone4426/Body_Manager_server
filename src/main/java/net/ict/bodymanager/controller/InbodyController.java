package net.ict.bodymanager.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.InbodyDTO;
import net.ict.bodymanager.dto.InbodyRequestDTO;
import net.ict.bodymanager.service.InbodyService;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/inbody")
@Log4j2
@RequiredArgsConstructor
public class InbodyController {

    private final InbodyService inbodyService;

    @ApiOperation(value = "Inbody post", notes = "POST 인바디")
    @PostMapping(value = "/in", consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @ApiOperation(value = "Inbody post2", notes = "POST 인바디 개별정보")
    @PostMapping(value = "/in2", consumes = MediaType.APPLICATION_JSON_VALUE)
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




}
