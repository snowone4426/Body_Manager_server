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
public class PTProgram{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long program_id;

  @ManyToOne
  @JoinColumn(name="pt_member_id")
  private PTMember ptMember;

  @Column(nullable = false)
  private int pt_count;

  @Column(length = 100, nullable = false)
  private String title;

  @Column(nullable = false)
  private double weight;

  @Column(nullable = false)
  private int count;

  @Column(nullable = false)
  private LocalDateTime pt_date;
}
