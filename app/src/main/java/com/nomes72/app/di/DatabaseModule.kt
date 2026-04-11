package com.nomes72.app.di

import android.content.Context
import androidx.room.Room
import com.nomes72.app.data.local.NamesDatabase
import com.nomes72.app.data.local.dao.SacredNameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NamesDatabase {
        return Room.databaseBuilder(
            context,
            NamesDatabase::class.java,
            "names_database"
        ).build()
    }

    @Provides
    fun provideSacredNameDao(database: NamesDatabase): SacredNameDao {
        return database.sacredNameDao()
    }
}