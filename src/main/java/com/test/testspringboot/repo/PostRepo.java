package com.test.testspringboot.repo;

import com.test.testspringboot.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post, Long> {
}
