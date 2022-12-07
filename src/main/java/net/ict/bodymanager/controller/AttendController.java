package net.ict.bodymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.AttendDTO;
import net.ict.bodymanager.service.AttendService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/attend")
@Log4j2
@RequiredArgsConstructor
public class AttendController {
    private final ModelMapper modelMapper;
    private final AttendService attendService;


    @PostMapping(value = "/register")
    public ResponseEntity<Map<String,Object>> register(){
//        Attend attend = new Attend();
//        AttendDTO attendDTO = new AttendDTO();
//        attend = Attend.builder().member(Member.builder().member_id(1l).build()).build();
//        log.info("***********a : " + attend.getMember().getMember_id()); //2
//        attend.setStart_date(LocalDateTime.now());
//        attend.setMember_id(a.getMember().getMember_id()); // 실제로는 로그인 정보에서 아이디 따오기
//        log.info("***********attendDTO : " + attendDTO.getMember_id()); //2
//        JSONObject result = new JSONObject();
        AttendDTO attendDTO = new AttendDTO();
        attendService.register(attendDTO);
        Map<String,Object> result = Map.of("message","ok");
        log.info(result);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value ="/modify")  //, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>>modify(){
        attendService.modify();
        Map<String,String> result = Map.of("message","ok");
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/readDay")
    public String readDay(){
        return attendService.readDay();
    }


    @PostMapping(value = "/readmonth")
    public String readMonth(@RequestBody Map<String, Object > map){
        return attendService.readMonth(String.valueOf(map.get("year")), String.valueOf(map.get("month")));
    }

}


