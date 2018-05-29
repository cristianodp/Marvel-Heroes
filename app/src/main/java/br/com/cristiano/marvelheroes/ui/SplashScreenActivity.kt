package br.com.cristiano.marvelheroes.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cristiano.marvelheroes.R
import java.util.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val i = Intent(this@SplashScreenActivity, CharactersActivity::class.java)
                startActivity(i)
                this.cancel()
                this@SplashScreenActivity.finish()
            }
        }, 1500, 1)

    }
}
