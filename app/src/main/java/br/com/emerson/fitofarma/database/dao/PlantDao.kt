package br.com.emerson.fitofarma.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.emerson.fitofarma.domain.Plant

@Dao
interface PlantDao {
    @Query("SELECT * FROM Plant")
    suspend fun findAll(): List<Plant>

    @Insert
    suspend fun insert(plant: Plant)
}