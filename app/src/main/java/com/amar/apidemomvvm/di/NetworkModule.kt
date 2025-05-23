package com.amar.apidemomvvm.di

import com.amar.apidemomvvm.common.Constants.BASE_URL
import com.amar.apidemomvvm.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

     @Provides
     @Singleton
     fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
          return HttpLoggingInterceptor().apply {
               level = HttpLoggingInterceptor.Level.BODY
          }
     }

     @Provides
     @Singleton
     fun provideOkHttpClient(
          httpLoggingInterceptor: HttpLoggingInterceptor
     ): OkHttpClient {
          return OkHttpClient.Builder()
               .addInterceptor(httpLoggingInterceptor)
               .build()
     }

     @Provides
     @Singleton
     fun provideRetrofit(
          okHttpClient: OkHttpClient
     ): Retrofit {
          return Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .client(okHttpClient)
               .build()
     }

     @Provides
     @Singleton
     fun provideApiService(retrofit: Retrofit): ApiService {
          return retrofit.create(ApiService::class.java)
     }
}