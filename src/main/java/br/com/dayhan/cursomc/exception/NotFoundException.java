package br.com.dayhan.cursomc.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -829886630418123452L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
