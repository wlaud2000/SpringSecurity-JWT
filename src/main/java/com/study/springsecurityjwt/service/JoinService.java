package com.study.springsecurityjwt.service;

import com.study.springsecurityjwt.dto.JoinDTO;
import com.study.springsecurityjwt.entity.UserEntity;
import com.study.springsecurityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);

        if(isExist) { //DB에 이미 동일한 username이 있다면 그냥 return

            return;
        }
        // 동일한 username이 없다면
        UserEntity data = UserEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password)) //암호화 후 password에 저장
                .role("ROLE_ADMIN") //강제로 ADMIN권한을 가지게 일단 설정함.
                .build();

        //그 후 DB에 저장
        userRepository.save(data);

    }
}
