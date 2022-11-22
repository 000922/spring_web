package com.Ezenweb.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
    AuditingEntityListener : 엔티티 감시[Auditing] 정보를 주입하는 JPA 엔티티 리스너 클래스
    @EntityListeners : 엔티티 정보 적용하기 전후로 콜백을 요청
 */


@Getter @Setter     // 롬복
@MappedSuperclass   // 상솓받을 경우 자식 클래스에게 매핑 정보 전달
@EntityListeners( AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate    // 데이터 생성 날짜를 자동 주입
    @Column( updatable = false )    // 수정 불가
    private LocalDateTime cdate;

    @LastModifiedDate   // 데이터 수정 날짜를 자동 주입
    private LocalDateTime udate;
}
