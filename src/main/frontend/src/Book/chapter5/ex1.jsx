
function App( props ) {
        // 1. <컴포넌트명 />
        // 2. <컴포넌트명 속성명 = "문자열 " , 속성명 { 숫자 }>
    return(
        <Profile name="소플" introduction="안녕하세요, 소플입니다." viewCount={1500}/>
    );
}

// 2.
function App( props ) {

    return(
        <Layout             // Layout : 컴포넌트
            width={2560}    // Layout 컴포넌트에 width 속성 의 값 전달
            height={1440}
            header={ <Header title="소플의 블로그입니다." /> }
            footer={ <Footer /> }
        />
    )
}




