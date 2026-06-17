package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.databinding.ActivityProfileBinding
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.userName.observe(this) { name ->
            binding.tvUserName.text = name
        }

        viewModel.userEmail.observe(this) { email ->
            binding.tvUserEmail.text = email
        }
    }
}