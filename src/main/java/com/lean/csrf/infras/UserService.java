package com.lean.csrf.infras;

import com.lean.csrf.view.dto.LoginResultDto;
import com.lean.csrf.view.dto.UserRequestDto;
import com.lean.csrf.view.dto.UserResponseDto;

public interface UserService {
    LoginResultDto loginUser(UserRequestDto users);
    UserResponseDto createUser(UserRequestDto users);
    void logoutUser();
}
