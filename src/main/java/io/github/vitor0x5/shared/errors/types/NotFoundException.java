package io.github.vitor0x5.shared.errors.types;

public class NotFoundException extends RuntimeException{

    public static String userNotFound = "User not found";

    public NotFoundException(String message) {
        super(message);
    }
}
