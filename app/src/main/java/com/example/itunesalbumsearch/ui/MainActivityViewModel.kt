package com.example.itunesalbumsearch.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesalbumsearch.api.AlbumResponse
import com.example.itunesalbumsearch.data.ItunesAlbumRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: ItunesAlbumRepository,
    state: SavedStateHandle
) : ViewModel() {

    val currentQuery = state.getLiveData(LAST_QUERY, DEFAULT_QUERY)
    val isProgressBarLoading = MutableLiveData(false)

    val isNoInternet = MutableLiveData(false)
    val isNoResults = MutableLiveData(false)

    val albums = MutableLiveData<AlbumResponse>()

    fun searchAlbums(query: String) {
        currentQuery.value = query
        viewModelScope.launch {
            try {
                isNoInternet.value = false
                isProgressBarLoading.value = true
                val albumResponse = withContext(Dispatchers.IO) {
                    repository.getSearchAlbumsResult(query)
                }.apply { results = results.sortedBy { it.collectionName } }
                isProgressBarLoading.value = false
                isNoResults.value = albumResponse.results.isEmpty()
                albums.value = albumResponse
            } catch (exception: Throwable) {
                isProgressBarLoading.value = false
                isNoInternet.value = true
                isNoResults.value = false
            }
        }
    }

    companion object {
        private const val DEFAULT_QUERY = "Lift your skinny fists like antennas to heaven"
        private const val LAST_QUERY = "last_query"
    }
}
