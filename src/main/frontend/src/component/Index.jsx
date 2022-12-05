// jsx : 리액트 확장 표현식 파일
// 컴포넌트 단위 애플리케이션 제작
// SPA : 싱글 페이지 애플리케이션 [ 페이지는 하나 ]
    // 라우터
// 컴포넌트 만들기 준비물
    // 1. 첫글자는 대문자 [ 컴포넌트명 == 파일명 ]
    // 2. 리액트 [ 프레임워크가 아니다 ] 라이브러리 집합소 [import 많다 ]
        // 1. import React from 'react';
        // 2. function 컴포넌트명() { return (렌더링할 코드 ) ; }
        // 3. export default 컴포넌트명 ;
            // 2,3 : export default function 컴포넌트명(){ return (렌더링할 코드); }

// 1.
import React from 'react';
import Header from './Header';
import Home from './Home';
import Footer from './Footer';
import Signup from './member/Signup';
import Login from './member/Login';
import BoardList from './board/BoardList';
// 라이터 설치 [ 터미널] : npm i react-router-dom
    // import { 컴포넌트명 } from 'react-router-dom';
import { HashRouter, BrowserRouter , Routes , Route , Link , Router } from 'react-router-dom';
    // BrowserRouter : 가상 URL
    // VS // HashRouter :
    // Routes : Route 목록/리스트
    // Route :  가상 URL 만들기 --> 해당 URL 에 따른 컴포넌트 렌더링 [ SPA ]
    // Link :   <---> a 태그 유사 : 하이퍼링크
        // Link to = "Route URL" 가상 경로
    // Router

// 2.
export default function Index( props ){
     return  (
            <div className="webbox">
                <BrowserRouter>
                    <Header/>
                        <h3>메인페이지</h3>
                    <Footer/>
                    <Routes>
                        <Route path="/" element = { <Home/> } />
                        <Route path="/member/signup" element={ <Signup/> }/>
                        <Route path="/member/login" element={ <Login/> }/>
                        <Route path="/board/list" element={ <BoardList/> }/>
                    </Routes>
                </BrowserRouter>
            </div>
        );
    }



