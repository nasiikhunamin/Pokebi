package com.nasikhunamin.pokebi

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListDetailAdapter(private val detailPokemon: ArrayList<Hero>) : RecyclerView.Adapter<ListDetailAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tinggi: TextView = itemView.findViewById(R.id.tv_item_tinggi)
        val berat: TextView = itemView.findViewById(R.id.tv_item_berat)
        val kelemahan: TextView = itemView.findViewById(R.id.tv_item_kelemahan)
        val kategori: TextView = itemView.findViewById(R.id.tv_item_kategori)
        val tipe: TextView = itemView.findViewById(R.id.tv_item_tipe)
        val kemampuan: TextView = itemView.findViewById(R.id.tv_item_kemampuan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_detail, parent, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (tinggi, berat, kelemahan, kategori, tipe, kemampuan) = detailPokemon[position]
        holder.tinggi.text = tinggi
        holder.berat.text = berat
        holder.kelemahan.text = kelemahan
        holder.kategori.text = kategori
        holder.tipe.text = tipe
        holder.kemampuan.text = kemampuan


        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_hero", detailPokemon[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = detailPokemon.size

}