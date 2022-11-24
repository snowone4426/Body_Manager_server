package net.ict.bodymanager.entity;

import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Log4j2
@SpringBootTest
public class entity {
  @Autowired
  private JoinRepository joinRepository;
  @Test
  public void testMember(){
    Member memberEntity = Member.builder()
            .email("test1@email.com")
            .password("1234")
            .name("테스터1")
            .address("서울 신촌")
            .phone("01012345678")
            .gender("male")
            .height(180.3)
            .remark("하이염")
            .birth(LocalDate.of(2022,11,22))
            .profile("image.jpg")
            .type("trainer")
            .build();
    Member result = joinRepository.save(memberEntity);
    log.info(result);
  }


  @Autowired
  private InbodyRepository inbodyRepository;

  @Test
  public void testInbody(){
    Inbody inbody = Inbody.builder()
            .member(Member.builder().member_id(1L).build())
            .weight(211)
            .SMM(21)
            .BFM(2)
            .BMI(22)
            .PBF(22)
            .WHR(22)
            .BMR(20)
            .body_muscle(2)
            .left_hand_muscle(1)
            .right_hand_muscle(32)
            .right_leg_muscle(11)
            .left_leg_muscle(123)
            .body_fat(13)
            .left_hand_fat(3)
            .right_hand_fat(3)
            .left_leg_fat(1)
            .right_leg_fat(3321)
            .build();
    Inbody result = inbodyRepository.save(inbody);
    log.info(result);

  }
  @Autowired
  private FoodRepository foodRepository;
  @Test
  public void testFood(){
    Food food = Food.builder()
            .member(Member.builder().member_id(1L).build())
            .time("저녁")
            .content("test content")
            .food_img("image.jpg")
            .grade(1)
            .build();
    Food result = foodRepository.save(food);
    log.info(result);
  }

  @Autowired
  private AccountRepository accountRepository;
  @Test
  public void testOrder(){
    OrderInfo order = OrderInfo.builder()
            .member(Member.builder().member_id(1L).build())
            .build();
    OrderInfo result = accountRepository.save(order);
    log.info(result);
  }

  @Autowired
  private PriceRepository priceRepository;
  @Test
  public void testPrice(){
    Price price =Price.builder()
            .price_name("3개월")
            .price_info(120000)
            .build();
    Price result = priceRepository.save(price);
    log.info(result);
  }

  @Autowired
  private AttendRepository attendRepository;
  @Test
  public void testAttend(){
    Attend attend = Attend.builder()
            .member(Member.builder().member_id(1l).build())
            .start_date(LocalDateTime.now())
            .end_date(LocalDateTime.now())
            .build();
    Attend result = attendRepository.save(attend);
    log.info(result);
  }

  @Autowired
  private CabinetRepository cabinetRepository;
  @Test
  public void testCabinet(){
    Cabinet cabinet = Cabinet.builder()
            .cab_num(50)
            .start_date(LocalDate.now())
            .end_date(LocalDate.of(2022, 12, 20))
            .cab_pwd(0000)
            .member(Member.builder().member_id(1l).build())
            .build();
    Cabinet result = cabinetRepository.save(cabinet);
    log.info(result);
  }

  @Autowired
  private PTInfoRepository ptInfoRepository;
  @Test
  public void testPTInfo(){
    PTInfo ptInfo = PTInfo.builder()
            .trainer(Member.builder().member_id(3l).build())
            .pt_price(400000)
            .build();
    PTInfo result = ptInfoRepository.save(ptInfo);
    log.info(result);
  }

  @Autowired
  private PTMemberRepository ptMemberRepository;
  @Test
  public void testPTMember(){
    PTMember ptMember = PTMember.builder()
            .start_date(LocalDate.now())
            .member(Member.builder().member_id(1l).build())
            .pt_total_count(20)
            .ptInfo(PTInfo.builder().pt_id(3l).build())
            .build();
    PTMember result = ptMemberRepository.save(ptMember);
    log.info(result);
  }

  @Autowired
  private PTProgramRepository ptProgramRepository;
  @Test
  public void testPTProgram(){
    PTProgram ptProgram = PTProgram.builder()
            .ptMember(PTMember.builder().pt_member_id(2l).build())
            .pt_count(5)
            .title("test program")
            .weight(40)
            .count(15)
            .build();
    PTProgram result = ptProgramRepository.save(ptProgram);
    log.info(result);
  }

  @Autowired
  private PurchaseRepository purchaseRepository;
  @Test
  public void testPurchase(){
    Purchase purchase = Purchase.builder()
            .orderInfo(OrderInfo.builder().order_id(1l).build())
            .price(Price.builder().price_id(1l).build())
            .ptInfo(PTInfo.builder().pt_id(3l).build())
            .period(5)
            .build();
    Purchase result = purchaseRepository.save(purchase);
    log.info(result);
  }

  @Autowired
  private SubscribeRepository subscribeRepository;
  @Test
  public void testSubscribe(){
    Subscribe subscribe = Subscribe.builder()
            .member(Member.builder().member_id(1l).build())
            .membership_start(LocalDate.now())
            .membership_end(LocalDate.of(2022, 12, 8))
            .suit_start(LocalDate.now())
            .suit_end(LocalDate.of(2022, 12, 8))
            .build();
    Subscribe result = subscribeRepository.save(subscribe);
    log.info(result);
  }

  @Autowired
  private MessageRoomRepository messageRoomRepository;
  @Test
  public void testMessageRoom(){
    MessageRoom messageRoom = MessageRoom.builder()
            .member(Member.builder().member_id(1l).build())
            .trainer(Member.builder().member_id(3l).build())
            .build();
    MessageRoom result = messageRoomRepository.save(messageRoom);
    log.info(result);
  }

  @Autowired
  private MessageLogRepository messageLogRepository;
  @Test
  public void testMessageLog(){
    MessageLog messageLog = MessageLog.builder()
            .messageRoom(MessageRoom.builder().room_id(1l).build())
            .member(Member.builder().member_id(1l).build())
            .message_content("hiyo~")
            .build();
    MessageLog result = messageLogRepository.save(messageLog);
    log.info(result);
  }

}
