package com.kitahara.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kitahara.data.local.entity.TokenEntity
import java.util.Date

@Dao
interface TokenDao {
    @Upsert
    suspend fun upsert(entity: TokenEntity)

    @Query("SELECT expire_time FROM token_table WHERE id = 1")
    suspend fun getExpireDate(): Date?

    @Query("SELECT token FROM token_table WHERE id = 1")
    suspend fun getToken(): String?
}