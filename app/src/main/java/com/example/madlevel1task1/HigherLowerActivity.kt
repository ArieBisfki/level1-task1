package com.example.madlevel1task1

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madlevel1task1.databinding.ActivityHigherLowerBinding

class HigherLowerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHigherLowerBinding
    private var currentThrow: Int = 1
    private var lastThrow: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHigherLowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.btnHigher.setOnClickListener { onHigherClick() }
        binding.btnEquals.setOnClickListener { onEqualClick() }
        binding.btnLower.setOnClickListener { onLowerClick() }
        updateUI()
    }

    private fun updateUI() {
        binding.textLastThrow.text = getString(R.string.text_last_throw, lastThrow)

        var imageDrawableId = 1
        when (currentThrow) {
            1 -> imageDrawableId = R.drawable.dice1
            2 -> imageDrawableId = R.drawable.dice2
            3 -> imageDrawableId = R.drawable.dice3
            4 -> imageDrawableId = R.drawable.dice4
            5 -> imageDrawableId = R.drawable.dice5
            6 -> imageDrawableId = R.drawable.dice6
        }
        binding.imageCenter.setImageResource(imageDrawableId)
    }

    /**
     * Replaces the previous dice value with the current one and replaces the current dice with a new dice
     * with a random number between 1 and 6 (inclusive).
     */
    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onHigherClick() {
        rollDice()
        if (currentThrow > lastThrow) onAnswerCorrect()
        else onAnswerIncorrect()
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onLowerClick() {
        rollDice()
        if (currentThrow < lastThrow) onAnswerCorrect()
        else onAnswerIncorrect()
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onEqualClick() {
        rollDice()
        if (currentThrow == lastThrow) onAnswerCorrect()
        else onAnswerIncorrect()
    }

    private fun onAnswerCorrect() {
        Toast.makeText(this, R.string.text_correct, Toast.LENGTH_LONG).show()
    }

    /**
     * Displays an incorrect Toast message.
     */
    private fun onAnswerIncorrect() {
        Toast.makeText(this, R.string.text_incorrect, Toast.LENGTH_LONG).show()
    }
}