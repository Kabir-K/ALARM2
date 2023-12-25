package com.example.wwww.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.wwww.R

class intro : baseactivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor=ContextCompat.getColor(this@intro,R.color.black)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        var signupbutton: Button =findViewById(R.id.signupbutton)
        signupbutton.setOnClickListener{
            startActivity(Intent(this@intro, newsignupactivity::class.java))
        }
        var signinbutton: Button =findViewById(R.id.signinbutton)
        signinbutton.setOnClickListener{
            startActivity(Intent(this@intro, signin::class.java))
        }
        var eeee:Button=findViewById(R.id.timer)
        eeee.setOnClickListener {
            startActivity(Intent(this@intro,timer::class.java))
        }
    }
}