package com.example.ecommerceconcept

import com.example.ecommerceconcept.dependencies_injection.components.AppComponent
import com.example.ecommerceconcept.dependencies_injection.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ApplicationClass : DaggerApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        applicationInjector()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().application(this).build()
        return appComponent
    }
}