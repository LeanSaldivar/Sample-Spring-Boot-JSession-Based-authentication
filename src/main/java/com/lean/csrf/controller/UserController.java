package com.lean.csrf.controller;

import com.lean.csrf.infras.UserMapper;
import com.lean.csrf.infras.UserService;
import com.lean.csrf.service.UserServiceImpl;
import com.lean.csrf.view.dto.AuthResponseDto;
import com.lean.csrf.view.dto.LoginResultDto;
import com.lean.csrf.view.dto.UserRequestDto;
import com.lean.csrf.view.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/auth/action/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody UserRequestDto userRequest) {
        LoginResultDto loginResult = userService.loginUser(userRequest);

         AuthResponseDto responseBody = new AuthResponseDto(
                userMapper.toResponseDTO(loginResult.user())
        );

        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/auth/action/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequest) {
        UserResponseDto userResponseDto = userService.createUser(userRequest);
        return ResponseEntity.ok().body(userResponseDto);
    }

    @GetMapping("/query/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@RequestParam Long id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        return ResponseEntity.ok().body(userResponseDto);
    }
}
