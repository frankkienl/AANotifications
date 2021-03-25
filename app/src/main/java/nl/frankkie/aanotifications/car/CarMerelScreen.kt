package nl.frankkie.aanotifications.car

import android.app.Notification
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import androidx.car.app.notification.CarAppExtender
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.IconCompat
import nl.frankkie.aanotifications.MyApplication
import nl.frankkie.aanotifications.R

class CarMerelScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        val templateBuilder = ListTemplate.Builder()
        templateBuilder.setHeaderAction(Action.BACK)
//        templateBuilder.setActionStrip(
//            ActionStrip.Builder()
//                .addAction(
//                    Action.Builder()
//                        .setIcon(
//                            CarIcon.Builder(
//                                IconCompat.createWithResource(
//                                    carContext,
//                                    R.drawable.merel
//                                )
//                            ).build()
//                        )
//                        .setOnClickListener {
//                            carContext.getCarService(ScreenManager::class.java).pop()
//                        }
//                        .setTitle("Merel")
//                        .build()
//                ).build()
//        )
        templateBuilder.setSingleList(buildList())
        return templateBuilder.build()
    }

    private fun buildList(): ItemList {
        val icon = CarIcon.Builder(
            IconCompat.createWithResource(
                carContext,
                R.drawable.merel
            )
        ).build()

        val itemListBuilder = ItemList.Builder()
        itemListBuilder.addItem(
            Row.Builder()
                .setImage(icon)
                .setTitle("Category navigation")
                .setOnClickListener { test1() }
                .build()
        )
        return itemListBuilder.build()
    }

    private fun test1() {
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
                //setOngoing(false)

                //Category
                setCategory(NotificationCompat.CATEGORY_NAVIGATION)

                setSmallIcon(R.drawable.merel)
                setContentTitle("Title Merel")
                setContentText("Text Merel")
                setTicker("Ticker Merel")

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
            CarAppExtender.Builder()
                .apply {
                    setImportance(NotificationManagerCompat.IMPORTANCE_HIGH)
                    setLargeIcon(
                        BitmapFactory.decodeResource(
                            carContext.resources,
                            R.drawable.merel
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