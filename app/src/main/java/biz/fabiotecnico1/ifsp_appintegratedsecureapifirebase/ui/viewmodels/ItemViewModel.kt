package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.model.Item
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.usecase.*
import kotlinx.coroutines.launch

class ItemViewModel(
    private val getItemsUseCase: GetItemsUseCase,
    private val createItemUseCase: CreateItemUseCase,
    private val updateItemUseCase: UpdateItemUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {

    // --- LiveData para a lista de itens ---
    private val _items = MutableLiveData<List<Item>>(emptyList())
    val items: LiveData<List<Item>> = _items

    // --- LiveData para loading ---
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // --- LiveData para erros ---
    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    // --- LiveData para operações bem-sucedidas ---
    private val _operationSuccess = MutableLiveData(false)
    val operationSuccess: LiveData<Boolean> = _operationSuccess

    // --- LiveData para o item atual (para edição) ---
    private val _currentItem = MutableLiveData<Item?>(null)
    val currentItem: LiveData<Item?> = _currentItem

    // --- LiveData para mensagens de sucesso ---
    private val _successMessage = MutableLiveData<String?>(null)
    val successMessage: LiveData<String?> = _successMessage

    // --- LiveData para controle de diálogo de confirmação ---
    private val _showDeleteDialog = MutableLiveData<Item?>(null)
    val showDeleteDialog: LiveData<Item?> = _showDeleteDialog

    /**
     * Carrega a lista de itens da API
     */
    fun loadItems() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = getItemsUseCase()
            result.onSuccess { items ->
                _items.value = items
                _isLoading.value = false
            }.onFailure { error ->
                _error.value = error.message ?: "Erro ao carregar itens"
                _isLoading.value = false
            }
        }
    }

    /**
     * Cria um novo item
     */
    fun createItem(name: String, description: String) {
        if (name.isEmpty() || description.isEmpty()) {
            _error.value = "Preencha todos os campos"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = createItemUseCase(name, description)
            result.onSuccess { item ->
                _operationSuccess.value = true
                _successMessage.value = "Item criado com sucesso!"
                loadItems() // Recarrega a lista
                _isLoading.value = false
            }.onFailure { error ->
                _error.value = error.message ?: "Erro ao criar item"
                _isLoading.value = false
            }
        }
    }

    /**
     * Atualiza um item existente
     */
    fun updateItem(id: String, name: String, description: String) {
        if (name.isEmpty() || description.isEmpty()) {
            _error.value = "Preencha todos os campos"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = updateItemUseCase(id, name, description)
            result.onSuccess { item ->
                _operationSuccess.value = true
                _successMessage.value = "Item atualizado com sucesso!"
                loadItems() // Recarrega a lista
                _isLoading.value = false
            }.onFailure { error ->
                _error.value = error.message ?: "Erro ao atualizar item"
                _isLoading.value = false
            }
        }
    }

    /**
     * Exclui um item (chamado após confirmação)
     */
    fun deleteItem(item: Item) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = deleteItemUseCase(item.id)
            result.onSuccess {
                _operationSuccess.value = true
                _successMessage.value = "Item excluído com sucesso!"
                loadItems() // Recarrega a lista
                _isLoading.value = false
                _showDeleteDialog.value = null // Fecha o diálogo
            }.onFailure { error ->
                _error.value = error.message ?: "Erro ao excluir item"
                _isLoading.value = false
            }
        }
    }

    /**
     * Abre o diálogo de confirmação de exclusão
     */
    fun confirmDelete(item: Item) {
        _showDeleteDialog.value = item
    }

    /**
     * Cancela a exclusão (fecha o diálogo)
     */
    fun cancelDelete() {
        _showDeleteDialog.value = null
    }

    /**
     * Carrega um item específico para edição
     */
    fun loadItemForEdit(itemId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = getItemsUseCase()
            result.onSuccess { items ->
                val item = items.find { it.id == itemId }
                _currentItem.value = item
                _isLoading.value = false
            }.onFailure { error ->
                _error.value = error.message ?: "Erro ao carregar item"
                _isLoading.value = false
            }
        }
    }

    /**
     * Limpa as mensagens de erro e sucesso
     */
    fun clearMessages() {
        _error.value = null
        _successMessage.value = null
    }

    /**
     * Reseta o estado de operação bem-sucedida
     */
    fun resetOperationSuccess() {
        _operationSuccess.value = false
    }

    /**
     * Salva um item (cria ou atualiza dependendo se tem ID)
     */
    fun saveItem(id: String?, name: String, description: String) {
        if (id.isNullOrEmpty()) {
            createItem(name, description)
        } else {
            updateItem(id, name, description)
        }
    }

    /**
     * Verifica se o formulário está no modo de edição
     */
    fun isEditing(): Boolean {
        return _currentItem.value != null
    }

    /**
     * Obtém o título apropriado para a tela de formulário
     */
    fun getFormTitle(): String {
        return if (isEditing()) {
            "Editar Item"
        } else {
            "Novo Item"
        }
    }
}