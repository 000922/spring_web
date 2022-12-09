// p. 313
import React , { useState } from 'react';

export default function NameForm( props ) {

     const [ value , setValue ] = useState(''); // 컴포넌트에서 사용되는 메모리
     const [ value2 , setValue2 ] = useState('');

     const handleChange = ( e ) => { setValue( e.target.value ); };
     const handleChange2 = ( e ) => { setValue2( e.target.value ); };

      return{
        <form>
            <label>
                이름 : <input type="text" value={value} onChange={handleChange} />
            </label>
            <label>
                요청사항 <textarea value2 onChange={handleChange2} />
            </label>
            <button type="submit">제출</button>
        </form>
      }

}











