import React from 'react';
import { Map as LeafletMap, TileLayer } from 'react-leaflet';
import VenueMarker from './markers/VenueMarker';
import VehicleMarker from './markers/VehicleMarker';
import BoundryCircle from './circles/BoundryCircle';
import 'leaflet/dist/leaflet.css';

const MapView = (props) => {

  var vehicleMarkers = [];

  props.vehicles.forEach((vehicle) => {

    vehicleMarkers.push(
      <VehicleMarker vehicle={vehicle} />
    );
  });

  return (
    <LeafletMap center={props.center} zoom={props.zoom}>
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution="&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
      />

      <VenueMarker />

      {vehicleMarkers}
      {/* {console.log( vehicleMarkers.length)} */}

      <BoundryCircle center={props.center} radius={props.radius} />

    </LeafletMap >
  );
}

export default MapView;