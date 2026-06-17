package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.usecase

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.model.Item
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.ItemRepository

class CreateItemUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(name: String, description: String): Result<Item> {
        return repository.createItem(name, description)
    }
}