package com.example.moviewatchlist.data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class MoviesData(
    var movieTitle: String? = null,
    var movieImage: String? = null,
    var movieWatch: Boolean? = null,
    var movieRating: Int? = null,
    var movieTrailer: String? = null,
    var movieDescription: String?= null,
    var movieReleasedDate: String? = null,
    var movieDuration: String? = null,
    var movieGenre: String? = null
):Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(movieTitle)
        parcel.writeString(movieImage)
        parcel.writeValue(movieWatch)
        parcel.writeValue(movieRating)
        parcel.writeString(movieTrailer)
        parcel.writeString(movieDescription)
        parcel.writeString(movieReleasedDate)
        parcel.writeString(movieDuration)
        parcel.writeString(movieGenre)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoviesData> {
        override fun createFromParcel(parcel: Parcel): MoviesData {
            return MoviesData(parcel)
        }

        override fun newArray(size: Int): Array<MoviesData?> {
            return arrayOfNulls(size)
        }
    }
}