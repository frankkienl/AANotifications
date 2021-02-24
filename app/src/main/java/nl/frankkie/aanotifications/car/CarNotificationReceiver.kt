package nl.frankkie.aanotifications.car

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.google.android.libraries.car.app.CarContext

class CarNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { safeContext ->
            intent?.let { safeIntent ->
                CarContext.startCarApp(safeIntent, Intent(Intent.ACTION_VIEW).setComponent(
                    ComponentName(safeContext, BaseCarAppService::class.java)
                ))
            }
        }
    }

    companion object {
        const val INTENT_ACTION = "ACTION_OPEN_AUTO_APP"
    }
}