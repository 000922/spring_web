import React from 'react';
import Comment from'./Comment'


// 1. 데이터 리스트 [ 서버 통신와 통신된 결과물 ]
const comments = [  // 댓글3개 객체를 저장하는 리스트 객체
    {   // 댓글1
        name : "이인제" ,
        comment : "안녕하세요, 소플입니다"
    },
    { // 댓글2
        name : "유재석" ,
        comment : "리액트 재미있어요"
    },
    { // 댓글3
         name : "강호동" ,
         comment : "저도 리액트 배워보고 싶습니다"
     }
];

function CommentList( props ){
    // 2.
    return(
        <div>
            { comments.map( (c) => {
                return(
                 <Comment name={ c.name } comment={ c.comment } />
                ) ;
            })}
        </div>
    );
}
export default CommentList