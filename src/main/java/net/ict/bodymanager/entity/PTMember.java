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
public class PTMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pt_member_id;

    @Column(nullable = false)
    private LocalDate start_date;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Column(nullable = false)
    private int pt_total_count;

    @Column(nullable = false)
    private int pt_remain_count;

    @Column(nullable = false)
    private int pt_present_count;

    @ManyToOne
    @JoinColumn(name="pt_id")
    private PTInfo ptInfo;

}
