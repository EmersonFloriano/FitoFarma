package br.com.emerson.fitofarma.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.emerson.fitofarma.database.dao.PlantDao
import br.com.emerson.fitofarma.domain.Plant

@Database(
    entities = [
        Plant::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RoomHelper : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    companion object {
        fun getInstance(context: Context): RoomHelper {
            return Room.databaseBuilder(context, RoomHelper::class.java, "fitofarma.db")
                .build()
        }
    }

}