package net.ict.bodymanager.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderInfo{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long order_id;

  @ManyToOne
  @JoinColumn(name="member_id")
  private Member member;

  @Column
  private LocalDateTime created_at;

}
