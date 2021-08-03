package space.stanton.technicaltask

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var AppContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        AppContext = this
    }
}
