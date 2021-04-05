package com.android.challenge.core

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import com.android.challenge.BuildConfig
import com.android.challenge.base.di.BaseComponent
import com.android.challenge.core.di.ApplicationComponent
import com.android.challenge.core.di.DaggerApplicationComponent
import com.android.challenge.core.di.modules.ApplicationModule
import timber.log.Timber

open class ChalengeApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        val appComponent = createApplicationComponent()
        ApplicationComponent.INSTANCE = appComponent
        BaseComponent.INSTANCE = appComponent

        setupTimber()
    }

    open fun createApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this@ChalengeApplication))
            .build()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
