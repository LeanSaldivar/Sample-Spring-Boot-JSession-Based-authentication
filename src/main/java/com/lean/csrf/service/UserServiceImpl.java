package com.lean.csrf.service;

import com.lean.csrf.config.security.CustomUserDetails;
import com.lean.csrf.exception.custom.DuplicateResourceException;
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

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

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
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new DuplicateResourceException("Email '" + userRequest.email() + "' is already in use.");
        }

        User userEntity = userMapper.toEntity(userRequest);
        userEntity.setPassword(passwordEncoder.encode(userRequest.password()));

        User savedUser = userRepository.save(userEntity);
        return userMapper.toResponseDTO(savedUser);
    }

    @Override
    public void logoutUser() {

    }
}
