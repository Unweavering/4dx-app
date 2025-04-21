package com._dx.service;

import com._dx.dto.auth.LoginRequest;
import com._dx.dto.auth.SignupRequest;
import com._dx.dto.common.MessageResponse;
import com._dx.model.User;
import com._dx.repository.UserRepository;
import com._dx.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public MessageResponse signup(SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return new MessageResponse("회원가입 성공!");
    }


    public String login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isPresent() && passwordEncoder.matches(request.getPassword(), userOptional.get().getPassword())) {
            return jwtUtil.generateToken(request.getUsername());
        }
        throw new RuntimeException("로그인 실패: 잘못된 자격 증명");
    }
}

