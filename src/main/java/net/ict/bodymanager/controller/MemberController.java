package net.ict.bodymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.dto.MemberDTO;
import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.filter.JwtTokenProvider;
import net.ict.bodymanager.repository.MemberRepository;
import net.ict.bodymanager.service.MemberService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/initial")
public class MemberController {

  @Value("${net.ict.upload.path}")// import 시에 springframework으로 시작하는 Value
  private String uploadPath;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final MemberRepository memberRepository;
  private final MemberService memberService;

  // 회원가입
  @PostMapping(value = "/join", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, String>> join(@RequestBody MemberDTO memberDTO) {
    //비동기 통신으로 받아줘야하니까 @RequestBody를 사용
    memberService.register(memberDTO);
    Map<String, String> result = Map.of("message", "ok");
    return ResponseEntity.ok(result);
  }

  //이메일 중복체크
  @PostMapping(value = "/emailcheck", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, String>> emailDuple(@RequestBody Member member) {
    memberRepository.existsByEmail(member.getEmail());
    if (memberRepository.existsByEmail(member.getEmail())) {
      Map<String, String> result = Map.of("message", "duplicate");
      return ResponseEntity.ok(result);
    } else {
      Map<String, String> result = Map.of("message", "ok");
      return ResponseEntity.ok(result);
    }
  }

  //휴대폰 번호 중복체크
  @PostMapping(value = "/phonecheck", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, String>> phoneDuple(@RequestBody Member member) {
    memberRepository.existsByPhone(member.getPhone());
    if (memberRepository.existsByPhone(member.getPhone())) {
      Map<String, String> result = Map.of("message", "duplicate");
      return ResponseEntity.ok(result);
    } else {
      Map<String, String> result = Map.of("message", "ok");
      return ResponseEntity.ok(result);
    }
  }

  // 로그인
  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> user , HttpServletResponse response) {
    Member member = memberRepository.findByEmail(user.get("email"))
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
    if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
      throw new IllegalArgumentException("잘못된 비밀번호입니다.");
    }

    String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    response.setHeader("X-AUTH-TOKEN" , token);   //헤더 "X-AUTH-TOKEN" 에 토큰 값 저장
    Cookie cookie = new Cookie("X-AUTH-TOKEN", token);  //쿠키에 저장
    System.out.println("cookie 넣기 전 value : "+cookie.getValue());
    System.out.println("cookie 넣기 전 이름 : "+cookie.getName());

    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setMaxAge(30*60);
    response.addCookie(cookie);

    System.out.println("cookie.getValue() : "+cookie.getValue());


    JSONObject object = new JSONObject();
    JSONObject data = new JSONObject();



    String token_email = jwtTokenProvider.getUserPk(token);   //토큰에서 이에일 가져옴
    System.out.println("get-user : "+token_email);

    Map<String, String> result = Map.of("message", "ok");
    return ResponseEntity.ok(result);
  }

  //이메일 찾기
  @PostMapping("/findEmail")
  public String findEmail(@RequestBody Map<String, Object > map){
    return memberService.findEmail(String.valueOf(map.get("phone")), String.valueOf(map.get("name")));
  }



  //로그아웃 *미구현
  @GetMapping("/logout")
  public void logout(HttpServletResponse response){
    Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
    cookie.setHttpOnly(true);
    cookie.setSecure(false);
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);
    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("로그아웃");
    System.out.println("cookie.getValue()"+cookie.getValue());
    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
  }











}