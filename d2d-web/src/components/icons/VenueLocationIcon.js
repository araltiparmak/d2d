import L from 'leaflet';

export const VenueLocationIcon = L.icon({
  iconUrl: require('../../assets/img/venueLocationIcon.svg'),
  iconRetinaUrl: require('../../assets/img/venueLocationIcon.svg'),
  iconAnchor: null,
  shadowUrl: null,
  shadowSize: null,
  shadowAnchor: null,
  iconSize: [35, 35],
  className: 'leaflet-venue-icon'
});