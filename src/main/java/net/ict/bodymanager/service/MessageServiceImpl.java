package net.ict.bodymanager.service;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.entity.*;
import net.ict.bodymanager.repository.MemberRepository;
import net.ict.bodymanager.repository.MessageRepository;
import net.ict.bodymanager.repository.MessageRoomRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
public class MessageServiceImpl implements MessageService{

    private final MemberRepository memberRepository;
    private final MessageRoomRepository messageRoomRepository;
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MemberRepository memberRepository, MessageRoomRepository messageRoomRepository, MessageRepository messageRepository) {
        this.memberRepository = memberRepository;
        this.messageRoomRepository = messageRoomRepository;
        this.messageRepository = messageRepository;
    }

    @Autowired
    EntityManager entityManager;


    @Override
    public String room_list() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        Optional<Member> id = memberRepository.findById(2l);

        QMessageRoom messageRoom = QMessageRoom.messageRoom;
        QMember member = QMember.member;

        // 로그인 - 회원일 경우 - receiver 가 트레이너
        List<Tuple> roomListTrainer = jpaQueryFactory.select(messageRoom.roomId, messageRoom.trainerId.name, member.profile)
                .from(messageRoom)
//                .join(messageRoom.memberId, member)
                .join(messageRoom.trainerId, member)
                .where(messageRoom.memberId.member_id.eq(id.get().getMember_id()))
                .fetch();

        // 로그인 - 트레이너일 경우 - receiver 가 회원
        List<Tuple> roomListMem = jpaQueryFactory.select(messageRoom.roomId, messageRoom.memberId.name, member.profile)
                .from(messageRoom)
                .join(messageRoom.memberId, member)
//                .join(messageRoom.trainerId, member)
                .where(messageRoom.trainerId.member_id.eq(id.get().getMember_id()))
                .fetch();

        JSONArray array = new JSONArray();
        JSONObject object = null;
        JSONObject data = null;

        if (id.get().getType().equals("member")){
            for (int i = 0; i< roomListTrainer.size(); i++){
                object = new JSONObject();
                object.put("room_id", roomListTrainer.get(i).toArray()[0]);
                object.put("receiver_name", roomListTrainer.get(i).toArray()[1]);
                object.put("receiver_profile", roomListTrainer.get(i).toArray()[2]);
                array.put(object);
            }
            data = new JSONObject();
            data.put("data", array);
            data.put("message", "ok");
        }else {
            for (int i = 0; i< roomListMem.size(); i++){
                object = new JSONObject();
                object.put("room_id", roomListMem.get(i).toArray()[0]);
                object.put("receiver_name", roomListMem.get(i).toArray()[1]);
                object.put("receiver_profile", roomListMem.get(i).toArray()[2]);
                array.put(object);
            }
            data = new JSONObject();
            data.put("data", array);
            data.put("message", "ok");
        }



        return data.toString();
    }


    @Override
    public String people_list() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        Optional<Member> id = memberRepository.findById(3l);

        QPTMember ptMember = QPTMember.pTMember;
        QMessageRoom messageRoom = QMessageRoom.messageRoom;
        QMember member = QMember.member;
        QPTInfo ptInfo = QPTInfo.pTInfo;

        // 로그인 - 회원일 경우 - 상대가 트레이너
        List<Tuple> trainerList = jpaQueryFactory.select(member.member_id, member.name, member.profile)
                .from(member)
                .where(member.type.eq("trainer"))
                .fetch();

        // 로그인 - 트레이너일 경우 - 상대가 회원 - 담당회원만
/*        List<Tuple> memberList = jpaQueryFactory.select(member.member_id, member.name, member.profile)
                .from(member)
                .where(member.type.eq("member"))
                .fetch();*/

/*        List<Tuple> memberList = jpaQueryFactory.select(member.member_id, member.name, member.profile)
                .from(member)
                .join()*/

        List<Tuple> memberList = jpaQueryFactory.select(member.member_id, member.name, member.profile)
                .from(member)
                .where(member.member_id
                                .in(jpaQueryFactory.select(ptMember.member.member_id).from(ptMember).where(ptMember.ptInfo.pt_id
                                        .in(jpaQueryFactory.select(ptInfo.pt_id).from(ptInfo).where(ptInfo.trainer.member_id.eq(id.get().getMember_id())))))).fetch();


        JSONArray array = new JSONArray();
        JSONObject object = null;
        JSONObject data = null;

        if (id.get().getType().equals("member")){
            for (int i = 0; i< trainerList.size(); i++){
                object = new JSONObject();
                object.put("member_id", trainerList.get(i).toArray()[0]);
                object.put("receiver_name", trainerList.get(i).toArray()[1]);
                object.put("receiver_profile", trainerList.get(i).toArray()[2]);
                array.put(object);
            }
            data = new JSONObject();
            data.put("data", array);
            data.put("message", "ok");
        }else {
            for (int i = 0; i< memberList.size(); i++){
                object = new JSONObject();
                object.put("member_id", memberList.get(i).toArray()[0]);
                object.put("receiver_name", memberList.get(i).toArray()[1]);
                object.put("receiver_profile", memberList.get(i).toArray()[2]);
                array.put(object);
            }
            data = new JSONObject();
            data.put("data", array);
            data.put("message", "ok");
        }

        return data.toString();
    }

    @Override
    public void roomCreate(String receiverId) {

        Optional<Member> id = memberRepository.findById(3l);
        Member member = memberRepository.getById(3l);
        Member receiver = memberRepository.getById(Long.valueOf(receiverId));

        // 로그인한 회원이 일반회원일 경우 - 메시지를 받는 사람은 트레이너
        if (id.get().getType().equals("member")){
            MessageRoom messageRoom = MessageRoom.builder()
                    .createdAt(LocalDateTime.now())
                    .memberId(member)   // member 타입으로 넣어줘야함
                    .trainerId(receiver)
                    .build();
            messageRoomRepository.save(messageRoom);

        }else {
            MessageRoom messageRoom = MessageRoom.builder()
                    .createdAt(LocalDateTime.now())
                    .memberId(receiver)   // member 타입으로 넣어줘야함
                    .trainerId(member)
                    .build();
            messageRoomRepository.save(messageRoom);
        }

    }

    @Override
    public String messageList(String room_id, String offset, String limit) {

        QMessage message = QMessage.message;
        QMember member = QMember.member;

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        List<Tuple> chatList = jpaQueryFactory.select(member.name, member.profile, message.content, message.createdAt)
                .from(message)
                .join(message.member, member)
                .where(message.member.member_id.eq(Long.valueOf((room_id)))).offset(Long.parseLong(offset)).limit(Long.parseLong(limit))
                .orderBy(message.createdAt.desc())
                .fetch();

        JSONArray array = new JSONArray();
        JSONObject object = null;

        for (int i = 0 ; i < chatList.size() ; i++){
            object = new JSONObject();
            object.put("member_name", chatList.get(i).toArray()[0]);
            object.put("profile", chatList.get(i).toArray()[1]);
            object.put("contents", chatList.get(i).toArray()[2]);
            object.put("created_at", chatList.get(i).toArray()[3]);
            array.put(object);
        }

        JSONObject data = new JSONObject();
        data.put("data", array);
        data.put("message", "ok");

        return data.toString();
    }

    @Override
    public void chatRegister(Long room_id, String content) {
//        Optional<Member> id = memberRepository.findById(3l);
        Member member = memberRepository.getById(3l);
        MessageRoom messageRoom = messageRoomRepository.getById(room_id);

        Message message = dtoTOEntity(messageRoom, content, member);
        messageRepository.save(message);

    }


}
