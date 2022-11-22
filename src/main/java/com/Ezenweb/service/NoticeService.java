package com.Ezenweb.service;

import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.NoticeDto;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.notice.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class NoticeService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private NoticeService noticeService;

    // 1. 비회원 게시물 작성
    @Transactional
    public boolean setboard ( NoticeDto noticeDto){ }
    public boolean Nolist(){ }


}
