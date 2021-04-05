package com.android.challenge.core.di.modules

import com.android.challenge.core.di.scopes.ApplicationScope
import com.android.challenge.home.data.HomeRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideHomeRepository(dataSource: HomeRepository.Remote): HomeRepository = dataSource

}
