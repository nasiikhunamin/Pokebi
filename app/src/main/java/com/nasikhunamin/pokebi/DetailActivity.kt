package com.nasikhunamin.pokebi

import android.os.Build
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nasikhunamin.pokebi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val KEY_HERO = "key_hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataHero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_HERO, Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Hero>(KEY_HERO)
        }

        dataHero?.let { hero ->
            binding.tvHeroName.text = hero.name
            binding.tvHeroDescription.text = hero.description
            Glide.with(this)
                .load(hero.photo)
                .into(binding.imgHeroPhoto)
            binding.tvItemTinggi.text = hero.tinggi
            binding.tvItemBerat.text = hero.berat
            binding.tvItemKelemahan.text = hero.kelemahan
            binding.tvItemKategori.text = hero.kategori
            binding.tvItemTipe.text = hero.tipe
            binding.tvItemKemampuan.text = hero.kemampuan
        } ?: run {
            Toast.makeText(this, "Data pokemon tidak ditemukan", Toast.LENGTH_SHORT).show()
        }

        binding.actionShare.setOnClickListener {
            shareHero(dataHero)
        }
    }

    private fun shareHero(hero: Hero?) {
        hero?.let {
            val message = "Hero: ${it.name}\nDescription: ${it.description}"
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, message)
            }
            startActivity(Intent.createChooser(sendIntent, null))
        }
    }
}
