package com.example.itunesalbumsearch.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {

    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
    }

    @GET("search")
    suspend fun searchAlbums(
        @Query("term") queryString: String,
        @Query("media") media: String = "music",
        @Query("entity") entity: String = "album"
    ): AlbumResponse

    @GET("lookup")
    suspend fun searchAlbumSongs(
        @Query("id") queryString: Int,
        @Query("entity") entity: String = "song"
    ): SongResponse
}