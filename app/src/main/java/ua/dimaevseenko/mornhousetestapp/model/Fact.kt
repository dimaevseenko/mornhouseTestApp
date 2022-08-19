package ua.dimaevseenko.mornhousetestapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fact(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val factId: String,
    val fact: String
): Parcelable {

    constructor(factId: String, fact: String): this(null, factId, fact)

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(factId)
        parcel.writeString(fact)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Fact> {
        override fun createFromParcel(parcel: Parcel): Fact {
            return Fact(parcel)
        }

        override fun newArray(size: Int): Array<Fact?> {
            return arrayOfNulls(size)
        }
    }
}