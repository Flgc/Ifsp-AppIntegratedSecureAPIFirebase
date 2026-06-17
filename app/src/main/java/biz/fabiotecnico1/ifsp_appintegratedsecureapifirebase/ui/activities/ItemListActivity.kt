package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.databinding.ActivityItemListBinding
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.model.Item
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.adapters.ItemAdapter
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels.ItemViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemListBinding
    private val viewModel: ItemViewModel by viewModel()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        setupListeners()

        viewModel.loadItems()
    }

    private fun setupRecyclerView() {
        adapter = ItemAdapter(
            items = emptyList(),
            onItemClick = { item ->
                // Abre o formulário para edição
                val intent = Intent(this, ItemFormActivity::class.java)
                intent.putExtra("item_id", item.id)
                startActivity(intent)
            },
            onDeleteClick = { item ->
                viewModel.confirmDelete(item)
            }
        )

        binding.recyclerViewItems.apply {
            layoutManager = LinearLayoutManager(this@ItemListActivity)
            adapter = this@ItemListActivity.adapter
        }
    }

    private fun setupObservers() {
        // Observa a lista de itens
        viewModel.items.observe(this) { items ->
            adapter.updateItems(items)
            binding.tvEmpty.visibility = if (items.isEmpty()) View.VISIBLE else View.GONE
        }

        // Observa o loading
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observa erros
        viewModel.error.observe(this) { error ->
            error?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                viewModel.clearMessages()
            }
        }

        // Observa mensagens de sucesso
        viewModel.successMessage.observe(this) { message ->
            message?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
                viewModel.clearMessages()
            }
        }

        // Observa o diálogo de confirmação de exclusão
        viewModel.showDeleteDialog.observe(this) { item ->
            item?.let {
                showDeleteConfirmationDialog(it)
            }
        }
    }

    private fun setupListeners() {
        binding.fabAddItem.setOnClickListener {
            startActivity(Intent(this, ItemFormActivity::class.java))
        }
    }

    private fun showDeleteConfirmationDialog(item: Item) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar exclusão")
            .setMessage("Deseja realmente excluir o item '${item.name}'?")
            .setPositiveButton("Excluir") { _, _ ->
                viewModel.deleteItem(item)
            }
            .setNegativeButton("Cancelar") { _, _ ->
                viewModel.cancelDelete()
            }
            .show()
    }
}