package com.fruits.library.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    private String username;
    private String password;
    @JsonProperty("repassword")
    private String rePassword;
    private String firstname;
    private String lastname;
    private String role;
}
