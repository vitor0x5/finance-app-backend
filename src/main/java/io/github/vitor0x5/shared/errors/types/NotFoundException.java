package io.github.vitor0x5.shared.errors.types;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}
