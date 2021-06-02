package io.github.vitor0x5.domains.transaction;

import lombok.Getter;

@Getter
public enum TransactionTypes {
    INCOME("income"),
    OUTCOME("outcome");

    private final String type;

    TransactionTypes(String type) {
        this.type = type;
    }
}
