package com.example.intent

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    private var remainingTimeInMillis = 0L
    private lateinit var timerText: TextView
    private val timerDurations = mapOf(
        R.id.buttonMilk to 2 * 60 * 1000L,   // 2 minutes
        R.id.buttonWaterMotor to 5 * 60 * 1000L, // 5 minutes
        R.id.buttonToast to 3 * 60 * 1000L // 3 minutes
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.timerText)

        findViewById<Button>(R.id.buttonMilk).setOnClickListener {
            startTimer(timerDurations[R.id.buttonMilk]!!)
        }

        findViewById<Button>(R.id.buttonWaterMotor).setOnClickListener {
            startTimer(timerDurations[R.id.buttonWaterMotor]!!)
        }

        findViewById<Button>(R.id.buttonToast).setOnClickListener {
            startTimer(timerDurations[R.id.buttonToast]!!)
        }
    }

    private fun startTimer(duration: Long) {
        timer?.cancel()
        remainingTimeInMillis = duration
        timerText.text = formatTime(remainingTimeInMillis)

        timer = object : CountDownTimer(remainingTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                timerText.text = formatTime(remainingTimeInMillis)
            }

            override fun onFinish() {
                timerText.text = "Done!"
            }
        }.start()
    }

    private fun formatTime(millis: Long): String {
        val minutes = millis / 60000
        val seconds = (millis % 60000) / 1000
        return String.format("%d:%02d", minutes, seconds)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}
