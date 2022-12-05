// p. 109
import React from 'react';  // 1. 래액트 라이브러리
import Book from "./Book";  // 2. 북 컴포넌트 호출 [ p.108]

// 함수
function Library( props ){
    return (
        <div>
            <Book name="처음 만난 파이썬" numOfPage={300} />
            <Book name="처음 만난 AWS" numOfPage={400} />
            <Book name="처음 만난 리액트" numOfPage={500} />
        </div>
    ); // return end
} // 함수 end

// 함수 내보내기
export default Library;












