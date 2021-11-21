package com.example.itunesalbumsearch.api

import com.example.itunesalbumsearch.data.Album

data class AlbumResponse(
    var results: List<Album>
)