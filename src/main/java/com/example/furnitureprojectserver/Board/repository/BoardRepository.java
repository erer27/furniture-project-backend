package com.example.furnitureprojectserver.Board.repository;

import com.example.furnitureprojectserver.Board.model.Post;
import com.example.furnitureprojectserver.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BoardRepository extends JpaRepository<Post, Long> {
    public List<Post> findAllByWriter(Member writer);

    public List<Post> findTop16ByWriterAndTitleContainingOrderByCreatedDateDesc
            (Member writer,String keyword);

    public List<Post> findTop16ByWriterAndTitleContainingAndCreatedDateLessThanOrderByCreatedDateDesc
            (Member member, String keyword, Date createdDate);

    @Query("SELECT p FROM Post p WHERE p.postId IN :postId")
    public List<Post> findByPostIdIn(List<Long> postId);
}
