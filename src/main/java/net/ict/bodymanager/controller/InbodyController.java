package net.ict.bodymanager.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.InbodyDTO;
import net.ict.bodymanager.service.InbodyService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
