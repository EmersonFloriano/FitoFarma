package br.com.emerson.fitofarma.domain.use_cases

import br.com.emerson.fitofarma.domain.entities.Plant
import br.com.emerson.fitofarma.data.PlantRepository

class GetAllPlantsUseCase(private val repository: PlantRepository) {
    suspend fun call(): List<Plant> {
        return repository.getAll()
    }
}