// 1. import : 상대경로 [ 현재파일 위치 기준 ./ : 현재폴더     ../ 상위폴더 ]
import React from 'react'
import Notification from './Notification'

// 2. data
const reservedNotifications = [
    { id : 1 , message : '안녕하세요, 오늘 일정 알려드립니다.' },
    { id : 2 , message : '점심 식사 시간입니다. '},
    { id : 3 , message : '이제 곧 미팅이 시작됩니다.' },
];
// 3. 타이머 변수 [ Interval ]
var timer
// 4. 클래스를 이용한 컴포넌트 만들기
class NotificationList extends React.Component{
    // 1. 생성자
    constructor(props){
        super(props);
        this.state = { notifications : [  ] }; // 비어 있음
    }
    // 2. 함수1 [ 컴포넌트 생성 ]
    componentDidMount(){
        const { notifications } = this.state; // 생명주기
        // timer = setInterval( () => { 실행코드 } , 1000 ) ;
        timer = setInterval( () => {
            if( notifications.length < reservedNotifications.length ) {
                const index = notifications.length;
                notifications.push( reservedNotifications[index] );
                this.setState( {notifications : notifications} );
            }else{

                this.setState( {notifications : [] } )  // 상태 초기화

                clearInterval(timer);   // 타이머 초기화
            }
         } , 2000 ) ;  // 2초마다 위 코드 실행
    }
    // 추가코드 // NotificationList 컴포넌트 사망시 timer 클리어
    componentWillUnmount(){
        if( timer ){
            clearInterval( timer );
        }
    }



    // 3. 함수2
    render(){
        return (
            <div>
                { this.state.notifications.map( ( n ) => {
                    return <Notification id={ n.id } message={ n.message } />;
                } ) }
            </div>
        )
    }
}
// 3.
export default NotificationList;
