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
public class Member extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long member_id;
  @Column(length = 50, nullable = false, unique = true)
  private String email;
  @Column(length = 30, nullable = false)
  private String password;
  @Column(length = 10, nullable = false)
  private String name;
  @Column(length = 100, nullable = false)
  private String address;
  @Column(length = 15, nullable = false, unique = true)
  private String phone;
  @Column(length = 10, nullable = false)
  private String gender;
  @Column(nullable = false)
  private double height;
  @Column(length = 300, nullable = false)
  private String remark;
  @Column(nullable = false)
  private LocalDate birth;
  @Column(length = 100, nullable = false)
  private String profile;
  @Column(length = 10, nullable = false)
  private String type;  //dormant(휴면), common, trainer , admin
}
