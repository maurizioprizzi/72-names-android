package com.nomes72.app.di

import com.nomes72.app.data.repository.SacredNameRepositoryImpl
import com.nomes72.app.data.repository.UserProfileRepositoryImpl
import com.nomes72.app.domain.repository.SacredNameRepository
import com.nomes72.app.domain.repository.UserProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSacredNameRepository(
        impl: SacredNameRepositoryImpl
    ): SacredNameRepository

    @Binds
    @Singleton
    abstract fun bindUserProfileRepository(
        impl: UserProfileRepositoryImpl
    ): UserProfileRepository
}