package br.com.emerson.fitofarma.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import br.com.emerson.fitofarma.R

class CustomSplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        val statusBarColor = R.color.background1
//        print("-------------- $statusBarColor")
//       window.statusBarColor = Color.parseColor(statusBarColor)


        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, CatalogActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000
        )
    }
}