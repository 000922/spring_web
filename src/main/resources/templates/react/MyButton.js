
alert('dd');
// 1. 해당 'root' 라는 id 를 갖는 html 태그 호출
const domContainer = document.querySelector('#root')
console.log( domContainer )

// 2. 리액트 렌더링 [ render( 이벤트 , 위치 ) ]
ReactDOM.render(React.createElement(MyButton) , domContainer )

// 3. MyButton 생성 함수
function MyButton(props) {  // props : properties

    const [isClicked, setIsClicked] = React.useState(false);

    return React.createElement( // React.createElement( <button> )
        'button',   // 태그명
        { onClick : () => setIsClicked(true) }, // 옵션 : 이벤트
        isClicked ? 'Clicked!' : 'Click here!'  // html 작성
    )

}



