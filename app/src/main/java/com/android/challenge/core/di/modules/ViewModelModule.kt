package com.android.challenge.core.di.modules

import androidx.lifecycle.ViewModel
import com.android.challenge.base.di.ViewModelKey
import com.android.challenge.home.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel

}
