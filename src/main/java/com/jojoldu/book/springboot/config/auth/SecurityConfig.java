package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity         //Spring Security 설정들을 활성화시켜 줍니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomeOAuth2UserService customeOAuth2UserService;

    /*
    1. config.auth 패키지 창성
    2. build.gradle에
    implementation 'org.springframework.boot:spring-boot-starter-oauth2-client' 추가
    3. Configuration.class 만들기 (SecurityConfig.java)
    3-1. Configuration class를 만들고 WebSecurityConfigureAdapter를 상속한 후 @EnableWebSecurity 애노테이션 추가한다.
    3-2. configure(HttpSecurity http) 메소드 만들어서
    http..authorizeRequests()로 권한관리 시작하고
    .antMatchers("/", "js/**").permitAll()로 전체 열람 권한 주고
    .antMatchers("/api/v1/**").hasRole("ADMIN")으로 ADMIN만 권한 주고
    .anyRequest().authenticated()로 로그인 한 사람만 권한 준다.

    *** 기억해야될 부분은 스프링시큐리티의 설정은 HttpSecurity 하는 것 입니다.
    스프링시큐리티의 각종 설정은 HttpSecurity로 대부분 하게 됩니다.
    리소스(URL) 접근 권한 설정
    인증 전체 흐름에 필요한 Login, Logout 페이지 인증완료 후 페이지 인증 실패 시 이동페이지 등등 설정
    인증 로직을 커스텀하기위한 커스텀 필터 설정
    기타 csrf, 강제 https 호출 등등 거의 모든 스프링시큐리티의 설정


     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()                                                                        // Default로 spring security에서 설정, 왜? api방식으로 인증할거면(OAuth2, jwt토큰 등) 서버에 인증정보 저장하지 않기때문에
                .headers().frameOptions().disable()                                                      //  h2-console 화면을 사용하기 위해서 해당 옵션 disable, 안하면 화면 frame 다 깨지고 안뜸.
                .and()
                    .authorizeRequests()                                                                 //  권한관리 시작, antMatcher사용할 수 있음.
                    .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "h2-console/**", "/profile").permitAll()                                           //  permitAll을 사용해서 해당 디렉토리로 시작하는 것 전체 열람권한 준다.
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())                      //  "/api/v1/**" 주소를 가진 API는 USER 권한 가진 사람만 가능
                    .anyRequest().authenticated()                                                        //  나머지는 인증된 사용자들(로그인한 사용자들)에게만 준다.
                .and()
                    .logout()
                        .logoutSuccessUrl("/")                                                           //  로그아웃 성공시 해당 주소로 간다.
                .and()
                    .oauth2Login()                                                                       //  로그인 기능 설정 시작
                        .userInfoEndpoint()                                                              //  로그인 성공 후 사용자 정보 가져올 때 설정
                            .userService(customeOAuth2UserService);                                      //  성공시 customeOAuth2UserService에서 처리를 하겠다.
    }


}
