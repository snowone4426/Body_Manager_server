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
public class Subscribe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long sub_id;

  @ManyToOne
  @JoinColumn(name="member_id")
  private Member member;

  @Column(nullable = false)
  private LocalDate membership_start;

  @Column(nullable = false)
  private LocalDate membership_end;

  @Column(nullable = false)
  private LocalDate suit_start;

  @Column(nullable = false)
  private LocalDate suit_end;


}
