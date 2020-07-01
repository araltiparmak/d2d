import React from 'react';
import { Popup } from 'react-leaflet';

const MarkerPopup = (props) => {

    return (
        <Popup>
            <div className='poup-text'>{props.label}</div>
        </Popup>
    );
};

export default MarkerPopup;