// ---- 전역변수 --//
let bcno = 0;   // 선택된 카테고리 / 기본값 0 : 전체보기

// 1. 게시물 출력
boardlist()
function boardlist(){
    $.ajax({
            url : "/board/boardlist",
            type : "get",
            data : { "bcno" : bcno },
            success : function(list) {
                let html = '<tr>  <th> 번호 </th> <th> 제목 </th> <th> 작성자 </th></tr>';
                list.forEach( (b) => {
                   html +=
                   '<tr>  <td> '+b.bno+' </td> <td onclick="getview('+b.bno+')"> '+b.btitle+' </td> <td> '+b.memail+' </td></tr>';
                })
                 document.querySelector(".btable").innerHTML = html;
            }
        })
}

// 2. 게시물 조회 페이지 [ 페이지 전환 --> 클릭한 게시물번호 기록 ( java , 템플릿 , js(페이지 전환시 초기화/세션 초기화 ) ]
function getview( bno ){
    // 1. 클릭한 게시물번호 저장
    sessionStorage.setItem("bno",bno);
    // 2. 페이지 전환
    location.href="/board/view";
}

// 3. 모든 카테고리 출력
bcategorylist()
function bcategorylist(){
    $.ajax({
        url : "/board/bcategorylist" ,  type : "get" ,
        success : function(re){
            let html = '<button type="button" onclick="bcnochage(0)">전체보기</button>';
            re.forEach( c =>{
                console.log( c )
                html += '<button type="button" onclick="bcnochage('+c.bcno+')">'+c.bcname+'</button>';
            })
            document.querySelector('.bcategorybox').innerHTML = html;
        }
    })
}
// 4. 카테고리 버튼을 클릭했을때 선택된 카테고리 번호 대입
function bcnochage( cno ){ bcno = cno; alert( bcno ); boardlist();  }













