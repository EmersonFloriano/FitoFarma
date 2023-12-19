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

    @Query("SELECT * FROM Plant WHERE id = :id")
    suspend fun getByID(id: Long): Plant

    @Query("SELECT * FROM Plant WHERE inCart = 1")
    suspend fun findAllFilteredByInCart(): List<Plant>

    @Query("UPDATE Plant SET inCart = 1 WHERE id = :id")
    suspend fun addToCart(id: Long)

}