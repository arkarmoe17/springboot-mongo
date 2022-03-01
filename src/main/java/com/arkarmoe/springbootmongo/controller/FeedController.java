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
    public BaseResponse fetchAllFeeds() {
        return feedService.fetchAllFeedLists();
    }

    @GetMapping("/pagination-lists")
    public BaseResponse fetchAllFeedsByPagination(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                  @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return feedService.fetchAllFeedListsWithPagination(offset, limit);
    }

    @PostMapping()
    public BaseResponse createNewFeed(@RequestBody FeedReq req) {
        return feedService.createFeed(req);
    }

    @GetMapping("/{id}")
    public BaseResponse findById(@PathVariable("id") String id) {
        return feedService.findById(id);
    }

    @PutMapping("/{id}")
    public BaseResponse updateFeed(@PathVariable("id") String id, @RequestBody FeedReq req) {
        return feedService.updateFeed(id, req);
    }

    @GetMapping("/findAllBy")
    public BaseResponse findAllStatus(@RequestParam("status") String status) {
        return feedService.findAllByStatus(status);
    }

    @GetMapping("/findAllPaginationBy")
    public BaseResponse findAllPaginationBy(@RequestParam("status") String status,
                                            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return feedService.findAllPaginationByStatus(status, offset, limit);
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteById(@PathVariable("id") String id) {
        return feedService.deleteById(id);
    }

    @PostMapping("/like/{feedId}")
    public BaseResponse likeFeed(@PathVariable("feedId") String id) {
        return feedService.likeFeed(id);
    }


}
