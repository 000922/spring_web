package com.Ezenweb.domain.dto;

import lombok.*;
// 룸봅ㄱ : 생성자 , GET / SET , ToString, 빌더 패턴
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class BoardDto {

    private String btitle;
    private String bcontent;

}
