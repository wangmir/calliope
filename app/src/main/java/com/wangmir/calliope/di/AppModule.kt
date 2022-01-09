package com.wangmir.calliope.di

import android.app.Application
import com.wangmir.calliope.model.DataRepository
import com.wangmir.calliope.model.adapters.data.DataRepositoryImpl
import com.wangmir.calliope.model.adapters.data.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(app: Application): LocalDatabase = LocalDatabase.getDatabase(app)

    @Provides
    @Singleton
    fun provideDataRepository(db: LocalDatabase): DataRepository = DataRepositoryImpl(db.dayLogDao)
}
