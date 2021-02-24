package nl.frankkie.aanotifications.car

import android.content.Intent
import com.google.android.libraries.car.app.CarAppService
import com.google.android.libraries.car.app.Screen

class BaseCarAppService : CarAppService() {
    override fun onCreateScreen(intent: Intent): Screen {
        return CarMapScreen(carContext)
    }

}