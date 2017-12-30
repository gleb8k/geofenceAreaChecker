package checker.area.geofence.geofenceareacheker.livedata

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

/**
 * Created by Gleb on 12/29/17.
 */
class LocationDetectorLiveData(var context:Context): LiveData<Location>(), LocationListener {

    companion object {
        private val LOCATION_INTERVAL = 1000L
        private val LOCATION_DISTANCE = 10f
    }

    private val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    override fun onInactive() {
        locationManager.removeUpdates(this)
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, LOCATION_INTERVAL,
                LOCATION_DISTANCE, this)
    }

    override fun onLocationChanged(location: Location?) {
        value = location
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        //
    }

    override fun onProviderDisabled(provider: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}