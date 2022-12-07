package net.ict.bodymanager.service;

import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.entity.Message;
import net.ict.bodymanager.entity.MessageRoom;

import java.time.LocalDateTime;

public interface MessageService {

    String room_list();

    String people_list();

    void roomCreate(String receiverId);

    String messageList(String room_id, String offset, String limit);

    void chatRegister(Long room_id, String content);

    // 로그인한 회원이 일반회원일 경우
    default Message dtoTOEntity(MessageRoom messageRoom, String content, Member member){
        Message message = Message.builder()
                .messageRoom(messageRoom)
                .createdAt(LocalDateTime.now())
                .content(content)
                .member(member)
                .build();
        return message;
    }

/*
    // 로그인한 회원이 일반회원일 경우
    default Message dtoTOEntityTrainer(MessageRoom messageRoom, String content, Member member){
        Message message = Message.builder()
                .messageRoom(messageRoom)
                .createdAt(LocalDateTime.now())
                .content(content)
                .member(member)
                .build();
        return message;
    }
*/

}
