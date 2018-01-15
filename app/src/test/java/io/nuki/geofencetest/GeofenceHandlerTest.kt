package io.nuki.geofencetest

import android.content.Context
import com.nhaarman.mockito_kotlin.mock
import com.pathsense.android.sdk.location.PathsenseGeofenceEvent
import com.pathsense.android.sdk.location.PathsenseGeofenceEventReceiver
import com.pathsense.android.sdk.location.PathsenseMonitoringGeofenceCallback
import org.junit.Test


class GeofenceHandlerTest {
    val geofenceId = "MyGeofence"
    val lat = 47.059670;
    val lon = 15.451875;

    val mockContext: Context = mock()

    @Test
    fun addGeofence() {
        val geofenceHandler = GeofenceHandler(mockContext);
        geofenceHandler.isMonitoringFence(geofenceId, PathsenseMonitoringGeofenceCallback { geofenceId, isMonitored ->
            assert(!isMonitored)
        });

        geofenceHandler.addGeofence(geofenceId, lat, lon, GeofenceReceiver()::class.java)

        geofenceHandler.isMonitoringFence(geofenceId, PathsenseMonitoringGeofenceCallback { geofenceId, isMonitored ->
            assert(isMonitored)
        });
    }

    inner class GeofenceReceiver : PathsenseGeofenceEventReceiver() {
        override fun onGeofenceEvent(p0: Context?, p1: PathsenseGeofenceEvent?) {
            var huehue = p1;
        }
    }
}
