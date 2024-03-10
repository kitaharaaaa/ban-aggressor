package com.kitahara.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kitahara.data.local.converters.DateConverter
import com.kitahara.data.local.dao.SpotifyStateDao
import com.kitahara.data.local.dao.TokenDao
import com.kitahara.data.local.entity.SpotifyStateEntity
import com.kitahara.data.local.entity.TokenEntity

@TypeConverters(DateConverter::class)
@Database(entities = [TokenEntity::class,SpotifyStateEntity::class,], version = 1)
abstract class SpotifyDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
    abstract fun spotifyStateDao(): SpotifyStateDao
}