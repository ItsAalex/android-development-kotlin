package com.atvss.marvellist.views

import android.os.Build
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.atvss.marvellist.R
import com.atvss.marvellist.data.Hero
import com.atvss.marvellist.databinding.ActivityHeroBinding
import com.bumptech.glide.Glide

class HeroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val hero: Hero? = if(VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("hero", Hero::class.java)
        } else {
            intent.getSerializableExtra("hero") as Hero
        }

        // if hero is not null do this..
        hero?.let { hero ->
            binding.apply {
                Glide.with(this@HeroActivity).load(hero.images).into(imageView)
                titleView.text = hero.name
                titleView.text = hero.personal
            }
        }


    }
}