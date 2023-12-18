package br.com.emerson.fitofarma.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.emerson.fitofarma.data.database.FirestoreHelper
import br.com.emerson.fitofarma.databinding.PlantFormActivityBinding
import br.com.emerson.fitofarma.domain.use_cases.AddPlantUseCase
import br.com.emerson.fitofarma.data.PlantRepository
import br.com.emerson.fitofarma.presentation.dtos.PlantDTO
import br.com.emerson.fitofarma.presentation.utils.EditTextValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PlantFormActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)

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

                val helper = FirestoreHelper.getInstance()
                val repository = PlantRepository(helper)
                val addPlant = AddPlantUseCase(repository)

                scope.launch {
                    val result = withContext(Dispatchers.IO) {
                        addPlant.call(
                            PlantDTO(
                                name = name,
                                description = description,
                                imageUrl = imageUrl
                            )
                        )
                    }

                    if (result) {
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