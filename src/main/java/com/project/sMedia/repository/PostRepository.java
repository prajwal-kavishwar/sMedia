package com.project.sMedia.repository;

import com.project.sMedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Long> {

}
