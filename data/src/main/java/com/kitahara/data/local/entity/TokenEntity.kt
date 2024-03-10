package com.kitahara.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("token_table")
data class TokenEntity(
    @PrimaryKey(false)
    val id: Int = 1,
    val token: String,
    @ColumnInfo("expire_time")
    val expireTime: Date?
)
