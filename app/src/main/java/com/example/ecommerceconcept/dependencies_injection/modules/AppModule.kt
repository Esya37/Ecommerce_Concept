package com.example.ecommerceconcept.dependencies_injection.modules

import android.app.Application
import android.content.Context
import com.example.ecommerceconcept.dependencies_injection.annotations.ActivityScope
import com.example.ecommerceconcept.ui.activities.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityFragmentsModule::class])
    abstract fun providesMainActivity(): MainActivity

}