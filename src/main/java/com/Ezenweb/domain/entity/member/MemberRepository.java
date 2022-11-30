package com.Ezenweb.domain.entity.member;

import com.Ezenweb.domain.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 해당 인터페이스가 리포지토리 임을 명시
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {
                                // extends JpaRepository < 메핑할클래스명 , @ID필드의 자료형 >

    Optional<MemberEntity> findByMemail(String memail );
}
