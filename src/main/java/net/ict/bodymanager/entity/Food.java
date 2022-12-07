package net.ict.bodymanager.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Food extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long food_id;

  @ManyToOne
  @JoinColumn(name="member_id")
  private Member member;

  @Column(nullable = false)
  private String time;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String food_img;

  @Column(nullable = false)
  private int grade;    //default 0

}
