package com.Ezenweb.domain.entity;

import com.Ezenweb.domain.dto.MemberDto;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity  // 해당연결된 DB의 테이블과 매핑[연결]
@Table(name="member") // db에서 사용될 테이블 이름
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class MemberEntity {
    // 1. 필드
    @Id // 엔티티당 무조건 1개이상[ PK ]
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // 자동번호 부여
    private int mno;

    @Column( nullable = false)  // not null for
    private String memail;      // 회원이메일 = 회원아이디 필드

    @Column( nullable = false)
    private String mpassword;      // 회원비밀번호

    @Column( nullable = false)
    private String mphone;      // 회원 전화번호 필드





    // 2. 생성자 [ 롬복으로 사용 ]
    // 3. 메소드 [ 롬복으로 사용 ]
    // 엔티티 --> DTO
    public MemberDto toDto(){
        return MemberDto
                .builder()
                .mno( this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mphone(this.mphone)
                .build();
    }
}