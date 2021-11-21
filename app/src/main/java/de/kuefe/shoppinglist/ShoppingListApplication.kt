package de.kuefe.shoppinglist

import android.app.Application
import timber.log.Timber

class ShoppingListApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}