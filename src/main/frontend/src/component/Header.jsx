// 1.
import React , {useState} from 'react';  // 컴포넌트 호출
import Styles from '../css/header.css'; // src -> css -> header.css
import logo from '../img/logo.png'  //  // 이미지 적용
import { BrowserRouter , Routes , Route , Link , Router} from 'react-router-dom';   // 라우타루 오늘 배운것 중에 제일 중요 *
import axios from 'axios'; // react 비동기 통신 라이브러리 [ npm i axios ]
// 2.
export default function Header(){
    const [ login , setLogin] = useState(null); // 로그인된 회원정보 state 생명주기
    // 1. 서버와 통신 [ axios ]
    axios.get("/member/getloginMno")
    .then( (response) => {setLogin( response.data); } )
    // axios.type( 'URL' ).then( res => {응답} )


    return (
        <div className="webbox">
            <div className="header">
                <div className="header_logo">
                     <Link to="/" > <img className="logo" src={logo} /> </Link>
                </div>
                <ul className="top_menu">

                    { login == "" ?
                         (
                            <>
                                <li> <Link to="/member/signup" > 회원가입 </Link> </li>
                                <li> <Link to="/member/login" > 로그인 </Link> </li>
                            </>
                         )
                         :
                         (
                            <>
                                <li> {login} </li>
                                <li> <a href="/member/logout"> 로그아웃 </a> </li>
                                <li> <Link to="/book/list"> 리액트공부방 </Link> </li>
                            </>
                         )
                     }
                     <li> <Link to="/board/list" > 자유게시판 </Link> </li>
                </ul>
            </div>
        </div>
    );
}

/*
    가상 DOM 작성시 주의점
        1. <태그명> </태그명> , <태그명/>
        2. ( <태그명></태그명> )
        JSX 문법에서 태그 [요소]들
        3-1. ( <div> <태그명></태그명> <태그명></태그명> </div>)
        3-2. ( <> <태그명></태그명> <태그명></태그명> </>)
        return ( <button type=""> </button> )

*/

