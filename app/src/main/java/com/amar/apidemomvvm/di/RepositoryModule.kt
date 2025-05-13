package com.amar.apidemomvvm.di

import com.amar.apidemomvvm.data.repository.PostRepository
import com.amar.apidemomvvm.data.repository.PostRepositoryImpl
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
     abstract fun bindPostRepository(
          postRepositoryImpl: PostRepositoryImpl
     ): PostRepository
}