alert('gggg');

function setboard(){

    let data ={
        btitle :document.querySelector('.btitle').value ,
        btitle : document.querySelector('.bcontent').value
    }
    console.log('let');
    alert('let');
    function setboard(){
            $.ajax({
                url : "/board/setboard" ,
                type : "post",
                data : JSON.stringify(data),
                contentType : "application/json" ,
                success : function(re) { console.log( re ) }

            })
            console.log('ajax');
            alert('ajax');
        }
}