package com.antsix.domain.repository;

import com.antsix.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<User,Long> {
    User findByName(String name);
}
