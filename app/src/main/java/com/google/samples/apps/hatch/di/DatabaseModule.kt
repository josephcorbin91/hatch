package com.google.samples.apps.hatch.di

import android.content.Context
import com.google.samples.apps.hatch.data.AppDatabase
import com.google.samples.apps.hatch.data.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideProfileDao(appDatabase: AppDatabase): ProfileDao {
        return appDatabase.profileDao()
    }
}
