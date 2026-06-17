package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.repository

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.network.ApiService
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.security.TokenManager
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.remote.model.AuthRequest
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: ApiService,
    private val tokenManager: TokenManager
) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<AuthResponse> {
        return try {
            val response = api.login(AuthRequest(email, password))
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                tokenManager.saveToken(body.token)
                tokenManager.saveUserInfo(body.userId, body.name)
                Result.success(body)
            } else {
                Result.failure(Exception("Falha no login: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        tokenManager.clearToken()
    }

    override fun isLoggedIn(): Boolean = tokenManager.isLoggedIn()
    override fun getUserName(): String? = tokenManager.getUserName()
}