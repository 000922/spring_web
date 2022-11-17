package com.Ezenweb.controller.test;

import com.Ezenweb.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
// * p.70
@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    // 1. p.70
    @PutMapping(value = "member")
    public String putMember(@RequestBody Map<String , String> putData){
        return putData.toString();
    }


    // 2. p.71          반환타입 : 문자열[String]
    @PutMapping("/member1")
    public String postMemberDto(@RequestBody MemberDto memberDto ){
        return memberDto.toString();
    }
    // 2. p.72          반환타입: 객체[MemberDto ]
    @PutMapping("/member2")
    @ResponseBody
    public MemberDto postMemberDto2( @RequestBody MemberDto memberDto ){
        return memberDto;
    }
}
