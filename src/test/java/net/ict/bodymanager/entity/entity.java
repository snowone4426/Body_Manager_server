package net.ict.bodymanager.entity;

import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.repository.AccountRepository;
import net.ict.bodymanager.repository.FoodRepository;
import net.ict.bodymanager.repository.InbodyRepository;
import net.ict.bodymanager.repository.JoinRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Log4j2
@SpringBootTest
public class entity {
  @Autowired
  private JoinRepository joinRepository;
  @Test
  public void testMember(){
    Member memberEntity = Member.builder()
            .email("test@email.com")
            .password("1234")
            .name("테스터")
            .address("서울 신촌")
            .phone("01012345678")
            .gender("male")
            .height(180.3)
            .remark("하이염")
            .birth(LocalDate.of(2022,11,22))
            .profile("image.jpg")
            .type("common")
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


}
