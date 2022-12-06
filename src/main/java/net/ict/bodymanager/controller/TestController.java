package net.ict.bodymanager.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Log4j2
public class TestController {
  @PostMapping("/user/test")
  public Map userResponseTest() {
    Map<String, String> result = new HashMap<>();
    result.put("result","user ok");
    return result;
  }

  @PostMapping("/admin/test")
  public Map adminResponseTest() {
    Map<String, String> result = new HashMap<>();
    result.put("result","admin ok");
    return result;
  }
}
