package com.fruits.library.authentication.token;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType {
    BEARER("Bearer");
    private final String tokenType;
}
