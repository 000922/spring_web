package com.Ezenweb.service;

import com.Ezenweb.domain.dto.MemberDto;
import com.Ezenweb.domain.dto.OauthDto;
import com.Ezenweb.domain.entity.member.MemberEntity;
import com.Ezenweb.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service // 헤당 클래스 service 임을 명시
public class MemberService implements
        UserDetailsService ,
        OAuth2UserService< OAuth2UserRequest , OAuth2User> {

    @Override // 로그인 성공한 소셜 회원 정보 받는 메소드
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
                                        // userRequest 인증 결과 요청변수
        System.out.println("1. userRequest: " + userRequest.toString() );

        // 1. 인증[로그인] 결과 정보 요청
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser( userRequest );
            System.out.println("2. oauth2user: " + oAuth2User.toString() );

        // 2. oauth2 클라이언트 식별 [ 카카오 vs 네이버 vs 구글 ]
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("3. oauth2회사명 : "+ registrationId );

        // 3. 회원정보 담는 객체 [JSON 형태 ]
        String oauth2UserInfo = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        System.out.println("4.회원정보 : "+ oauth2UserInfo );
        System.out.println("5.인증 결과 :" + oAuth2User.getAttributes() );
        // 4. 요청 정보 원본
       // oAuth2User.getAuthorities();

        // 4. Dto 처리
        OauthDto oauthDto = OauthDto.of( registrationId , oauth2UserInfo , oAuth2User.getAttributes() );

        // * DB처리
        // 1. 이메일로 엔티티 검색 [ 가입 or 기존회원 구분 ]
        Optional< MemberEntity >optional = memberRepository.findByMemail( oauthDto.getMemail() );

        MemberEntity memberEntity = null; //
        if( optional.isPresent() ){ // 기존회원이면 // optional 클래스 [null 예외처리 방지 ]
            memberEntity = optional.get();
        }else{  // 기존회원이 아니면 [ 가입 ]
           memberEntity = memberRepository.save( oauthDto.toEntity() );
        }

        // 권한부여
        Set<GrantedAuthority> authorities   = new HashSet<>();
        authorities.add( new SimpleGrantedAuthority( memberEntity.getMrol() ) );
        // 5. 반환
        MemberDto memberDto = new MemberDto();
            memberDto.setMemail( memberEntity.getMemail() );
            memberDto.setAuthorities( authorities );
            memberDto.setAttributes( oauthDto.getAttributes());

        return memberDto;
    }



    // --------- 전역 객체 -------------- //
    @Autowired
    private MemberRepository memberRepository;
    @Autowired // 스프링 컨테이너 [메모리] 위임
    private HttpServletRequest request;         // 요청 객체
    @Autowired
    private JavaMailSender javaMailSender;  // 메일전송 객체


    // -------------------------------- 서비스 -----------------------//

    // * 로그인된 엔티티 호출
    public MemberEntity getEntity(){
        // 1. 로그인 정보 확인[세션 =  loginMno ]
        Object object = request.getSession().getAttribute("loginMno");
        if (object == null){ return null; }
        // 2. 로그인된 회원정보 호출
        int mno = (Integer)object;
        // 3. 회원번호 --> 회원정보 호출
        Optional<MemberEntity> optional = memberRepository.findById(mno);
        if( !optional.isPresent() ){return null;}
        // 4. 로그인된 회원의 엔티티 움티티
        return optional.get();
    }
    // 1. 회원가입 [ 일반 ]
    @Transactional
    public int setmember(MemberDto memberDto) {
        // 암호화 : 해시함수 사용하는 암호화 기법중 하나 [ BCrypt ]
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setMpassword( passwordEncoder.encode( memberDto.getPassword() ) );

        // 1. Dao 처리
        MemberEntity entity = memberRepository.save(memberDto.toEntity() );


        // 회원등급 넣어주기
        entity.setMrol("user");


        // 2. 결과 반환
        return entity.getMno();
    }
    // 2. 로그인 [ 시큐리티 사용시 필요없음 ]
    /*@Transactional
    public int getmember(MemberDto memberDto){
        List<MemberEntity> entityList = memberRepository.findAll();
        // 2. 입력받은 데이터와 일치값 찾기
        for( MemberEntity entity : entityList ){
            if( entity.getMemail().equals(memberDto.getMemail())){
                if( entity.getMpassword().equals(memberDto.getMpassword())){
                    // 세션 부여 [ 로그인 성공시 ]
                    request.getSession().setAttribute("loginMno" , entity.getMno() );
                    return  1;
                }else{
                    return 2;
                }
            }
        }
        return 0; // 아디 틀림
    }*/

    // 2. [시큐리티 사용시 ] 로그인 인증 메소드


    // 2. [ 시큐리티 사용시 ] 로그인 인증 메소드 재정의
    @Override
    public UserDetails loadUserByUsername(String memail ) throws UsernameNotFoundException {
        // 1. 입력받은 아이디 [ memail ] 로 엔티티 찾기
        MemberEntity memberEntity = memberRepository.findByMemail( memail )
                .orElseThrow( ()-> new UsernameNotFoundException("사용자가 존재하지 않습니다,") );  // .orElseThrow : 검색 결과가 없으면 화살표함수[람다식]를 이용한

        // 2. 검증된 토큰 생성
        Set<GrantedAuthority>  authorities = new HashSet<>();
        authorities.add(
                new SimpleGrantedAuthority(memberEntity.getMrol() ) ); // 토큰정보에 일반회원 내용 넣기
        // 3.
        MemberDto memberDto = memberEntity.toDto();                     // 엔티티 --> Dto
        memberDto.setAuthorities( authorities );                        // dto --> 토큰 추가
        System.out.println( memberDto );
        return memberDto;

    }

    // 3. 비밀번호 찾기
    @Transactional
    public String getpassword( String memail) {
        // 1. 모든 레코드/엔티티 꺼내온다.
        List<MemberEntity> entityList
                = memberRepository.findAll();
        // 2. 리스트 찾기
        for (MemberEntity entity : entityList) {
            if (entity.getMemail().equals(memail)) {
                return entity.getMpassword();
            }
        }
        return null;
    }
    // 4. 회원탈퇴
    @Transactional
    public int setdelete( String mpassword ){
        // 1. 로그인된 회원의 엔티티 필요!!
        // 1. 세션 호출
        Object object =  request.getSession().getAttribute("loginMno");
        // 2. 세션 확인
        if( object != null ){ // 만약에 세션이 null 이 아니면 로그인 됨
            int mno = (Integer) object; // 형변환 [ object --> int ]
            // 3. 세션에 있는 회원번호[PK] 로 리포지토리 찾기 [ findById : select * from member where mno = ? ]
            Optional< MemberEntity > optional =  memberRepository.findById(mno);
            if( optional.isPresent() ){ // optional객체내 엔티티 존재 여부 판단
                // Optional 클래스 : null 관련 메소드 제공
                // 4.Optional객체에서 데이터[엔티티] 빼오기
                MemberEntity entity = optional.get();
                // 5. 탈퇴 [ delete : delete from member where mno = ? ]
                memberRepository.delete( entity );
                // 6. 세션 [ 세션삭제 = 로그아웃 ]
                request.getSession().setAttribute("loginMno" , null);
                return 1;
            }
        }
        return 0; // [ 만약에 세션이 null 이면 반환 o 혹은 select 실패시   ]
    }
    // 5. 회원 수정
    @Transactional // 데이터 수정[update]시 필수 ~~
    public int setupdate( String mpassword ){
        // 1. 세션 호출
        Object object = request.getSession().getAttribute("loginMno");
        // 2. 세션 존재여부 판단
        if( object != null ){
            int mno = (Integer)object;
            // 3. pk값을 가지고 엔티티[레코드] 검색
            Optional<MemberEntity> optional
                    =  memberRepository.findById( mno );
            // 4. 검색된 결과 여부 판단
            if( optional.isPresent() ){ // 엔티티가 존재하면
                MemberEntity entity = optional.get();
                // 5. 찾은 엔티티[레코드]의 필드값 변경 [ update member set 필드명 = 값  where 필드명 = 값 ]
                entity.setMpassword( mpassword );
                return  1 ;
            }
        }
        return 0;
    }
    // 6. 로그인 여부 판단 메소드
    /*public int getloginMno(){
        // 1. 세션 호출
        Object object= request.getSession().getAttribute("loginMno");
        System.out.println(object);
        System.out.println( (Integer) object);
        // 2. 세션 여부 판단
        if (object != null) { return (Integer) object; }
        else { return 0; }
    }*/
    // 6. 로그인 여부 판단
    public String getloginMno(){
        // 1. 인증된 토큰 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 2. 인증된 토큰 내용 확인
        Object principal = authentication.getPrincipal();
        System.out.println("토큰 내용확인 : " + principal);
        // 3. 토큰 내용에 따른 제어
        if( principal.equals("anonymousUser") ){    // anonymousUser 이면 로그인 전
            return null;
        }else{  // anonymousUser 아니면 로그인후
            MemberDto memberDto = (MemberDto) principal;
            return memberDto.getMemail()+"_"+memberDto.getAuthorities();
        }
    }




    // 7. 로그아웃
   /* public void logout(){
        request.getSession().setAttribute("loginMno" , null);
    }*/

    // 8. 회원목록
    public List<MemberDto> list(){
       List<MemberEntity> list = memberRepository.findAll();
       // 2. 엔티티 --> DTO
            // Dto list 선언
        List<MemberDto> dtoList = new ArrayList<>();
        for (MemberEntity entity : list){
            dtoList.add( entity.toDto() );
        }
        return dtoList;
    }

    // 9. 인증코드 발송
    public String getauth( String toemail){

        String auth = "";  // 인증코드
        String html = "<html><body><h1> EZENWEB 회원 가입 이메일 인증코드 입니다. </h1>";

        Random random = new Random();   // 1. 난수 객체
        for( int i = 0 ; i<6 ; i++){    // 2. 6 회전
            char randcher =(char)(random.nextInt(26)+97);  // 97~122 : 알파벳 소문자
            // char randcher = random.nextInt((10)+48);  // 48~57   : 0~9
            auth += randcher;
        }

        html += "<div>인증코드 : "+auth+"</div>";
        html += "</body> </html>";
        meailsend( toemail , "EzenWeb 인증코드" , html );   // 메일전송
        return auth; // 인증코드 반환
    }

    // *. 메일 전송 서비스
    public void meailsend( String toemail , String title , String content ){
        try {
            // 1. Mime 프로토콜 객체 생성
            MimeMessage message = javaMailSender.createMimeMessage();
            // 2. Mime 설정
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "utf-8");
            // 3. 보내는 사람 정보
            mimeMessageHelper.setFrom("dlxownswnsdn@naver.com", "EzenWeb");
            //4. 받는 사람
            mimeMessageHelper.setTo(toemail);
            // 5. 메일 제목
            mimeMessageHelper.setSubject(title);
            // 6. 메일 내용
            mimeMessageHelper.setText(content.toString(), true);   // html 형식
            // 7. 메일 전송
            javaMailSender.send(message);
        }catch (Exception e){System.out.println( "메일전송 실패 : "+e ); }
    }



} // END


/*
        메일 전송
        1. 라이브러리 : implementation 'org.springframework.boot:spring-boot-starter-mail'
        2. 메일 보내는 사람
        네이버rlwns
                1. 네이버로그인 -> 메일 -> 환경설정
                2. POP3/IMAP 설정 -> 사용험
                3. host , port 등 정보 작성
        3. 메소드 작성
*/

