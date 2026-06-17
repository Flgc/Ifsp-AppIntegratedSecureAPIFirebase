package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.AuthRepository

class SettingsViewModel(
    private val authRepository: AuthRepository,
    private val context: Context
) : ViewModel() {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    private val _isDarkTheme = MutableLiveData(prefs.getBoolean("dark_theme", false))
    val isDarkTheme: LiveData<Boolean> = _isDarkTheme

    fun toggleTheme() {
        val newValue = !(_isDarkTheme.value ?: false)
        _isDarkTheme.value = newValue
        prefs.edit().putBoolean("dark_theme", newValue).apply()
    }

    fun logout() {
        authRepository.logout()
    }
}