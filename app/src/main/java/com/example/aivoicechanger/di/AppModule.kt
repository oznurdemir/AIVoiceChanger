package com.example.aivoicechanger.di

import android.content.Context
import androidx.room.Room
import com.example.aivoicechanger.data.database.Database
import com.example.aivoicechanger.data.datasource.DataSource
import com.example.aivoicechanger.network.ApiClient
import com.example.aivoicechanger.network.ApiService
import com.example.aivoicechanger.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
class AppModule {
    @Provides
    @Singleton
    fun providesDataSource (apiService: ApiService, voiceDatabase: Database): DataSource {
        return DataSource(apiService, voiceDatabase)
    }

    @Provides
    @Singleton
    fun providesRepository (dataSource: DataSource) : Repository {
        return Repository(dataSource)
    }

    @Provides
    @Singleton
    fun providesMusicUrl() : ApiService{
        return ApiClient.getClient()
    }

    @Provides
    @Singleton
    fun roomDatabase(@ApplicationContext context: Context) : Database =
        Room.databaseBuilder(
            context,
            Database :: class.java,
            "Voice_Db"
        ).build()
}