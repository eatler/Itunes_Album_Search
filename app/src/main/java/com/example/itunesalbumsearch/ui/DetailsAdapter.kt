package com.example.itunesalbumsearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesalbumsearch.data.Song
import com.example.itunesalbumsearch.databinding.ItemDetailsLayoutBinding
import com.example.itunesalbumsearch.databinding.ItemSongLayoutBinding
import com.squareup.picasso.Picasso

class DetailsAdapter(private val songs: List<Song>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_DETAILS = 1
        const val VIEW_TYPE_SONG = 2
    }

    inner class SongsViewHolder(binding: ItemSongLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.imageViewSong
        val nameSong: TextView = binding.nameSong
        val authorSong: TextView = binding.authorSong

    }

    inner class DetailsViewHolder(binding: ItemDetailsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val detailsImageViewAlbum: ImageView = binding.detailsImageViewAlbum
        val detailsArtistName: TextView = binding.detailsArtistName
        val detailsAlbumName: TextView = binding.detailsAlbumName
        val detailsGenre: TextView = binding.detailsGenre
        val year: TextView = binding.year
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_DETAILS) {
            val binding = ItemDetailsLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            DetailsViewHolder(binding)
        } else {
            val binding = ItemSongLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            SongsViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = songs[position]
        if (currentItem.wrapperType == "collection") {
            val detailsHolder = holder as DetailsViewHolder
            Picasso.get().load(currentItem.artworkUrl100).fit()
                .into(detailsHolder.detailsImageViewAlbum)
            detailsHolder.detailsAlbumName.text = currentItem.collectionName
            detailsHolder.detailsArtistName.text = currentItem.artistName
            detailsHolder.detailsGenre.text = currentItem.primaryGenreName
            detailsHolder.year.text = currentItem.releaseDate.substringBefore('-')
        } else {
            val songHolder = holder as SongsViewHolder
            Picasso.get().load(currentItem.artworkUrl60).fit().into(songHolder.imageView)
            songHolder.authorSong.text = currentItem.artistName
            songHolder.nameSong.text = currentItem.trackName
        }
    }

    override fun getItemCount() = songs.size

    override fun getItemViewType(position: Int): Int {
        return if (songs[position].wrapperType == "collection") {
            VIEW_TYPE_DETAILS
        } else {
            VIEW_TYPE_SONG
        }
    }
}