package net.ict.bodymanager.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PTInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pt_id;

    @ManyToOne
    @JoinColumn(name="trainer_id")
    private Member trainer;

    @Column(nullable = false)
    private int pt_price;

/*    @Formula("(select 'PT' as pt_name from ptinfo)")
//    @Transient
    private String pt_name;*/
}
