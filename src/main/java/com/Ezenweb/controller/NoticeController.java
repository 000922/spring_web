package com.Ezenweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    /*@Autowired
    private NoticeService noticeService = new NoticeService();*/

    // 1. 오픈
    /*@GetMapping("/Nolist")
    public Resource getlist(){ return new ClassPathResource("templates/notice/Nolist.html");}*/
    // 비회원 게시물 열기
    @GetMapping("/Nolist")
    public Resource getlist(){ return new ClassPathResource("templates/notice/Nolist.html"); }
    // 비회원 게시물 쓰기 페이지
    @GetMapping("/Nowrite")
    public Resource getwrite(){ return new ClassPathResource("templates/notice/Nowrite.html"); }
    // 조회
    @GetMapping("/Noview")
    public Resource getview(){ return new ClassPathResource("templates/notice/Noview.html"); }
    // t





}
