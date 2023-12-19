package br.com.emerson.fitofarma.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
class Plant(
    @PrimaryKey(autoGenerate = true) var id : Long = 0L,
    var name: String,
    var description: String,
    var imageUrl: String,
    var inCart: Int = 0,
) : Parcelable