alert('write on');
let bcno = 2; // 카테고리 번호   // 카테고리 기본값 = 자유게시판


function setboard(){

    let data ={
        btitle :document.querySelector('.btitle').value ,
        bcontent : document.querySelector('.bcontent').value ,
        bfile : document.querySelector('.bfile').value ,
        bcno : bcno
    }

            $.ajax({
                url : "/board/setboard" ,
                type : "post",
                data : JSON.stringify(data),
                contentType : "application/json" ,
                success : function(re) {
                    if( re == true ){
                        alert('글작성성공');
                        location.href="/board/list"
                    }
                    else{ alert("글작성실패");}
                }
            })

} // end

// 2. 게시물 카테고리 등록 메소드
function setbcategory(){
    let data = { bcname : document.querySelector(".bcname").value }

    $.ajax({
        url : "/board/setbcategory" ,
        type : "post",
        data : JSON.stringify(data),
        contentType : "application/json",
        success : function(re) {
            if( re == true){ alert('카테고리추가성공'); bcategorylist(); }
            else{ alert('카테고리추가실패')}
        }
    })
} // end

// 3. 모든 카테고리 출력
bcategorylist() // 이거 꼭 있어야 함
function bcategorylist(){
    $.ajax({
        url : "/board/bcategorylist" ,  type : "get" ,
        success : function(re){
            let html = "";
            re.forEach( c =>{
                console.log( c )
                html += '<button type="button" onclick="bcnochage('+c.bcno+')">'+c.bcname+'</button>';
            })
            document.querySelector('.bcategorybox').innerHTML = html;
        }
    })
} // end

// 4. 카테고리 버튼을 클릭했을때 선택된 카테고리 번호 대입
function bcnochage( cno ){ bcno = cno; alert( bcno+"의 카테고리 선택");  }