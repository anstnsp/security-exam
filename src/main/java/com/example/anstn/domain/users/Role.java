package com.example.anstn.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//각 사용자의 권한을 관리할 Enum 클래스 
//스프링시큐리티에서는 권한 코드에 항상 ROLE_이 앞에 있어야만 함. 
@Getter
@RequiredArgsConstructor
public enum Role {

  GUEST("ROLE_GUEST", "비회원"), // 여길보면 위에서 언급한것처럼 ROLE_이 앞에 붙음.
  USER("ROLE_USER", "회원"), ADMIN("ROLE_ADMIN", "관리자");

  private final String key;
  private final String title;

}