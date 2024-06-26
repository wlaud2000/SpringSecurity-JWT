package com.study.springsecurityjwt.jwt;

import com.study.springsecurityjwt.dto.CustomUserDetails;
import com.study.springsecurityjwt.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 Authorization 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        //Authorization 헤더 검증
        if(authorization == null || !authorization.startsWith("Bearer ")) { //authorization이 null이거나, 접두사가 Bearer이 아니면
            System.out.println("token null");
            filterChain.doFilter(request, response); //다음 필터로 request와 response를 넘겨줌

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        System.out.println("authorization now");

        //Bearer부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        if(jwtUtil.isExpired(token)) { //토큰이 만료 되었으면

            System.out.println("token expired");
            filterChain.doFilter(request, response); //다음 필터로 넘겨라

            return;
        }

        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        //userEntity를 생성하여 값 set
        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password("temppassword")
                .role(role)
                .build();

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity); //CustomUserDetails의 생성자에 userEntity 객체를 전달(CustomUserDetails 객체가 userEntity의 정보를 이용할 수 있도록 함)

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
