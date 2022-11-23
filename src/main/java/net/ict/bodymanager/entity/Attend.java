package net.ict.bodymanager.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attend {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long attend_id;

  @ManyToOne
  @JoinColumn(name="member_id")
  private Member member;

  @Column(nullable = false)
  private LocalDateTime start_date;

  @Column(nullable = false)
  private LocalDateTime end_date;
}
