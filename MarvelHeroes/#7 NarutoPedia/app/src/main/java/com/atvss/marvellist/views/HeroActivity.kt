package com.atvss.marvellist.views

import android.os.Build
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.atvss.marvellist.R
import com.atvss.marvellist.data.Hero
import com.atvss.marvellist.databinding.ActivityHeroBinding
import com.bumptech.glide.Glide

// displaying details about selected hero
class HeroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // handle supports for different android versions
        // serialize as hero class
        // nullability check for Hero
        // Importing data that were sent from mainActivity
        val hero: Hero? = if(VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("hero", Hero::class.java)
        } else {
            intent.getSerializableExtra("hero") as Hero
        }

        // if hero is not null the function will execute
        // show data on screen
        hero?.let { hero ->
            binding.apply {
                Glide.with(this@HeroActivity).load(hero.imageUrl).into(imageView)
                titleView.text = hero.name
                nameView.text = hero.realName
                createdView.text = hero.createdBy
                bioView.text = hero.bio
            }
        }


    }
}