// p.324
import React, { useState } from 'react';

export default function SignUp(props) {
    // 1. 컴포넌트 메모리
    const [ name , setName ]
        = useState('');
    const [ gender , setGender ]
        = useState('남자');

    // 2. 이벤트 함수
    const handleChangeName = (e) =>
        { setName(e.target.value); };
    const handleChangeGender = (e) =>
        { setGender(e.target.value); };
    // 2. 이벤트 함수
    const handleSubmit = (e) => {
        alert(`이름 : ${name}, 성별 : ${gender} `); // state
        e.preventDefault();
    }
    // 3. 렌더링
    return (
        <form onSubmit = { handleSubmit} >
            <label>
                이름 :
                <input
                    type="text"
                    value={name}
                    onChange={ handleChangeName } />
            </label>
            <br />
            <label>
                성별 :
                <select
                   value={gender}
                   onChange={ handleChangeGender }>

                   <option value="남자">남자</option>
                   <option value="여자">여자</option>
                </select>
            </label>
            <button type="submit"> 제출 </button>
        </form>
    )
}




