package net.proselyte.webfluxsecurity.controller;

import net.proselyte.webfluxsecurity.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaContrtoller {

  @PostMapping("/java")
  public ResponseEntity<?> java(@RequestBody UserDto body) {
    return ResponseEntity.ok(body);
  }
}
