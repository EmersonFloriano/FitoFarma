package br.com.emerson.fitofarma.domain.use_cases

import br.com.emerson.fitofarma.data.PlantRepository
import br.com.emerson.fitofarma.presentation.dtos.PlantDTO

class AddPlantUseCase(private val repository: PlantRepository) {
    suspend fun call(dto: PlantDTO): Boolean {
        return repository.add(dto)
    }
}