package com.lean.csrf.view.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto(

        @NotNull
        @Size(min = 3, max = 50)
        String userName,

        @NotNull
        @Size(min = 3, max = 50)
        String email,

        @NotNull
        @Size(min = 6, max = 20)
        String password,

        @NotNull
        @Size(min = 6, max = 20)
        String confirmPassword
) {
}
