package com.nasikhunamin.pokebi

import android.os.Build
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object {
        const val `key-hero` = "key_hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvName: TextView = findViewById(R.id.tv_hero_name)
        val tvDescription: TextView = findViewById(R.id.tv_hero_description)
        val imgPhoto: ImageView = findViewById(R.id.img_hero_photo)
        val tvTinggi: TextView = findViewById(R.id.tv_item_tinggi)
        val tvBerat: TextView = findViewById(R.id.tv_item_berat)
        val tvKelemahan: TextView = findViewById(R.id.tv_item_kelemahan)
        val tvKategori: TextView = findViewById(R.id.tv_item_kategori)
        val tvTipe: TextView = findViewById(R.id.tv_item_tipe)
        val tvKemampuan: TextView = findViewById(R.id.tv_item_kemampuan)


        val dataHero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(`key-hero`, Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Hero>(`key-hero`)
        }

        dataHero?.let {
            tvName.text = it.name
            tvDescription.text = it.description
            Glide.with(this)
                .load(it.photo)
                .into(imgPhoto)
            tvTinggi.text = it.tinggi
            tvBerat.text = it.berat
            tvKelemahan.text = it.kelemahan
            tvKategori.text = it.kategori
            tvTipe.text = it.tipe
            tvKemampuan.text = it.kemampuan
        }
        val btnShare: Button = findViewById(R.id.action_share)
        btnShare.setOnClickListener {
            shareHero(dataHero)
        }
    }

    private fun shareHero(hero: Hero?) {
        hero?.let {
            val message = it.name
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, message)
            }
            startActivity(Intent.createChooser(sendIntent, null))
        }
    }
}
