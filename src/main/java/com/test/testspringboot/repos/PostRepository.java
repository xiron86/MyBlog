package com.test.testspringboot.repos;

import com.test.testspringboot.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
