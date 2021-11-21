package com.example.itunesalbumsearch.data

import com.example.itunesalbumsearch.api.ItunesApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItunesAlbumRepository @Inject constructor(private val itunesApi: ItunesApi) {

    suspend fun getSearchAlbumsResult(query: String) =
        UserRemoteDataSource(itunesApi).getAlbums(query)

    suspend fun getSearchAlbumSongsResult(id: Int) =
        UserRemoteDataSource(itunesApi).getSongs(id)
}