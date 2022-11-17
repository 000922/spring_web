alert('pass')

function findpassword(){
    let memail = document.querySelector('.memail').value
alert('word')
    $.ajax({
        url : "/member/getpassword",
        type : "get",
        data : { "memail" : memail } ,
        success : function( re ){ alert(re) }
    })



}