package io.github.vitor0x5.shared.encoder;

import io.github.vitor0x5.shared.encoder.implementations.BCrypt;

public class Encoder implements IEncoder{
    private final IEncoder encoderImplementation;

    public Encoder() {
        encoderImplementation = new BCrypt();
    }

    @Override
    public String encode(CharSequence charSequence) {
        return encoderImplementation.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encoderImplementation.matches(charSequence, s);
    }
}
