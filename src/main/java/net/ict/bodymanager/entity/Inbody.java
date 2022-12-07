package net.ict.bodymanager.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inbody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inbody_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Column(nullable = false)
    private double weight;
    @Column(nullable = false)
    private double SMM;
    @Column(nullable = false)
    private double BFM;
    @Column(nullable = false)
    private double BMI;
    @Column(nullable = false)
    private double PBF;
    @Column(nullable = false)
    private double WHR;
    @Column(nullable = false)
    private double BMR;
    @Column(nullable = false)
    private double body_muscle;
    @Column(nullable = false)
    private double left_hand_muscle;
    @Column(nullable = false)
    private double right_hand_muscle;
    @Column(nullable = false)
    private double left_leg_muscle;
    @Column(nullable = false)
    private double right_leg_muscle;
    @Column(nullable = false)
    private double body_fat;
    @Column(nullable = false)
    private double left_hand_fat;
    @Column(nullable = false)
    private double right_hand_fat;
    @Column(nullable = false)
    private double left_leg_fat;
    @Column(nullable = false)
    private double right_leg_fat;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate created_at;

    public void change(double weight, double SMM, double BFM, double BMI, double PBF, double WHR, double BMR,
                       double body_muscle, double left_hand_muscle, double right_hand_muscle, double left_leg_muscle, double right_leg_muscle,
                       double body_fat, double left_hand_fat, double right_hand_fat, double left_leg_fat, double right_leg_fat){
        this.weight = weight;
        this.SMM = SMM;
        this.BFM = BFM;
        this.BMI = BMI;
        this.PBF = PBF;
        this.WHR = WHR;
        this.BMR = BMR;
        this.body_muscle = body_muscle;
        this.left_hand_muscle = left_hand_muscle;
        this.right_hand_muscle = right_hand_muscle;
        this.left_leg_muscle = left_leg_muscle;
        this.right_leg_muscle = right_leg_muscle;
        this.body_fat = body_fat;
        this.left_hand_fat = left_hand_fat;
        this.right_hand_fat = right_hand_fat;
        this.left_leg_fat = left_leg_fat;
        this.right_leg_fat = right_leg_fat;
    }


}
