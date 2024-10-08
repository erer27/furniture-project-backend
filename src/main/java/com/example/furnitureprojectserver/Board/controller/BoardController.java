package com.example.furnitureprojectserver.Board.controller;


import com.example.furnitureprojectserver.Board.model.Post;
import com.example.furnitureprojectserver.Board.model.getCardListParam;
import com.example.furnitureprojectserver.Board.service.BoardService;
import com.example.furnitureprojectserver.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @RequestMapping(value="/test", method = {RequestMethod.GET, RequestMethod.POST})
    public String test(){
        System.out.println("test");
        return "test";
    }

    @RequestMapping(value="/savePost", method = {RequestMethod.GET, RequestMethod.POST})
    public Post savePost(@RequestBody Post post){
        return boardService.savePost(post);
    }

    @RequestMapping(value="/deletePost", method = {RequestMethod.GET, RequestMethod.POST})
    public void deletePost(@RequestBody Post post){
        boardService.deletePost(post);
    }

    @RequestMapping(value="/cardList", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Post> getCardList(@RequestBody Member member){
        return boardService.getCardList(member);
    }

    @RequestMapping(value="/firstPageCardList", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Post> getFirstPageCardList(@RequestBody getCardListParam param){
        return boardService.getFirstPageCardList(param);
    }

    @RequestMapping(value="/nextPageCardList", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Post> getNextPageCardList(@RequestBody getCardListParam param){
        return boardService.getNextPageCardList(param);
    }

    @RequestMapping(value="/cardImage", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Resource> getCardImage(@RequestBody Map<String, String> payload) throws IOException {
        return boardService.getCardImage(payload.get("imageName"));
    }

    @RequestMapping(value="/captureCardImage", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> captureCardImage(@RequestParam("file") MultipartFile file, @RequestParam("postId") Long postId, @RequestParam(value="imageName", required = false) String imageName) {
        return boardService.saveCardImage(file, postId, imageName);
    }


}
