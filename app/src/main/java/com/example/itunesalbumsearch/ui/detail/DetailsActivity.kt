package com.example.itunesalbumsearch.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.itunesalbumsearch.data.Album
import com.example.itunesalbumsearch.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private val viewModel by viewModels<DetailsActivityViewModel>()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val album = intent.getParcelableExtra<Album>("album")

        binding.detailsRecyclerView.isVisible = false
        binding.detailsRecyclerView.adapter = DetailsAdapter(listOf())

        viewModel.songs.observe(this) {
            binding.detailsRecyclerView.adapter = DetailsAdapter(it.results)
        }

        viewModel.isNoInternet.observe(this) {
            binding.textViewError.isVisible = it
            binding.detailsRecyclerView.isVisible = !it
        }

        viewModel.isProgressBarLoading.observe(this) {
            binding.progressBarDetails.isVisible = it
        }

        if (album != null) {
            viewModel.getSongs(album.collectionId)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}