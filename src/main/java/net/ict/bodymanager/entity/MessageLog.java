package net.ict.bodymanager.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageLog extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long message_id;

 @ManyToOne
 @JoinColumn(name="room_id")
 private MessageRoom messageRoom;

  @ManyToOne
  @JoinColumn(name="sender_id")
  private Member member;

  @Column(length = 1000, nullable = false)
  private String message_content;



}
