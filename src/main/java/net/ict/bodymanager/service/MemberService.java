package net.ict.bodymanager.service;


import net.ict.bodymanager.dto.MemberDTO;
import net.ict.bodymanager.dto.PageRequestDTO;
import net.ict.bodymanager.dto.PageResponseDTO;
import net.ict.bodymanager.entity.Member;

import java.util.List;
import java.util.stream.Collectors;

public interface MemberService {

    Long register(MemberDTO memberDTO);

    MemberDTO readOne(Long member_id);

    void modify(MemberDTO memberDTO);

    void remove(Long bno);

    PageResponseDTO<MemberDTO> list(PageRequestDTO pageRequestDTO);


    default Member dtoToEntity(MemberDTO memberDTO){

        Member member = Member.builder()
                .member_id(memberDTO.getMember_id())
                .email(memberDTO.getEmail())
                .gender(memberDTO.getGender())
                .name(memberDTO.getName())

                .build();

        if(memberDTO.getFileNames() != null){
            memberDTO.getFileNames().forEach(fileName -> {
                String[] arr = fileName.split("_");
                member.addImage(arr[0], arr[1]);
            });
        }
        return member;
    }

    default MemberDTO entityToDTO(Member member) {

        MemberDTO memberDTO = MemberDTO.builder()
                .member_id(member.getMember_id())
                .email(member.getEmail())
                .gender(member.getGender())
                .name(member.getName())
                .created_at(member.getCreated_at())
//                .modDate(member.getModDate())
                .build();

        List<String> fileNames =
                member.getImageSet().stream().sorted().map(boardImage ->
                        boardImage.getUuid()+"_"+boardImage.getFileName()).collect(Collectors.toList());

        memberDTO.setFileNames(fileNames);

        return memberDTO;
    }

}

