import React , {useState , useEffect } from 'react'
import axios from 'axios'
import Pagination from 'react-js-pagination'

export default function BoardList(){

   // 1. 메모리
    // 1. 게시물 리스트 state
   const [ pageInfo , setPageInfo ] = useState( { bcno : 0 , page : 1 , key : "" , keyword : "" } )
   const [ pageDto , setPageDto ] = useState( { list : [] } )                                // 1. 게시물 리스트 state
   const [ bcategory , setBcategoryList ] = useState( [ ] )                                      // 1. 카테고리 리스트
    // 2. 서버로부터 게시물 리스트를 가져오는 함수 [ 실행조건 : 1. 렌더링될때 2. 검색할때 3.카테고리선택 4.페이징선택 --> 일반 함수화
    function getboardlist(){
         axios   .post( "/board/boardlist" ,  pageInfo )
                 .then( res => {  console.log( res.data );  setPageDto( res.data );  } ).catch( err => { console.log( err ); } )
           }
    // 3. 렌더링 될때 그리고 *** pageInfo 변경될때 마다
    useEffect( getboardlist , [pageInfo ] )

    // 4.
    function getBcategory(){
         axios.get("/board/bcategorylist")
             .then( res => { setBcategoryList( res.data); } ).catch( err => { console.log( err); } )
    }
     useEffect( getBcategory , [ ] )

    // 카테고리 버튼을 선택했을때
    const onCategory = ( bcno ) =>{ setPageInfo( { bcno : bcno , page : 1 , key : "" , keyword:""  } )  }
    //================================================================
    const onPage = ( page ) =>{
        setPageInfo(
            {   bcno : pageInfo.bcno ,      // 기존 카테고리
                page : page ,
                key : pageInfo.key ,        // 기존 검색 필드명
                keyword: pageInfo.keyword }    // 기존 검색할 단어
            )
        }
    // =============================== 4. 검색 == ===================================
   const onSearch = () => {
        setPageInfo(
            {   bcno : pageInfo.bcno ,  // 카테고리 번호 [ 기존 그대로 ]
                page : 1 ,              // 검색시 첫페이지부터 보여주기 [ 1]
                key : document.querySelector('.key').value ,     // 검색할 필드명
                keyword: document.querySelector('.keyword').value  }    // 검색할 단어
            )
   }

   const loadView = ( bno ) => {
           window.location.href = '/board/view/'+bno
       }

    return(
        <div>
            <a href="/board/write">글쓰기</a>
            <h1>글 목록 페이지</h1>

         <div className="bcategorybox">

            <button type="button" onClick = { ()=> onCategory( 0 ) }> 전체보기 </button>
          {
             bcategory.map( (c) => {
                return (
                   <button type="button" onClick = { ()=> onCategory( c.bcno ) }>{c.bcname}</button>
               )
             })
          }
         </div>

         <table className="btable">
             {
                pageDto.list.map( (b) => {
                    return(
                        <tr>
                            <td> {b.bno} </td>
                            <td onClick={() => loadView( b.bno) }> {b.btitle} </td>
                            <td> {b.memail} </td>
                            <td> {b.bdate} </td>
                            <td> {b.bview} </td>
                        </tr>
                    )
                })
             }
         </table>

         <Pagination
            activePage={ pageInfo.page  }
            itemsCountPerPage = { 3 }
            totalItemsCount = { pageDto.totalBoards }
            pageRangeDisplayed = { 5 }
            onChange={ onPage }
         />

        <div className="searchBox">
            <select className="key">
                <option value="btitle"> 제목 </option>
                <option value="bcontent"> 내용 </option>
            </select>
            <input type="text" className="keyword" />
            <button type="button" onClick={ onSearch }> 검색 </button>
        </div>

       </div>
    );

}