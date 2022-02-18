package com.scolotin.healthmonitoring.di

import android.app.Application
import com.scolotin.healthmonitoring.di.modules.mainModule
import com.scolotin.healthmonitoring.di.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class HealthMonitoring : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            fragmentFactory()
            androidContext(applicationContext)
            modules(listOf(mainModule, networkModule))
        }
    }

}
