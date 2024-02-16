package com.example.aivoicechanger.di

import com.example.aivoicechanger.data.datasource.DataSource
import com.example.aivoicechanger.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
class AppModule {
    @Provides
    @Singleton
    fun providesDataSource (): DataSource {
        return DataSource()
    }

    @Provides
    @Singleton
    fun providesRepository (dataSource: DataSource) : Repository {
        return Repository(dataSource)
    }
}