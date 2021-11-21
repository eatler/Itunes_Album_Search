package com.example.itunesalbumsearch.data

import com.example.itunesalbumsearch.api.ItunesApi

class UserRemoteDataSource(
    private val itunesApi: ItunesApi,
) {
    suspend fun getAlbums(queryString: String) = itunesApi.searchAlbums(queryString)
    suspend fun getSongs(id: Int) = itunesApi.searchAlbumSongs(id)
}