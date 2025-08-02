package com.rag.RagsJobPosts.exceptions;

public class DeletionFailedException extends RuntimeException {
    public DeletionFailedException(String message) {
        super(message);
    }
}
