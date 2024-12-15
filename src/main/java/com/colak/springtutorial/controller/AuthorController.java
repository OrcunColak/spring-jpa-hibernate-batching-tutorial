package com.colak.springtutorial.controller;

import com.colak.springtutorial.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    // http://localhost:8080
    @GetMapping
    public ResponseEntity<StreamingResponseBody> authorStream() {
        StreamingResponseBody authorStream = authorService.getAuthorStream();
        return ResponseEntity.ok(authorStream);
    }
}
