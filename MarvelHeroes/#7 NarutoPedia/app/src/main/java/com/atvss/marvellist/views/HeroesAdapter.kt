package com.atvss.marvellist.views

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atvss.marvellist.R
import com.atvss.marvellist.data.Hero
import com.bumptech.glide.Glide
// adapter should create and show specific number of elements

// constructor
class HeroesAdapter(
    private val heroes: ArrayList<Hero>,
    // by using "->" compiler knows that its waiting for function (function will active onclick)
    private val onItemClick: (Hero) -> Unit
) : RecyclerView.Adapter<HeroesAdapter.HeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        // connecting item with adapter
        val view = View.inflate(parent.context, R.layout.item_hero, null)
        // send this view to HeroViewHolder
        return HeroViewHolder(view)
    }

    // as many as elements, create viewHolders
    override fun getItemCount(): Int {
        return heroes.size
    }

    // void function that on specific position sets existing holder
    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        // take only 1 hero on specific position
        val hero = heroes[position]
        holder.bind(hero)
    }

    // for specific view based on item, inserts data
    // data will be shown multiple times.
    // Showing our item (1 hero in a list of heroes)
    inner class HeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // create for every view it's instance
        private val ivHero: ImageView = view.findViewById(R.id.heroImage)
        private val tvHeroName: TextView = view.findViewById(R.id.heroName)
        private val tvHeroRealName: TextView = view.findViewById(R.id.heroRealName)
        private val tvCreatedBy: TextView = view.findViewById(R.id.heroCreatedBy)

        // based on data above, fill the data
        fun bind(hero: Hero) {
            Glide.with(ivHero).load(hero.imageUrl).into(ivHero)
            tvHeroName.text = hero.name
            tvHeroRealName.text = hero.realName
            tvCreatedBy.text = hero.createdBy
            // clicking on specific item, will return existing hero
            itemView.setOnClickListener {
                onItemClick(hero)
            }
        }
    }
}