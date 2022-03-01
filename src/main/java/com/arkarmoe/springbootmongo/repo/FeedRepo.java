package com.arkarmoe.springbootmongo.repo;

import com.arkarmoe.springbootmongo.entity.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepo extends MongoRepository<Feed,String> {
    List<Feed> findAllByStatusContainingIgnoreCaseOrderByCreatedAtDesc(String status);
    Page<Feed> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
