package com.igorgomes.pi_5semestre.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.igorgomes.pi_5semestre.R
import com.igorgomes.pi_5semestre.Responses.ProdutoResponse

class DetalhesBuchanasActivity : AppCompatActivity() {

    private lateinit var btnAdicionar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_buchanas)

    }
}