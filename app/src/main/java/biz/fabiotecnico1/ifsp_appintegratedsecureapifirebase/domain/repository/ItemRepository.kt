package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.model.Item

/**
 * Interface do repositório de itens
 * Define os contratos para operações CRUD de itens
 */
interface ItemRepository {

    /**
     * Obtém todos os itens da API
     * @return Result com lista de itens ou erro
     */
    suspend fun getItems(): Result<List<Item>>

    /**
     * Cria um novo item
     * @param name Nome do item
     * @param description Descrição do item
     * @return Result com o item criado ou erro
     */
    suspend fun createItem(name: String, description: String): Result<Item>

    /**
     * Atualiza um item existente
     * @param id ID do item a ser atualizado
     * @param name Novo nome do item
     * @param description Nova descrição do item
     * @return Result com o item atualizado ou erro
     */
    suspend fun updateItem(id: String, name: String, description: String): Result<Item>

    /**
     * Exclui um item
     * @param id ID do item a ser excluído
     * @return Result com Unit em caso de sucesso ou erro
     */
    suspend fun deleteItem(id: String): Result<Unit>
}