package com.mega.cinematica.controllers;

import com.mega.cinematica.models.dto.requests.CreateSessionRequest;
import com.mega.cinematica.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;
    @PostMapping("/create")
    ResponseEntity<?> createSession(@RequestBody CreateSessionRequest request){
        return new ResponseEntity<>(sessionService.createSession(request), HttpStatus.CREATED);
    }
}
