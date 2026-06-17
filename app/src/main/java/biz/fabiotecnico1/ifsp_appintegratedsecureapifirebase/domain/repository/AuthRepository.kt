package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.remote.model.AuthResponse

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<AuthResponse>
    suspend fun logout()
    fun isLoggedIn(): Boolean
    fun getUserName(): String?
}