package checker.area.geofence.geofenceareacheker.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import checker.area.geofence.geofenceareacheker.livedata.LocationDetectorLiveData
import checker.area.geofence.geofenceareacheker.livedata.WiFiAnalyzerLiveData

/**
 * Created by Gleb on 12/29/17.
 */
class GeofenceViewModel(application: Application) : AndroidViewModel(application) {

    //constructor(application: Application) : super(application)
    var locationLiveData: LocationDetectorLiveData = LocationDetectorLiveData(application)
    var wifiLiveData: WiFiAnalyzerLiveData = WiFiAnalyzerLiveData(application)
    var statusLiveData: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        //
    }
}