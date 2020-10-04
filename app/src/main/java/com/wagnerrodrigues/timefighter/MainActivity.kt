package com.wagnerrodrigues.timefighter

import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextview: TextView

    internal var score = 0

    internal var gameStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 6000
    internal val coutDownInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton = findViewById(R.id.tapMeButton)
        gameScoreTextView = findViewById(R.id.gameScoreTextView)
        timeLeftTextview = findViewById(R.id.timeLeftTextView)

        tapMeButton.setOnClickListener { view ->
            incrementScore()
        }
        //gameScoreTextView.text = getString(R.string.yourScore, score)//Seta o score inicial como 0
        resetGame()
    }

    private fun incrementScore() {
        if(!gameStarted){
            startGame()
        }

        score += 1
        val newScore = getString(R.string.yourScore, score)
        gameScoreTextView.text = newScore
    }

    private fun startGame(){
        countDownTimer.start()
        gameStarted = true
    }

    private fun resetGame() {
        score = 0

        gameScoreTextView.text = getString(R.string.yourScore, score)
        val initialTimeLeft = initialCountDown/1000
        timeLeftTextview.text = getString(R.string.timeLeft,initialTimeLeft)

        countDownTimer = object : CountDownTimer(initialCountDown, coutDownInterval){
            override fun onTick(millisUntilFinished: Long){
                val timeLeft = millisUntilFinished/1000
                timeLeftTextview.text = getString(R.string.timeLeft, timeLeft)
            }

            override fun onFinish() {
                endgame()
            }
        }

        gameStarted = false
    }

    private fun endgame(){
        Toast.makeText(this,getString(R.string.gameOverMessage, score), Toast.LENGTH_LONG).show()
        resetGame()
    }
}