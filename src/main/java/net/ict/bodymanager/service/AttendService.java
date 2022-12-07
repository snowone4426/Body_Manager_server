package net.ict.bodymanager.service;

import net.ict.bodymanager.dto.AttendDTO;
import net.ict.bodymanager.entity.Attend;
import net.ict.bodymanager.entity.Member;

public interface AttendService {
    void register(AttendDTO attendDTO);
//    void register(Attend attend);
    String readDay();
    void modify();
    String readMonth(String year,String month);

    default Attend dtoToEntity(AttendDTO attendDTO, Member member){
        Attend attend = Attend.builder()
                .member(member)
                .start_date(attendDTO.getStart_date())
                .build();
        return attend;
    }

}
