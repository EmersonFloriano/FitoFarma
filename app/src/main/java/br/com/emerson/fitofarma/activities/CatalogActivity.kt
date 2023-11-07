package br.com.emerson.fitofarma.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.emerson.fitofarma.adapters.PlantAdapter
import br.com.emerson.fitofarma.database.RoomHelper
import br.com.emerson.fitofarma.databinding.CatalogActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatalogActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.IO)

    private val binding by lazy {
        CatalogActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        scope.launch {
            val dao = RoomHelper.getInstance(this@CatalogActivity).plantDao()
            val plants = dao.findAll()

            runOnUiThread {
                val listView = binding.plantsListView
                val adapter = PlantAdapter(this@CatalogActivity, plants)
                listView.adapter = adapter
                adapter.updatePlantListVisibility(binding)
            }
        }

        val addPlantButton = binding.addPlantButton
        addPlantButton.setOnClickListener {
            val intent = Intent(this, PlantFormActivity::class.java)
            startActivity(intent)
        }
    }
}