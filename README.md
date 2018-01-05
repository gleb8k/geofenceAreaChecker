# geofenceAreaChecker

This is an Android sample app which check current status of your device: inside of geofence area OR outside of area.

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

## Edit geofence area
![screen](https://user-images.githubusercontent.com/34940037/34617050-c0796c9a-f242-11e7-8bae-07ce4310426b.jpg)

## There 4 editText fields:
### - Wifi network name
      wifi network, which can be connected in your geofence area;
### - Geofence area radius
      geographic area radius(in meters)
### - Geofence area lattitude
      geographic point lattitude
### - Geofence area longitude
      geographic point longitude

You can apply your configuration by click on button "Update config"
and see the actual status message of your device: "Inside" or "Outside"

