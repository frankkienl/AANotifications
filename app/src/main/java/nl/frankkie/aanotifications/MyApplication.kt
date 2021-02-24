package nl.frankkie.aanotifications

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initNotificationChannels(this)
    }

    private fun initNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //NAVIGATION
            val notificationChannelAANavigation = NotificationChannel(
                CHANNEL_ID_ANDROID_AUTO_NAVIGATION,
                context.getString(R.string.notification_channel_android_auto_navigation_title),
                NotificationManager.IMPORTANCE_MIN //Don't make a big deal on phone
            ).apply {
                description =
                    context.getString(R.string.notification_channel_android_auto_navigation_description)
                enableVibration(false)
                enableLights(false)
                setSound(null, null)
            }
            context.getSystemService(NotificationManager::class.java)
                .createNotificationChannel(notificationChannelAANavigation)

            //ALERTS
            val notificationChannelAAAlerts = NotificationChannel(
                CHANNEL_ID_ANDROID_AUTO_ALERTS,
                context.getString(R.string.notification_channel_android_auto_alerts_title),
                NotificationManager.IMPORTANCE_MIN //Don't make a big deal on phone
            ).apply {
                description =
                    context.getString(R.string.notification_channel_android_auto_alerts_description)
                enableVibration(false)
                enableLights(false)
                setSound(null, null)
            }
            context.getSystemService(NotificationManager::class.java)
                .createNotificationChannel(notificationChannelAAAlerts)
        }
    }

    companion object {
        const val CHANNEL_ID_ANDROID_AUTO_NAVIGATION = "Android-Auto-Navigation"
        const val CHANNEL_ID_ANDROID_AUTO_ALERTS = "Android-Auto-Alerts"
    }
}