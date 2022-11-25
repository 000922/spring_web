

// * 리액트 사용하기 전
const name = '소플';
const element = '<h1>안녕 , '+name+'</h1>';
document.querySelector("#root").innerHTML = element

// * 리액트 사용
// p.104
// ---------- 변수 표현식 --------------------- //
const name = '소플';      // js 문자 변수
const element = <h1>안녕,{name}</h1>; // js + html => jsx
    // jsx 변수 호출 표현식 : { 변수명 }
// ReactDOM.render ( 요소변수 , DOM컨테이너 )
ReactDOM.render( element,document.getElementById("#root)" );

// -------------- 함수 표현식 ------------------ //
// 함수
function formatName( user ){
    return user.firstName + ' ' + user.lastName;
}
// 유저 객체
const user = { firstName : 'Inje' , lastName : 'Lee' }
// html 구성
const element = ( <h1> Hello , { formatName(user) }</h1> )
// 해당 id에 html 렌더링 ( 뿌려주기/넣어주기/표시해주기)
ReactDOM.render( element,document.querySelector("#root") )
// -------------------- 활용 -------------------- //

function getGreeting(user){
    if(user){
        return <h1>Hello, {formatName(user) } ! </h1>;  // 유저가 있을경우
    }
    return <h1> Hello , Stranger. </h1>  // 유저가 없을경우
}
// ---------------------- JSX 식 HTML -------------------- //
const element = <div> <h1>안녕하세요</h1> <h2>열심히 리액트를 공부해 봅시다!</h2> </div>
const element = '<div> <h1>안녕하세요</h1> <h2>열심히 리액트를 공부해 봅시다!</h2> </div>'




