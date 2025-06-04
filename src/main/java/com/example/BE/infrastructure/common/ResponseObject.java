package com.example.BE.infrastructure.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject<T> {

    private boolean isSuccess = false;

    private HttpStatus status;

    private T data;

    private String message;

}
