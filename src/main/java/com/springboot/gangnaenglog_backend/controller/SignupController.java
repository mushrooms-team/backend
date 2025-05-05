package com.springboot.gangnaenglog_backend.controller;

import com.springboot.gangnaenglog_backend.dto.JwtToken;
import com.springboot.gangnaenglog_backend.dto.LoginRequest;
import com.springboot.gangnaenglog_backend.dto.PasswordChangeRequest;
import com.springboot.gangnaenglog_backend.dto.PasswordResetRequest;
import com.springboot.gangnaenglog_backend.jwt.JwtTokenProvider;
import com.springboot.gangnaenglog_backend.service.EMailService;
import com.springboot.gangnaenglog_backend.entity.User;
import com.springboot.gangnaenglog_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class SignupController {

    private final EMailService emailService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public SignupController(EMailService emailService, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.emailService = emailService;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //인증코드전송
    @PostMapping("/email-verification")
    public String sendEmail(@RequestParam("email") String email) {
        int number = emailService.sendMail(email);
        return "이메일 인증 번호가 발송되었습니다: " + number;
    }

    //인증코드 확인
    @PostMapping("/email-verification/check")
    public String verifyEmail(@RequestParam("email") String email, @RequestParam("code") int code) {
        if (code == EMailService.getNumber()) {
            return "인증 성공! 회원가입을 진행하세요.";
        } else {
            return "인증 실패! 올바른 인증번호를 입력하세요.";
        }
    }

    //회원가입
    @PostMapping("/signup")
    public String signUp(@RequestBody User user) {
        if (userService.isEmailExist(user.getEmail())) {
            return "이미 등록된 이메일입니다.";
        }
        userService.saveUser(user);
        return "회원가입이 완료되었습니다!";
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (userService.authenticateUser(request.getEmail(), request.getPassword())) {
            Long userId = userService.getUserIdByEmail(request.getEmail());
            String token = jwtTokenProvider.generateToken(userId);

            return ResponseEntity.ok("로그인 성공!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }


    // 새로운 비밀번호가 일치하는지 확인
    @PostMapping("/password-reset")
    public ResponseEntity<?> changePassword(@RequestBody PasswordResetRequest request) {
        if (request.getNewPassword().equals(request.getConfirmPassword())) {
            userService.updatePassword(request.getEmail(), request.getNewPassword());
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    }

    // 기존 비밀번호를 확인하고, 맞으면 새 비밀번호 변경
    @PostMapping("/password-change")
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

}
