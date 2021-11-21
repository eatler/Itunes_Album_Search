package com.example.itunesalbumsearch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val trackId: Int,
    val wrapperType: String,
    val collectionName: String,
    val artistName: String,
    val trackName: String,
    val artworkUrl60: String?,
    val artworkUrl100: String?,
    val releaseDate: String,
    val primaryGenreName: String
) : Parcelable