package com.example.itunesalbumsearch.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesalbumsearch.api.SongResponse
import com.example.itunesalbumsearch.data.ItunesAlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsActivityViewModel @Inject constructor(
    private val repository: ItunesAlbumRepository
) : ViewModel() {

    val isProgressBarLoading = MutableLiveData(true)

    val isNoInternet = MutableLiveData(false)

    val songs = MutableLiveData<SongResponse>()

    fun getSongs(id: Int) {
        viewModelScope.launch {
            try {
                isProgressBarLoading.value = true
                val songResponse = withContext(Dispatchers.IO) {
                    repository.getSearchAlbumSongsResult(id)
                }
                songs.value = songResponse
                isNoInternet.value = false
                isProgressBarLoading.value = false
            } catch (exception: Throwable) {
                isNoInternet.value = true
                isProgressBarLoading.value = false
            }
        }
    }
}