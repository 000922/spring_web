package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // 해당 클래스가 컨트롤 목적 사용 @ResponseBody 생략
@RequestMapping("/board")
public class BoardController {

        // 컨트롤 역할 : HTTP 요청 / ModeLAndView 응답

        // --------- 1. 전역변수 ------------ //
        // 1. 서비스 메소드 호출 우한 객체 생성 [ 제어역전 ]
            // 1. 개발자가 new 사용해서 JVM 힙 메모리 할당해서 객체 생성
                // private BoardService boardService = new BoardService();
            // 2. @Autowired 어노테이션 이용해서 Spring 컨테이너에 빈[메모리] 생성
        @Autowired
        private  BoardService boardService = new BoardService();


        // --------2. 페이지[html] 요청 로드 [  view ] --------- //
        // 1. 게시물목록 열기
        @GetMapping("/list")     // URL : localhost:8081/board/list 요청시 해당 html 반환
        public Resource getlist(){ return new ClassPathResource("templates/board/list.html"); }
        // 2. 게시물쓰기 페이지 열기
        @GetMapping("/write")     // URL : localhost:8081/board/write 요청시 해당 html 반환
        public Resource getwrite(){ return new ClassPathResource("templates/board/write.html"); }
        // 3. 게시물조회 페이지 열기
        @GetMapping("/view")     // URL : localhost:8081/board/view 요청시 해당 html 반환
        public Resource getview(){ return new ClassPathResource("templates/board/view.html"); }
        // 4. 게시물수정 페이지 열기
        @GetMapping("/update")    // URL : localhost:8081/board/update 요청시 해당 html 반환
        public Resource getupdate(){ return new ClassPathResource("templates/board/update.html"); }

        // --------3.요청과응답 처리 [model] ----- //
        /*// 1. 게시물 쓰기 [ 첨부파일 없을때  ]
        @PostMapping("/setboard")
        public boolean setboard( @RequestBody BoardDto boardDto ){
                System.out.println( "확인 : " +boardDto.toString());
                return boardService.setboard( boardDto);
        }*/
        // 1. 게시물 쓰기 [ 첨부파일 있을때  ]
        @PostMapping("/setboard")
        public boolean setboard( BoardDto boardDto ){
                return boardService.setboard( boardDto);
        }

        // 2. 게시물 목록 조회 [ 페이징,검색 ]
        @GetMapping("/boardlist")
        public List<BoardDto> boardlist(@RequestParam("bcno") int bcno , @RequestParam("page") int page ) {
                return boardService.boardlist( bcno , page );
        }

        // 3. 게시물 개별 조회
        @GetMapping("/getboard")
        public BoardDto getboard( @RequestParam("bno") int bno ){
                return boardService.getboard( bno );
        }

        // 4. 게시물 삭제
        @DeleteMapping("/delboard")
        public boolean delboard( @RequestParam("bno") int bno ){
                return boardService.delboard( bno );
        }

        //5 . 게시물 수정
        @PutMapping("/upboard")
        public boolean upboard( BoardDto boardDto){  return boardService.upboard( boardDto );}

        // 6. 카테고리 등록
        @PostMapping("/setbcategory")
        public boolean setbcategory( @RequestBody BcategoryDto bcategoryDto){
                return boardService.setbcategory( bcategoryDto );
        }
        // 7. 모든 카테고리 출력
        @GetMapping("/bcategorylist")
        public List<BcategoryDto> bcategorylist(){
                return boardService.bcategorylist();
        }

        // 8. 첨부파일 다운로드
        @GetMapping("/filedownload")
        public void filedownload(@RequestParam("filename") String filename){
                boardService.filedownload(filename);
        }



} // class end
