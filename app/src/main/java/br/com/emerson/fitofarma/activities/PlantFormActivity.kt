package br.com.emerson.fitofarma.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.emerson.fitofarma.database.RoomHelper
import br.com.emerson.fitofarma.databinding.PlantFormActivityBinding
import br.com.emerson.fitofarma.domain.Plant
import br.com.emerson.fitofarma.utils.EditTextValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlantFormActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = PlantFormActivityBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.addButton.setOnClickListener {
            if (shouldRegisterPlant(binding)) {
                val name = binding.editTextPlantName.text.toString()
                val description = binding.editTextPlantDescription.text.toString()
                val imageUrl = binding.editTextPlantImageUrl.text.toString()

                scope.launch {
                    val dao = RoomHelper.getInstance(this@PlantFormActivity).plantDao()
                    dao.insert(Plant(name = name, description = description,
                        imageUrl = imageUrl, inCart = 0))

                    runOnUiThread {
                        finish()
                    }
                }
            }

        }

        setContentView(binding.root)
    }


    private fun shouldRegisterPlant(binding: PlantFormActivityBinding): Boolean {
        if (!EditTextValidator.isValid(binding.editTextPlantName)) {
            return false
        }
        if (!EditTextValidator.isValid(binding.editTextPlantDescription)) {
            return false
        }
        if (!EditTextValidator.isValid(binding.editTextPlantImageUrl)) {
            return false
        }

        return true
    }

}