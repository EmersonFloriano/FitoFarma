package br.com.emerson.fitofarma.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.emerson.fitofarma.databinding.PlantDatailsActivityBinding

class PlantDetailsActivity: AppCompatActivity() {
    private val binding by lazy {
        PlantDatailsActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        binding.backButton.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }
}