package io.nuki.geofencetest

import android.content.BroadcastReceiver
import com.pathsense.android.sdk.location.PathsenseLocationProviderApi
import com.pathsense.android.sdk.location.PathsenseMonitoringGeofenceCallback

class GeofenceHandler constructor(geofenceApi: PathsenseLocationProviderApi) {

    var geoApi: PathsenseLocationProviderApi? = geofenceApi

    fun addGeofence(id: String, lat: Double, lon: Double, radius: Int, receiver: Class<out BroadcastReceiver>) {
        geoApi?.addGeofence(id, lat, lon, radius, receiver)
    }

    fun isMonitoringFence(geofenceId: String, callback: PathsenseMonitoringGeofenceCallback) {
        geoApi?.monitoringGeofence(geofenceId, callback)
    }
}