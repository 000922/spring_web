package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class BcategoryDto {
    private int bcno;
    private String bcname;

    public BcategoryEntity toEntity(){
        return BcategoryEntity
                .builder()
                .bcno( this.bcno )
                .bcname( this.bcname )
                .build();
    }


}
