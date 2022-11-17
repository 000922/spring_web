function setdelete(){
    let mpassword
    = document.querySelector(".mpassword").value

    $.ajax({
        url : "/member/setdelete",
        type : "delete",
        data : { "mpassword" : mpassword } ,
        success : function(re) { alert(re) }
    })
}












