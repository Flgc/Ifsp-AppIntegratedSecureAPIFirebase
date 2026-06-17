package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    private val _loginSuccess = MutableLiveData(false)
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _error.value = "Preencha todos os campos"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = loginUseCase(email, password)
            result.onSuccess {
                _loginSuccess.value = true
                _isLoading.value = false
            }.onFailure {
                _error.value = it.message ?: "Erro ao fazer login"
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}