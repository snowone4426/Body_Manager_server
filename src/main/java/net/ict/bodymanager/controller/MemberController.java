package net.ict.bodymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.entity.Member;
import net.ict.bodymanager.filter.JwtTokenProvider;
import net.ict.bodymanager.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/initial")
@Log4j2
public class MemberController {

  @Value("${net.ict.upload.path}")// import 시에 springframework으로 시작하는 Value
  private String uploadPath;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final MemberRepository userRepository;
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  // 회원가입
  @PostMapping(value = "/join" , consumes = MediaType.APPLICATION_JSON_VALUE)
  public Long join(@RequestBody Map<String, String> user) {
//    return userRepository.save(Member.builder()
//            .email(user.get("email"))
//            .password(passwordEncoder.encode(user.get("password")))
//            .name(user.get("name"))
//            .address(user.get("address"))
//            .phone(user.get("phone"))
//            .gender(user.get("gender"))
//            .height(Double.parseDouble(user.get("height")))
//            .remark(user.get("remark"))
//            .profile(user.get("profile"))
//            .birth(LocalDate.parse((user.get("birth")),formatter))
//            .type("0")
//            .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
//            .build()).getMember_id();
    Map<String,String> result = Map.of("message","ok");
    return ResponseEntity.ok(result);
  }

  // 로그인
  @PostMapping("/login")
  public String login(@RequestBody Map<String, String> user) {
//    Member member = userRepository.findByEmail(user.get("email"))
//            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
//    if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
//      throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//    }
//    return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
  }
}