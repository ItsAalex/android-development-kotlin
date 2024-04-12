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
        // don't need to connect only by id
        // inflating (taking the layout xml that defines UI and turning into actual View objects)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // takes care of layout show. Which orientation it should be (vertical)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        // how RecycleView to arrange its items
        binding.heroRecyclerView.layoutManager = manager

        // object of interface api
        // create instance retrofit client based on api interface
        // now object api has access everything that interface api has.
        val api: Api = RetrofitClient.getClient().create(Api::class.java)

//         getHeroes is a method that defines an API endpoint to retrieve a list of heroes
//         enqueue is a method for asynchronous network requests. It takes a callback object as an argument (list of heroes)
//         it needs this callback object to handle response from the getHeroes api call.
//         with this, we are telling the app how it should respond to API calls
        api.getHeroes().enqueue(object : Callback<List<Hero>> {
            // this func will be called if response is successful and when response is received
            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {

                if (response.body() == null) {
                    return
                }
                if (!response.isSuccessful) {
                    return
                }
                // create array and insert data from response body in it
                val heroes = ArrayList<Hero>()
                //forcing that response is not null, because it's already inspected in if statements above
                // adding from response in heroes array
                heroes.addAll(response.body()!!)

                // instance of HeroesAdapter
                // adapter is a bridge between the data source and the RecycleView
                val adapter: HeroesAdapter = HeroesAdapter(heroes, onItemClick = { hero ->
                    startHeroActivity(hero)
                })
                // send new created Adapter to recyclerView (it's connecting the adapter to the RecycleView which enables it to display the data from the adapter)
                binding.heroRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    // one hero object send from one activity to another
    fun startHeroActivity(hero: Hero) {
        // create intent for activity class
        // from THIS activity start HEROACTIVITY (with current context)
        val intent = Intent(this, HeroActivity::class.java)
        // add hero object as extra to the intent. Hero is the key for the extra which is used to retrieve Hero object in the HeroActivity
        intent.putExtra("hero", hero)
        startActivity(intent)
    }
}