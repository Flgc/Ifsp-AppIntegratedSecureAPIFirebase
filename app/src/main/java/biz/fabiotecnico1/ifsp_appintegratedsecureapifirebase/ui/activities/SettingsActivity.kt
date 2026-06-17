package biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.databinding.ActivitySettingsBinding
import biz.fabiotecnico1.ifsp_appintegratedsecureapifirebase.ui.viewmodels.SettingsViewModel
import com.google.android.material.switchmaterial.SwitchMaterial
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        // Configura o switch de tema
        val switchTheme = findViewById<SwitchMaterial>(com.example.secureapp.R.id.switchTheme)
        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleTheme()
        }

        // Botão de logout
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            navigateToLogin()
        }
    }

    private fun setupObservers() {
        viewModel.isDarkTheme.observe(this) { isDark ->
            val switchTheme = findViewById<SwitchMaterial>(com.example.secureapp.R.id.switchTheme)
            switchTheme.isChecked = isDark
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }
}