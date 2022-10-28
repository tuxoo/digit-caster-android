package com.tuxoo.digit_caster_android.dependency

import android.content.Context
import androidx.room.Room
import com.tuxoo.digit_caster_android.model.history.HistoryRepository
import com.tuxoo.digit_caster_android.model.history.room.RoomHistoryRepository
import com.tuxoo.digit_caster_android.model.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    private val DB_NAME = "calculator"
    private val ASSET_FILE = "init_db.db"

    @Provides
    @Singleton
    fun provideDatabase(
        context: Context
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .createFromAsset(ASSET_FILE)
            .build()

    @Provides
    @Singleton
    fun provideHistoryRepository(database: AppDatabase): HistoryRepository =
        RoomHistoryRepository(database.getHistoryDao())
}