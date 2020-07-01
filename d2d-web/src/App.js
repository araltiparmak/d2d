import React from 'react';
import MapView from './components/MapView';
import './App.css';

import { w3cwebsocket as W3CWebSocket } from "websocket";

const apiUrl = process.env.REACT_APP_API_WS_URL;
const client = new W3CWebSocket(apiUrl);

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      vehicles: new Map(),
      center: { lat: 52.53, lng: 13.403 },
      zoom: 13,
      radius: 3.5
    }
  }

  componentWillMount() {

    client.onopen = () => {
      console.log('Connected to: ' + apiUrl);
    };

    client.onmessage = (message) => {
      const locations = JSON.parse(message.data);
      const newLocations = this.state.vehicles;
      newLocations.set(locations.vehicleId, locations);
      this.setState({ locations: newLocations });
    };
  }

  render() {
    return (
      <div className="App">
        <MapView
          center={this.state.center}
          zoom={this.state.zoom}
          vehicles={this.state.vehicles}
          radius={this.state.radius}
        ></MapView>
      </div >
    );
  }
}

export default App;
