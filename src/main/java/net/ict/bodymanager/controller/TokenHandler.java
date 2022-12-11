package net.ict.bodymanager.controller;

import lombok.RequiredArgsConstructor;
import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.filter.JwtTokenProvider;
import net.ict.bodymanager.repository.MemberRepository;
<<<<<<< HEAD
=======
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> 030fe50 ([준영] 로그인 유지 완료)
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

<<<<<<< HEAD
=======
import javax.servlet.http.Cookie;
>>>>>>> 030fe50 ([준영] 로그인 유지 완료)
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class TokenHandler {
  private final JwtTokenProvider jwtTokenProvider;
  private final MemberRepository memberRepository;

<<<<<<< HEAD
  public Long getIdFromToken() {
=======
  public Long getIdFromToken(){
>>>>>>> 030fe50 ([준영] 로그인 유지 완료)
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

<<<<<<< HEAD
  public String getEmailFromToken() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes()).getRequest();

    String token = jwtTokenProvider.resolveToken(request);
    String email = jwtTokenProvider.getUserPk(token);
    System.out.println("------------------------------member_id : " + email);
    return email;
  }


=======
  public Long getCookie(@CookieValue("X-AUTH-TOKEN") String token) {
    Member member = memberRepository.findByEmail(jwtTokenProvider.getUserPk(token))
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
    return member.getMember_id();
  }
>>>>>>> 030fe50 ([준영] 로그인 유지 완료)
}
