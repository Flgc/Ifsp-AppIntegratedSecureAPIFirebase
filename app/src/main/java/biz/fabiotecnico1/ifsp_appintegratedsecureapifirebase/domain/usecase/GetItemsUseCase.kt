package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.usecase

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.model.Item
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.ItemRepository

class GetItemsUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(): Result<List<Item>> {
        return repository.getItems()
    }
}