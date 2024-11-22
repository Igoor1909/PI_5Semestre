package com.igorgomes.pi_5semestre.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.igorgomes.pi_5semestre.R
import com.igorgomes.pi_5semestre.databinding.ActivityCarrinhoBinding

class CarrinhoActivity : AppCompatActivity() {

    lateinit var CarrinhoBiding: ActivityCarrinhoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carrinho)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        CarrinhoBiding = ActivityCarrinhoBinding.inflate(layoutInflater)
        setContentView(CarrinhoBiding.root)


        // codigos de mudanças de telas
        CarrinhoBiding.imgHomeCartAct.setOnClickListener {
            var intent = Intent(it.context, PrincipalActivity::class.java)
            startActivity(intent)
        }
        CarrinhoBiding.imgCartCartAct.setOnClickListener {
            var intent = Intent(it.context, CarrinhoActivity::class.java)
            startActivity(intent)
        }
        CarrinhoBiding.imgPerfilCartAct.setOnClickListener {
            var intent = Intent(it.context, PerfilActivity::class.java)
            startActivity(intent)
        }
        CarrinhoBiding.imgSobreCartAct.setOnClickListener {
            var intent = Intent(it.context, SobreActivity::class.java)
            startActivity(intent)
        }
    }
}