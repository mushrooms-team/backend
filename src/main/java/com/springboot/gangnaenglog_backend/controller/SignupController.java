package com.springboot.gangnaenglog_backend.controller;

import com.springboot.gangnaenglog_backend.dto.*;
import com.springboot.gangnaenglog_backend.jwt.JwtTokenProvider;
import com.springboot.gangnaenglog_backend.service.EmailService;
import com.springboot.gangnaenglog_backend.entity.User;
import com.springboot.gangnaenglog_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/auth")
public class SignupController {

    private final EmailService emailService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public SignupController(EmailService emailService, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.emailService = emailService;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 인증코드 전송 - @RequestBody 사용
    @PostMapping("/email-verification")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        int number = emailService.sendMail(request.getEmail());
        return ResponseEntity.ok("이메일 인증 번호가 발송되었습니다: " + number);
    }

    // 인증코드 확인 - @RequestBody 사용
    @PostMapping("/email-verification/check")
    public ResponseEntity<String> verifyEmail(@RequestBody EmailVerificationRequest request) {
        if (request.getCode() == EmailService.getNumber()) {
            return ResponseEntity.ok("인증 성공! 회원가입을 진행하세요.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 실패! 올바른 인증번호를 입력하세요.");
        }
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        if (userService.isEmailExist(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 등록된 이메일입니다.");
        }
        userService.saveUser(user);
        return ResponseEntity.ok("회원가입이 완료되었습니다!");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (userService.authenticateUser(request.getEmail(), request.getPassword())) {
            String token = jwtTokenProvider.generateToken(request.getEmail());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    // 이메일 인증 후 비밀번호 재설정
    @PutMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordResetRequest request) {
        // 이메일과 비밀번호가 일치하는지 확인
        if (request.getNewPassword().equals(request.getConfirmPassword())) {
            // 이메일로 해당 사용자를 찾아 비밀번호를 변경
            userService.updatePassword(request.getEmail(), request.getNewPassword());
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    }


    // 기존 비밀번호 입력 후 변경
    @PutMapping("/password-reset")
    public ResponseEntity<?> changePasswordWithCurrent(@RequestBody PasswordChangeRequest request) {
        if (userService.authenticateUser(request.getEmail(), request.getCurrentPassword())) {
            if (request.getNewPassword().equals(request.getConfirmNewPassword())) {
                userService.updatePassword(request.getEmail(), request.getNewPassword());
                return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("현재 비밀번호가 올바르지 않습니다.");
        }
    }

    // 닉네임 중복 확인
    @PostMapping("/name-check")
    public ResponseEntity<String> checkName(@RequestBody NameCheckRequest request) {
        User user = userService.getUserByName(request.getName());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 사용 중인 닉네임입니다.");
        } else {
            return ResponseEntity.ok("사용 가능한 닉네임입니다.");
        }
    }


}
