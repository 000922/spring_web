package com.Ezenweb.service;


import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.BoardEntity;
import com.Ezenweb.domain.entity.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // 컴포넌트 [Spring MVC]
public class BoardService {

    // ------------1.전역변수---------------//
    @Autowired
    private BoardRepository boardRepository;
        // @Transactional : 엔티티 수정시 사용되는 어노테이션
        // 1. 메소드
            /*

             */

    // ------------ 2. 서비스 ------------- //
    // 1. 게시물 쓰기
    @Transactional
    public boolean setboard( BoardDto boardDto ){
        // 1. dto --> entity [ INSERT ] 저장된 entity 반환
        BoardEntity entity  = boardRepository.save( boardDto.toEntity() );
        // 2. 생성된 entity의 게시물번호가 0 이 아니면  성공 ?
        if( entity.getMno() != 0 ){ return true; }
        // 3. 꽝
        else{ return false; }
    }

    // 2. 게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(){
        // 1.  모든 엔티티 호출한다.
        List<BoardEntity> elist = boardRepository.findAll();
        // 2. 컨트롤에게 전달할때 형변환 [ entity -> dto ] : 역할이 달라서
        List<BoardDto> dlist = new ArrayList<>();   // 메모리
        // 3. 변환
       for( BoardEntity entity : elist ){
           dlist.add( entity.toDto() );
       }
       // 4. 반환된 리스트 dlist반환
       return dlist;
    }
    // 3. 게시물 개별 조회
    @Transactional
    public BoardDto getboard( int bno ){
        // 1. 입력받은 게시물번호로 엔티티 검색 [ optional ]
        Optional<BoardEntity> optional = boardRepository.findById( bno );
        // 2. Optional 안에 있는 내용물 확인
        if(optional.isPresent() ){
            BoardEntity boardEntity = optional.get();
            return boardEntity.toDto();
        }else{
            return null;
        }
    }
    // 4. 게시물 삭제
    @Transactional
    public boolean delboard( int bno ){
        Optional<BoardEntity> optional = boardRepository.findById( bno);
        if( optional.isPresent() ){
            BoardEntity entity = optional.get();
            boardRepository.delete( entity );      // 찾은 엔티티를 삭제한다.
            return true;
        }else{ return false; }
    }

    // 5. 게시물 수정 [ 첨부파일 ]
    @Transactional
    public boolean upboard( BoardDto boardDto){
        // 1. DTO에서  수정할 PK번호 이용해서 엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById( boardDto.getBno() );
        // 2.
        if( optional.isPresent()){
            BoardEntity entity = optional.get();
            // 수정처리 [ 메소드 별도 존재x 엔티티 객체 <--매핑--> 레코드 / 엔티티 객체 필드를 수정]
            entity.setBtitle( boardDto.getBtitle());
            entity.setBcontent( boardDto.getBcontent());
            entity.setBfile( boardDto.getBfile() );
            return  true;
        }else{return false;}
    }

} // END
