package com.nasikhunamin.pokebi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nasikhunamin.pokebi.databinding.ItemRowHeroBinding

class ListPokemonAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListPokemonAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ItemRowHeroBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listHero[position]
        with(holder.binding) {
            Glide.with(holder.itemView.context)
                .load(photo)
                .into(imgItemPhoto)
            tvItemName.text = name
            tvItemDescription.text = description


            root.setOnClickListener {
                Toast.makeText(holder.itemView.context, "Kamu memilih $name", Toast.LENGTH_SHORT).show()
                val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
                intentDetail.putExtra("key_hero", listHero[holder.adapterPosition])
                holder.itemView.context.startActivity(intentDetail)
            }
        }
    }

    override fun getItemCount(): Int = listHero.size
}
