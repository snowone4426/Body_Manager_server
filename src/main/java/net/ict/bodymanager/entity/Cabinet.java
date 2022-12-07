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
public class Cabinet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cab_id;

  @Column(nullable = false)
  private int cab_num;

  @Column(nullable = false)
  private LocalDate start_date;

  @Column
  private LocalDate end_date;

  @Column(nullable = false)
  private int cab_pwd;

  @ManyToOne
  @JoinColumn(name="member_id")
  private Member member;
}
