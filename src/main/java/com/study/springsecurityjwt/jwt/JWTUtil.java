package com.study.springsecurityjwt.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

//JWTUtil: 0.12.3
@Component //Component로 관리
public class JWTUtil {

    //객체 key를 저장할 객체 변수를 만든다.
    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret) { //@Value를 통해 application.properties 변수 설정에 저장되어있는 특정한 변수 데이터를 들고 올 수 있다. 이 변수를 String secret로 들고옴

        //JWT에서 객체 타입으로 만들어서 저장하면서 그 키를 암호화를 진행함
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    //token을 전달받아서 내부 데이터를 확인
    public String getUsername(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(String username, String role, Long expiredMs) {

        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis())) //발생시간
                .expiration(new Date(System.currentTimeMillis() + expiredMs)) //발행시간 + expiredMs = 소멸시간
                .signWith(secretKey) //암호화진행
                .compact(); //토큰 compact시켜서 리턴
    }
}
