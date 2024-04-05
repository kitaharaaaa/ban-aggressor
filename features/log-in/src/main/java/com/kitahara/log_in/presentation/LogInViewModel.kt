package com.kitahara.log_in.presentation

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitahara.log_in.domain.TokenHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val tokenHandlingUseCase: TokenHandling
) : ViewModel() {
    suspend fun isTokenExpired(): Boolean = tokenHandlingUseCase.isTokenExpired()

    fun saveNewToken(intent: Intent?, onComplete: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            try {
                withContext(IO){ tokenHandlingUseCase.saveNewToken(intent) }
                onComplete()
            } catch (e: Exception) {
                onFailure()
            }
        }
    }
}