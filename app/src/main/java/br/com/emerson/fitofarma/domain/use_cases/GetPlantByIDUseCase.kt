package br.com.emerson.fitofarma.domain.use_cases

import br.com.emerson.fitofarma.data.PlantRepository
import br.com.emerson.fitofarma.domain.entities.Plant

class GetPlantByIDUseCase(private val repository: PlantRepository) {
    suspend fun call(id: String): Plant? {
        return repository.getByID(id);
    }
}
