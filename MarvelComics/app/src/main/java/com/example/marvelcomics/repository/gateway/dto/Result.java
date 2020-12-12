package com.example.marvelcomics.repository.gateway.dto;

public abstract class Result<T> {
    private Result() {
    }

    public static final class Success<T> extends Result<T> {
        private final T data;

        public Success(T data) {
            this.data = data;
        }

        public T get() {
            return data;
        }
    }

    public static final class Error<T> extends Result<T> {
        private final ErrorResponse error;

        public Error(ErrorResponse error) {
            this.error = error;
        }

        public ErrorResponse get() {
            return error;
        }
    }
}
