package com.igorgomes.pi_5semestre.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.igorgomes.pi_5semestre.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({

            val tela_inicial = Intent(this, TelaInicialActivity::class.java)

            startActivity(tela_inicial)

            finish()

        }, 2500)
    }
}
