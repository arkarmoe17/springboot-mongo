package com.arkarmoe.springbootmongo.service.impl;

import com.arkarmoe.springbootmongo.entity.Feed;
import com.arkarmoe.springbootmongo.repo.FeedRepo;
import com.arkarmoe.springbootmongo.request.FeedReq;
import com.arkarmoe.springbootmongo.response.BaseResponse;
import com.arkarmoe.springbootmongo.service.FeedService;
import com.arkarmoe.springbootmongo.utils.CommonMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedServiceImpl implements FeedService {
    private final FeedRepo feedRepo;

    @Override
    public BaseResponse fetchAllFeedLists() {
        List<Feed> feedList = feedRepo.findAll();
        return new BaseResponse(0,feedList, CommonMessage.SUCCESS);
    }

    @Override
    public BaseResponse createFeed(FeedReq req) {
        Feed feed = new Feed();
        feed.setUserId(req.getUserId());
        feed.setStatus(req.getStatus());
        feed.setCreatedAt(new Date());
        feedRepo.save(feed);
        return new BaseResponse(0,feed,CommonMessage.SUCCESS);
    }

    @Override
    public BaseResponse findById(String id) {
        Optional<Feed> feedOptional = feedRepo.findById(id);
        return new BaseResponse(0, feedOptional.orElse(null),CommonMessage.SUCCESS);
    }
}
