package br.com.emerson.fitofarma.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.emerson.fitofarma.domain.Plant

@Dao
interface PlantDao {
    @Query("SELECT * FROM Plant")
    fun findAll(): List<Plant>

    @Insert
    fun insert(plant: Plant)
}