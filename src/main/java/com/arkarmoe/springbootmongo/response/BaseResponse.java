package com.arkarmoe.springbootmongo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse {
    private int errorCode;
    private Object data;
    private String message;
}
