package io.nuki.geofencetest

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.pathsense.android.sdk.location.PathsenseLocationProviderApi
import com.pathsense.android.sdk.location.PathsenseMonitoringGeofenceCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fenceSize = 100

    val geofenceIdWork = "Home Fence"
    val latWork = 47.059670
    val lonWork = 15.451875

    val geofenceIdHome = "Work Fence"
    val latHome = 47.068890
    val lonHome = 15.426909

    lateinit var geofenceHandler: GeofenceHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        geofenceHandler = GeofenceHandler(PathsenseLocationProviderApi.getInstance(this))

        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, permissions,0)

        fence_output.setText("Checking for Registered Fences")

        addFenceIfNecessary(geofenceIdWork, latWork, lonWork, fenceSize)
        addFenceIfNecessary(geofenceIdHome, latHome, lonHome, fenceSize)
    }

    fun addFenceIfNecessary(fenceId: String, lat: Double, lon: Double, fenceSize: Int) {
        geofenceHandler.isMonitoringFence(fenceId, PathsenseMonitoringGeofenceCallback { monitoredFenceId, isMonitoring ->
            if (!isMonitoring) {
                geofenceHandler.addGeofence(fenceId, lat, lon, fenceSize, GeofenceEventReceiver::class.java)
                geofenceHandler.isMonitoringFence(fenceId, monitoringCallback)
                fence_output.append("\nregistering "+monitoredFenceId)
            } else {
                fence_output.append("\n"+monitoredFenceId + " was already registered")
            }
        })
    }

    val monitoringCallback = PathsenseMonitoringGeofenceCallback { geofenceId, isMonitoring ->
        if (isMonitoring) {
            fence_output.append(geofenceId + " is now registered")
        } else {
            fence_output.append(geofenceId + " failed to register")
        }
    }
}