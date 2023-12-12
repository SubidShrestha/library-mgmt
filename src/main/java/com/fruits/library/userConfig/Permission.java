package com.fruits.library.userConfig;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin::read"),
    ADMIN_UPDATE("admin::update"),
    ADMIN_DELETE("admin::delete"),
    ADMIN_CREATE("admin::create"),
    USER_READ("user::read"),
    USER_UPDATE("user::update"),
    USER_DELETE("user::delete"),
    USER_CREATE("user::create"),
    book_CREATE("book::create"),
    book_READ("book::read"),
    book_DELETE("book::delete"),
    book_UPDATE("book::update"),
    author_READ("author::read"),
    author_CREATE("author::create"),
    author_UPDATE("author::update"),
    author_DELETE("author::delete")
    ;

    private final String permission;
}
