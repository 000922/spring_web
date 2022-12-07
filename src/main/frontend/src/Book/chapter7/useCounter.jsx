import React , { useState } from 'react';

// 2. 커스텀 훅 [ use훅이름 정의 , 매개변수 ]
export default function useCounter( Value ) {
    // 3. useState 훅
        // 'count' 라는 이름으로 변수 선언 , setCount 해당 변수 변경
    const [ count , setCount ] = useState(Value);

    // 이벤트 함수 [ 1. count 변수 증가 이벤트  2. count 변수 감소 이벤트 ]
        // Math.max (값1 , 값2 ) : 둘 중에 큰값 호출
    // 변수 = 익명화살표 함수
    const increaseCount = () => setCount( (count) => count+1 );   // 기존값 +1
    const decreaseCount = () => setCount( (count) => Math.max(count,-1 , 0 ) ) ;   // 기존값 -1 , 혹은 0 중 더 큰값

    return [ count , increaseCount , decreaseCount ];    // 배열 반환
}



