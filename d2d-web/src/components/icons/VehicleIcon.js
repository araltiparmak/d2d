import L from 'leaflet';

export const VehicleIcon = L.icon({
  iconUrl: require('../../assets/img/vehicleIcon.svg'),
  iconRetinaUrl: require('../../assets/img/vehicleIcon.svg'),
  iconAnchor: null,
  shadowUrl: null,
  shadowSize: null,
  shadowAnchor: null,
  iconSize: [20, 30],
  className: 'leaflet-vehicle-icon'
});