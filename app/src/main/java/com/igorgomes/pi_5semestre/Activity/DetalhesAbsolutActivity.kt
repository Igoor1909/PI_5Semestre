package com.igorgomes.pi_5semestre.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.igorgomes.pi_5semestre.R


class DetalhesAbsolutActivity : AppCompatActivity() {

    private lateinit var btnAdicionar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_absolut)

    }
}
