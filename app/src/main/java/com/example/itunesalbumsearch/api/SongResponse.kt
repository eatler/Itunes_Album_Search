package com.example.itunesalbumsearch.api

import com.example.itunesalbumsearch.data.Song

data class SongResponse(
    var results: List<Song>
)