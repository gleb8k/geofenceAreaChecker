package checker.area.geofence.geofenceareacheker.livedata

import android.arch.lifecycle.LiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager

/**
 * Created by Gleb on 12/29/17.
 */
class WiFiAnalyzerLiveData(var context: Context): LiveData<String>() {

    private var broadCastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            checkWifi()
        }
    }

    override fun onActive() {
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(broadCastReceiver, filter)
        checkWifi()
    }

    override fun onInactive() {
        context.unregisterReceiver(broadCastReceiver)
    }

    private fun checkWifi() {
        value = getWifiConnectedName()
    }

    private fun getWifiConnectedName(): String? {
        val wifiManager: WifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectionManager.activeNetworkInfo != null && connectionManager.activeNetworkInfo.isConnected)  {
            return wifiManager.connectionInfo.ssid.replace("\"", "")
        }
        return ""
    }

}