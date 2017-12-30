package checker.area.geofence.geofenceareacheker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.location.Location
import android.net.wifi.WifiConfiguration
import android.support.annotation.Nullable
import checker.area.geofence.geofenceareacheker.livedata.LocationDetectorLiveData
import checker.area.geofence.geofenceareacheker.livedata.WiFiAnalyzerLiveData
import checker.area.geofence.geofenceareacheker.model.GeofenceState

/**
 * Created by Gleb on 12/29/17.
 */
class GeofenceViewModel(application: Application) : AndroidViewModel(application) {

    //constructor(application: Application) : super(application)
    private var locationLiveData: LocationDetectorLiveData = LocationDetectorLiveData(application)
    private var wifiLiveData: WiFiAnalyzerLiveData = WiFiAnalyzerLiveData(application)
    private var mediatorLiveData: MediatorLiveData<GeofenceState> = MediatorLiveData()
    var statusLiveData: MutableLiveData<String> = MutableLiveData()

    private var networkName: String = ""
    private var point: Location = Location("geofence point")
    private var pointRadius: Float = -1f

    private val locationObserver = object : Observer<Location> {
        fun onChanged(@Nullable location: Location) {
            checkArea()
        }
    }
    private val networkObserver = object : Observer<String> {
        fun onChanged(@Nullable s: String) {
            checkArea()
        }
    }
    private val mediatorStatusObserver = object : Observer<GeofenceState> {
        fun onChanged(@Nullable state: GeofenceState) {
            statusLiveData.value = state.toString()
        }
    }

    init {
        mediatorLiveData.addSource(locationLiveData, locationObserver)
        mediatorLiveData.addSource(wifiLiveData, networkObserver)
        mediatorLiveData.observeForever(mediatorStatusObserver)
    }

    override fun onCleared() {
        super.onCleared()
        mediatorLiveData.removeSource(locationLiveData)
        mediatorLiveData.removeSource(wifiLiveData)
        mediatorLiveData.removeObserver(mediatorStatusObserver)
    }

    private fun checkArea() {
        if (isConnectedToCurrentNetwork() || isInArea()) {
            mediatorLiveData.value = GeofenceState.INSIDE
        }
        else {
            mediatorLiveData.value = GeofenceState.OUTSIDE
        }
    }

    private fun isConnectedToCurrentNetwork(): Boolean {
        if (wifiLiveData.value == null || networkName.isEmpty()) {
            return false
        }
        return networkName.toLowerCase() == wifiLiveData.value!!.toLowerCase()
    }

    private fun isInArea(): Boolean {
        if (pointRadius < 0 || locationLiveData.value == null) {
            return false
        }
        val distance = point.distanceTo(locationLiveData.value)
        return distance <= pointRadius
    }
}