package net.ict.bodymanager.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long purchase_id;

  @ManyToOne
  @JoinColumn(name="order_id")
  private OrderInfo orderInfo;

  @ManyToOne
  @JoinColumn(name="price_id")
  private Price price;

  @ManyToOne
  @JoinColumn(name="pt_id")
  private PTInfo ptInfo;

  @Column(nullable = false)
  private int period; //몇달 남았는지
}
