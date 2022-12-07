package net.ict.bodymanager.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
public class MemberImage implements Comparable<MemberImage> {

    @Id
    private String uuid;

    private String fileName;

    private int ord;

    @ManyToOne
    private Member member;


    @Override
    public int compareTo(MemberImage other) {
        return this.ord - other.ord;
    }

    public void changeBoard(Member board){
        this.member = board;
    }

}
