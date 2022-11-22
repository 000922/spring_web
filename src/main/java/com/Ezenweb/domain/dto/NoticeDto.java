package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.notice.NoticeEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class NoticeDto {
    private int bno;            // 게시물번호
    private String btitle;      // 게시물제목
    private String bcontent;    // 게시물 내용
    private int bview;          // 조회수
    private String bfile;       // 첨부파일
    private int bcno;            // 카테고리[ 카테고리-fk ]

    // 형변환
    public NoticeEntity toEntity(){
        return NoticeEntity.builder()
                .bno( this.bno)
                .btitle( this.btitle)
                .bcontent( this.bcontent)
                .bview( this.bview)
                .bfile( this.bfile)
                .build();

    }

}
