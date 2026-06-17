package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.repository

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.core.network.ApiService
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.data.remote.model.ItemDto
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.model.Item
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.ItemRepository
import java.io.IOException

/**
 * Implementação do repositório de itens
 * Responsável por comunicar com a API e mapear DTOs para modelos de domínio
 */
class ItemRepositoryImpl(
    private val apiService: ApiService
) : ItemRepository {

    override suspend fun getItems(): Result<List<Item>> {
        return try {
            val response = apiService.getItems()
            if (response.isSuccessful && response.body() != null) {
                val items = response.body()!!.map { dto ->
                    mapDtoToDomain(dto)
                }
                Result.success(items)
            } else {
                val errorMsg = "Erro ao carregar itens: ${response.code()} - ${response.message()}"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Erro de conexão: Verifique sua internet", e))
        } catch (e: Exception) {
            Result.failure(Exception("Erro inesperado ao carregar itens", e))
        }
    }

    override suspend fun createItem(name: String, description: String): Result<Item> {
        return try {
            // Validação de entrada
            if (name.isBlank() || description.isBlank()) {
                return Result.failure(Exception("Nome e descrição são obrigatórios"))
            }

            val itemDto = ItemDto(
                id = "", // O backend vai gerar o ID
                name = name.trim(),
                description = description.trim(),
                createdAt = ""
            )

            val response = apiService.createItem(itemDto)
            if (response.isSuccessful && response.body() != null) {
                val createdItem = mapDtoToDomain(response.body()!!)
                Result.success(createdItem)
            } else {
                val errorMsg = "Erro ao criar item: ${response.code()} - ${response.message()}"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Erro de conexão: Verifique sua internet", e))
        } catch (e: Exception) {
            Result.failure(Exception("Erro inesperado ao criar item", e))
        }
    }

    override suspend fun updateItem(id: String, name: String, description: String): Result<Item> {
        return try {
            // Validação de entrada
            if (id.isBlank()) {
                return Result.failure(Exception("ID do item é obrigatório"))
            }
            if (name.isBlank() || description.isBlank()) {
                return Result.failure(Exception("Nome e descrição são obrigatórios"))
            }

            val itemDto = ItemDto(
                id = id,
                name = name.trim(),
                description = description.trim(),
                createdAt = "" // Não precisa atualizar
            )

            val response = apiService.updateItem(id, itemDto)
            if (response.isSuccessful && response.body() != null) {
                val updatedItem = mapDtoToDomain(response.body()!!)
                Result.success(updatedItem)
            } else {
                val errorMsg = "Erro ao atualizar item: ${response.code()} - ${response.message()}"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Erro de conexão: Verifique sua internet", e))
        } catch (e: Exception) {
            Result.failure(Exception("Erro inesperado ao atualizar item", e))
        }
    }

    override suspend fun deleteItem(id: String): Result<Unit> {
        return try {
            if (id.isBlank()) {
                return Result.failure(Exception("ID do item é obrigatório"))
            }

            val response = apiService.deleteItem(id)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorMsg = "Erro ao excluir item: ${response.code()} - ${response.message()}"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Erro de conexão: Verifique sua internet", e))
        } catch (e: Exception) {
            Result.failure(Exception("Erro inesperado ao excluir item", e))
        }
    }

    /**
     * Mapeia um ItemDto para um Item (modelo de domínio)
     */
    private fun mapDtoToDomain(dto: ItemDto): Item {
        return Item(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            createdAt = dto.createdAt
        )
    }
}