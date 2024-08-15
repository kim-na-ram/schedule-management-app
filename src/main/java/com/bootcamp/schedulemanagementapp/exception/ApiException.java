package com.bootcamp.schedulemanagementapp.exception;

import com.bootcamp.schedulemanagementapp.constants.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final String resultMessage;
    private final HttpStatus httpStatus;

    public ApiException(ResponseCode responseCode){
        super(responseCode.getResponseMessage());
        this.resultMessage = responseCode.getResponseMessage();
        this.httpStatus = responseCode.getHttpStatus();
    }
}