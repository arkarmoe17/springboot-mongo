package com.arkarmoe.springbootmongo.service;

import com.arkarmoe.springbootmongo.request.FeedReq;
import com.arkarmoe.springbootmongo.response.BaseResponse;

public interface FeedService {
    BaseResponse fetchAllFeedLists();

    BaseResponse fetchAllFeedListsWithPagination(int offset, int limit);

    BaseResponse createFeed(FeedReq req);

    BaseResponse findById(String id);

    BaseResponse updateFeed(String feedId, FeedReq req);

    BaseResponse findAllByStatus(String status);

    BaseResponse findAllPaginationByStatus(String status, int offset, int limit);

    BaseResponse deleteById(String id);

    BaseResponse likeFeed(String id);
}
