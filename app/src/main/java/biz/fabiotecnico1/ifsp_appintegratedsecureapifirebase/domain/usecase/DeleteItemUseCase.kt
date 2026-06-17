package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.usecase

import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.ItemRepository

class DeleteItemUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(id: String): Result<Unit> {
        return repository.deleteItem(id)
    }
}