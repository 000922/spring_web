package com.Ezenweb.controller.test;

import com.Ezenweb.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// p.56
@RestController
@RequestMapping("api/v1/get-api")   // 요청 URL 정의하ㅣ
public class GetController {
    // 요청시 해당 클래스 접근 [호출/요청] http://localhost:8081/api/v1/get-api
    // 해당 클래스내 메소드 호출 : http://localhost:8081/api/v1/get-api/hello

    // 1. p.57
    @RequestMapping(value = "/hello", method = RequestMethod.GET)   // get 요청
    public String getHello() {   //함수명 [ 아무거나 / 중복X ]
        return "Hello World";   // response 응답
    }
    // @RequestMapping
    // GetMapping
    // @RequestMapping( value = "/hello", method = RequestMethod.GET ) // get 요청
    // PostMapping
    // PutMapping
    // DeleteMapping

    // 2. p.58
    @GetMapping("/name")    // http://localhost:8081/api/v1/get-api/name
    public String getName() {
        return "Flature";
    }

    // 3. p.59
    // 주의 : @GetMapping 경로상의 변수명 [ {variable} ] 과   // http://localhost:8081/api/v1/get-api/variable1/test
    @GetMapping("/variable1/{variable}")    // 경로상의 변수[값]
    public String getVariable1(@PathVariable String variable) {
        return variable;
    }

    // 4. p.60
    @GetMapping("/variable2/{variable}")    // http://localhost:8081/api/v1/get-api/variable2/test2
    public String getVariable2(@PathVariable(value = "variable") String test) {
        return test;
    }
    // [변수1개]
    // VS
    // [ 변수여러개 => 키값 ]


    // 4-2 [ 비교 ]
    @GetMapping("/variable3/")
    public String getVariable3(@PathVariable String variable) {
        return variable;
    }

    // 5. p.61
    @GetMapping("/requst1") // ?변수명=값 & 변수명2 = 값2 & 변수명3 = 값3  // 뛰어쓰기 금지
    public String getRequstParam1(
            @RequestParam String name, @RequestParam String email, @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }
    // http://localhost:8081/api/v1/get-api/variable1/requst1?name=qwe&email=qwe@qwe&organization=qweqwe

    // p.62
    @GetMapping("/requst2")     // http://localhost:8081/api/v1/get-api/requst2?key1=value1&key2=value2
    public String getRequstParam2(@RequestParam Map< String , String > param ){ return param.toString(); }
    /*
        JAVA 컬렉션 프레임워크
            1. list : 인덱스[중복가능] , set: 인덱스x[중복불가능] , map:인덱스x[ 키 : 값 ] 엔트리 사용
        js
            1. JSON
    */

    // 7.p.66
    @GetMapping("/requst3")     // http://localhost:8081/api/v1/get-api/requst3?name=value1&email=value2&organization=value3
    public String getRequstParam3(MemberDto memberDto ){
        return memberDto.toString();
    }


}


