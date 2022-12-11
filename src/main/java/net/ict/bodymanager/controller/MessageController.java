package net.ict.bodymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.repository.MessageRepository;
import net.ict.bodymanager.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/message")
@Log4j2
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    private final SimpMessagingTemplate template;
    @MessageMapping("/chat/mes/{roomid}")
//    @SendTo("/chat/send/{roomid}")
    public void chat(@DestinationVariable("roomid") Long room_id, @RequestBody Map<String , String> map){

        messageService.chatRegister(room_id, map.get("content"), map.get("type"));
        template.convertAndSend("/sub/chat/send/" + room_id, map.get("content"));
    }


    @GetMapping(value = "/roomlist"  , produces = "application/text;charset=UTF-8")
    public String roomList(){
        return messageService.room_list();
    }

    @GetMapping(value = "/memlist"  , produces = "application/text;charset=UTF-8")
    public String memberList(){
        return messageService.people_list();
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String >> roomCreate(@RequestBody Map<String , String> map){

        log.info(map.get("receiver_id"));
        messageService.roomCreate(map.get("receiver_id"));
        Map<String , String> resultMap = Map.of("message", "ok");
        return ResponseEntity.ok(resultMap);
    }

    @PostMapping(value = "/content", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/text;charset=UTF-8")
    public String chatList(@RequestBody Map<String , String> map){

        log.info(map.get("room_id"));
        log.info(map.get("offset"));
        log.info(map.get("limit"));
        return messageService.messageList(map.get("room_id"), map.get("offset"), map.get("limit"));
    }



}
