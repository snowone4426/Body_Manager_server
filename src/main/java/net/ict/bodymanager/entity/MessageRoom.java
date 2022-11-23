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
public class MessageRoom extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long room_id;

  @ManyToOne
  @JoinColumn(name="member_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name="trainer_id")
  private Member trainer;
}
