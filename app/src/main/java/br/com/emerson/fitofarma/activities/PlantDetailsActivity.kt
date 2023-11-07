package br.com.emerson.fitofarma.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.emerson.fitofarma.R
import br.com.emerson.fitofarma.database.RoomHelper
import br.com.emerson.fitofarma.databinding.PlantDatailsActivityBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlantDetailsActivity: AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val binding by lazy {
        PlantDatailsActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        scope.launch {
            val dao = RoomHelper.getInstance(this@PlantDetailsActivity).plantDao()
            val plant = dao.getByID(intent.getLongExtra("id", 0L))

            runOnUiThread {
                Glide.with(this@PlantDetailsActivity)
                    .load(plant.imageUrl)
                    .error(R.drawable.image_not_supported)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.plantImage)
                binding.plantName.text = plant.name
                binding.plantDescription.text = plant.description
            }
        }
    }
}