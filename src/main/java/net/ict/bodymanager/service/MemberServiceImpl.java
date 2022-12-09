package net.ict.bodymanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.MemberDTO;
import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.filter.JwtTokenProvider;
import net.ict.bodymanager.repository.MemberRepository;
import org.json.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  @Override
  public Long register(MemberDTO memberDTO) {
    Member member = dtoToEntity(memberDTO);
    member.changePassword(passwordEncoder.encode(memberDTO.getPassword()));
    Long member_id = memberRepository.save(member).getMember_id();

    return member_id;
  }
  @Override
  public MemberDTO readOne(Long member_id) {

    //board_image까지 조인 처리되는 findByWithImages()를 이용
    Optional<Member> result = memberRepository.findByIdWithImages(member_id);

    Member member = result.orElseThrow();

    MemberDTO memberDTO = entityToDTO(member);

    return memberDTO;
  }

  @Override
  public void modify(MemberDTO memberDTO) {

    Optional<Member> result = memberRepository.findById(memberDTO.getMember_id());

    Member member = result.orElseThrow();

    member.change(memberDTO.getEmail(), memberDTO.getGender());

    //첨부파일의 처리
    member.clearImages();

    if (memberDTO.getFileNames() != null) {
      for (String fileName : memberDTO.getFileNames()) {
        String[] arr = fileName.split("_");
        member.addImage(arr[0], arr[1]);
      }
    }
    memberRepository.save(member);
  }

  //이메일 찾기
  @Override
  public String findEmail(String phone, String name) {
    String email = memberRepository.findByPhoneAndName(phone,name);
    JSONObject object = new JSONObject();
    JSONObject data = new JSONObject();

    object.put("email",email);

    if(email == null || email.equals("")){
      data.put("message","not found");

    }else {
      data.put("message", "ok");
      data.put("data", object);
    }
    return data.toString();
  }

  @Override
  public void remove(Long member_id) {
    memberRepository.deleteById(member_id);
  }



}
