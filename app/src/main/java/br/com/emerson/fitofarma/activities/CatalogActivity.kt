package br.com.emerson.fitofarma.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.emerson.fitofarma.adapters.PlantAdapter
import br.com.emerson.fitofarma.databinding.CatalogActivityBinding
import br.com.emerson.fitofarma.domain.Plant

class CatalogActivity : AppCompatActivity() {
    private val binding by lazy {
        CatalogActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()

        val list = listOf(
            Plant("Planta 1", "lorem Ipsum lorem Ipsum"),
            Plant("Planta 2", "lorem Ipsum lorem Ipsum"),
            Plant("Planta 3", "lorem Ipsum lorem Ipsum"),
            Plant("Planta 4", "lorem Ipsum lorem Ipsum"),
            Plant("Planta 1", "lorem Ipsum lorem Ipsum"),
            Plant("Planta 2", "lorem Ipsum lorem Ipsum"),
            Plant("Planta 3", "lorem Ipsum lorem Ipsum"),
            Plant("Planta 4", "lorem Ipsum lorem Ipsum")
        )

        val addPlantButton = binding.addPlantButton
        addPlantButton.setOnClickListener {
            val intent = Intent(this, PlantFormActivity::class.java)
            startActivity(intent)
        }

        val listView = binding.plantsListView
        val adapter = PlantAdapter(this, list)

        listView.adapter = adapter
    }
}