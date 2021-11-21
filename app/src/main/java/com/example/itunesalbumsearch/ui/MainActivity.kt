package com.example.itunesalbumsearch.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.example.itunesalbumsearch.R
import com.example.itunesalbumsearch.databinding.ActivityMainBinding
import com.example.itunesalbumsearch.ui.detail.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AlbumAdapter.OnItemClickListener {

    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewMain.adapter = AlbumAdapter(listOf(), this)

        viewModel.albums.observe(this) {
            binding.recyclerViewMain.adapter = AlbumAdapter(it.results, this)
        }

        viewModel.isProgressBarLoading.observe(this) {
            binding.progressBarMain.isVisible = it
            binding.recyclerViewMain.isVisible = !it
        }

        viewModel.isNoInternet.observe(this) {
            binding.textViewError.isVisible = it
            binding.recyclerViewMain.isVisible = !it
        }

        viewModel.isNoResults.observe(this) {
            binding.textViewNoResults.isVisible = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_itunes_album, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    viewModel.searchAlbums(newText)
                }
                return true
            }
        })

        if (!viewModel.currentQuery.value.isNullOrEmpty()) {
            searchItem.expandActionView()
            searchView.setQuery(viewModel.currentQuery.value, false)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("album", viewModel.albums.value?.results?.get(position))
        startActivity(intent)
    }
}