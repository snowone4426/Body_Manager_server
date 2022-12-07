package net.ict.bodymanager.service;


import net.ict.bodymanager.dto.MemberDTO;
import net.ict.bodymanager.entity.Member;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public interface MemberService {
    Long register(MemberDTO memberDTO);
    MemberDTO readOne(Long member_id);
    void modify(MemberDTO memberDTO);
    String findEmail(String phone, String name);
    void remove(Long member_id);
    String infoToToken(String token);


    default Member dtoToEntity(MemberDTO memberDTO){
        System.out.println("===========================안녕 dtoToEntity=============================");

           Member member = Member.builder()
                   .member_id(memberDTO.getMember_id())
                   .email(memberDTO.getEmail())
                   .password(memberDTO.getPassword())
                   .name(memberDTO.getName())
                   .address(memberDTO.getAddress())
                   .phone(memberDTO.getPhone())
                   .gender(memberDTO.getGender())
                   .height(Double.parseDouble(memberDTO.getHeight()))
                   .remark(memberDTO.getRemark())
                   .birth(memberDTO.getBirth())
                   .type("0")
                   .roles(Collections.singletonList("ROLE_USER"))
                   .build();

           System.out.println(member);

           System.out.println("===========================2222222222=============================");


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
                .password(member.getPassword())
                .gender(member.getGender())
                .name(member.getName())
                .created_at(member.getCreated_at())
                .build();

        List<String> fileNames =
                member.getImageSet().stream().sorted().map(profile ->
                        profile.getUuid()+"_"+profile.getFileName()).collect(Collectors.toList());

        memberDTO.setFileNames(fileNames);

        return memberDTO;
    }
}

