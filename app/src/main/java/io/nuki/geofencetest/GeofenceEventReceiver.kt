package io.nuki.geofencetest

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import com.pathsense.android.sdk.location.PathsenseGeofenceEvent
import com.pathsense.android.sdk.location.PathsenseGeofenceEventReceiver

class GeofenceEventReceiver : PathsenseGeofenceEventReceiver() {
    override fun onGeofenceEvent(context: Context?, geofenceEvent: PathsenseGeofenceEvent?) {
        var eventType = "Enter"

        if (geofenceEvent?.isEgress!!) {
            eventType = "Exit"
        }

        val notification = Notification.Builder(context)
                .setContentTitle("Got " + geofenceEvent.geofenceId + " " + eventType + " Event")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Event: was " + eventType)

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(geofenceEvent.geofenceId, 1, notification.build())
    }
}