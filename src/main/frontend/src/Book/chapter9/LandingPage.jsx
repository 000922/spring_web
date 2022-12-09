// p. 281 : 컴포넌트
import React , {useState} from 'react';
import Toolbar from './Toolbar';

export default function LandingPage(props) {
    // 1. state 선언 : const [ 변수명 , set함수명 ] = useState ( 초기값 )
    const [ isLoggedIn , setIsLoggedIn ] = useState(false); // 초기값은 로그인 안함 false
    // 2. 함수
    const onClickLogin = () => { setIsLoggedIn(true); };    // 로그인 실행
    // 3. 함수
    const onClickLogout = () => { setIsLoggedIn(false); }; // 로그아웃 실행 함수

     // 4. 렌더링
        return (
              <div>
                <Toolbar
                    isLoggedIn={isLoggedIn}
                    onClickLogin={onClickLogin}
                    onClickLogout={onClickLogout}
                />
                <div style={{ padding:16 }}> 소플과 함께하는 리액트 공부! </div>
              </div>
        );

}