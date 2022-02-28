package com.arkarmoe.springbootmongo.request;

import lombok.Data;

@Data
public class FeedReq {
    private Long userId;
    private String status;

}
