package br.com.emerson.fitofarma.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.emerson.fitofarma.database.RoomHelper
import br.com.emerson.fitofarma.databinding.PlantFormActivityBinding
import br.com.emerson.fitofarma.domain.Plant

class PlantFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val binding = PlantFormActivityBinding.inflate(layoutInflater)
        binding.addButton.setOnClickListener {
            val name = binding.editTextPlantName.text.toString()
            val description = binding.editTextPlantDescription.text.toString()
            val imageUrl = binding.editTextPlantImageUrl.text.toString()

            val dao = RoomHelper.getInstance(this).plantDao()
            dao.insert(Plant(name = name, description = description, imageUrl = imageUrl))

            finish()
        }

        setContentView(binding.root)
    }

}