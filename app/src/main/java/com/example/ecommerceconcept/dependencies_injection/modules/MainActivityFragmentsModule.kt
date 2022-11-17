package com.example.ecommerceconcept.dependencies_injection.modules

import com.example.ecommerceconcept.dependencies_injection.annotations.FragmentScope
import com.example.ecommerceconcept.ui.fragments.BottomNavigationViewFragment
import com.example.ecommerceconcept.ui.fragments.MainScreenFragment
import com.example.ecommerceconcept.ui.fragments.MyCartFragment
import com.example.ecommerceconcept.ui.fragments.ProductDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideMainScreenFragment(): MainScreenFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideProductDetailsFragment(): ProductDetailsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideMyCartFragment(): MyCartFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideBottomNavigationViewFragment(): BottomNavigationViewFragment
}