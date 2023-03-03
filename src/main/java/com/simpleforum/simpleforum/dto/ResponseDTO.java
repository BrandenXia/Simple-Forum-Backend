package com.simpleforum.simpleforum.dto;

import lombok.Builder;

@Builder
public class ResponseDTO {
    private int status;

    private String message;

    private Object data;

    private String error;
}
