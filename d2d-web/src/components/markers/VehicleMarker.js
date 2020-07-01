import React from 'react';
import { Marker } from 'react-leaflet';
import { VehicleIcon } from '../icons/VehicleIcon';
import MarkerPopup from '../MarkerPopup';


const VehicleMarker = (props) => {

  const vehicle = props.vehicle

  const label = <span>
    "Vehicle Id:" {vehicle.vehicleId} <br />
     "Location:" [{vehicle.lat}, {vehicle.lng}] <br />
  </span>

  return (
    <Marker key={vehicle.vehicleId} position={[vehicle.lat, vehicle.lng]} icon={VehicleIcon} >
      <MarkerPopup label={label} />
    </Marker>
  )
};

export default VehicleMarker;