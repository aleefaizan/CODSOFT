package org.example.atmmachine;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Insufficient funds. Cannot withdraw.");
    }
}

