package com.atvss.dicee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.atvss.dicee.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {
    //xml connecting with its kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //connect context with xml that we need.
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //root layout is the parent of where all of this layouts are.
        setContentView(binding.root)

        val diceArray = intArrayOf(
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6,
        )

        binding.apply {
            btnRollPlayer1.setOnClickListener{
                Log.e("MainActivityTAG", "roll button clicked")
                val randomNumberGenerator = Random()
                var index = randomNumberGenerator.nextInt(6)
                dice1Player1.setImageResource(diceArray[index])

                index = randomNumberGenerator.nextInt(6)
                dice2Player1.setImageResource(diceArray[index])
            }

            btnRollPlayer2.setOnClickListener{
                Log.e("MainActivityTAG", "roll button clicked")
                val randomNumberGenerator = Random()
                var index = randomNumberGenerator.nextInt(6)
                dice1Player2.setImageResource(diceArray[index])

                index = randomNumberGenerator.nextInt(6)
                dice2Player2.setImageResource(diceArray[index])
            }
        }
    }

    // start the app
    override fun onStart() {
        super.onStart()
    }

    //when the user can see the app
    override fun onResume() {
        super.onResume()
    }

    //when we press home button for example
    override fun onPause() {
        super.onPause()
    }

    //when we delete the app from processes
    override fun onStop() {
        super.onStop()
    }

    //destroyed app process and free used memory
    override fun onDestroy() {
        super.onDestroy()
    }
}