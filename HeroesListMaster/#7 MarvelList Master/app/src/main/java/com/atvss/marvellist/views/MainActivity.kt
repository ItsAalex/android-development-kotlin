package com.atvss.marvellist.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atvss.marvellist.api.Api
import com.atvss.marvellist.api.RetrofitClient
import com.atvss.marvellist.data.Hero
import com.atvss.marvellist.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.heroRecyclerView.layoutManager = manager

        val api: Api = RetrofitClient.getClient().create(Api::class.java)

        api.getCharacters().enqueue(object : Callback<List<Hero>> {
            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {
                if (response.body() == null) {
                    return
                }
                if (!response.isSuccessful) {
                    return
                }
                val heroes = ArrayList<Hero>()
                heroes.addAll(response.body()!!)

                val adapter: HeroesAdapter = HeroesAdapter(heroes, onItemClick = { hero ->
                    startHeroActivity(hero)
                })
                binding.heroRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    fun startHeroActivity(hero: Hero) {
        val intent = Intent(this, HeroActivity::class.java)
        intent.putExtra("hero", hero)
        startActivity(intent)
    }
}