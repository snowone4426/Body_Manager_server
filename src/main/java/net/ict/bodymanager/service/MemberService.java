package net.ict.bodymanager.service;


import net.ict.bodymanager.dto.MemberDTO;
<<<<<<< HEAD
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
=======
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
>>>>>>> origin/feat/mail
    }

    default MemberDTO entityToDTO(Member member) {

        MemberDTO memberDTO = MemberDTO.builder()
                .member_id(member.getMember_id())
                .email(member.getEmail())
<<<<<<< HEAD
                .password(member.getPassword())
                .gender(member.getGender())
                .name(member.getName())
                .created_at(member.getCreated_at())
                .build();

        List<String> fileNames =
                member.getImageSet().stream().sorted().map(profile ->
                        profile.getUuid()+"_"+profile.getFileName()).collect(Collectors.toList());
=======
                .gender(member.getGender())
                .name(member.getName())
                .created_at(member.getCreated_at())
//                .modDate(member.getModDate())
                .build();

        List<String> fileNames =
                member.getImageSet().stream().sorted().map(boardImage ->
                        boardImage.getUuid()+"_"+boardImage.getFileName()).collect(Collectors.toList());
>>>>>>> origin/feat/mail

        memberDTO.setFileNames(fileNames);

        return memberDTO;
    }
<<<<<<< HEAD
=======

>>>>>>> origin/feat/mail
}

