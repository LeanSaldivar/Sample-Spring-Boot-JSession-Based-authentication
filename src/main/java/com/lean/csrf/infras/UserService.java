package com.lean.csrf.infras;

import com.lean.csrf.view.dto.LoginResultDto;
import com.lean.csrf.view.dto.UserRequestDto;
import com.lean.csrf.view.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    LoginResultDto loginUser(UserRequestDto users);
    UserResponseDto createUser(UserRequestDto users);
    public UserResponseDto getUserById(Long id);
    void logoutUser();
}
