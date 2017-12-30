package checker.area.geofence.geofenceareacheker.view

import android.Manifest
import android.annotation.TargetApi
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.content.ContextCompat
import android.widget.Toast
import checker.area.geofence.geofenceareacheker.R
import checker.area.geofence.geofenceareacheker.viewmodel.GeofenceViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.Nullable


class MainActivity : AppCompatActivity() {

    companion object {
        val REQUIRED_PERMISSIONS = arrayOf(
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION)

        val REQUEST_CODE_REQUIRED_PERMISSIONS = 100
        val TAG = "WiFiAnalyzer"
    }

    private var geofenceViewModel: GeofenceViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        geofenceViewModel = ViewModelProviders.of(this).get(GeofenceViewModel::class.java)
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onStart() {
        if (hasPermissions(this, *REQUIRED_PERMISSIONS)) {
            checkArea()
        } else {
            requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_REQUIRED_PERMISSIONS)
        }
        super.onStart()
    }

    @CallSuper
    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_REQUIRED_PERMISSIONS) {
            if (onPermissionResult(grantResults)) {
                checkArea()
            }
            else {
                recreate()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun onPermissionResult(grantResult: IntArray): Boolean {
        grantResult.forEach {
            if (it == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Missing permissions", Toast.LENGTH_LONG).show()
                return false
            }
        }
        return true
    }

    fun hasPermissions(context: Context, vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun checkArea() {
        geofenceViewModel.statusLiveData.observe(this, object : Observer<String> {
            fun onChanged(@Nullable status: String) {
                //updateUI(status)
            }
        })
    }
}
