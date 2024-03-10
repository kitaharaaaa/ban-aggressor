package com.kitahara.log_in.presentation

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitahara.log_in.domain.TokenHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val tokenHandlingUseCase: TokenHandling
) : ViewModel() {
    suspend fun isTokenExpired(): Boolean = tokenHandlingUseCase.isTokenExpired()

    fun saveNewToken(intent: Intent?) {
        viewModelScope.launch(IO) {
            tokenHandlingUseCase.saveNewToken(intent)
        }
    }
}