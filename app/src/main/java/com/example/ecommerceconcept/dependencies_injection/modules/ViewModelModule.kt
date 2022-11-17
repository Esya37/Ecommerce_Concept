package com.example.ecommerceconcept.dependencies_injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceconcept.dependencies_injection.annotations.ViewModelKey
import com.example.ecommerceconcept.viewmodels.MainScreenFragmentViewModel
import com.example.ecommerceconcept.viewmodels.MyCartViewModel
import com.example.ecommerceconcept.viewmodels.ProductDetailsViewModel
import com.example.ecommerceconcept.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenFragmentViewModel::class)
    abstract fun bindMainScreenFragmentViewModel(viewModel: MainScreenFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailsViewModel::class)
    abstract fun bindProductDetailsFragmentViewModel(viewModel: ProductDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyCartViewModel::class)
    abstract fun bindMyCartFragmentViewModel(viewModel: MyCartViewModel): ViewModel
}