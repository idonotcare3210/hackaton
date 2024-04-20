package com.blindcats.lifecourse.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderBean extends BCryptPasswordEncoder {
}
