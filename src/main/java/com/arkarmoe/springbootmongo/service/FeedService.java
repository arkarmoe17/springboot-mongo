package com.arkarmoe.springbootmongo.service;

import com.arkarmoe.springbootmongo.request.FeedReq;
import com.arkarmoe.springbootmongo.response.BaseResponse;

public interface FeedService {
    BaseResponse fetchAllFeedLists();
    BaseResponse createFeed(FeedReq req);
    BaseResponse findById(String id);
}
