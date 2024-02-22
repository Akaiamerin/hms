package com.hms;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
@SpringBootTest
public class HMSApplicationTest {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Test
    public void passwordEncoderTest() {
        System.out.println(passwordEncoder.encode("a"));
    }
}