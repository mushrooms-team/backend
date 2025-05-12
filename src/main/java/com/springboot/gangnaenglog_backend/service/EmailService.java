package com.springboot.gangnaenglog_backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private static final String sendEmail = "gangnaenglog@gmail.com";
    private static int number; // 인증번호 변수

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // 랜덤 인증번호 생성
    public static void creatNumber() {
        number = (int) (Math.random() * 90000 + 100000); // 인증번호 6자리 생성
    }

    // 인증번호 반환
    public static int getNumber() {
        return number;
    }

    public MimeMessage CreateMail(String mail) {
        creatNumber();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.setFrom(sendEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("강냉로그 이메일 인증 번호 전송");
            String body = "<h3>요청하신 인증번호 입니다.</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h2>이메일 확인 창에 해당 코드를 입력해주세요.</h2>";
            message.setText(body, "utf-8", "html");
        } catch (MessagingException e) {
            throw new RuntimeException("메일 생성 오류 발생", e);
        }
        return message;
    }

    public int sendMail(String mail) {
        MimeMessage message = CreateMail(mail);
        javaMailSender.send(message);
        return number;
    }
}