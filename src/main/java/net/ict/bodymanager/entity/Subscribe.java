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

    @Column
    private LocalDate membership_start;

    @Column
    private LocalDate membership_end;

    @Column
    private LocalDate suit_start;

    @Column
    private LocalDate suit_end;

    public void change(LocalDate membership_start, LocalDate membership_end, LocalDate suit_start, LocalDate suit_end){
        this.membership_start = membership_start;
        this.membership_end = membership_end;
        this.suit_end = suit_end;
        this.suit_start = suit_start;
    }

}
