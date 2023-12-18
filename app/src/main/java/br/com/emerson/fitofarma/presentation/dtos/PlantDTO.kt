package br.com.emerson.fitofarma.presentation.dtos

data class PlantDTO(
    val name: String,
    val description: String,
    val imageUrl: String,
) {
    fun toJson(): Map<String, Any> {
        return hashMapOf(
            "name" to name,
            "description" to description,
            "imageUrl" to imageUrl
        )
    }
}