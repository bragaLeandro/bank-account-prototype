package br.com.fiap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<String> HelloWorld() {
        return ResponseEntity.ok("HELLO");
    }

}
