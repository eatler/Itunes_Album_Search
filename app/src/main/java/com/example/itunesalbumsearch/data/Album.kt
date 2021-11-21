package com.example.itunesalbumsearch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val artistId: Int,
    val collectionId: Int,
    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String?,
    val artworkUrl100: String?,
    val releaseDate: String,
    val primaryGenreName: String
) : Parcelable