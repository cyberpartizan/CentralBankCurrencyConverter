package com.cyber.partizan.currencyconverter.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class LoginCredits {
    @NotEmpty(message = "Username cant be empty")
    private String username;
    @NotEmpty(message = "Password cant be empty")
    private String password;
}
