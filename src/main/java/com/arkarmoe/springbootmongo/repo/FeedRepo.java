package com.arkarmoe.springbootmongo.repo;

import com.arkarmoe.springbootmongo.entity.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepo extends MongoRepository<Feed,String> {
}
