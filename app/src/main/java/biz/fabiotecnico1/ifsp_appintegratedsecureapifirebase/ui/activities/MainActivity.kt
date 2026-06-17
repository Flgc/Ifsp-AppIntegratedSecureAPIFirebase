package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.R
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.databinding.ActivityMainBinding
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.domain.repository.AuthRepository
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val authRepository: AuthRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!authRepository.isLoggedIn()) {
            navigateToLogin()
            return
        }

        setupUI()
    }

    private fun setupUI() {
        binding.tvWelcome.text = "Bem-vindo, ${authRepository.getUserName() ?: "Usuário"}"

        binding.cardItems.setOnClickListener {
            startActivity(Intent(this, ItemListActivity::class.java))
        }

        binding.cardProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.cardSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            authRepository.logout()
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}