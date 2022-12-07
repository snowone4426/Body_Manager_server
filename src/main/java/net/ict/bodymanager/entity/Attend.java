package net.ict.bodymanager.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
  @JoinColumn(name="member_id" ) //, insertable = false, updatable = false
  private Member member;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime start_date;

  @Column(nullable = true)
  private LocalDateTime end_date;

  public void change(LocalDateTime end_date){
    this.end_date= end_date;
  }

}
