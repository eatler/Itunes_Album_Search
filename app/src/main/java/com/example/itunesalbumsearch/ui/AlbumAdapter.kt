package com.example.itunesalbumsearch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesalbumsearch.data.Album
import com.example.itunesalbumsearch.databinding.ItemAlbumLayoutBinding
import com.squareup.picasso.Picasso

class AlbumAdapter(private val albums: List<Album>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<AlbumAdapter.AlbumsViewHolder>() {

    inner class AlbumsViewHolder(binding: ItemAlbumLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        val imageView: ImageView = binding.imageViewAlbum
        val nameAlbum: TextView = binding.nameAlbum
        val authorAlbum: TextView = binding.authorAlbum

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val binding = ItemAlbumLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return AlbumsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val currentItem = albums[position]

        Picasso.get().load(currentItem.artworkUrl60).fit().into(holder.imageView)
        holder.authorAlbum.text = currentItem.artistName
        holder.nameAlbum.text = currentItem.collectionName
    }

    override fun getItemCount() = albums.size


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}