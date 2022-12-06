package net.ict.bodymanager.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inbody{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long inbody_id;

  @ManyToOne
  @JoinColumn(name="member_id")
  private Member member;

  @Column(nullable = false)
  private double weight;
  @Column(nullable = false)
  private double SMM;
  @Column(nullable = false)
  private double BFM;
  @Column(nullable = false)
  private double BMI;
  @Column(nullable = false)
  private double PBF;
  @Column(nullable = false)
  private double WHR;
  @Column(nullable = false)
  private double BMR;
  @Column(nullable = false)
  private double body_muscle;
  @Column(nullable = false)
  private double left_hand_muscle;
  @Column(nullable = false)
  private double right_hand_muscle;
  @Column(nullable = false)
  private double left_leg_muscle;
  @Column(nullable = false)
  private double right_leg_muscle;
  @Column(nullable = false)
  private double body_fat;
  @Column(nullable = false)
  private double left_hand_fat;
  @Column(nullable = false)
  private double right_hand_fat;
  @Column(nullable = false)
  private double left_leg_fat;
  @Column(nullable = false)
  private double right_leg_fat;

  @CreatedDate
  @Column(updatable = false)
  private LocalDate created_at;


}