import React , { useState, useEffect} from 'react';
import useCounter from './useCounter';  // 커스텀 훅 가져오기

const MAX_CAPACITY = 10;    // 전역변수 // 최대수용인원
// 1. 컴포넌트 선언
export default function Accommodate(props){

    // 1. 커스텀 훅
    const [ count , increaseCount , decreaseCount ] = useCounter(0);
    // 2. 최대 인원 체크 훅
    const [ isFull , setIsFull ] = useState( false );

    // 3.
    useEffect( () => {
        console.log( "---------------------")
        console.log("이펙트 훅 실행")
        console.log("isFull : " + isFull)
    })

    // 3. 만약에 현재인원이 최대수용 인원보다 크면 true 가 usFull 변수에 true 저장되고 아니면 false 가 저장
    useEffect( () => { setIsFull( count >= MAX_CAPACITY ) } , [count] )

    // 2. 렌더링 되는 구역
    return (
        <div style={{ padding : 16 }}>
            <p> 총 { count } 명 수용했습니다. </p>
            <button onClick={increaseCount} disabled = {isFull} >입장</button>
            <button onClick={decreaseCount} >퇴장</button>
            { isFull && <p style={{ color:"red"}} >정원이 가득 찼습니다.</p> }
        </div>
    );
}






























