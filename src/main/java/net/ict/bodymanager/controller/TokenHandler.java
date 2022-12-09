package net.ict.bodymanager.controller;

import lombok.RequiredArgsConstructor;
import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.filter.JwtTokenProvider;
import net.ict.bodymanager.repository.MemberRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class TokenHandler {
  private final JwtTokenProvider jwtTokenProvider;
  private final MemberRepository memberRepository;

  public Long getIdFromToken() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes()).getRequest();

    String token = jwtTokenProvider.resolveToken(request);
    String email = jwtTokenProvider.getUserPk(token);
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("정보 없음"));

    Long member_id = member.getMember_id();  // member_id 에 토큰에서 뺴온 Long 값 저장
    System.out.println("------------------------------member_id : " + member_id);
    return member_id;
  }

  public String getEmailFromToken() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes()).getRequest();

    String token = jwtTokenProvider.resolveToken(request);
    String email = jwtTokenProvider.getUserPk(token);
    System.out.println("------------------------------member_id : " + email);
    return email;
  }


}
