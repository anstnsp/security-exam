package com.example.anstn.config.security;

import com.example.anstn.domain.users.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

//토큰에 저장된 유저 정보를 활용해야 하기 때문에 CustomUserDetatilService 라는 
//이름의 클래스를 만들고 UserDetailsService를 상속받아 재정의 하는 과정을 진행합니다.
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
  }

}