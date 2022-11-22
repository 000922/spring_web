package com.Ezenweb.domain.entity.bcategory;

import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.board.BoardEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity@Table(name = "bcategory")
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter@ToString@Builder
public class BcategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bcno;       // 카테고리번호
    private String bcname;  // 카테고리이름
    @OneToMany( mappedBy = "bcategoryEntity")
    @Builder.Default
    private List<BoardEntity> boardEntityList
            =new ArrayList<>();

    public BcategoryDto toDto(){
        return BcategoryDto.builder()
                .bcno(this.bcno).bcname(this.bcname)
                .build();
    }


}
