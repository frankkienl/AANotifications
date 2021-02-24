package nl.frankkie.aanotifications.car

import android.app.Notification
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Handler
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.google.android.libraries.car.app.CarContext
import com.google.android.libraries.car.app.CarToast
import com.google.android.libraries.car.app.Screen
import com.google.android.libraries.car.app.model.Action
import com.google.android.libraries.car.app.model.ActionStrip
import com.google.android.libraries.car.app.model.CarIcon
import com.google.android.libraries.car.app.model.Template
import com.google.android.libraries.car.app.navigation.model.NavigationTemplate
import com.google.android.libraries.car.app.notification.CarAppExtender
import nl.frankkie.aanotifications.MyApplication
import nl.frankkie.aanotifications.R

class CarMapScreen(carContext: CarContext) : Screen(carContext) {
    private val handler = Handler()

    override fun getTemplate(): Template {
        val templateBuilder = NavigationTemplate.builder()
        val actionStrip = buildActionStrip(carContext)
        templateBuilder.setActionStrip(actionStrip)
        return templateBuilder.build()
    }

    private fun buildActionStrip(carContext: CarContext): ActionStrip {
        val builder = ActionStrip.builder()
        //this builder pattern is pretty much unreadable.
        builder.addAction(
            Action.builder()
                .setIcon(
                    CarIcon.of(
                        IconCompat.createWithResource(
                            carContext,
                            R.drawable.reiger
                        )
                    )
                )
                .setOnClickListener {
                    CarToast.makeText(
                        carContext,
                        "Notification coming soon!",
                        CarToast.LENGTH_SHORT
                    ).show()
                    //Delay few seconds, so you can move to different app;
                    //To test the background notification aspect.
                    handler.postDelayed({
                        reigerNotification()
                    }, 2000)
                }.build()
        )
        return builder.build()
    }


    /**
     * for reference, the notification-testing-bird is named Reiger.
     */
    private fun reigerNotification() {
        val notificationManager: NotificationManagerCompat =
            NotificationManagerCompat.from(carContext)

        //Build notification
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(
                carContext,
                MyApplication.CHANNEL_ID_ANDROID_AUTO_ALERTS
            ).apply {
                setWhen(System.currentTimeMillis())
                setAutoCancel(true)
                setOnlyAlertOnce(true) //Needed for HUN
                //setOngoing(false)

                //Intentionally commented line
                //setCategory(NotificationCompat.CATEGORY_NAVIGATION)

                setSmallIcon(R.drawable.reiger)
                setContentTitle("Title Reiger")
                setContentText("Text Reiger")
                setTicker("Ticker Reiger")

                setLargeIcon(
                    BitmapFactory.decodeResource(
                        carContext.resources,
                        R.drawable.reiger
                    )
                )

                priority = NotificationManagerCompat.IMPORTANCE_HIGH
            }

        //Set Android Auto part of notification
        notificationBuilder.extend(
            CarAppExtender.builder()
                .apply {
                    setImportance(NotificationManagerCompat.IMPORTANCE_HIGH)
                    setLargeIcon(
                        BitmapFactory.decodeResource(
                            carContext.resources,
                            R.drawable.reiger
                        )
                    )
                    setContentIntent(
                        PendingIntent.getBroadcast(
                            carContext,
                            1337,
                            Intent(CarNotificationReceiver.INTENT_ACTION).setComponent(
                                ComponentName(
                                    carContext,
                                    CarNotificationReceiver::class.java
                                )
                            ),
                            0
                        )
                    )
                }.build()
        )

        //Show
        val notification: Notification = notificationBuilder.build()
        notificationManager.notify(1337, notification)
    }
}