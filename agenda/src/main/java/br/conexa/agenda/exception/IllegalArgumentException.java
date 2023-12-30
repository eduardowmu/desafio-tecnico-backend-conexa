package br.conexa.agenda.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class IllegalArgumentException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long timeStamp;
    private Integer status;
    private String message;

    public IllegalArgumentException(String message) {
        this.message = message;
    }

    public IllegalArgumentException(Long timeStamp, Integer status, String message) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.message = message;
    }
}