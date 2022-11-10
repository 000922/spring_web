package com.Ezenweb.domain.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class MemberDto {

    private String name;
    private String email;
    private String organization;

}
