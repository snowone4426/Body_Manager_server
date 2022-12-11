package net.ict.bodymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.filter.JwtTokenProvider;
import net.ict.bodymanager.repository.MemberRepository;
import net.ict.bodymanager.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Log4j2
public class TestController {
  private final JwtTokenProvider jwtTokenProvider;
  private final MemberRepository memberRepository;
  private final MemberService memberService;

  @PostMapping("/user/test")
  public Map userResponseTest() {
    Map<String, String> result = new HashMap<>();
    result.put("result", "user ok");
    return result;
  }

  @PostMapping("/admin/test")
  public Map adminResponseTest() {
    Map<String, String> result = new HashMap<>();
    result.put("result", "admin ok");
    return result;
  }

  @GetMapping("token/test")
  public Long tokenCheck() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes()).getRequest();

    String token = jwtTokenProvider.resolveToken(request);
    String email = jwtTokenProvider.getUserPk(token);
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("정보 없음"));

    Long member_id = member.getMember_id();  // member_id 에 토큰에서 뺴온 Long 값 저장

    return member_id;
  }

  @GetMapping("token/test1")
  public Long tokenCheck1() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes()).getRequest();

    System.out.println("헤더에있는 토큰 가져와잇 : " + request.getHeader("X-AUTH-TOKEN"));
    System.out.println("쿠키에있는 토큰 가져와잇 : " +   request.getCookies());
    String token = jwtTokenProvider.resolveToken(request);
    String email = jwtTokenProvider.getUserPk(token);
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("정보 없음"));

    Long member_id = member.getMember_id();  // member_id 에 토큰에서 뺴온 Long 값 저장

    return member_id;
  }
}
