// p.249
// 1. 컴포넌트 첫글자 대문자
// 2. 클래스 컴포넌트 / 함수컴포넌트
// 3. this. 필드명 : 현재 클래스의 멤버[필드.함수.생성자]
import React from 'react';

class Ex1_Event extends React.Component {

    // -------------------------- 1 -------------------------- //
    // 1. 생성자
    constructor(props) {
        super(props);
        this.state = { isToggleOn : true }; // 메모리 관리
        this.handleClick = this.handleClick.bind(this);
    }
    // -------------------------- 2 -------------------------- //
    // 2. 이벤트 함수    졸리다
    handleClick(){  // prevState : state 값
        this.setState( prevState => ({
         isToggleOn : !prevSate.isToggleOn
         }));
    }
    // -------------------------- 3 -------------------------- //
    // 2. 렌더링 함수
    render() {
        return(
            <button onClick={ this.handleClick }>
                {this.state.isToggleOn ? '켜짐' : '꺼짐'}
            </button>
        );
    }
}
export default Ex1_Event







