package br.com.emerson.fitofarma.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.emerson.fitofarma.R
import br.com.emerson.fitofarma.data.PlantRepository
import br.com.emerson.fitofarma.data.database.FirestoreHelper
import br.com.emerson.fitofarma.databinding.PlantDatailsActivityBinding
import br.com.emerson.fitofarma.domain.use_cases.GetPlantByIDUseCase
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlantDetailsActivity: AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)

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

    override fun onResume() {
        super.onResume()

        val helper = FirestoreHelper.getInstance()
        val repository = PlantRepository(helper)
        val getPlantByID = GetPlantByIDUseCase(repository)

        scope.launch {
            val plant = withContext(Dispatchers.IO) {
                intent.getStringExtra("id")?.let { getPlantByID.call(it) }
            }

            Glide.with(this@PlantDetailsActivity)
                .load(plant?.imageUrl)
                .error(R.drawable.image_not_supported)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.plantImage)
            binding.plantName.text = plant?.name
            binding.plantDescription.text = plant?.description
        }
    }
}