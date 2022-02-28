package com.arkarmoe.springbootmongo.controller;

import com.arkarmoe.springbootmongo.request.FeedReq;
import com.arkarmoe.springbootmongo.response.BaseResponse;
import com.arkarmoe.springbootmongo.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @GetMapping("/lists")
    public BaseResponse fetchAllFeeds(){
        return feedService.fetchAllFeedLists();
    }

    @PostMapping()
    public BaseResponse createNewFeed(@RequestBody FeedReq req){
        return feedService.createFeed(req);
    }

    @GetMapping("/{id}")
    public BaseResponse findById(@PathVariable("id")String id){
        return feedService.findById(id);
    }
}
