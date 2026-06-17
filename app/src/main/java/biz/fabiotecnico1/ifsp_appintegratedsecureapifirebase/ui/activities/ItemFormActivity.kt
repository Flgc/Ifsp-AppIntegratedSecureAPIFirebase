package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.databinding.ActivityItemFormBinding
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels.ItemViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemFormBinding
    private val viewModel: ItemViewModel by viewModel()
    private var itemId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtém o ID do item se estiver editando
        itemId = intent.getStringExtra("item_id")

        setupObservers()
        setupListeners()

        // Se for edição, carrega os dados do item
        if (itemId != null) {
            viewModel.loadItemForEdit(itemId!!)
        } else {
            binding.tvFormTitle.text = "Novo Item"
        }
    }

    private fun setupObservers() {
        // Observa o item atual (para edição)
        viewModel.currentItem.observe(this) { item ->
            item?.let {
                binding.etItemName.setText(it.name)
                binding.etItemDescription.setText(it.description)
                binding.tvFormTitle.text = "Editar Item"
            }
        }

        // Observa loading
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnSave.isEnabled = !isLoading
        }

        // Observa erros
        viewModel.error.observe(this) { error ->
            error?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                viewModel.clearMessages()
            }
        }

        // Observa sucesso na operação
        viewModel.operationSuccess.observe(this) { success ->
            if (success) {
                viewModel.resetOperationSuccess()
                finish() // Volta para a lista
            }
        }
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            val name = binding.etItemName.text.toString().trim()
            val description = binding.etItemDescription.text.toString().trim()

            if (name.isEmpty() || description.isEmpty()) {
                Snackbar.make(binding.root, "Preencha todos os campos", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Salva (cria ou atualiza)
            viewModel.saveItem(itemId, name, description)
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}