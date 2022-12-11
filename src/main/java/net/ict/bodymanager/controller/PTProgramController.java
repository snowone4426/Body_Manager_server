package net.ict.bodymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.service.PTProgramService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ptprogram")
@Log4j2
@RequiredArgsConstructor
public class PTProgramController {

    private final PTProgramService ptProgramService;

    @PostMapping(value = "/list", produces = "application/text;charset=UTF-8")
    public String readList(@RequestBody Map<String, Object> map) {
        return ptProgramService.readlist(String.valueOf(map.get("date")));
    }

    @PostMapping(value = "/listmonth")
    public String readMonth(@RequestBody Map<String, Object> map) {
        return ptProgramService.readmonth(String.valueOf(map.get("year")), String.valueOf(map.get("month")));
    }
}
