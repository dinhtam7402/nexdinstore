package com.nexdin.store.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDateTime;

@Value
public class Error<T> {
    int code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    LocalDateTime timestamp;
    String message;
    T detail;
}
