package org.sigarLLD.ConcurrentURLShortener.Helper;

import jakarta.persistence.OptimisticLockException;

public class CustomOptimisticLockException extends RuntimeException {
    public CustomOptimisticLockException(String message) {
        super(message);
    }

    public CustomOptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
