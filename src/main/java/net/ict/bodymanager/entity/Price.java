package net.ict.bodymanager.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Price {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long price_id;

  @Column(nullable = false)
  private String price_name;

  @Column(nullable = false)
  private int price_info;
}
