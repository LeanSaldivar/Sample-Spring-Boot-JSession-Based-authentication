package com.lean.csrf.service;

import com.lean.csrf.config.security.CustomUserDetails;
import com.lean.csrf.exception.custom.DuplicateResourceException;
import com.lean.csrf.exception.custom.PasswordsDoesNotMatchException;
import com.lean.csrf.exception.custom.ResourceNotFoundException;
import com.lean.csrf.infras.UserMapper;
import com.lean.csrf.infras.UserRepository;
import com.lean.csrf.infras.UserService;
import com.lean.csrf.view.User;
import com.lean.csrf.view.dto.LoginResultDto;
import com.lean.csrf.view.dto.UserRequestDto;
import com.lean.csrf.view.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private User findUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
    }


    @Override
    public LoginResultDto loginUser(UserRequestDto userRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new LoginResultDto(user);

    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequest) {
        if (userRepository.existsByEmail(userRequest.email()) && userRepository.existsByUserName(userRequest.userName())) {
            throw new DuplicateResourceException("This email or username is already taken.");
        }

        if (!userRequest.password().equals(userRequest.confirmPassword())) {
            throw new PasswordsDoesNotMatchException("Passwords do not match.");
        }


        User userEntity = userMapper.toEntity(userRequest);
        userEntity.setPassword(passwordEncoder.encode(userRequest.password()));

        User savedUser = userRepository.save(userEntity);
        return userMapper.toResponseDTO(savedUser);
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDto getUserById(Long id) {
        User user = findUserByIdOrThrow(id);
        return userMapper.toResponseDTO(user);

    }

    @Override
    public void logoutUser() {

    }
}
