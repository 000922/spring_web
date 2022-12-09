// p. 279 : 컴포넌트
import React from 'react'
import Styles from './Toolbar'

export default function Toolbar(props) {
    const { isLoggedIn , onClickLogin , onClickLogout } = props;
    return(
        <div>
            { props.isLoggedIn && <span> 환영합니다. </span>}
            { /*isLoggedIn ? (참DOM) : (거짓DOM)*/ }
            { isLoggedIn ? (
                <button onClick={onClickLogout}> 로그아웃 </button>
            ) : (
                <button onClick={onClickLogin}> 로그인 </button>
            ) }
        </div>
    )
}














