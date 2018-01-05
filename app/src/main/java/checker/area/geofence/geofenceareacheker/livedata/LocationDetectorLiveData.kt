package checker.area.geofence.geofenceareacheker.livedata

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log

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
        val provider = locationManager.getBestProvider(getProviderCriteria(), true)
        locationManager.requestLocationUpdates(provider, LOCATION_INTERVAL,
                LOCATION_DISTANCE, this)
    }

    private fun getProviderCriteria(): Criteria {
        val c = Criteria()
        c.accuracy = Criteria.ACCURACY_COARSE
        c.isAltitudeRequired = false
        c.isBearingRequired = false
        c.isCostAllowed = false
        c.powerRequirement = Criteria.NO_REQUIREMENT
        c.isSpeedRequired = false
        return c
    }

    override fun onLocationChanged(location: Location?) {
        value = location
        //Log.v("LocationDetector", "lat = " + location!!.latitude + " long = " + location.longitude)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {
        onActive()
    }

    override fun onProviderDisabled(provider: String?) {
        onInactive()
    }

}