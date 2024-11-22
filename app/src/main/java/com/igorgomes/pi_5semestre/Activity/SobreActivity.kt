package com.igorgomes.pi_5semestre.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.igorgomes.pi_5semestre.R
import com.igorgomes.pi_5semestre.databinding.ActivitySobreBinding

class SobreActivity : AppCompatActivity() {

    lateinit var sobreBinding: ActivitySobreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sobre)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sobreBinding = ActivitySobreBinding.inflate(layoutInflater)
        setContentView(sobreBinding.root)

        // codigos de mudanças de telas
        sobreBinding.imgHomeSobreAct.setOnClickListener {
            var intent = Intent(it.context, PrincipalActivity::class.java)
            startActivity(intent)
        }
        sobreBinding.imgCartSobreAct.setOnClickListener {
            var intent = Intent(it.context, CarrinhoActivity::class.java)
            startActivity(intent)
        }
        sobreBinding.imgPerfilSobreAct.setOnClickListener {
            var intent = Intent(it.context, PerfilActivity::class.java)
            startActivity(intent)
        }
        sobreBinding.imgSobreSobreAct.setOnClickListener {
            var intent = Intent(it.context, SobreActivity::class.java)
            startActivity(intent)
        }
    }
}