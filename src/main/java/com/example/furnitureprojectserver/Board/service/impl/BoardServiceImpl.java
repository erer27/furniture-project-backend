package com.example.furnitureprojectserver.Board.service.impl;

import com.example.furnitureprojectserver.Board.model.Post;
import com.example.furnitureprojectserver.Board.model.getCardListParam;
import com.example.furnitureprojectserver.Board.repository.BoardRepository;
import com.example.furnitureprojectserver.Board.service.BoardService;
import com.example.furnitureprojectserver.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.imgscalr.Scalr;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    @Override
    public Post savePost(Post post) {
        return boardRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        boardRepository.deleteById(post.getPostId());

        String srcFileName = null;
        String absolutePath = new File("").getAbsolutePath() + "\\images\\";
        String imagePath = absolutePath + post.getCardImageName();
        new File(imagePath).delete();

    }
    @Override
    public List<Post> getCardList(Member member) {
        return boardRepository.findAllByWriter(member);
    }

    @Override
    public List<Post> getFirstPageCardList(getCardListParam param) {
        String keyword = param.getKeyword()==null?"": param.getKeyword();
        return boardRepository.findTop16ByWriterAndTitleContainingOrderByCreatedDateDesc(param.getMember(),keyword);
    }

    @Override
    public List<Post> getNextPageCardList(getCardListParam param) {
        Post post = param.getPost();
        String keyword = param.getKeyword()==null?"": param.getKeyword();
        return boardRepository.findTop16ByWriterAndTitleContainingAndCreatedDateLessThanOrderByCreatedDateDesc(post.getWriter(),keyword, post.getCreatedDate());
    }

    @Override
    public ResponseEntity<Resource> getCardImage(String imageName) throws  IOException {

        String absolutePath = new File("").getAbsolutePath() + "\\images\\";
        Resource resource = new InputStreamResource(Files.newInputStream(Path.of(absolutePath + imageName)));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/octect-stream")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+resource.getFilename()+";")
                .body(resource);
    }

    @Override
    public ResponseEntity<String> saveCardImage(MultipartFile file, Long postId, String imageName) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file!", HttpStatus.BAD_REQUEST);
        }
        try {
            //클라이언트에서 받은 postId로 post 찾기
            Optional<Post> optionalPost = boardRepository.findById(postId);
            Post post;
            if(optionalPost.isPresent()){
                post = optionalPost.get();
            }else{
                throw new IOException();
            }

            //파일 저장하기
            String fileName = imageName==null ? UUID.randomUUID() + ".png" : post.getCardImageName().split("\\.")[0] + ".png";
            BufferedImage bi= ImageIO.read(file.getInputStream());
            bi=Scalr.resize(bi, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, bi.getWidth()/2, bi.getHeight()/2, Scalr.OP_ANTIALIAS);
            String absolutePath = new File("").getAbsolutePath() + "\\images\\";

            String imagePath = absolutePath + fileName;
            ImageIO.write(bi,"png",new File(imagePath));

            //저장 성공하면 Post 엔티티에 이미지 이름 추가해서 저장하기
            post.setCardImageName(fileName);
            boardRepository.save(post);
            
            return new ResponseEntity<>("File uploaded successfully: " + file.getOriginalFilename(), HttpStatus.OK);
            
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
