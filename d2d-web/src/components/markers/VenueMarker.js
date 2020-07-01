import React from 'react'
import { Marker } from 'react-leaflet';
import { VenueLocationIcon } from '../icons/VenueLocationIcon';
import MarkerPopup from '../MarkerPopup';

const VenueMarker = () => {

    const latlng = [
        52.53,
        13.403
    ]
    const venueLabel = "door2door"

    return (
        <Marker position={latlng} icon={VenueLocationIcon} >
            <MarkerPopup label={venueLabel} />
        </Marker>
    )
};

export default VenueMarker;