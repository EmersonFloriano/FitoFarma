package br.com.emerson.fitofarma.data

import br.com.emerson.fitofarma.domain.entities.Plant
import br.com.emerson.fitofarma.presentation.dtos.PlantDTO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class PlantRepository(private val firestore: FirebaseFirestore) {

    suspend fun getAll(): List<Plant> {
        val plants = mutableListOf<Plant>()

        try {
            val querySnapshot: QuerySnapshot = firestore.collection("plants").get().await()

            for (document in querySnapshot.documents) {
                val name = document.getString("name") ?: ""
                val description = document.getString("description") ?: ""
                val imageUrl = document.getString("imageUrl") ?: ""

                val plant = Plant(document.id, name, description, imageUrl)
                plants.add(plant)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return plants
    }

    suspend fun add(dto: PlantDTO): Boolean {
        return try {
            firestore.collection("plants").add(dto.toJson()).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun getByID(id: String): Plant? {
        return try {
            val document = firestore.collection("plants").document(id).get().await()
            val plantID = document.getString("id") ?: ""
            val name = document.getString("name") ?: ""
            val description = document.getString("description") ?: ""
            val imageUrl = document.getString("imageUrl") ?: ""

            Plant(
                id = plantID,
                name,
                description,
                imageUrl
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}