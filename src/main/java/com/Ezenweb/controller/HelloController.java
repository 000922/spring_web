package com.Ezenweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// p.49
@RestController // 현재클래스를 스프링의 RestController 사용
public class HelloController {
    // 클라이언트[사용자] 요청 , 응답 수행 공간
    // 실제 테이터 처리[가공] 이나 로직[기능] => DAO 또는 서비스 하자
    // JSP 패키기[폴더] HTTP URL VS Spring RESTFUL
    @RequestMapping("/hello")
    public String Hello(){ return "Hello Word"; }

}
