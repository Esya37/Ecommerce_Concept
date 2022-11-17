package com.example.ecommerceconcept.dependencies_injection.components

import android.app.Application
import com.example.data.dependency_injection.modules.RepositoriesImplModule
import com.example.ecommerceconcept.ApplicationClass
import com.example.ecommerceconcept.dependencies_injection.modules.AppModule
import com.example.ecommerceconcept.dependencies_injection.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        ViewModelModule::class,
        RepositoriesImplModule::class,
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class]
)
interface AppComponent : AndroidInjector<ApplicationClass> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}