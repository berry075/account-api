package com.berry.account.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse {

    private final int code;
    private final String message;
    private Object data;

    public SuccessResponse(SuccessCode successCode) {
        this.code = successCode.getSuccessCode();
        this.message = successCode.getSuccessMessage();
    }

    public SuccessResponse(SuccessCode successCode, Object data) {
        this(successCode);
        this.data = data;
    }
}
