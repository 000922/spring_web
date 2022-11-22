package com.Ezenweb.domain.entity.notice;


import com.Ezenweb.domain.dto.NoticeDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.member.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table( name = "notice")
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder @ToString // 롬복
public class NoticeEntity extends BaseEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)

    private int bno;                // 번호
    @Column( nullable = false )
    private String btitle;          // 제목
    @Column( nullable = false , columnDefinition = "TEXT")
    private String bcontent;        // 내용
    @Column( nullable = false )
    @ColumnDefault( "0" )
    private int bview;              // 조회수
    @Column( nullable = false )
    private String bfile;           // 첨부파일

    // 연관
    @ManyToOne
    @JoinColumn(name = "mmno")
    @ToString.Exclude
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name="mbcno")
    @ToString.Exclude
    private BcategoryEntity bcategoryEntity;

    // 형변환
    public NoticeDto toDto(){
        return NoticeDto.builder()
                .bno( this.bno)
                .btitle( this.btitle)
                .bcontent( this.bcontent)
                .bview( this.bview)
                .bfile( this.bfile)
                .build();
    }
}
