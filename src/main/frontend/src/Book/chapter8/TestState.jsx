// setState VS prevState
import React, { useState } from 'react';

export default function TestState( props ) {
    const [ state , setState ] = useState(0);

    const stateadd = () => {
        setState( state + 1);
        setState( state + 2);
        setState( state + 3);
        setState( state + 4);
        setState( state + 5);
    }

    const stateadd2 = () => {
            setState( (prevState) => prevState + 1);
            setState( (prevState) => prevState + 2);
            setState( (prevState) => prevState + 3);
            setState( (prevState) => prevState + 4);
            setState( (prevState) => prevState + 5);
        }

    return (
        <div>
            <div> state 에 저장된 값 : {state} </div>
            <button onClick={ stateadd }> 클릭이벤트 </button>
            <button onClick={ stateadd2 }> 클릭이벤트 </button>
        </div>
    );
}


