package br.com.emerson.fitofarma.presentation.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.emerson.fitofarma.presentation.adapters.PlantAdapter
import br.com.emerson.fitofarma.data.database.FirestoreHelper
import br.com.emerson.fitofarma.databinding.CatalogActivityBinding
import br.com.emerson.fitofarma.domain.use_cases.GetAllPlantsUseCase
import br.com.emerson.fitofarma.data.PlantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatalogActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)

    private val binding by lazy {
        CatalogActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        val helper = FirestoreHelper.getInstance()
        val repository = PlantRepository(helper)
        val getAllPlants = GetAllPlantsUseCase(repository)

        scope.launch {
            val plants = withContext(Dispatchers.IO) {
                getAllPlants.call()
            }

            val listView = binding.plantsListView
            val adapter = PlantAdapter(this@CatalogActivity, plants)
            listView.adapter = adapter
            adapter.updatePlantListVisibility(binding)
        }

        val addPlantButton = binding.addPlantButton
        addPlantButton.setOnClickListener {
            val intent = Intent(this, PlantFormActivity::class.java)
            startActivity(intent)
        }
    }
}