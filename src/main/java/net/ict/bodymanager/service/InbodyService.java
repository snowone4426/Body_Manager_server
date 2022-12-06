package net.ict.bodymanager.service;

import net.ict.bodymanager.dto.InbodyDTO;
import net.ict.bodymanager.dto.InbodyRequestDTO;
import net.ict.bodymanager.entity.Inbody;
import net.ict.bodymanager.entity.Member;

import java.time.LocalDate;
import java.util.List;

public interface InbodyService {

    void register(InbodyDTO inbodyDTO);

    void modify(InbodyDTO inbodyDTO);

    List<LocalDate> check();

    String musclePart(InbodyRequestDTO inbodyRequestDTO);

    String fatPart(InbodyRequestDTO inbodyRequestDTO);

    String bodyChangeFlow();

    default Inbody dtoTOEntity(InbodyDTO inbodyDTO, Member member){
        Inbody inbody = Inbody.builder()
                .member(member)
                .created_at(LocalDate.now())
                .weight(inbodyDTO.getWeight())
                .SMM(inbodyDTO.getSMM())
                .BFM(inbodyDTO.getBFM())
                .BMI(inbodyDTO.getBMI())
                .PBF(inbodyDTO.getPBF())
                .WHR(inbodyDTO.getWHR())
                .BMR(inbodyDTO.getBMR())
                .body_muscle(inbodyDTO.getBody_muscle())
                .left_hand_muscle(inbodyDTO.getLeft_hand_muscle())
                .right_hand_muscle(inbodyDTO.getRight_hand_muscle())
                .left_leg_muscle(inbodyDTO.getLeft_leg_muscle())
                .right_leg_muscle(inbodyDTO.getRight_leg_muscle())
                .body_fat(inbodyDTO.getBody_fat())
                .left_hand_fat(inbodyDTO.getLeft_hand_fat())
                .right_hand_fat(inbodyDTO.getRight_hand_fat())
                .left_leg_fat(inbodyDTO.getLeft_leg_fat())
                .right_leg_fat(inbodyDTO.getRight_leg_fat())
                .build();
        return inbody;
    }

}
