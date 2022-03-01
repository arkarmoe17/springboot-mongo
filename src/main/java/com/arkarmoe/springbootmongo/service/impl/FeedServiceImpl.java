package com.arkarmoe.springbootmongo.service.impl;

import com.arkarmoe.springbootmongo.entity.Feed;
import com.arkarmoe.springbootmongo.repo.FeedRepo;
import com.arkarmoe.springbootmongo.request.FeedReq;
import com.arkarmoe.springbootmongo.response.BaseResponse;
import com.arkarmoe.springbootmongo.service.FeedService;
import com.arkarmoe.springbootmongo.utils.CommonMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedServiceImpl implements FeedService {
    private final FeedRepo feedRepo;
    private final MongoTemplate mongoTemplate;

    @Override
    public BaseResponse fetchAllFeedLists() {
        List<Feed> feedList = feedRepo.findAll();
        return new BaseResponse(0, feedList, CommonMessage.SUCCESS);
    }

    @Override
    public BaseResponse fetchAllFeedListsWithPagination(int offset, int limit) {
        Page<Feed> feedPage = feedRepo.findAllByOrderByCreatedAtDesc(PageRequest.of(offset, limit));
        return new BaseResponse(0, feedPage, CommonMessage.SUCCESS);
    }

    @Override
    public BaseResponse createFeed(FeedReq req) {
        Feed feed = new Feed();
        feed.setUserId(req.getUserId());
        feed.setStatus(req.getStatus());
        feed.setCreatedAt(new Date());
        feedRepo.save(feed);
        return new BaseResponse(0, feed, CommonMessage.SUCCESS);
    }

    @Override
    public BaseResponse findById(String id) {
        Optional<Feed> feedOptional = feedRepo.findById(id);
        return new BaseResponse(0, feedOptional.orElse(null), CommonMessage.SUCCESS);
    }

    @Override
    public BaseResponse updateFeed(String feedId, FeedReq req) {
        Optional<Feed> feedOptional = feedRepo.findById(feedId);
        if (!feedOptional.isPresent())
            return new BaseResponse(1, null, "Feed id is not found.");
        Feed feed = feedOptional.get();
        feed.setStatus(req.getStatus());
        feed.setUpdatedAt(new Date());
        feedRepo.save(feed);
        return new BaseResponse(0, feed, CommonMessage.SUCCESS);
    }

    @Override
    public BaseResponse findAllByStatus(String status) {
        List<Feed> feedList = feedRepo.findAllByStatusContainingIgnoreCaseOrderByCreatedAtDesc(status);
        return new BaseResponse(0, feedList, CommonMessage.SUCCESS);
    }

    @Override
    public BaseResponse findAllPaginationByStatus(String status, int offset, int limit) {
        final Pageable pageable = PageRequest.of(offset, limit);
        Query query = new Query();
        query.addCriteria(Criteria.where("status").regex(status));
        query.with(pageable);
        List<Feed> feedList = mongoTemplate.find(query, Feed.class);
        long count = mongoTemplate.count(query.skip(-1).limit(-1), Feed.class);
        Page<Feed> feedPage = new PageImpl<Feed>(feedList, pageable, count);
        return new BaseResponse(0, feedPage, CommonMessage.SUCCESS);
    }

    @Override
    public BaseResponse deleteById(String id) {
        Optional<Feed> feedOptional = feedRepo.findById(id);
        if (!feedOptional.isPresent())
            return new BaseResponse(1, null, "Feed id is not found.");
        feedRepo.deleteById(id);
        return new BaseResponse(0, null, CommonMessage.SUCCESS);
    }

    @Override
    public BaseResponse likeFeed(String id) {
        Feed feed = mongoTemplate.findById(id, Feed.class);
        if (feed == null)
            return new BaseResponse(1, null, "Feed id is not found.");
        feed.setLikedCount(feed.getLikedCount() == null ? 1 : feed.getLikedCount() + 1);
        mongoTemplate.save(feed);
        return new BaseResponse(0, feed, CommonMessage.SUCCESS);
    }
}
