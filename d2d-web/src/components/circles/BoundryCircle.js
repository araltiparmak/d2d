import React from 'react';
import { Circle } from 'react-leaflet';

const BoundryCircle = (props) => {

    return (
        <Circle
            center={props.center}
            radius={props.radius * 1000}
            color={'blue'}
            fillColor={'#f03'}
            fillOpacity={0.1}
        />
    );
}

export default BoundryCircle;