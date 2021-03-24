package nl.frankkie.aanotifications.car

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

class CarMerelScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        val templateBuilder = ListTemplate.Builder()
        templateBuilder.setSingleList(buildList())
        return templateBuilder.build()
    }

    private fun buildList(): ItemList {
        val itemListBuilder = ItemList.Builder()
        itemListBuilder.addItem(Row.Builder().setTitle("TODO").build())
        return itemListBuilder.build()
    }
}