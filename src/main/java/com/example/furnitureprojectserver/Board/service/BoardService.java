package com.example.furnitureprojectserver.Board.service;

import com.example.furnitureprojectserver.Board.model.Post;
import com.example.furnitureprojectserver.Board.model.getCardListParam;
import com.example.furnitureprojectserver.member.model.Member;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface BoardService {
    public Post savePost(Post post);

    public void deletePost(Post post);

    public List<Post> getCardList(Member member);

    public List<Post> getFirstPageCardList(getCardListParam param);

    public List<Post> getNextPageCardList(getCardListParam param);

    public ResponseEntity<Resource> getCardImage(String imageName) throws IOException;

    public ResponseEntity<String> saveCardImage(MultipartFile file, Long postId, String imageName);


}
