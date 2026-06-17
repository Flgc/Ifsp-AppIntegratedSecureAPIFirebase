package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.usecase

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.remote.model.AuthResponse
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.AuthRepository

/**
 * Caso de uso para login do usuário
 * Encapsula a lógica de autenticação
 */
class LoginUseCase(
    private val authRepository: AuthRepository
) {

    /**
     * Executa o login do usuário
     * @param email Email do usuário
     * @param password Senha do usuário
     * @return Result com AuthResponse ou erro
     */
    suspend operator fun invoke(email: String, password: String): Result<AuthResponse> {
        // Validação de entrada
        if (email.isBlank()) {
            return Result.failure(Exception("Email é obrigatório"))
        }

        if (password.isBlank()) {
            return Result.failure(Exception("Senha é obrigatória"))
        }

        // Validação básica de formato de email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.failure(Exception("Email inválido"))
        }

        return authRepository.login(email, password)
    }
}