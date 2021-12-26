package com.guwave.onetest.bff.config;

import com.guwave.onetest.common.BaseResponse;
import com.guwave.onetest.common.exception.BaseRuntimeException;
import com.guwave.onetest.common.exception.SystemException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2(topic = "GlobalExceptionHandler")
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<BaseResponse<Void>> handleBaseRuntimeException(BaseRuntimeException e) {
        log.info(e);
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(BaseResponse.failure(e.code, e.getMessage()));
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<BaseResponse<Void>> handleSystemException(SystemException e) {
        log.error(e);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(BaseResponse.failure(e.code, e.getMessage()));
    }
}
