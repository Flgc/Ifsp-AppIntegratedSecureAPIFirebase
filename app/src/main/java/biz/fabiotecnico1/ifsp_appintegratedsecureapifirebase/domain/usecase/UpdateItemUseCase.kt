package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.usecase

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.model.Item
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.ItemRepository

class UpdateItemUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(id: String, name: String, description: String): Result<Item> {
        return repository.updateItem(id, name, description)
    }
}