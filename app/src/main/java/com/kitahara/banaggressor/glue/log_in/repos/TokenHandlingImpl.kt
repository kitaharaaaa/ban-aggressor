package com.kitahara.banaggressor.glue.log_in.repos

import android.content.Intent
import android.util.Log
import com.kitahara.data.local.dao.TokenDao
import com.kitahara.data.local.entity.TokenEntity
import com.kitahara.data.remote.token.TokenExtraction
import com.kitahara.log_in.domain.TokenHandling
import dagger.hilt.android.scopes.ViewModelScoped
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@ViewModelScoped
class TokenHandlingImpl @Inject constructor(
    private val tokenDao: TokenDao,
    private val tokenExtraction: TokenExtraction
) : TokenHandling {

    private val tag = "TokenHandlingImpl"

    override suspend fun isTokenExpired(): Boolean {
        val expireTime = tokenDao.getExpireDate() ?: return true

        return System.currentTimeMillis() >= expireTime.time
    }

    override suspend fun saveNewToken(intent: Intent?) {
        val tokenData = tokenExtraction.getTokenData(intent)

        if (tokenData != null) {
            val calendar = Calendar.getInstance().apply {
                add(Calendar.SECOND, tokenData.expiresIn)
            }
            val updatedDate: Date? = calendar.time
            Log.e(tag, "token = ${tokenData.token}\n expiresIn = ${tokenData.expiresIn}")
            tokenDao.upsert(TokenEntity(token = tokenData.token, expireTime = updatedDate))
        } else {
            Log.e(tag, "Token is null")
        }
    }
}