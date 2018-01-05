# geofenceAreaChecker

This is a sample app which check current status of your device: inside of geofence area OR outside of area.

Geofence area is defined as a combination of some geographic point, radius, and specific Wifi network name.
A device is considered to be inside of the geofence area if the device is connected to the specified WiFi network
or remains geographically inside the defined circle.

If device coordinates are reported outside of the zone, but the device still connected to the specific Wifi network,
then the device is treated as being inside the geofence area.

## Getting Started

### Clone the Repository

As usual, you get started by cloning the project to your local machine:

```
$ https://github.com/gleb8k/geofenceAreaChecker.git
```

## screens
<img src="https://github.com/gleb8k/geofenceAreaChecker/images/screen.jpg"/>