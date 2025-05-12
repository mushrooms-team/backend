package com.springboot.gangnaenglog_backend.service;

import com.springboot.gangnaenglog_backend.entity.User;
import com.springboot.gangnaenglog_backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원 저장
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // 이메일 중복 확인
    public boolean isEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // 로그인 검증
    public boolean authenticateUser(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.isPresent() && passwordEncoder.matches(rawPassword, userOpt.get().getPassword());
    }

    // 이메일로 사용자 ID 찾기
    public Long getUserIdByEmail(String email) {
        System.out.println("🔍 getUserIdByEmail() 호출됨. 받은 이메일: [" + email + "]");

        return userRepository.findByEmail(email)
                .map(User::getId)
                .orElseThrow(() -> {
                    System.out.println("❌ DB에서 이메일을 찾지 못함: [" + email + "]");
                    return new IllegalArgumentException("해당 멤버 없음: " + email);
                });
    }


    // Spring Security가 사용자 정보를 로딩할 때 사용
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 사용자를 찾을 수 없습니다: " + email));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("해당 ID의 사용자를 찾을 수 없습니다: " + id));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    //비밀번호 재설정
    public void updatePassword(String email, String newPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }


}
