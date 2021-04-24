package io.github.vitor0x5.shared.encoder.implementations;

import io.github.vitor0x5.shared.encoder.IEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

public class BCrypt implements IEncoder {

    private final BCryptPasswordEncoder encoder;

    public BCrypt() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence charSequence) {
        return encoder.encode(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encoder.matches(charSequence, s);
    }





}
