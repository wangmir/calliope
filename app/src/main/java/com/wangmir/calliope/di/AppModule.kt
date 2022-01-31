package com.wangmir.calliope.di

import android.app.Application
import com.wangmir.calliope.adapters.data.FakeDataRepository
import com.wangmir.calliope.adapters.data.LocalDatabase
import com.wangmir.calliope.domain.repositories.DataRepository
import com.wangmir.calliope.domain.usecases.GetDayLogList
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
    fun provideDataRepository(db: LocalDatabase): DataRepository = FakeDataRepository()
    // TODO: until make create dayLog view, fake repository will be used as list of view.
    // fun provideDataRepository(db: LocalDatabase): DataRepository = DataRepositoryImpl(db.dayLogDao)

    @Provides
    @Singleton
    fun provideGetDayLogList(dataRepository: DataRepository): GetDayLogList =
        GetDayLogList(dataRepository)
}
