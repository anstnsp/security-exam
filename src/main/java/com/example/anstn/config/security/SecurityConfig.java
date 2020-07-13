package com.example.anstn.config.security;

import com.example.anstn.domain.users.Role;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

//Spring Security를 사용하기 위해서는 Spring Security Filter Chain 을 사용한다는 것을 명시해 줘야 합니다. 
//이것은 WebSecurityConfigurerAdapter를 상속받은 클래스에 @EnableWebSecurity 어노테이션을 달아주면 해결됩니다. 
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtTokenProvider jwtTokenProvider;

  // 스프링 부트 시큐리티 설정 클래스에 PasswordEncoder 에 대한 반환값을 생성하는 메서드를 작성하였습니다.
  // PasswordEncoder를 반환하는 메서드를 구현하지 않으면 스프링 부트에서는 PassEncoder에
  // 대한 정보를 찾을 수 없다면서 예외를 발생시킵니다.
  // 암호화에 필요한 PasswordEncoder 를 Bean 등록합니다.
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  // authenticationManager를 Bean 등록합니다.
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
        .csrf().disable() // csrf 보안 토큰 disable처리.
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
        .and().authorizeRequests() // 요청에 대한 사용권한 체크
        .antMatchers("/admin/**").hasRole(Role.ADMIN.name()).antMatchers("/user/**").hasRole(Role.USER.name())
        .anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능
        .and()
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
  }
}