function getMapping1(){
    $.ajax({
        url : "/api/v1/get-api/hello" ,
        success:function(re) { alert(re);}
    })
}

function getMapping2(){
    $.ajax({
        url : "/api/v1/get-api/name" ,
        success:function(re) { alert(re);}
    })
}

function getMapping3(){
    $.ajax({
        url : "/api/v1/get-api/variable1/test" ,
        success:function(re) { alert(re);}
    })
}

function getMapping4(){
    $.ajax({
        url : "/api/v1/get-api/variable2/test2" ,
        success:function(re) { alert(re);}
    })
}

function getMapping5(){
    $.ajax({
        url : "/api/v1/get-api/variable1/requst1?name=qwe&email=qwe@qwe&organization=qweqwe" ,
        success:function(re) { alert(re);}
    })
}

function getMapping6(){
    $.ajax({
        url : "/api/v1/get-api/requst2?key1=value1&key2=value2" ,
        success:function(re) { alert(re);}
    })
}

function getMapping7(){
    $.ajax({
        url : "/api/v1/get-api/requst3?name=value1&email=value2&organization=value3" ,
        success:function(re) { alert(re);}
    })
}
// ------ 여기까지 이상 무 --------- //
// ------------------------------------------------------------ //
function postMapping1(){
    $.ajax({
        url : "/api/v1/post-api/domain" ,
        type:"post",
        success:function(re) { alert(re);}
    })
}

function postMapping2(){

    let member = {
        name : "유재석" ,
        email : "dddd@naver" ,
        organization : "qwqwqw"
    }

    $.ajax({
        url : "/api/v1/post-api/member" ,
        type : "post"
        data : JSON.stringify(member) ,
        contentType : "application/json" ,  // 전송타입 : application/json
        success : function(re){ alert(re); }
    })

function postMapping3(){

    let member = {
        name : "유재석" ,
        email : "dddd@naver" ,
        organization : "qwqwqw"
    }

    $.ajax({
        url : "/api/v1/post-api/member2" ,
        type : "post"
        data : JSON.stringify(member) ,
        contentType : "application/json" ,  // 전송타입 : application/json
        success : function(re){ alert(re); }
    })

}

// --------------------------------------------- //

function putMapping1(){
    let member = {name : "유재석" ,email : "dddd@naver" ,organization : "qwqwqw"}
    $.ajax({
        url : "/api/v1/put-api/member" ,
        type : "PUT"
        data : JSON.stringify(member),
        contentType : "application/json" ,
        success : function(re) { alert(re); }

    })
}

function putMapping2(){
     let member = {name : "유재석" ,email : "dddd@naver" ,organization : "qwqwqw"}
        $.ajax({
            url : "/api/v1/put-api/member1" ,
            type : "PUT"
            data : JSON.stringify(member),
            contentType : "application/json" ,
            success : function(re) { console.log( re ) }

        })
    }

function putMapping3(){
     let member = {name : "유재석" ,email : "dddd@naver" ,organization : "qwqwqw"}
        $.ajax({
            url : "/api/v1/put-api/member2" ,
            type : "PUT"
            data : JSON.stringify(member),
            contentType : "application/json" ,
            success : function(re) {
                console.log( re );
                console.log( re.name);
            }

        })
    }

// ------------------------------------------ //
function deletemapping1(){
    $.ajax({
        url : "/api/v1/delete-api/하하호호" ,
        type : "DELETE",
        success : function(re) { alert(re); }
    })
}

function deletemapping2(){
    $.ajax({
        url : "/api/v1/delete-api/request1?variable=하하호호2",
        type : "DELETE",
        success : function(re) { alert(re); }
    })
}


