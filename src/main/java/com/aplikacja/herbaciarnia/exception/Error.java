package com.aplikacja.herbaciarnia.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Error {

    public final String message;

    public Error(String message) {
        this.message = message;
    }
}
