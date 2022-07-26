package com.jojoldu.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),                  //spring security는 key에 항상 Role이 있어야 함.
    USER("ROLE_USER", "일반사용쟈");

    private final String key;
    private final String title;
}
