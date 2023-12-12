package com.fruits.library.authentication;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String access;
    private String refresh;
}
