package com.rana.firstrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rana.firstrest.model.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

}
