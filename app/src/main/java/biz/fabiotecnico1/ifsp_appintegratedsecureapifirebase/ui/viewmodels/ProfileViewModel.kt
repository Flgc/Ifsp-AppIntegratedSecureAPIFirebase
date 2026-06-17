package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.AuthRepository

class ProfileViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> = _userEmail

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        _userName.value = authRepository.getUserName() ?: "Usuário"
        // O email não está sendo armazenado no TokenManager, mas poderia ser
        _userEmail.value = "usuario@email.com" // Placeholder
    }
}