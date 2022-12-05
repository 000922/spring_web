import React from 'react'

export default function BoardList(){
    return(
        <div>
         <a href="/board/write">글쓰기</a>
         <h1>글 목록 페이지</h1>
         <div className="bcategorybox"></div>
         <table className="btable"> </table>
        </div>
    );

}