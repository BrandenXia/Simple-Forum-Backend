package com.simpleforum.simpleforum.controller;

import com.simpleforum.simpleforum.dto.ResponseDTO;
import com.simpleforum.simpleforum.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(RequestBodyException.class)
    public ResponseDTO handleRequestBodyException(RequestBodyException e) {
        return ResponseDTO.builder()
                .status(400)
                .error("RequestBodyException: " + e.getMessage())
                .build();
    }

    @ExceptionHandler(TokenException.class)
    public ResponseDTO handleTokenException(TokenException e) {
        return ResponseDTO.builder()
                .status(400)
                .error("TokenException: " + e.getMessage())
                .build();
    }

    @ExceptionHandler(PermissionException.class)
    public ResponseDTO handlePermissionException(PermissionException e) {
        return ResponseDTO.builder()
                .status(400)
                .error("PermissionException: " + e.getMessage())
                .build();
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseDTO handleAlreadyExistException(AlreadyExistException e) {
        return ResponseDTO.builder()
                .status(400)
                .error("AlreadyExistException: " + e.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseDTO handleNotFoundException(NotFoundException e) {
        return ResponseDTO.builder()
                .status(400)
                .error("NotFoundException: " + e.getMessage())
                .build();
    }
}
