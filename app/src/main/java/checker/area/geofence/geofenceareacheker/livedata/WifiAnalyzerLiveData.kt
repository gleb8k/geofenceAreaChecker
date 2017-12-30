package checker.area.geofence.geofenceareacheker.livedata

import android.annotation.SuppressLint
import android.app.Activity
import android.arch.lifecycle.LiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.SupplicantState
import android.net.wifi.WifiManager
import android.net.wifi.WifiInfo
import android.net.ConnectivityManager





/**
 * Created by Gleb on 12/29/17.
 */
class WiFiAnalyzerLiveData(var context: Context): LiveData<String>() {

    private var wifiManager: WifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    private var connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var broadCastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
             value = getWifiConnectedName()
        }
    }

    override fun onActive() {
        val filter = IntentFilter()
        filter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        context.registerReceiver(broadCastReceiver, filter)
    }

    override fun onInactive() {
        context.unregisterReceiver(broadCastReceiver)
    }

    fun registerAndScan() {
        if (!wifiManager.isWifiEnabled) {
            wifiManager.isWifiEnabled = true
        }

    }

    private fun getWifiConnectedName(): String {
        if (connectionManager.activeNetworkInfo.isConnected)  {
            return wifiManager.connectionInfo.ssid
        }
        return ""
    }

}