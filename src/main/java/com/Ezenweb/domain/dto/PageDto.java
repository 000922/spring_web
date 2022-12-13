package com.Ezenweb.domain.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class PageDto {

    private int bcno;       // 카테고리
    private int page ;      // 현재 페이지
    private String key;     // 검색 필드
    private String keyword; // 검색 단어
    @Builder.Default // 빌더 사용시 현재 객체가 기본적으로 할당
    private List<BoardDto> list = new ArrayList<BoardDto>();    // 검색된 결과 게시물 리스트
    private Long totalBoards;   // 총 게시물수 // .getTotalElements() 메소드가 반환타입 LONG

}
