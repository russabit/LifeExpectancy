package ru.ruslan.life_expectancy.DI

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.ruslan.life_expectancy.DI.roomModule
import ru.ruslan.life_expectancy.DI.viewModelModule

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(viewModelModule, roomModule))
        }
    }
}