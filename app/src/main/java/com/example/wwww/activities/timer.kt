package com.example.wwww.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.wwww.R

class timer : AppCompatActivity() {
    private lateinit var timerTextView: TextView
    private lateinit var cancleButton: Button
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        timerTextView=findViewById(R.id.timertext)
        cancleButton=findViewById(R.id.timercanclebutton)
        startTimer()
        cancleButton.setOnClickListener {
            canclealert()
        }
    }

    private fun startTimer() {
        val totalTimeInMillis: Long = 45000
        val intervalInMillis: Long = 1000
        countDownTimer = object : CountDownTimer(totalTimeInMillis, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                // Update the UI with the remaining time
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                timerTextView.text = String.format("%02d:%02d", minutes, remainingSeconds)
            }

            override fun onFinish() {
                timerTextView.text = "ALERT SENT"
            }
        }
        countDownTimer.start()
    }
    private fun canclealert() {
        // Cancel the existing timer
        countDownTimer?.cancel()
        Toast.makeText(this@timer,"ALERT CANCLED",Toast.LENGTH_LONG).show()
    }

}
